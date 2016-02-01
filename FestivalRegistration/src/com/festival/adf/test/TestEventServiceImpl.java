package com.festival.adf.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.festival.adf.businesstier.entity.Event;
import com.festival.adf.businesstier.entity.EventCoordinator;
import com.festival.adf.businesstier.entity.Visitor;
import com.festival.adf.businesstier.service.EventServiceImpl;

/**
 * Junit test case to test class EventServiceImpl
 * 
 */
public class TestEventServiceImpl {

	private List<Object[]> eventList;
	private Visitor visitor;
	private EventServiceImpl eventServiceImpl;
	List<EventCoordinator> eventCoordinatorList = new ArrayList<EventCoordinator>();
	Event event=new Event();

	/**
	 * Set up the objects required before execution of every method
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		eventServiceImpl = new EventServiceImpl();
		visitor = new Visitor();
	}

	/**
	 * Deallocates the objects after execution of every method
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		

		visitor=null;
		eventServiceImpl=null;

		eventServiceImpl=null;
		visitor=null;

	}

	/**
	 * Test case to test the method getAllEvents
	 */
	@Test
	@Ignore
	public void testGetAllEvents() {
		
		eventList=new EventServiceImpl().getAllEvents();

		System.out.println("event:"+eventList.size());
		assertEquals(18,eventList.size());

		assertEquals("There are total 7 events in database",7,eventList.size());

	}

	/**
	 * Test case to test the method checkEventsofVisitor
	 */
	@Test
	public void testCheckEventsofVisitor() {
		
		visitor.setVisitorId(1007);
		assertEquals("There is 1 event registered for admin with event id 1001",true,eventServiceImpl.checkEventsofVisitor(visitor, 1002, 10002));
		
	}

	/**
	 * Test case to test the method updateEventDeletions
	 */
	@Test
	public void testUpdateEventDeletions() {
			
		eventServiceImpl.updateEventDeletions(1004, 10004);
		assertTrue("Event updateEventDeletion Successful",true);
	}

	/**
	 * Junit test case for getEventCoordinator
	 */
	@Test
	public void testGetEventCoordinator() {
				
		eventCoordinatorList=eventServiceImpl.getAllEventCoordinators();
		assertTrue(eventCoordinatorList.size()>0);
	}

	/**
	 * Junit test case for getEvent
	 */
	@Test
	public void testGetEvent() {
			
		int eventId=1002;
		int sessionId=10002;
		event=eventServiceImpl.getEvent(eventId, sessionId);
		assertEquals(event.getEventid(),eventId);
		
		
		
	}

	/**
	 * Junit test case for updateEvent
	 */
	@Test
	@Ignore
	public void testInsertEvent() {
		
		int status=0;
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
		status=eventServiceImpl.insertEvent(event);
		assertEquals(status,1);
	}

	/**
	 * Junit test case for updateEvent
	 */
	@Test
	public void testUpdateEvent() {
		
		int eventId=1002;
		int sessionId=10002;
		int status=0;
		event=eventServiceImpl.getEvent(eventId, sessionId);
		event.setEventtype("sports");
		event.setName("cricket");
		event.setDescription("t20");
		event.setPlace("MCA Stadium");
		event.setDuration("0900-0500");
		event.setSeatsavailable("2000");
		status=eventServiceImpl.updateEvent(event);
		assertEquals(status,1);
	}

	/**
	 * Junit test case for deleteEvent
	 */
	@Test
	@Ignore
	public void testDeleteEvent() {
		
			int status=0;
		
				//event=eventdao.getEvent(1010, 10012);
				status=eventServiceImpl.deleteEvent(1013, 10018);
			
			assertEquals(status,1);
		
	}

}
