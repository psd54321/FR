package com.festival.adf.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.festival.adf.businesstier.dao.EventDAO;
import com.festival.adf.businesstier.entity.Event;
import com.festival.adf.businesstier.entity.EventCoordinator;
import com.festival.adf.businesstier.entity.Visitor;
import com.festival.adf.businesstier.service.EventServiceImpl;

import junit.framework.Assert;

/** 
 * Junit test case to test class EventServiceImpl
 * 
 */
public class AssemblyTestEventServiceImpl {

	private List<Object[]> eventList;
	private Visitor visitor;
	private EventServiceImpl eventServiceImpl;
	private EventDAO eventDao;
	private Event event;
	private Event event1;

	/**
	 * Set up the objects required before execution of every method
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		eventDao = new EventDAO();
		eventServiceImpl = new EventServiceImpl();
		visitor = new Visitor();
		event=new Event();
		 event1=new Event();
	}

	/**
	 * Deallocates the objects after execution of every method
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		eventDao = null;
		eventServiceImpl = null;
		visitor = null;
		event=null;
		event1=null;
	}

	/**
	 * Test case to test the method getAllEvents
	 */
	@Test
	public void testGetAllEvents() {
		try {
			eventList = eventServiceImpl.getAllEvents();
			
			ArrayList<Object[]> showEvents = new ArrayList<Object[]>();
			
			showEvents = eventDao.showAllEvents();
				
			assertEquals(eventList.size() , showEvents.size());
		
		} catch (Exception e){
			fail("Exception");
		}
	}

	/**
	 * Test case to test the method checkEventsofVisitor
	 */
	@Test
	public void testCheckEventsofVisitor() {
		visitor.setVisitorId(1001);
		int eventid = 1001;
		int eventSessionId = 10001;
		boolean eventStatus = eventServiceImpl.checkEventsofVisitor(visitor,
				eventid, eventSessionId);
		
		boolean daoStatus = false;
		try {	
			daoStatus = eventDao.checkEventsofVisitor(visitor, eventid, eventSessionId);
		} catch (Exception exception) {
			fail("Exception");
		}
		
		assertEquals(eventStatus, daoStatus);		
	}	

	/**
	 * Junit test case for getEventCoordinator
	 */
	public void testGetEventCoordinator()
	{
		List<EventCoordinator> eventCoordinatorList = new ArrayList<EventCoordinator>();
		List<EventCoordinator> eventCoordinatorList1 = new ArrayList<EventCoordinator>();
		try {
			eventCoordinatorList = eventDao.getEventCoordinator();
			EventCoordinator eventCoordinator = new EventCoordinator();
			eventCoordinator.setUserName("admin1");
			eventCoordinator.setEventcoordinatorid(101);
			eventCoordinatorList1.add(eventCoordinator);
			
		
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(eventCoordinatorList,eventCoordinatorList1);
	}
	




	/**
	 * Junit test case for getEvent
	 */
	public void testGetEvent()
	{
		 
		
		try {
			event = eventDao.getEvent(1001, 10001);
			event1= eventServiceImpl.getEvent(1001, 10001);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(event,event1);
	}




	/**
	 * Junit test case for insertEvent
	 */
	public void insertEvent()
	{
		int status=1;
		int status1=0;
		try {
			status = eventDao.insertEvent(event);
			event.setEventid(1023);
			event.setEventtype("sports");
			event.setName("cricket");
			event.setDescription("t20");
			event.setPlace("Lords Stadium");
			event.setDuration("0900-0500");
			event.setSeatsavailable("2000");
			event.setSessionId(10014);
			event.setEventCoordinatorId(104);
			event.setEventSession(2);	
			event.setAdd(true);
			status1=eventServiceImpl.insertEvent(event);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(status,status1);
	}




	/**
	 * Junit test case for updateEvent
	 */
	
	public void testUpdateEvent()
	{
		int status=0;
		int eventId=1002;
		int sessionId=10002;
		int status1=0;
		try {
			status = eventDao.updateEvent(event);
			
		
			event=eventServiceImpl.getEvent(eventId, sessionId);
			event.setEventtype("sports");
			event.setName("cricket");
			event.setDescription("t20");
			event.setPlace("MCA Stadium");
			event.setDuration("0900-0500");
			event.setSeatsavailable("2000");
			status1=eventServiceImpl.updateEvent(event);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	



	/**
	 * Junit test case for deleteEvent
	 */
	@Test
	public void testDeleteEvent() {
		List<Object[]> eventObjectList = eventServiceImpl.getAllEvents("junit_event_name_update");

		Event deleteEvent = new Event();

		for (Object[] eveObject : eventObjectList) {
			deleteEvent.setEventid((Integer) eveObject[0]);
			deleteEvent.setSessionId((Integer) eveObject[7]);
		}

		eventServiceImpl.deleteEvent(deleteEvent.getEventid(),	deleteEvent.getSessionId());
		
		try {			
			
			Event daoEvent = eventDao.getEvent(deleteEvent.getEventid(),deleteEvent.getSessionId());
			
			Assert.assertTrue(daoEvent != null);
			
		} catch(Exception e){
			fail("Exception");
		}		
	}

}