/*******************************************************************************
 * Copyright (c) 2010 Stephan Zehrer and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Stephan Zehrer - initial API and implementation based on the code of FastPartitioner
 *******************************************************************************/

package net.zehrer.no2.semantic.editor.text;

import java.util.ArrayList;
import java.util.List;

import net.zehrer.no2.semantic.editor.adapter.NodeContentAdapter;
import net.zehrer.no2.semantic.editor.model.CompositeNode;
import net.zehrer.no2.text.IResourceDocument;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.DefaultPositionUpdater;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.IDocumentPartitionerExtension;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.TypedPosition;
import org.eclipse.jface.text.TypedRegion;


public class SemanticTextPartition implements IDocumentPartitioner, IDocumentPartitionerExtension

//IDocumentPartitionerExtension, IDocumentPartitionerExtension2, IDocumentPartitionerExtension3

{
	
	public final static String TEXT = "__text";
	
	// ----
	
	/** The position category this partitioner uses to store the document's partitioning information. */
	private static final String CONTENT_TYPES_CATEGORY= "__content_types_category"; //$NON-NLS-1$

	/**The position category this partitioner uses to store the document's partitioning information. */
	private final String fPositionCategory;
	
	/** The legal content types of this partitioner */
	public final  String[] fLegalContentTypes = new String[] { TEXT };
	
	/** The partitioner's document */
	protected IResourceDocument fDocument;

	/** The model root */
	protected CompositeNode fRootNode;
	
	/** The document length before a document change occurred */
	protected int fPreviousDocumentLength;
	
	/** The position updater used to for the default updating of partitions */
	protected final DefaultPositionUpdater fPositionUpdater;
	
	/** The offset at which the first changed partition starts */
	protected int fStartOffset;
	
	/** The offset at which the last changed partition ends */
	
	protected int fEndOffset;
	
	/**The offset at which a partition has been deleted */
	protected int fDeleteOffset;
	
	/** Debug option for cache consistency checking. */
	private static final boolean CHECK_CACHE_CONSISTENCY = false;
	
	private PartitionUtil fPartitionUtil = null;
	
	/** The active document rewrite session.*/
//	private DocumentRewriteSession fActiveRewriteSession;
	
	/**
	 * The cached positions from our document, so we don't create a new array every time
	 * someone requests partition information.
	 */
	private Position[] fCachedPositions= null;
	
	
	public SemanticTextPartition() {

		fPositionCategory = CONTENT_TYPES_CATEGORY + hashCode();
		fPositionUpdater = new DefaultPositionUpdater(fPositionCategory);
		fPartitionUtil = new PartitionUtil(fPositionCategory, TEXT);
	}
	
	/**
	 * @see IDocumentPartitioner#connect (IDocument)
	 * @param document instance of ISemanticDocument
	 */
	@Override
	public final void connect (IDocument document) {
				
		Assert.isNotNull(document);
		Assert.isTrue(!document.containsPositionCategory(fPositionCategory));
		document.addPositionCategory(fPositionCategory);
		
		Assert.isTrue(document instanceof IResourceDocument);
		this.fDocument = (IResourceDocument) document;	
		this.fRootNode = (CompositeNode) fDocument.getTextModel();
		
		initialize();
	}

	/*
	 * @see IDocumentPartitioner#disconnect()
	 */
	@Override
	public void disconnect() {
		Assert.isTrue(fDocument.containsPositionCategory(fPositionCategory));

		try {
			fDocument.removePositionCategory(fPositionCategory);
			fDocument = null;
		} catch (BadPositionCategoryException x) {
			// can not happen because of Assert
		}
	}
	
	/*
	 * @see IDocumentPartitioner#documentAboutToBeChanged(DocumentEvent)
	 */
	@Override
	public void documentAboutToBeChanged(DocumentEvent event) {
		
		Assert.isTrue(event.getDocument() == fDocument);

		fPreviousDocumentLength= event.getDocument().getLength();
		fStartOffset= -1;
		fEndOffset= -1;
		fDeleteOffset= -1;
	}

	/*
	 * @see IDocumentPartitioner#documentChanged(DocumentEvent)
	 */
	@Override
	public boolean documentChanged(DocumentEvent event) {
		if (fDocument != null) {
			IRegion region= documentChanged2(event);
			return (region != null);
		}
		return false;

	}

	/*
	 * @see IDocumentPartitioner#getLegalContentTypes()
	 */
	@Override
	public String[] getLegalContentTypes() {
		return TextUtilities.copy(fLegalContentTypes);
	}
   
	/*
	 * @see IDocumentPartitioner#getContentType(int)
	 */
	@Override
	public String getContentType(int offset) {
		
		TypedPosition p = findClosestPosition(offset);
		if (p != null && p.includes(offset))
			return p.getType();

		return IDocument.DEFAULT_CONTENT_TYPE;
	}
	

	/*
	 * @see IDocumentPartitioner#computePartitioning(int, int)
	 */
	@Override
	public ITypedRegion[] computePartitioning(int offset, int length) {

		List<TypedRegion> list= new ArrayList<TypedRegion>();

		try {

			int endOffset= offset + length;

			Position[] category= getPositions();

			TypedPosition previous= null, current= null;
			int start, end, gapOffset;
			Position gap= new Position(0);

			int startIndex= getFirstIndexEndingAfterOffset(category, offset);
			int endIndex= getFirstIndexStartingAfterOffset(category, endOffset);
			for (int i= startIndex; i < endIndex; i++) {

				current= (TypedPosition) category[i];

				gapOffset= (previous != null) ? previous.getOffset() + previous.getLength() : 0;
				gap.setOffset(gapOffset);
				gap.setLength(current.getOffset() - gapOffset);
				if ((gap.getLength() > 0 && gap.overlapsWith(offset, length))) {
					start= Math.max(offset, gapOffset);
					end= Math.min(endOffset, gap.getOffset() + gap.getLength());
					list.add(new TypedRegion(start, end - start, IDocument.DEFAULT_CONTENT_TYPE));
				}

				if (current.overlapsWith(offset, length)) {
					start= Math.max(offset, current.getOffset());
					end= Math.min(endOffset, current.getOffset() + current.getLength());
					list.add(new TypedRegion(start, end - start, current.getType()));
				}

				previous= current;
			}

			if (previous != null) {
				gapOffset= previous.getOffset() + previous.getLength();
				gap.setOffset(gapOffset);
				gap.setLength(fDocument.getLength() - gapOffset);
				if ((gap.getLength() > 0 && gap.overlapsWith(offset, length))) {
					start= Math.max(offset, gapOffset);
					end= Math.min(endOffset, fDocument.getLength());
					list.add(new TypedRegion(start, end - start, IDocument.DEFAULT_CONTENT_TYPE));
				}
			}

			if (list.isEmpty())
				list.add(new TypedRegion(offset, length, IDocument.DEFAULT_CONTENT_TYPE));

		} catch (BadPositionCategoryException ex) {
			// Make sure we clear the cache
			clearPositionCache();
		} catch (RuntimeException ex) {
			// Make sure we clear the cache
			clearPositionCache();
			throw ex;
		}

		TypedRegion[] result= new TypedRegion[list.size()];
		list.toArray(result);
		return result;
	}

	/*
	 * @see IDocumentPartitioner#getPartition(int)
	 */
	@Override
	public ITypedRegion getPartition(int offset) {

		try {

			Position[] category = getPositions();

			if (category == null || category.length == 0)
				return new TypedRegion(0, fDocument.getLength(), IDocument.DEFAULT_CONTENT_TYPE);

			int index= fDocument.computeIndexInCategory(fPositionCategory, offset);

			if (index < category.length) {

				TypedPosition next= (TypedPosition) category[index];

				if (offset == next.offset)
					return new TypedRegion(next.getOffset(), next.getLength(), next.getType());

				if (index == 0)
					return new TypedRegion(0, next.offset, IDocument.DEFAULT_CONTENT_TYPE);

				TypedPosition previous= (TypedPosition) category[index - 1];
				if (previous.includes(offset))
					return new TypedRegion(previous.getOffset(), previous.getLength(), previous.getType());

				int endOffset= previous.getOffset() + previous.getLength();
				return new TypedRegion(endOffset, next.getOffset() - endOffset, IDocument.DEFAULT_CONTENT_TYPE);
			}

			TypedPosition previous= (TypedPosition) category[category.length - 1];
			if (previous.includes(offset))
				return new TypedRegion(previous.getOffset(), previous.getLength(), previous.getType());

			int endOffset= previous.getOffset() + previous.getLength();
			return new TypedRegion(endOffset, fDocument.getLength() - endOffset, IDocument.DEFAULT_CONTENT_TYPE);

		} catch (BadPositionCategoryException x) {
		} catch (BadLocationException x) {
		}

		return new TypedRegion(0, fDocument.getLength(), IDocument.DEFAULT_CONTENT_TYPE);
	}

	
	// ------ IDocumentPartitionerExtension
	
	
	/*
	 * IDocumentPartitionerExtension#documentChanged2(DocumentEvent)
	 */
	@Override
	public IRegion documentChanged2(DocumentEvent e) {

		if (fDocument == null)
			return null;

		try {
			Assert.isTrue(e.getDocument() == fDocument);
			
			this.fRootNode.update(e);
						
			fPositionUpdater.update(e);
			
			clearPositionCache();

//			fPartitionUtil.partitionDocment(fDocument);
			
//			ITypedRegion partition = fDocument.getPartition(e.getOffset());


			
//			IRegion line = fDocument.getLineInformationOfOffset(e.getOffset());
//			int reparseStart= line.getOffset();
//			int partitionStart= -1;
//			int newLength= e.getText() == null ? 0 : e.getText().length();



//
//		} catch (BadPositionCategoryException x) {
//			// should never happen on connected documents
//		} catch (BadLocationException x) {
		} finally {
			clearPositionCache();
		}

		return null; //createRegion();

	}
	
	
	// ----- IDocumentPartitionerExtension2
	
	/*
	 * @IDocumentPartitionerExtension2#getManagingPositionCategories()
	 */
	public String[] getManagingPositionCategories() {
		return new String[] { fPositionCategory };
	}



	// ----- protected methods
	
	protected void initialize() {

		clearPositionCache();
		
		NodeContentAdapter.createAdapterAndAddToNode(this.fRootNode);
				
		try {
			fPartitionUtil.partitionDocment(fDocument);

		} catch (BadLocationException x) {
			// cannot happen as offsets come from model
		} catch (BadPositionCategoryException x) {
			// TODO: understand 
			// cannot happen if document has been connected before 
		}
	}

	
	// ----- protected methods
	
	/**
	 * Clears the position cache. Needs to be called whenever the positions have
	 * been updated.
	 */
	protected final void clearPositionCache() {
		if (fCachedPositions != null) {
			fCachedPositions= null;
		}
	}
	
	/**
	 * Returns the partitioners positions.
	 *
	 * @return the partitioners positions
	 * @throws BadPositionCategoryException if getting the positions from the
	 *         document fails
	 */
	protected final Position[] getPositions() throws BadPositionCategoryException {
		if (fCachedPositions == null) {
			fCachedPositions= fDocument.getPositions(fPositionCategory);
		} else if (CHECK_CACHE_CONSISTENCY) {
			Position[] positions= fDocument.getPositions(fPositionCategory);
			int len= Math.min(positions.length, fCachedPositions.length);
			for (int i= 0; i < len; i++) {
				if (!positions[i].equals(fCachedPositions[i]))
					System.err.println("FastPartitioner.getPositions(): cached position is not up to date: from document: " + toString(positions[i]) + " in cache: " + toString(fCachedPositions[i])); //$NON-NLS-1$ //$NON-NLS-2$
			}
			for (int i= len; i < positions.length; i++)
				System.err.println("FastPartitioner.getPositions(): new position in document: " + toString(positions[i])); //$NON-NLS-1$
			for (int i= len; i < fCachedPositions.length; i++)
				System.err.println("FastPartitioner.getPositions(): stale position in cache: " + toString(fCachedPositions[i])); //$NON-NLS-1$
		}
		return fCachedPositions;
	}
	
	
	/**
	 * Returns the position in the partitoner's position category which is
	 * close to the given offset. This is, the position has either an offset which
	 * is the same as the given offset or an offset which is smaller than the given
	 * offset. This method profits from the knowledge that a partitioning is
	 * a ordered set of disjoint position.
	 * <p>
	 * May be extended or replaced by subclasses.
	 * </p>
	 * @param offset the offset for which to search the closest position
	 * @return the closest position in the partitioner's category
	 */
	protected TypedPosition findClosestPosition(int offset) {

		try {

			int index= fDocument.computeIndexInCategory(fPositionCategory, offset);
			Position[] category= getPositions();

			if (category.length == 0)
				return null;

			if (index < category.length) {
				if (offset == category[index].offset)
					return (TypedPosition) category[index];
			}

			if (index > 0)
				index--;

			return (TypedPosition) category[index];

		} catch (BadPositionCategoryException x) {
		} catch (BadLocationException x) {
		}

		return null;
	}
	
	
	// ----- private helper methods
	

	/**
	 * Returns the index of the first position which ends after the given offset.
	 *
	 * @param positions the positions in linear order
	 * @param offset the offset
	 * @return the index of the first position which ends after the offset
	 */
	private int getFirstIndexEndingAfterOffset(Position[] positions, int offset) {
		int i= -1, j= positions.length;
		while (j - i > 1) {
			int k= (i + j) >> 1;
			Position p= positions[k];
			if (p.getOffset() + p.getLength() > offset)
				j= k;
			else
				i= k;
		}
		return j;
	}

	/**
	 * Returns the index of the first position which starts at or after the given offset.
	 *
	 * @param positions the positions in linear order
	 * @param offset the offset
	 * @return the index of the first position which starts after the offset
	 */
	private int getFirstIndexStartingAfterOffset(Position[] positions, int offset) {
		int i= -1, j= positions.length;
		while (j - i > 1) {
			int k= (i + j) >> 1;
			Position p= positions[k];
			if (p.getOffset() >= offset)
				j= k;
			else
				i= k;
		}
		return j;
	}
	
	/**
	 * Pretty print a <code>Position</code>.
	 *
	 * @param position the position to format
	 * @return a formatted string
	 */
	private String toString(Position position) {
		return "P[" + position.getOffset() + "+" + position.getLength() + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	
}
