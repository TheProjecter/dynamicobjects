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

import org.junit.Test;

public class EIntIntervalTest extends TestCase {
	
	private EIntInterval i1_10 = new EIntIntervalImpl(1, 10);
	
	private EIntInterval i1_5 = new EIntIntervalImpl(1,5); 
	private EIntInterval i3_7 = new EIntIntervalImpl(3,7);
	
	
	private EIntInterval i4_6 = new EIntIntervalImpl(4,6);

	private EIntInterval i5_5 = new EIntIntervalImpl(5, 5);
	
	private EIntInterval i5_8 = new EIntIntervalImpl(5, 8);

	private EIntInterval i5_10 = new EIntIntervalImpl(5, 10);
	
	private EIntInterval i5_15 = new EIntIntervalImpl(5,15);
	
	private EIntInterval i12_16 = new EIntIntervalImpl(12,16);
	
	@Test
    public void testSubset() {
        assertTrue(i5_8.isSubsetOf(i1_10));
        assertTrue(i5_10.isSubsetOf(i1_10));
        assertTrue(i1_10.isSubsetOf(i1_10));
        
        assertFalse(i1_10.isSubsetOf(i5_8));
        assertFalse(i5_15.isSubsetOf(i12_16));
        
    }

	@Test
    public void testIntersects() {
        assertTrue("i5_10.intersects(i1_10)", i5_10.intersects(i1_10));
        assertTrue("i1_10.intersects(i5_10)", i1_10.intersects(i5_10));
        assertTrue("i4_6.intersects(i1_10)", i4_6.intersects(i1_10));
        assertTrue("i1_10.intersects(i4_6)", i1_10.intersects(i4_6));
        assertTrue("i5_10.intersects(i5_15)", i5_10.intersects(i5_15));
        assertTrue("i5_15.intersects(i1_10)", i5_15.intersects(i1_10));
        
        assertFalse("i1_10.intersects(i12_16)", i1_10.intersects(i12_16));
    }
    
	@Test
    public void testRelativeComplementOverlapLeft() {

        EIntInterval complement = null;
        
        complement = i3_7.leftComplementTo(i1_5);
        assertNull(complement);
        
        complement = i3_7.leftComplementTo(i12_16);
        assertNull(complement);
        
        complement = i12_16.leftComplementTo(i3_7);
        assertNull(complement);
        
        complement = i1_5.leftComplementTo(i3_7);
        assertEquals(new EIntIntervalImpl(1, 2), complement);
        
        complement  = i1_10.leftComplementTo(i5_5);
        assertEquals(new EIntIntervalImpl(1, 4), complement);
    }

	@Test
    public void testRelativeComplementOverlapRight() {
		
		EIntInterval complement = null;
		
		complement = i1_5.rightComplementTo(i3_7);
        assertNull(complement);
        
        complement = i3_7.rightComplementTo(i12_16);
        assertNull(complement);
        
        complement = i12_16.rightComplementTo(i3_7);
        assertNull(complement);

        complement = i3_7.rightComplementTo(i1_5);
        assertEquals(new EIntIntervalImpl(6, 7), complement);
        
        complement  = i1_10.rightComplementTo(i5_5);
        assertEquals(new EIntIntervalImpl(6, 10), complement);
    }
}
