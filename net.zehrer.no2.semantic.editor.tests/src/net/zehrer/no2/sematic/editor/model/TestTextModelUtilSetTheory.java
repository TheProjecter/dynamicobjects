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
import net.zehrer.common.interval.EIntInterval;
import net.zehrer.common.interval.impl.EIntIntervalImpl;
import net.zehrer.no2.semantic.editor.TextModelManager;
import net.zehrer.no2.semantic.editor.adapter.NodeContentAdapter;
import net.zehrer.no2.semantic.editor.model.AbstractNode;
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
		//   23    67      <- node2 
		//      45         <- node3
		
		CompositeNode node1 = TextModelManager.createCompositeNode("01");
		
		CompositeNode node2 = TextModelManager.createCompositeNode("23");
		node1.getChildren().add(node2);
		
		CompositeNode node3 = TextModelManager.createCompositeNode("45");
		node2.getChildren().add(node3);
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
	public void testintersect01() {
		
		EIntInterval sel = new EIntIntervalImpl(4, 5);
		
//		CompositeNode node = getFixture();
		LeafNode leaf = TextModelManager.createLeafeNode("01234567890");
		
		LeafNode result = (LeafNode) leaf.intersect(sel);
		
		assertEquals("45", result.getText());
	}
	
	@Test
	public void testintersect02() {
		
		EIntInterval sel = new EIntIntervalImpl(4, 5);
		
//		CompositeNode node = getFixture();
		LeafNode leaf = TextModelManager.createLeafeNode("01234567890");
		
		LeafNode result = (LeafNode) leaf.intersect(sel);
		
		assertEquals("45", result.getText());
	}
	
	// XX 
	// 01234567890
	// 01      890   <- node1
	//   23  67      <- node2 
	//     45        <- node3
	
	@Test
	public void testintersect03() {
		
		EIntInterval sel = new EIntIntervalImpl(0, 1);
		
		CompositeNode node = getFixture();
		
		AbstractNode result =  (AbstractNode) node.intersect(sel);
		
		assertEquals("01", result.serialize());
	}
}

