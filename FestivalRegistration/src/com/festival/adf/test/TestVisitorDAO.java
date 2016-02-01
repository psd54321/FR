package com.festival.adf.test;

import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.festival.adf.businesstier.dao.VisitorDAO;
import com.festival.adf.businesstier.entity.Visitor;

/**
 * JUnit test case for VisitorDAO class for testing all repository methods to
 * call database sub-routines
 * 
 */
public class TestVisitorDAO {

	private Visitor visitor;
	private VisitorDAO visitorDAO;
	private ArrayList<Object[]> registeredEvents;

	/**
	 * Setting up initial objects
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		visitor = new Visitor();
		visitorDAO = new VisitorDAO();
		registeredEvents = new ArrayList<Object[]>();
	}

	/**
	 * Deallocating objects after execution of every method
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		/**
		 * @TODO: Release all the objects here by assigning them null  
		 */
	}

	/**
	 * Test case for method insertData
	 */
	@Test
	public void testInsertData() {
			
	}	

	/**
	 * Test case for method searchUser
	 */
	@Test
	public void testSearchUser() {
			
	}

	/**
	 * Test case for method registerVisitorToEvent
	 */
	@Test
	public void testRegisterVisitorToEvent() {
		
	}	

	/**
	 * Test case for method registeredEvents
	 */
	@Test
	public void testRegisteredEvents() {
		
	}

	/**
	 * Test case for method updateVisitor
	 */
	@Test
	public void testUpdateVisitor() {
		
	}

	/**
	 * Test case for method registeredEvents
	 */
	@Test
	public void testUnregisterEvent() {
			
	}
	
	
	
	/**
	 * Test case for method change password
	 */
	@Test
	public void testChangePassword_VisitorNull() {
		try {
			visitor = null;
			visitorDAO.changePassword(visitor);
		} catch (SQLException exception) {
			fail("SQL Exception");
		} catch (ClassNotFoundException exception) {
			fail("Class Not Found Exception");
		} catch (Exception exception) {
			fail("NULL Exception");
		}
	}

}
