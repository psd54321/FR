package com.festival.adf.businesstier.service;

import java.util.ArrayList;

import com.festival.adf.businesstier.entity.Visitor;
import com.festival.adf.exceptions.FERSGenericException;

public interface VisitorFacade {

	public boolean createVisitor(Visitor visitor);

	public Visitor searchVisitor(String username, String password);

	public void RegisterVisitor(Visitor visitor, int eventid, int sessionid);

	public ArrayList<Object[]> showRegisteredEvents(Visitor visitor);

	public int updateVisitorDetails(Visitor visitor);

	public void unregisterEvent(Visitor visitor, int eventid, int eventsessionid);

	public int changePassword(Visitor visitor) throws FERSGenericException;
}
