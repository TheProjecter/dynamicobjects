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
import net.zehrer.no2.semantic.editor.model.EditorFactory;
import net.zehrer.no2.semantic.editor.model.LeafNode;

import org.junit.After;
import org.junit.Before;

/**
 * @author Stephan Zehrer
 */
public class TestModelUseCases extends TestCase {
	
	final static String testText = "Hello World";
	
	/**
	 * The fixture for this model test case.
	 */
	protected CompositeNode fixture = null;

	/**
	 * Constructs a new test case with the given name.
	 */
	public TestModelUseCases(String name) {
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
		CompositeNode node = TextModelManager.createCompositeNode(testText);
		
		NodeContentAdapter.createAdapterAndAddToNode(node);
		
		setFixture(node);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		setFixture(null);
	}

	
	// ------- TEST CODE ------
	
	public void testSerialize() {

		CompositeNode node = getFixture();
		
		assertEquals(testText, node.serialize());
		
	}

	
	public void testAdd() {

		CompositeNode node = getFixture();
		
		LeafNode leafNode = EditorUtil.getNode("12345");
		
		System.out.println(EditorUtil.logGraph(node));
		
		node.getChildren().add(leafNode);
		
		System.out.println(EditorUtil.logGraph(node));
		
		leafNode = EditorUtil.getNode("Test");
		
		node.getChildren().add(1, leafNode);
		
		System.out.println(EditorUtil.logGraph(node));
		
		//System.out.printf(node.serialize());
	}
}
