/*
* generated by Xtext
*/
package net.zehrer.no2.ui.labeling;

import net.zehrer.no2.texlik.Model;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.xtext.parsetree.CompositeNode;
import org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider;

import com.google.inject.Inject;

/**
 * Provides labels for a EObjects.
 * 
 * see http://www.eclipse.org/Xtext/documentation/latest/xtext.html#labelProvider
 */
public class TexlikLabelProvider extends DefaultEObjectLabelProvider {

	@Inject
	public TexlikLabelProvider(AdapterFactoryLabelProvider delegate) {
		super(delegate);
	}

/*
	//Labels and icons can be computed like this:
	
	String text(MyModel ele) {
	  return "my "+ele.getName();
	}
*/	 
    String image(Model ele) {
    	CompositeNode node = null;
    	
      return "MyModel.gif";
    }

}