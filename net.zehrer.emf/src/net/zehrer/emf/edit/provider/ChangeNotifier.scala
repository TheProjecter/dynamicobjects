/**
 * <copyright> 
 *
 * Copyright (c) 2002-20010 Stephan Zehrer and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation in java
 * 	 Stephan Zehrer - portet as trait to scala
 *
 * </copyright>
 */

package net.zehrer.emf.edit.provider

import scala.collection.jcl.ArrayList;

import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.common.notify.Notification;

trait ChangeNotifier extends IChangeNotifier{
  
  var listnerList = new ArrayList[INotifyChangedListener]
  
  /**
   * This calls {@link org.eclipse.emf.edit.provider.INotifyChangedListener#notifyChanged notifyChanged} for each listener.
   */
  def fireNotifyChanged(notification: Notification) {
    
    // what is about modCount? 
    var listeners = listnerList.toArray
    listeners.foreach ((listener:INotifyChangedListener)  =>
      if (listnerList.contains(listener))
          listener notifyChanged notification
    )
  }  
  
  def addListener (notifyChangedListener: INotifyChangedListener) {
    listnerList.add (notifyChangedListener);
  }
    
  def removeListener (notifyChangedListener: INotifyChangedListener)  {
    listnerList.remove (notifyChangedListener);
  }

}
