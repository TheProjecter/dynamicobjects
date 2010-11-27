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

package net.zehrer.no2.semantic.editor.model.java;

import net.zehrer.common.interval.EInterval;
import net.zehrer.common.interval.impl.EIntervalImpl;

/**
 *@deprecated 
 */
public class IntervalNode extends EIntervalImpl<Integer>{
	
	public IntervalNode(Integer lower, Integer upper) {
		setLowerLimit(lower);
		setUpperLimit(upper);
	}

	@Override
	protected EInterval<Integer> newOfSameType(Integer lower, Integer upper) {
		return new IntervalNode(lower,upper);
	}

}
