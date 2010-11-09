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

import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;

public class NO2Region extends Region {
	
	public NO2Region(int offset, int length) {
		super(offset, length);
	}

	/**
	 * Returns the intersection between this region and the given region
	 * @param region 
	 * @return the intersection region
	 */
	public NO2Region getIntersection (IRegion region) {
		return null;
	}
	
	/**
	 * Returns the complement of this region and the given region (this - region)
	 * @param region 
	 * @return the complement region
	 */
	public NO2Region getComplement (IRegion region) {
		return null;
	}


}
