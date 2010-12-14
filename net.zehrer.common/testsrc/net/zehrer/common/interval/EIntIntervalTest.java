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

    public void testSubset() {
    	EIntInterval subset = new EIntIntervalImpl(5,8);
        EIntInterval range =  new EIntIntervalImpl(1,10);
        
        assertTrue(subset.isSubset(range));
        assertFalse(range.isSubset(subset));
    }
}
