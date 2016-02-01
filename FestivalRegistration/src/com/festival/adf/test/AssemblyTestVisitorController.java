package com.festival.adf.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import com.festival.adf.businesstier.controller.VisitorController;
import com.festival.adf.businesstier.dao.EventDAO;
import com.festival.adf.businesstier.dao.VisitorDAO;
import com.festival.adf.businesstier.entity.Visitor;
import com.festival.adf.businesstier.service.EventFacade;
import com.festival.adf.businesstier.service.EventServiceImpl;
import com.festival.adf.businesstier.service.VisitorFacade;
import com.festival.adf.businesstier.service.VisitorServiceImpl;
import com.festival.adf.exceptions.FERSGenericException;

/** 
 * Junit test case to test the class VisitorController
 * 
 */
public class AssemblyTestVisitorController {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private MockHttpSession session;
	private VisitorController controller;
	private VisitorDAO visitorDao;
	private EventDAO eventDao;
	private Visitor visitor;

	/**
	 * Set up initial methods required before execution of every method
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		controller = new VisitorController();
		session = new MockHttpSession();
		response = new MockHttpServletResponse();
		visitorDao = new VisitorDAO();
		eventDao = new EventDAO();
		visitor=new Visitor();
	}

	/**
	 * Deallocate objects after execution of every method
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		controller = null;
		session = null;
		response = null;
		visitorDao = null;
		eventDao = null;
		visitor=null;
	}

	/**
	 * Positive test case to test the method newVisitor
	 */
	@Test
	public void testNewVisitor() {
		try {
			request = new MockHttpServletRequest("GET", "/newVistor.htm");

			request.setParameter("USERNAME", "ylee");
			request.setParameter("PASSWORD", "password");
			request.setParameter("FIRSTNAME", "TestVFname");
			request.setParameter("LASTNAME", "lname");
			request.setParameter("EMAIL", "mail");
			request.setParameter("PHONENO", "11111");
			request.setParameter("ADDRESS", "testAddress");
			controller.newVisitor(request, response);
			Visitor visitor = visitorDao.searchUser("ylee", "password");
			assertEquals("ylee", visitor.getUserName());
		} catch (Exception exception) {
			fail("Exception");
		}
	}

	/**
	 * Positive test case to test the method searchVisitor
	 */
	@Test
	public void testSearchVisitor() {
		try {
			request = new MockHttpServletRequest("GET", "/searchVisitor.htm");
			request.setParameter("USERNAME", "ylee");
			request.setParameter("PASSWORD", "password");
			controller.searchVisitor(request, response);
			Visitor visitor = visitorDao.searchUser("ylee", "password");
			assertEquals("ylee", visitor.getUserName());
		} catch (Exception exception) {
			fail("Exception");
		}
	}

	/**
	 * Positive test case for method registerVisitor
	 */
	@Test
	public void testRegisterVisitor() {
		try {
			request = new MockHttpServletRequest("GET", "/eventreg.htm");
			Visitor visitor = visitorDao.searchUser("ylee", "password");
			session.setAttribute("VISITOR", visitor);
			request.setParameter("eventId", "1001");
			request.setParameter("sessionId", "10001");
			request.setSession(session);
			request.setParameter("USERNAME", "ylee");
			request.setParameter("PASSWORD", "password");
			controller.registerVisitor(request, response);
			boolean status = eventDao
					.checkEventsofVisitor(visitor, 1001, 10001);
			assertEquals(status, true);
		} catch (Exception exception) {
			fail("Exception");
		}
	}

	/**
	 * Positive test case for method updateVisitor
	 */
	@Test
	public void testUpdateVisitor() {
		try {
			request = new MockHttpServletRequest("GET", "/updatevisitor.htm");
			Visitor visitor = visitorDao.searchUser("ylee", "password");
			session.setAttribute("VISITOR", visitor);
			request.setSession(session);
			request.setParameter("username", "ylee");
			request.setParameter("password", "password");
			request.setParameter("firstname", "fname");
			request.setParameter("lastname", "lname");
			request.setParameter("email", "mail");
			request.setParameter("phoneno", "3333");
			request.setParameter("address", "testaddress");
			controller.updateVisitor(request, response);
			visitor = visitorDao.searchUser("ylee", "password");
			
			assertEquals("testaddress", visitor.getAddress());
		} catch (Exception exception) {
			fail("Exception");
		}
	}

