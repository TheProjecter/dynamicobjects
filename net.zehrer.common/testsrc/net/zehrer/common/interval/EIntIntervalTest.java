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

package net.zehrer.common.interval;


import junit.framework.TestCase;
import net.zehrer.common.interval.impl.EIntIntervalImpl;

public class EIntIntervalTest extends TestCase {
	
	
	private EIntInterval i5_10 = new EIntIntervalImpl(5, 10);
	private EIntInterval i1_10 = new EIntIntervalImpl(1, 10);
	private EIntInterval i4_6 = new EIntIntervalImpl(4,6);
	private EIntInterval i5_15 = new EIntIntervalImpl(5,15);
	private EIntInterval i12_16 = new EIntIntervalImpl(12,16);
	

    public void testSubset() {
    	EIntInterval subset = new EIntIntervalImpl(5,8);
        EIntInterval range =  new EIntIntervalImpl(1,10);
        
        assertTrue(subset.isSubset(range));
        assertFalse(range.isSubset(subset));
    }


    public void testIntersects() {
        assertTrue("i5_10.intersects(i1_10)", i5_10.intersects(i1_10));
        assertTrue("i1_10.intersects(i5_10)", i1_10.intersects(i5_10));
        assertTrue("i4_6.intersects(i1_10)", i4_6.intersects(i1_10));
        assertTrue("i1_10.intersects(i4_6)", i1_10.intersects(i4_6));
        assertTrue("i5_10.intersects(i5_15)", i5_10.intersects(i5_15));
        assertTrue("i5_15.intersects(i1_10)", i5_15.intersects(i1_10));
    }
}
