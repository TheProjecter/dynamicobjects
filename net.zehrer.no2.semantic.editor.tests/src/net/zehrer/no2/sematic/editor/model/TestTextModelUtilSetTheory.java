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
import net.zehrer.no2.semantic.editor.model.LeafNode;
import net.zehrer.no2.semantic.editor.model.java.TextModelUtil;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.DocumentEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestTextModelUtilSetTheory extends TestCase{

	/**
	 * The fixture for this model test case.
	 */
	protected CompositeNode fixture = null;

	/**
	 * Constructs a new test case with the given name.
	 */
	public TestTextModelUtilSetTheory(String name) {
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
		
		// 01234567890
		// 01        890   <- node1
		//   23 45 67      <- node2 
		
		CompositeNode node1 = TextModelManager.createCompositeNode("01");
		
		CompositeNode node2 = TextModelManager.createCompositeNode("23");
		node1.getChildren().add(node2);
		
		TextModelManager.createLeafeNode("45",node2);
		TextModelManager.createLeafeNode("67",node2);
		
		TextModelManager.createLeafeNode("890",node1);
		
		NodeContentAdapter.createAdapterAndAddToNode(node1);
		setFixture(node1);
		
		//System.out.println(EditorUtil.logGraph(getFixture()));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		setFixture(null);
	}
	

	// --------------------
	
	@Test
	public void testCheckLeafNode01() {
		
		CompositeNode node = TextModelManager.createCompositeNode("012345");
		NodeContentAdapter.createAdapterAndAddToNode(node);

		LeafNode leafNode =  (LeafNode) node.getChildren().get(0);
	
		assertTrue(TextModelUtil.checkNodeOffset(leafNode, 0));
	}

	
	@Test
	public void testCheckLeafNode02() {
		
		CompositeNode node = TextModelManager.createCompositeNode("012345");
		NodeContentAdapter.createAdapterAndAddToNode(node);

		LeafNode leafNode =  (LeafNode) node.getChildren().get(0);
	
		assertFalse(TextModelUtil.checkNodeOffset(leafNode, 6));

	}
	
	// --------------------
	
	
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
	
		assertFalse(TextModelUtil.checkNodeOffset(getFixture(), 11));

	}
	
	@Test
	public void testCheckNode03() {
	
		assertFalse(TextModelUtil.checkNodeOffset(getFixture(), -1));
	}
	
	// ------------- 
	
	@Test
	public void testgetLeafNode01() {
		
		CompositeNode node = getFixture();
		
		
		LeafNode lNode = TextModelUtil.getLeafNode(node,3);
		
		assertEquals("23", lNode.getText());
	}
	
	@Test
	public void testgetLeafNode02() {
		
		CompositeNode node = getFixture();
		
		
		LeafNode lNode = TextModelUtil.getLeafNode(node,8);
		
		assertEquals("890", lNode.getText());
	}
	
	// ------------- 
	
	@Test
	public void testgetLeafNodes01() {
		
		CompositeNode node = getFixture();
		
		EList<LeafNode> result = TextModelUtil.getLeafNodes(node, 1, 9);
		
		assertEquals(5, result.size());
	}
	
	@Test
	public void testgetLeafNodes02() {
		
		CompositeNode node = getFixture();
		
		EList<LeafNode> result = TextModelUtil.getLeafNodes(node, 0, 3);
		
		assertEquals(2, result.size());
	}


	@Test
	public void testgetLeafNodes03() {
		
		CompositeNode node = getFixture();
		
		EList<LeafNode> result = TextModelUtil.getLeafNodes(node, 4, 5);
		
		assertEquals(1, result.size());
	}

	@Test
	public void testgetLeafNodes04() {
		
		CompositeNode node = getFixture();
		
		EList<LeafNode> result = TextModelUtil.getLeafNodes(node, 6, 9);
		
		assertEquals(2, result.size());
	}
	
	@Test
	public void testgetLeafNodes05() {
		
		CompositeNode node = getFixture();
		
		EList<LeafNode> result = TextModelUtil.getLeafNodes(node, 6, 11);  // no error
		
		assertEquals(2, result.size());
	}
	
	// 01234567890
	// 01        890   <- node1
	//   23 45 67      <- node2 
}

