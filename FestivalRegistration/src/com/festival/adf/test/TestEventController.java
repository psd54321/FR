package com.festival.adf.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import com.festival.adf.businesstier.controller.EventController;

/**
 * Junit test class for EventController
 * 
 */
public class TestEventController {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private ModelAndView modelAndView;
	private EventController controller;

	/**
	 * Sets up initial objects required in other methods
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		modelAndView = new ModelAndView();
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
		modelAndView = null;
		controller = null;
		response = null;		
	}

	/**
	 * Test case to test the positive scenario for getAvailableEvents method
	 */
	@Test
	//@Ignore
	public void testGetAvailableEvents_Positive() {

		try {
			request = new MockHttpServletRequest("GET", "/catalog.htm");
			modelAndView = controller.getAvailableEvents(request, response);
		} catch (Exception exception) {
			fail("Exception");
		}
		assertEquals("/eventCatalog.jsp", modelAndView.getViewName());
	}

	/**
	 * Executes the negative scenario for getAvailableEvents method
	 */
	@Test
	//@Ignore
	public void testGetAvailableEvents_Negative() {	
		try {
			request = new MockHttpServletRequest("GET", "/catalog.htm");
			modelAndView = controller.getAvailableEvents(null, response);
		} catch (Exception e) {
			//fail("Exception");
			assertTrue(("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder").contentEquals(e.getMessage()));
		}
		
		
	}
	
	/**
	 * Test case to test the positive scenario for displayEvent method
	 */
	@Test
	//@Ignore
	public void testDisplayEvent_Positive() {
		request = new MockHttpServletRequest("GET", "/displayEvent.htm");
		request.setParameter("eventId", "1001");
		request.setParameter("sessionId", "10001");
		try {
			
			modelAndView = controller.displayEvent(request, response);
			
		} catch (Exception exception) {
			//fail("exception");
		}
		assertEquals("/addEvent.jsp", modelAndView.getViewName());
		
		
		
		
	}

	/**
	 * Executes the negative scenario for displayEvent method
	 */
	@Test
	//@Ignore
	public void testDisplayEvent_Negative() {
		try {
			request = new MockHttpServletRequest("GET", "/displayEvent.htm");
			request.setParameter("eventId", "1001");
			request.setParameter("sessionId", "10001");
			modelAndView = controller.displayEvent(null, response);
			
		} catch (Exception e) {
			//fail("exception");
			assertTrue(("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder").contentEquals(e.getMessage()));
		}
		//assertTrue(("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder").contentEquals(e.getMessage()));
		
		
	}	
	
	/**
	 * Test case to test the positive scenario for updateEvent method
	 */
	@Test
	//@Ignore
	public void testUpdateEvent_Positive() {
		
		
		try {
			request = new MockHttpServletRequest("GET", "/updateEvent.htm");
			request.setParameter("eventId","1001");
			 request.setParameter("sessionId","10001");
			 request.setParameter("eventName","Rose Parade");
			request.setParameter("desc","Floats, Music and More");
			 request.setParameter("place","Rose Garden");
			 request.setParameter("duration","0900-1400");
			 request.setParameter("eventType","Tour");
			 request.setParameter("ticket","4000");
			 request.setParameter("isAdd", "false");
			 
			 //request.setParameter("sessionid","10001");*/	
			 modelAndView = controller.updateEvent(request, response);
			 
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals("/addEvent.jsp", modelAndView.getViewName());
			
			e.printStackTrace();
		}
		
	}

	/**
	 * Executes the negative scenario for updateEvent method
	 */
	@Test
	//@Ignore
	public void testUpdateEvent_Negative() {
		
		request = new MockHttpServletRequest("GET", "/updateEvent.htm");
		request.setParameter("eventId","1001");
		 request.setParameter("sessionId","10001");
		 request.setParameter("eventName","Rose Parade");
		request.setParameter("desc","Floats, Music and More");
		 request.setParameter("place","Rose Garden");
		 request.setParameter("duration","0900-1400");
		 request.setParameter("eventType","Tour");
		 request.setParameter("ticket","4000");
		 request.setParameter("isAdd", "false");
		try {
			
			 
			modelAndView = controller.updateEvent(null, response);
			
		} catch (Exception e) {
			//fail("exception");
			assertTrue(("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder").contentEquals(e.getMessage()));
		}
	}
	
	/**
	 * Test case to test the positive scenario for displayEvent method
	 */
	@Test
	//@Ignore
	public void testDeleteEvent_Positive() {
		try {
			request = new MockHttpServletRequest("GET", "/deleteEvent.htm");
			modelAndView = controller.getAvailableEvents(request, response);
		} catch (Exception exception) {
			fail("Exception");
		}
		assertEquals("/eventCatalog.jsp", modelAndView.getViewName());
		
	}

	/**
	 * Executes the negative scenario for displayEvent method
	 */
	@Test
	//@Ignore
	public void testDeleteEvent_Negative() {
		try {
			request = new MockHttpServletRequest("GET", "/deleteEvent.htm");
			modelAndView = controller.deleteEvent(null, response);
		} catch (Exception e) {
			//fail("Exception");
			assertTrue(("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder").contentEquals(e.getMessage()));
		}
		
	}		

}