	/**
	 * Positive test case for search events by name
	 */
	void testSearchEventsByName()
	{
		List<Object[]> eventList = new ArrayList<Object[]>();
		List<Object[]> eventList1 = new ArrayList<Object[]>();
		request = new MockHttpServletRequest("GET", "/searchEventByName.htm");
		Visitor visitor;
		try {
			
			controller.searchEventsByName(request, response);
		
			
			EventFacade serviceImpl = new EventServiceImpl();

			eventList = serviceImpl.getAllEvents("Garden Tour");
			eventList1= eventDao.showAllEvents("Garden Tour");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(eventList.size(),eventList1.size());
	}


	/**
	 * Positive test case for search events by name catalog
	 */
	void testSearchEventsByNameCatalog()
	{
		List<Object[]> eventList = new ArrayList<Object[]>();
		List<Object[]> eventList1 = new ArrayList<Object[]>();
		request = new MockHttpServletRequest("GET", "/searchEventByNameCatalog.htm");
		Visitor visitor;
		try {
			
			controller.searchEventsByNameCatalog(request, response);
		
			
			EventFacade serviceImpl = new EventServiceImpl();

			eventList = serviceImpl.getAllEvents("Garden Tour");
			eventList1= eventDao.showAllEvents("Garden Tour");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(eventList.size(),eventList1.size());
	}




	/**
	 * Test case for show events in asc order
	 */
	void testShowEventsAsc()
	{
		List<Object[]> eventList = new ArrayList<Object[]>();
		List<Object[]> eventList1 = new ArrayList<Object[]>();
		request = new MockHttpServletRequest("GET", "/displayasc.htm");
		try {
			
			controller.showEventsAsc(request, response);
		
			
			EventFacade serviceImpl = new EventServiceImpl();

			eventList = serviceImpl.getAllEventsAsc();
			eventList1= eventDao.showAllEventsAsc();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(eventList,eventList1);
	}





	/**
	 * Test case for show events in desc order
	 */
	
	void testShowEventsDesc()
	{
		List<Object[]> eventList = new ArrayList<Object[]>();
		List<Object[]> eventList1 = new ArrayList<Object[]>();
		request = new MockHttpServletRequest("GET", "/displaydesc.htm");
		try {
			
			controller.showEventsDesc(request, response);
		
			
			EventFacade serviceImpl = new EventServiceImpl();

			eventList = serviceImpl.getAllEventsDesc();
			eventList1= eventDao.showAllEventsDesc();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(eventList,eventList1);
	}




	/**
	 * Test case for show events catalog asc order
	 */
	void testShowEventsCatalogAsc()
	{
		List<Object[]> eventList = new ArrayList<Object[]>();
		List<Object[]> eventList1 = new ArrayList<Object[]>();
		request = new MockHttpServletRequest("GET", "/displayasc.htm");
		try {
			
			controller.showEventsCatalogAsc(request, response);
		
			
			EventFacade serviceImpl = new EventServiceImpl();

			eventList = serviceImpl.getAllEventsAsc();
			eventList1= eventDao.showAllEventsAsc();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(eventList,eventList1);
	}






	/**
	 * Test case for show events catalog desc
	 */
	
	void testShowEventsCatalogDesc()
	{
		List<Object[]> eventList = new ArrayList<Object[]>();
		List<Object[]> eventList1 = new ArrayList<Object[]>();
		request = new MockHttpServletRequest("GET", "/displayasc.htm");
		try {
			
			controller.showEventsCatalogDesc(request, response);
		
			
			EventFacade serviceImpl = new EventServiceImpl();

			eventList = serviceImpl.getAllEventsAsc();
			eventList1= eventDao.showAllEventsAsc();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(eventList,eventList1);
	}




	/**
	 * Positive test case for change password
	 */
	void testChangePassword()
	{
		int status=0;
		int status1=0;
		VisitorFacade vServiceImpl=new VisitorServiceImpl();
		try {
			 visitor = visitorDao.searchUser("ylee", "password");
			status=vServiceImpl.changePassword(visitor);
			status1=visitorDao.changePassword(visitor);
		} catch (FERSGenericException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(status,status1);
		
	}


}