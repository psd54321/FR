package com.festival.adf.businesstier.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.festival.adf.businesstier.entity.Event;
import com.festival.adf.businesstier.entity.Visitor;
import com.festival.adf.exceptions.FERSGenericException;
import com.festival.adf.helper.FERSDataConnection;
import com.festival.adf.helper.FERSDbQuery;


public class VisitorDAO {

	// LOGGER for handling all transaction messages in VISITORDAO
	private static Logger log = Logger.getLogger(VisitorDAO.class);

	//JDBC API classes for data persistence
	private Connection connection = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	private FERSDbQuery query;
	private Event event=null;

	//Default constructor for injecting Spring dependencies for SQL queries
	public VisitorDAO() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		query = (FERSDbQuery) context.getBean("SqlBean");
	}

	/**
	 *  <br/>
	 *  METHOD DESCRIPTION: <br/>
	 *  DAO method to insert visitor details into the VISITOR table 
	 *  after validating that the desired username of the visitor 
	 *  does not currently exist. <br/>
	 *  
	 *  @return boolean
	 *  
	 *  @param visitor
	 *  
	 *  @throws ClassNotFoundException
	 *  @throws SQLException
	 *  @throws Exception
	 *  
	 */  
	public boolean insertData(Visitor visitor) throws ClassNotFoundException,
			SQLException, Exception {
		connection = FERSDataConnection.createConnection();
		Statement selStatement = connection.createStatement();
		statement = connection.prepareStatement(query.getInsertQuery());
		resultSet = selStatement.executeQuery(query.getValidateVisitor());
		boolean userFound = false;
		while (resultSet.next()) {
			if (resultSet.getString("username").equals(visitor.getUserName())) {
				userFound = true;
				log.info("Vistor with USERNAME already exists in Database");
				break;
			}
		}
		if (userFound == false) {
			statement.setString(1, visitor.getUserName());
			statement.setString(2, visitor.getPassword());
			statement.setString(3, visitor.getFirstName());
			statement.setString(4, visitor.getLastName());
			statement.setString(5, visitor.getEmail());
			statement.setString(6, visitor.getPhoneNumber());
			statement.setString(7, visitor.getAddress());
			int status = statement.executeUpdate();
			if (status <= 0)
				throw new FERSGenericException("Records not updated properly",
						new Exception());
			log.info("Visitor details inserted into Database");
			FERSDataConnection.closeConnection();
			return true;
		}
		resultSet.close();
		FERSDataConnection.closeConnection();
		return false;
	}

	/**
	 *  <br/>
	 *  METHOD DESCRIPTION: <br/>
	 *  DAO method for searching for a existing visitor account 
	 *  using the entered username and password. <br/>
	 *  
	 *  @return Visitor
	 *  
	 *  @param  username
	 *  @param  password
	 *  
	 *  @throws ClassNotFoundException
	 *  @throws SQLException
	 *  
	 */
	public Visitor searchUser(String username, String password)
			throws ClassNotFoundException, SQLException {
		connection = FERSDataConnection.createConnection();
		event=new Event();
		Visitor visitor = new Visitor();
		statement = connection.prepareStatement(query.getSearchQuery());
		statement.setString(1, username);
		statement.setString(2, password);
		resultSet = statement.executeQuery();
		log.info("Retreived Visitor details from DATABASE for username :"
				+ username);
		while (resultSet.next()) {
			visitor.setUserName(resultSet.getString("username"));
			//visitor.setPassword(resultSet.getString("password"));
			visitor.setVisitorId(resultSet.getInt("visitorid"));
			visitor.setFirstName(resultSet.getString("firstname"));
			visitor.setLastName(resultSet.getString("lastname"));
			visitor.setEmail(resultSet.getString("email"));
			visitor.setPhoneNumber(resultSet.getString("phonenumber"));
			visitor.setAddress(resultSet.getString("address"));
			if(resultSet.getInt("isadmin")==1)
			{
				visitor.setAdmin(true);
				event.setAdd(true);
			}
			
		}
		resultSet.close();
		FERSDataConnection.closeConnection();
		return visitor;
	}

	/**
	 *  <br/>
	 *  METHOD DESCRIPTION: <br/>
	 *  DAO method to register visitor to specific event and checking about status
	 *  of visitor to particular event. <br/>
	 *  @return void
	 *  
	 *  @param visitor
	 *  @param eventid
	 *  @param sessionid
	 *  
	 *  @throws ClassNotFoundException
	 *  @throws SQLException
	 *  @throws Exception
	 *  
	 */
	public void registerVisitorToEvent(Visitor visitor, int eventid, int sessionid)
			throws ClassNotFoundException, SQLException, Exception {
		connection = FERSDataConnection.createConnection();
		
		statement = connection.prepareStatement(query.getRegisterQuery());
		statement.setString(1, Integer.toString(visitor.getVisitorId()));
		statement.setString(3, Integer.toString(eventid) );
		statement.setString(2, Integer.toString(sessionid) );
		int status=statement.executeUpdate();
		if (status <= 0)
			throw new FERSGenericException("Records not updated properly",
					new Exception());
		log.info("Visitor id:"+visitor.getVisitorId()+" Event id:"+eventid+" Session id:"+sessionid+" status:"+status);

	}

	/**
	 *  <br/>
	 *  METHOD DESCRIPTION:<br/>
	 *  DAO method to display all the events registered by particular visitor<br/>  
	 *  @return Collection of Object Arrays
	 *  
	 *  @param  visitor
	 *  
	 *  @throws ClassNotFoundException
	 *  @throws SQLException
	 *  
	 */
	public ArrayList<Object[]> registeredEvents(Visitor visitor)throws ClassNotFoundException, SQLException {

		ArrayList<Object[]> eventList = new ArrayList<Object[]>();
	
		 connection = FERSDataConnection.createConnection();
		 statement = connection.prepareStatement(query.getStatusQuery());
		 statement.setString(1, Integer.toString(visitor.getVisitorId()));
		 resultSet=statement.executeQuery();
		 String fname,lname;
		 while(resultSet.next())
		 {
			 Object[] eventObject = new Object[10];

			

			 eventObject[0]=(resultSet.getInt("EVENTID")); 
			 log.info("event id:"+resultSet.getInt("EVENTID"));
			 eventObject[1]=(resultSet.getString("NAME"));
			 log.info("Name:"+resultSet.getString("NAME"));
			 eventObject[2]=(resultSet.getString("DESCRIPTION"));
			 log.info("Description"+resultSet.getString("DESCRIPTION"));
			 eventObject[3]=(resultSet.getString("PLACES"));
			 log.info("Places:"+resultSet.getString("PLACES"));
			 eventObject[4]=(resultSet.getString("DURATION"));
			 log.info("Duration"+resultSet.getString("DURATION"));
			 eventObject[5]=(resultSet.getString("EVENTTYPE"));
			 log.info("Event type:"+resultSet.getString("EVENTTYPE"));
			 fname=resultSet.getString("FIRSTNAME");
			 lname=resultSet.getString("LASTNAME");
			 eventObject[6]=(fname+" "+lname);
			 log.info("fname lname:"+fname+" "+lname);
			 eventObject[7]=(resultSet.getInt("EVENTSESSIONID"));
			 log.info("session id:"+resultSet.getInt("EVENTSESSIONID"));
			 eventList.add(eventObject);
		 }
		
		return eventList;
	}

	/**
	 *  <br/>
	 *  METHOD DESCRIPTION: <br/>
	 *  DAO method to update visitor with additional information <br/>
	 * 
	 *  @return int
	 * 
	 *  @param visitor
	 * 
	 *  @throws ClassNotFoundException
	 *  @throws SQLException
	 *  
	 */
	public int updateVisitor(Visitor visitor) throws ClassNotFoundException,
			SQLException {
		connection = FERSDataConnection.createConnection();
		statement = connection.prepareStatement(query.getUpdateQuery());
		statement.setString(1, visitor.getFirstName());
		statement.setString(2, visitor.getLastName());
		statement.setString(3, visitor.getUserName());
		//statement.setString(4, visitor.getPassword());
		statement.setString(4, visitor.getEmail());
		statement.setString(5, visitor.getPhoneNumber());
		statement.setString(6, visitor.getAddress());
		statement.setInt(7, visitor.getVisitorId());

		int status = statement.executeUpdate();
		log.info("Updating visitor details in Database for Visitor ID :"
				+ visitor.getVisitorId());
		FERSDataConnection.closeConnection();
		return status;
	}
	
	/**
	 * 
	 * @param visitor
	 * @return
	 * @throws FERSGenericException 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int changePassword(Visitor visitor) throws ClassNotFoundException, SQLException {
		int status = -1;
		
		try{
			connection = FERSDataConnection.createConnection();
			
		 if(connection!=null){			
			if(visitor!=null){
				
				if(matchWithOldPwd(visitor, connection)){
					status = -10;
				}else{
					statement = connection.prepareStatement(query.getChangePWDQuery());
					statement.setString(1, visitor.getPassword());
					statement.setInt(2, visitor.getVisitorId());
			
					status = statement.executeUpdate();
					
					log.info("Updating visitor details in Database for Visitor ID :" + visitor.getVisitorId());
				}	
			}else{
				log.error("Visitor details are invalid");
			}
		 }else{
			 throw new SQLException("Connection Error, could not establish connection with database");
		 }
		}finally{
			if(statement!=null)
				statement.close();
			if(connection!=null)
				FERSDataConnection.closeConnection();
		}			
		return status;
	}

	private boolean matchWithOldPwd(Visitor visitor, Connection connection2) throws SQLException{
		String currentPWD = "";				
			
		try{
			statement = connection.prepareStatement(query.getVerifyPWDQuery());								
			statement.setInt(1, visitor.getVisitorId());
	
			resultSet = statement.executeQuery();
			if(resultSet.next())
				currentPWD = resultSet.getString("password");
			
			if(currentPWD.equalsIgnoreCase(visitor.getPassword())){
				log.info("New password must be different from previous password, please choose a different password");
				return true;
			}
			
		}finally{
			if(statement!=null)
				statement.close();			
		}			
		return false;	
	}

	/**
	 *  <br/>
	 *  METHOD DESCRIPTION: <br/>
	 *  DAO method to unregister a visitor from an event that the
	 *  visitor registered for previously <br/>
	 *  @return void
	 *  
	 *  @param  visitor
	 *  @param  eventid
	 *  @param  eventsessionid
	 *  
	 *  @throws ClassNotFoundException
	 *  @throws SQLException
	 *  @throws Exception
	 *  
	 */
	public void unregisterEvent(Visitor visitor, int eventid, int eventsessionid)
			throws ClassNotFoundException, SQLException, Exception {
		
		log.info("inside visitorDAO unregister event");
		int result;
		connection = FERSDataConnection.createConnection();	
		statement = connection.prepareStatement(query.getDeleteEventQuery());								
		statement.setInt(2, visitor.getVisitorId());
		statement.setInt(1, eventsessionid);
		statement.setInt(3, eventid);
		result=statement.executeUpdate();
		log.info("result is"+result);
		if(result>0)
		{
			log.info("event unregistered successfully.  Event id:"+eventid+" Event Session id:"+eventsessionid+" Visitor id:"+visitor.getVisitorId());

		
		
		
		}
		
	}

}