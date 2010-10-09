package net.zehrer.emf.adapter

import net.zehrer.emf.edit.provider.ChangeNotifier;
import net.zehrer.emf.edit.provider.ComposeableAdapter
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.notify.Notification;

class AbstractModelAdapterFactory extends AdapterFactoryImpl with ChangeNotifier with ComposeableAdapter{

   override def fireNotifyChanged (notification : Notification) = {
     
     //changeNotifier.fireNotifyChanged (notification)  TODO: learn scala :)
     
     if (parentAdapterFactory != null) 
      this.parentAdapterFactory.fireNotifyChanged(notification);
   }
  
}
