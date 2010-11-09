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

package net.zehrer.no2.sematic.editor.model;

import junit.framework.TestCase;
import net.zehrer.no2.semantic.editor.TextModelManager;
import net.zehrer.no2.semantic.editor.adapter.NodeContentAdapter;
import net.zehrer.no2.semantic.editor.model.CompositeNode;
import net.zehrer.no2.semantic.editor.model.impl.TextModelUtil;

import org.eclipse.emf.ecore.EObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestTextModelUtil extends TestCase{

	final static String testText = "Hello World";
	
	/**
	 * The fixture for this model test case.
	 */
	protected CompositeNode fixture = null;

	/**
	 * Constructs a new test case with the given name.
	 */
	public TestTextModelUtil(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this model test case.
	 */
	protected void setFixture(CompositeNode fixture) {
		this.fixture = fixture;
	}
	
	/**
	 * Returns the fixture for this model test case.
	 */
	protected CompositeNode getFixture() {
		return fixture;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		CompositeNode node = TextModelManager.modelInit(testText);
		
		NodeContentAdapter.createAdapterAndAddToNode(node);
		
		setFixture(node);
		
		//System.out.println(EditorUtil.logGraph(getFixture()));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		setFixture(null);
	}
	
	@Test
	public void testCheckNode00() {
	
		assertTrue(TextModelUtil.checkNodeOffset(getFixture(), 5));
	}

	@Test
	public void testCheckNode01() {
	
		assertTrue(TextModelUtil.checkNodeOffset(getFixture(), 0));
	}

	
	@Test
	public void testCheckNode02() {
	
		assertTrue(TextModelUtil.checkNodeOffset(getFixture(), 11));

	}
	
	@Test
	public void testCheckNode03() {
	
		assertTrue(!TextModelUtil.checkNodeOffset(getFixture(), 12));

	}
	
	@Test
	public void testCheckNode04() {
	
		assertTrue(!TextModelUtil.checkNodeOffset(getFixture(), -1));
	}
}
