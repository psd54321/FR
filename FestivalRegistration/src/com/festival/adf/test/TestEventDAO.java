package com.festival.adf.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.festival.adf.businesstier.dao.EventDAO;
import com.festival.adf.businesstier.dao.VisitorDAO;
import com.festival.adf.businesstier.entity.Event;
import com.festival.adf.businesstier.entity.EventCoordinator;
import com.festival.adf.businesstier.entity.Visitor;
import com.festival.adf.exceptions.FERSGenericException;
import com.festival.adf.helper.FERSDataConnection;

/**
 * Junit test class for EventDAO class
 * 
 */
public class TestEventDAO {

	private static Connection connection = null;
	private static PreparedStatement statement = null;
	private static ResultSet resultSet = null;
	private ArrayList<Object[]> showAllEvents;
	private EventDAO eventdao;
	private VisitorDAO visitordao;
	private Visitor visitor;
	private List<EventCoordinator> eventCoordinatorList;
	private Event event;

	/**
	 * Sets up database connection before other methods are executed in this
	 * class
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpDatabaseConnection() throws Exception {
		connection = FERSDataConnection.createConnection();
		
	}

	/**
	 * Closes the database connection after all the methods are executed
	 * 
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownDatabaseConnection() throws Exception {
		connection.close();
	}

	/**
	 * Sets up the objects required in other methods
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		showAllEvents = new ArrayList<Object[]>();
		eventdao = new EventDAO();
		event = new Event();
		visitordao=new VisitorDAO();
		visitor=new Visitor();
		eventCoordinatorList=new ArrayList<EventCoordinator>();
	}

	/**
	 * Deallocate the resources after execution of method
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		eventdao=null;
		showAllEvents=null;
	}

	/**
	 * Positive test case to test the method showAllEvents
	 */
	@Test
	public void testShowAllEvents_Positive() {	
		try {
			showAllEvents=eventdao.showAllEvents();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(showAllEvents.size()>0);
	}
	
	/**
	 * Junit test case to test positive case for updateEventDeletions
	 */
	@Test
	public void testUpdateEventDeletions_Positive() {
		int eid = 1003;
		int sessionid=10003;
		try {
			statement = connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENTSESSION WHERE EVENTID = ?");
			statement.setInt(1, eid);
			resultSet = statement.executeQuery();			
			assertTrue(resultSet.next());
			int testSeatsAvailableBefore = resultSet.getInt(1);
			eventdao.updateEventDeletions(eid,sessionid);	
			statement = connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENTSESSION WHERE EVENTID = ?");			
			statement.setInt(1, eid);
			resultSet = statement.executeQuery();
			resultSet.next();
			int testSeatsAvailableAfter = resultSet.getInt(1);
			assertTrue(testSeatsAvailableAfter == testSeatsAvailableBefore + 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	

		
	}

	/**
	 * Negative test case for method updateEventDeletions
	 */
	@Test
	public void testUpdateEventDeletions_Negative() {
	
		int eid = 6578;
		int sessionid = 6578;
		
		try {
			eventdao.updateEventDeletions(eid, sessionid);
		} 
		 catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals("Records not updated properly",e.getMessage());
		}
		
		
	}

	/**
	 * Positive test case for method updateEventNominations
	 */
	@Test
	public void testUpdateEventNominations_Positive() {
		 
			int eid = 1003;
			int sessionid=10003;
			try {
				statement = connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENTSESSION WHERE EVENTID = ?");
				statement.setInt(1, eid);
				resultSet = statement.executeQuery();			
				assertTrue(resultSet.next());
				int testSeatsAvailableBefore = resultSet.getInt(1);
				eventdao.updateEventNominations(eid,sessionid);	
				statement = connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENTSESSION WHERE EVENTID = ?");			
				statement.setInt(1, eid);
				resultSet = statement.executeQuery();
				resultSet.next();
				int testSeatsAvailableAfter = resultSet.getInt(1);
				assertTrue(testSeatsAvailableAfter == testSeatsAvailableBefore - 1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}

	/**
	 * Negative test case for method updateEventNominations
	 */
	@Test
	public void testUpdateEventNominations_Negative() {
		
		int eid = 6578;
		int sessionid = 6578;
		
		try {
			eventdao.updateEventNominations(eid, sessionid);
		} 
		 catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals("Records not updated properly",e.getMessage());
		}
		
	}

	/**
	 * Positive test case for method checkEventsofVisitor
	 */
	@Test
	public void testCheckEventsOfVisitor_Positive() {
		
		int eventid = 1001;
		int sessionid=10001;
		boolean status=false;
		try {
			visitor=visitordao.searchUser("ylee","password");
			status=eventdao.checkEventsofVisitor(visitor, eventid, sessionid);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(status,true);
	}
	
	/**
	 * Junit test case for getEventCoordinator
	 */
	@Test
	public void testGetEventCoordinator(){
		
		 new ArrayList<EventCoordinator>();
		try {
			eventCoordinatorList=eventdao.getEventCoordinator();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(eventCoordinatorList.size()>0);
	}
	
	/**
	 * Junit test case for getEvent
	 */
	@Test
	public void testGetEvent(){
				
		int eventid=1001;
		int sessionid=10001;
		try {
			event=eventdao.getEvent(eventid, sessionid);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(event.getEventid(),eventid);
	}	
	
	/**
	 * Junit test case for updateEvent
	 */
	@Test
	public void testInsertEvent(){
		
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
		try {
			status=eventdao.insertEvent(event);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(status,1);
	}
	
	/**
	 * Junit test case for updateEvent
	 */
	@Test
	public void testUpdateEvent(){
		
		int status=0;
		try {
			event=eventdao.getEvent(1001, 10001);
			event.setEventtype("sports");
			event.setName("cricket");
			event.setDescription("t20");
			event.setPlace("MCA Stadium");
			event.setDuration("0900-0500");
			event.setSeatsavailable("2000");
			status=eventdao.updateEvent(event);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(status,1);
		
		
	}
	
	/**
	 * Junit test case for deleteEvent
	 */
	@Test
	public void testDeleteEvent(){
		
		int status=0;
		try {
			//event=eventdao.getEvent(1010, 10012);
			status=eventdao.deleteEvent(1012, 10016);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FERSGenericException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(status,1);
	}

}
