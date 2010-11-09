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

import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.DocumentEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestTextModelUtilUpdate extends TestCase{

	/**
	 * The fixture for this model test case.
	 */
	protected CompositeNode fixture = null;

	/**
	 * Constructs a new test case with the given name.
	 */
	public TestTextModelUtilUpdate(String name) {
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
		
		CompositeNode node = TextModelManager.modelInit("0123456");
		TextModelManager.createLeafeNode("789", node);
		
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
	public void testUpdate00() {
		
		CompositeNode node = getFixture();
		
		DocumentEvent event = new DocumentEvent(new Document(), 5, 2, "XX");
		
		TextModelUtil.update(node, event);
		
		assertEquals("01234XX789", node.serialize());
	}

	/**
	 * replace 
	 */
	@Test
	public void testUpdate01() {
	
		// same as test 00 just to see real time 
		
		CompositeNode node = getFixture();
		
		DocumentEvent event = new DocumentEvent(new Document(), 5, 2, "XX");
		
		TextModelUtil.update(node, event);
		
		assertEquals("01234XX789", node.serialize());
		assertEquals(10, node.getLength());  // test if the node paramater are updated
	}
	
	/**
	 *  delete
	 */
	@Test
	public void testUpdate02() {
		CompositeNode node = getFixture();
		
		DocumentEvent event = new DocumentEvent(new Document(), 5, 2, "");
		
		TextModelUtil.update(node, event);
		
		assertEquals("01234789", node.serialize());
		assertEquals(8, node.getLength());  // test if the node paramater are updated
	}
	
	
	/**
	 * replace ++
	 */
	@Test
	public void testUpdate03() {
		CompositeNode node = getFixture();
		
		DocumentEvent event = new DocumentEvent(new Document(), 5, 2, "XXX");
		
		TextModelUtil.update(node, event);
		
		assertEquals("01234XXX789", node.serialize());
		assertEquals(11, node.getLength());  // test if the node paramater are updated
	}
	
	
	/**
	 * insert
	 */
	@Test
	public void testUpdate04() {
		CompositeNode node = getFixture();
		
		DocumentEvent event = new DocumentEvent(new Document(), 5, 0, "XX");
		
		TextModelUtil.update(node, event);
		
		assertEquals("01234XX56789", node.serialize());
		assertEquals(12, node.getLength());  // test if the node paramater are updated
	}
	
	
	
	@Test
	public void testUpdate05() {
		// split test 
		CompositeNode node = getFixture();
		
		DocumentEvent event = new DocumentEvent(new Document(), 6, 2, "XX");
		
		TextModelUtil.update(node, event);
		
		assertEquals("012345XX89", node.serialize());
		assertEquals(11, node.getLength());  // test if the node paramater are updated
	}
}

