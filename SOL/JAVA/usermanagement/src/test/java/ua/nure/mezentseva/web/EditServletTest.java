package ua.nure.mezentseva.web;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ua.nure.mezentseva.User;

public class EditServletTest extends MockServletTestCase {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
		createServlet(EditServlet.class);
		
	}
	
	@Test
	public void testEit(){
		Date date = new Date();
		User user = new User(156L,"Dasha", "Mezentseva", date);
		getMockUserDao().expect("update", user);
		
		addRequestParameter("id", "156");
		addRequestParameter("firstname", "Dasha");
		addRequestParameter("lastname", "Mezentseva");
		addRequestParameter("date", DateFormat.getInstance().format(date));
		addRequestParameter("okButton","Ok");
		doPost();
		
	}
	
	@Test
	public void testEitEmptyFirstName(){
		Date date = new Date();
		
		
		addRequestParameter("id", "156");
		
		addRequestParameter("lastname", "Mezentseva");
		addRequestParameter("date", DateFormat.getInstance().format(date));
		addRequestParameter("okButton","Ok");
		doPost();
		
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		
		assertNotNull(errorMessage);
		assertSame("First name is empty", errorMessage);
		
	}
	
	@Test
	public void testDateIncorrect(){
		Date date = new Date();
		
		
		addRequestParameter("id", "156");
		addRequestParameter("firstname", "Dasha");
		addRequestParameter("lastname", "Mezentseva");
		addRequestParameter("date", "gbfrbvs");
		addRequestParameter("okButton","Ok");
		doPost();
		
		String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
		
		assertNotNull(errorMessage);
		assertSame("Date format is incorrect", errorMessage);
		
		
	}

}
