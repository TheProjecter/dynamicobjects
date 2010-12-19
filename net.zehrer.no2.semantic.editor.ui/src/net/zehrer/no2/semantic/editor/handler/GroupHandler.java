package net.zehrer.no2.semantic.editor.handler;

import net.zehrer.common.interval.impl.EIntIntervalImpl;
import net.zehrer.no2.semantic.editor.SemanticEditor;
import net.zehrer.no2.semantic.editor.model.CompositeNode;
import net.zehrer.no2.semantic.editor.model.LeafNode;
import net.zehrer.no2.semantic.editor.model.java.TextModelUtil;
import net.zehrer.no2.text.IResourceDocument;
import net.zehrer.no2.ui.edit.EObjectItemProvider;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class GroupHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public GroupHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		 
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		IEditorPart editorPart = HandlerUtil.getActiveEditor(event);
		if (selection instanceof ITextSelection) {
			 ITextSelection textSelection = (ITextSelection)selection;

			 if (textSelection.getLength() > 0 ) {
			   performChange(textSelection, editorPart);
			}
		 }
		
		 return null;
	}

	private void performChange(ITextSelection sel, IEditorPart editorPart) {
		
		if (editorPart instanceof SemanticEditor) {
			SemanticEditor editor = (SemanticEditor) editorPart;
			
			IDocument document = editor.getDocumentProvider().getDocument(editor.getEditorInput());
			if (document instanceof IResourceDocument) {
				IResourceDocument resourceDocument = (IResourceDocument)document;
				CompositeNode model = (CompositeNode) resourceDocument.getTextModel();
				
				int eOffset = sel.getOffset() + sel.getLength() -1;
				
				model.groupNodes(new EIntIntervalImpl(sel.getOffset(),eOffset));
				
			}
		}
		
	}
}
