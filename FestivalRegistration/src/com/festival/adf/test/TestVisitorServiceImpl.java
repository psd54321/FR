package com.festival.adf.test;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.festival.adf.businesstier.entity.Event;
import com.festival.adf.businesstier.entity.Visitor;
import com.festival.adf.businesstier.service.VisitorServiceImpl;

/**
 * Junit test class for VisitorServiceImpl
 *
 */
public class TestVisitorServiceImpl {

	private List<Event> visitorList;	
	private Visitor visitor;
	private VisitorServiceImpl visitorServiceImpl;

	/**
	 * Set up the initial methods 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {		
		visitorServiceImpl = new VisitorServiceImpl();
		visitor = new Visitor();
	}

	/**
	 * Deallocates the objects after execution of every method
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		
	}

	/**
	 * Test case for method createVisitor
	 */
	@Test
	public void testCreateVisitor() {
				
	}

	/**
	 * Test case for method createVisitor
	 */
	@Test
	public void testSearchVisitor() {
		
	}

	/**
	 * Test case for method RegisterVisitor
	 */
	@Test
	public void testRegisterVisitor() {
			
	}

	/**
	 * Test case for method showRegisteredEvents
	 */
	@Test
	public void testShowRegisteredEvents() {
			
	}

	/**
	 * Test case for method updateVisitorDetails
	 */
	@Test
	public void testUpdateVisitorDetails() {
			
	}

	/**
	 * Test case for method unregisterEvent
	 */
	@Test
	public void testUnregisterEvent() {
			
	}

}
