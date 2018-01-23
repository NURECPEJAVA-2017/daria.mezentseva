package ua.nure.mezentseva.web;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import ua.nure.mezentseva.User;

public class BrowseServletTest extends MockServletTestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		createServlet(BrowseServlet.class);
		
	}
	
	@Test
	public void TestBrowse(){
		User user = new User (154L, "Dasha", "Mezentseva", new Date());
		List<User> list = Collections.singletonList(user);
		getMockUserDao().expectAndReturn("findAll", list);
		doGet();
		Collection<User> attrUsers = (Collection<User>)getWebMockObjectFactory().getMockSession().getAttribute("users");
		assertNotNull(attrUsers);
		assertSame(list, attrUsers);
		
	}
	
	@Test
	public void TestEdit(){
		User user = new User (154L, "Dasha", "Mezentseva", new Date());
		getMockUserDao().expectAndReturn("find", 154L, user);
		addRequestParameter("editButton", "Edit");
		addRequestParameter("id", "154");
		doPost();
		User userInSession = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
		assertNotNull(userInSession);
		assertSame(user,userInSession);
		
	}
	
	@Test
	public void testEditWithoutId(){
		addRequestParameter("editButton", "Edit");
		doPost();
		assertNotNull(getWebMockObjectFactory().getMockRequest().getAttribute("error"));
		
	}

}
