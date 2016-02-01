package com.festival.adf.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.festival.adf.businesstier.controller.EventController;
import com.festival.adf.businesstier.dao.EventDAO;

/** 
 * Junit test class for EventController
 * 
 */
public class AssemblyTestEventController {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;	
	private EventController controller;
	private EventDAO eventDao;

	/**
	 * Sets up initial objects required in other methods
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		eventDao = new EventDAO();		
		controller = new EventController();
		response = new MockHttpServletResponse();		
	}

	/**
	 * Deallocate the objects after execution of every method
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		eventDao = null;		
		controller = null;
		response = null;
		request = null;
	}

	/**
	 * Test case to test the positive scenario for getAvailableEvents method
	 */
	@Test
	public void testGetAvailableEvents() {

		ArrayList<Object[]> showEvents = new ArrayList<Object[]>();

		try {
			request = new MockHttpServletRequest("GET", "/catalog.htm");
			
			controller.getAvailableEvents(request, response);		
			
			showEvents = eventDao.showAllEvents();
			
		} catch (Exception exception) {			
			fail("Exception");
		}
		assertEquals(showEvents.size() > 0, true);
	}
	
	/**
	 * Test case to test the positive scenario for displayEvent method
	 */
	@Test
	public void testDisplayEvent() {
		
	}
	
	/**
	 * Test case to test the positive scenario for updateEvent method
	 */
	@Test
	public void testUpdateEvent() {
	}	
	
	/**
	 * Test case to test the positive scenario for displayEvent method
	 */
	@Test
	public void testDeleteEvent() {
	}
}
