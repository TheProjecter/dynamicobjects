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
import net.zehrer.no2.semantic.editor.model.LeafNode;
import net.zehrer.no2.semantic.editor.model.impl.TextModelUtil;
import net.zehrer.no2.text.IResourceDocument;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
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
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextUtilities;
import org.eclipse.jface.text.TypedPosition;
import org.eclipse.jface.text.TypedRegion;


public class SemanticTextPartition implements IDocumentPartitioner, IDocumentPartitionerExtension {
	
//IDocumentPartitionerExtension, IDocumentPartitionerExtension2, IDocumentPartitionerExtension3
	
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

		return IDocument.DEFAULT_CONTENT_TYPE;
	}
	

	/*
	 * @see IDocumentPartitioner#computePartitioning(int, int)
	 */
	@Override
	public ITypedRegion[] computePartitioning(int offset, int length) {

		List<TypedRegion> regionList = new ArrayList<TypedRegion>();
		int endOffset = offset + length;
		
		EList<LeafNode> leafNodeList  = TextModelUtil.getLeafNodes (this.fRootNode,offset, endOffset-1);

		for (LeafNode leafNode : leafNodeList) {
			regionList.add( TextModelUtil.createTypedRegion(leafNode, TEXT));
		}
		
		TypedRegion[] result= new TypedRegion[regionList.size()];
		regionList.toArray(result);
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
	public Region documentChanged2(DocumentEvent e) {

		if (fDocument == null)
			return null;

		Assert.isTrue(e.getDocument() == fDocument);
		
		this.fRootNode.update(e);					
		fPositionUpdater.update(e);
		clearPositionCache();

		// TODO: will changed with word generation
		return new Region(e.getOffset(), e.getLength());
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
			fCachedPositions = fDocument.getPositions(fPositionCategory);
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
	
	

	
	
}
