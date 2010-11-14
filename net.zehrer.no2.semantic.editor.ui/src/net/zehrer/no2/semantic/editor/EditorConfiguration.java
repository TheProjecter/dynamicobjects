package net.zehrer.no2.semantic.editor;

import net.zehrer.no2.semantic.editor.coloring.ColorManager;
import net.zehrer.no2.semantic.editor.text.SemanticTextPartition;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class EditorConfiguration extends SourceViewerConfiguration {
	
	private XMLDoubleClickStrategy doubleClickStrategy;
//	private ColorManager colorManager;

	public EditorConfiguration(ColorManager colorManager) {
//		this.colorManager = colorManager;
	}
	
	@Override
	public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
		
		// TODO: check out if there is access to SemanticTextPartition.getLegalContentTypes
		
		return new String[] {
			IDocument.DEFAULT_CONTENT_TYPE,
			SemanticTextPartition.TEXT};
	}
	
	@Override
	public ITextDoubleClickStrategy getDoubleClickStrategy( ISourceViewer sourceViewer, String contentType) {
		if (doubleClickStrategy == null)
			doubleClickStrategy = new XMLDoubleClickStrategy();
		return doubleClickStrategy;
	}

	
//	protected XMLScanner getXMLScanner() {
//		if (scanner == null) {
//			scanner = new XMLScanner(colorManager);
//			scanner.setDefaultReturnToken(
//				new Token(
//					new TextAttribute(
//						colorManager.getColor(IXMLColorConstants.DEFAULT))));
//		}
//		return scanner;
//	}
//	protected XMLTagScanner getXMLTagScanner() {
//		if (tagScanner == null) {
//			tagScanner = new XMLTagScanner(colorManager);
//			tagScanner.setDefaultReturnToken(
//				new Token(
//					new TextAttribute(
//						colorManager.getColor(IXMLColorConstants.TAG))));
//		}
//		return tagScanner;
//	}

	@Override
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
		
		PresentationReconciler reconciler = new PresentationReconciler();

//		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getXMLTagScanner());
//		reconciler.setDamager(dr, SemanticTextPartition.TAG);
//		reconciler.setRepairer(dr, SemanticTextPartition.TAG);

//		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getXMLScanner());
//		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
//		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);

//		NonRuleBasedDamagerRepairer ndr = new NonRuleBasedDamagerRepairer(
//				new TextAttribute(
//					colorManager.getColor(IXMLColorConstants.XML_COMMENT)));
//		reconciler.setDamager(ndr, SemanticTextPartition.COMMENT);
//		reconciler.setRepairer(ndr, SemanticTextPartition.COMMENT);

		return reconciler;
	}

}