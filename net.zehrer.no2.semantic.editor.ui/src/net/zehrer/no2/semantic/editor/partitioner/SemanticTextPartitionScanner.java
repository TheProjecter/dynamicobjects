package net.zehrer.no2.semantic.editor.partitioner;

import java.util.ArrayList;
import java.util.List;


import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;

public class SemanticTextPartitionScanner extends RuleBasedPartitionScanner {
	
	public final static String COMMENT = "_comment";
	public final static String TAG = "_tag";
	public final static String WORD = "_word";
	
	public final static String[] PARTITION_TYPES = new String[] { COMMENT, TAG, WORD };
	
	public SemanticTextPartitionScanner() {

		IToken comment = new Token(COMMENT);
		IToken tag = new Token(TAG);
		IToken word = new Token(WORD);
		
		List<IPredicateRule> rules= new ArrayList<IPredicateRule>();

		rules.add( new MultiLineRule("<!--", "-->", comment));
		rules.add( new TagRule(tag));
		rules.add( new WordPredicateRule(word));

		
		IPredicateRule[] result= new IPredicateRule[rules.size()];
		rules.toArray(result);
		setPredicateRules(result);
	}
	

	// ---------------------------
	
	static class WordPredicateRule extends WordRule implements IPredicateRule {

		private IToken fSuccessToken;

		public WordPredicateRule(IToken successToken) {
			super(new EmptyCommentDetector());
			this.fSuccessToken = successToken;
			addWord("/**/", fSuccessToken); //$NON-NLS-1$
		}

		/*
		 * @see org.eclipse.jface.text.rules.IPredicateRule#evaluate(ICharacterScanner, boolean)
		 */
		public IToken evaluate(ICharacterScanner scanner, boolean resume) {
			return super.evaluate(scanner);
		}

		/*
		 * @see org.eclipse.jface.text.rules.IPredicateRule#getSuccessToken()
		 */
		public IToken getSuccessToken() {
			return fSuccessToken;
		}
	}
	
	/**
	 * Detector for empty comments.
	 */
	static class EmptyCommentDetector implements IWordDetector {

		/* (non-Javadoc)
		* Method declared on IWordDetector
	 	*/
		public boolean isWordStart(char c) {
			return (c == '/');
		}

		/* (non-Javadoc)
		* Method declared on IWordDetector
	 	*/
		public boolean isWordPart(char c) {
			return (c == '*' || c == '/');
		}
	}




}
