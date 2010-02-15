package net.zehrer.emf.edit.provider

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.common.notify.Notification;

trait ComposeableAdapter extends ComposeableAdapterFactory {
  
  var parentAdapterFactory : ComposedAdapterFactory = null;
 
  def getRootAdapterFactory() : ComposeableAdapterFactory = {
    if (parentAdapterFactory == null) this 
      else parentAdapterFactory.getRootAdapterFactory;
  }
      
  def setParentAdapterFactory (parentAdapter: ComposedAdapterFactory ) = {
    parentAdapterFactory = parentAdapter
  }
  
  def fireNotifyChanged (notification : Notification) = {
    if (parentAdapterFactory != null) 
      this.parentAdapterFactory.fireNotifyChanged(notification);
  }
  
}
