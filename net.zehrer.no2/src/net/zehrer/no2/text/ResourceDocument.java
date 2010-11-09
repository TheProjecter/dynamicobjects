/*******************************************************************************
 * Copyright (c) 2010 Stephan Zehrer and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Stephan Zehrer - initial API and implementation
 *******************************************************************************/

package net.zehrer.no2.text;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.text.Document;


// TODO: think about generics for the model type
public class ResourceDocument extends Document implements IResourceDocument {
	
	private Resource resource = null;
	private EObject texModel = null;
	
	public void setResource(Resource resource) {
		Assert.isNotNull(resource);
		this.resource = resource;
	}
	
	public Resource getResource () {
		return this.resource;
	}
	
	public void disposeResource() {
		if (resource != null) {
			resource.eAdapters().clear();
			resource = null;
		}
	}

	public void setTextModel(EObject model) {
		this.texModel = model;
		
	}

	public EObject getTextModel() {
		return this.texModel;
	}

}



// --------------- see XtextDocument ------------
// TODO: analyze if this document is affected as far their is no validation job 

/*
 * fix for https://bugs.eclipse.org/bugs/show_bug.cgi?id=297946
 */
//private ReadWriteLock positionsLock = new ReentrantReadWriteLock();
//private Lock positionsReadLock = positionsLock.readLock();
//private Lock positionsWriteLock = positionsLock.writeLock();
//
//@Override
//public Position[] getPositions(String category, int offset, int length, boolean canStartBefore, boolean canEndAfter)
//		throws BadPositionCategoryException {
//	positionsReadLock.lock();
//	try {
//		return super.getPositions(category, offset, length, canStartBefore, canEndAfter);
//	} finally {
//		positionsReadLock.unlock();
//	}
//}
//
//@Override
//public Position[] getPositions(String category) throws BadPositionCategoryException {
//	positionsReadLock.lock();
//	try {
//		return super.getPositions(category);
//	} finally {
//		positionsReadLock.unlock();
//	}
//}
//
//@Override
//public void addPosition(Position position) throws BadLocationException {
//	positionsWriteLock.lock();
//	try {
//		super.addPosition(position);
//	} finally {
//		positionsWriteLock.unlock();
//	}
//}
//
//@Override
//public void addPosition(String category, Position position) throws BadLocationException,
//		BadPositionCategoryException {
//	positionsWriteLock.lock();
//	try {
//		super.addPosition(category, position);
//	} finally {
//		positionsWriteLock.unlock();
//	}
//}
//
//@Override
//public void removePosition(Position position) {
//	positionsWriteLock.lock();
//	try {
//		super.removePosition(position);
//	} finally {
//		positionsWriteLock.unlock();
//	}
//}
//
//@Override
//public void removePosition(String category, Position position) throws BadPositionCategoryException {
//	positionsWriteLock.lock();
//	try {
//		super.removePosition(category, position);
//	} finally {
//		positionsWriteLock.unlock();
//	}
//}