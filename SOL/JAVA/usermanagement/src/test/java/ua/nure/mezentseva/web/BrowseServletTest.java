package ua.nure.mezentseva.web;

import java.util.Date;

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
		User user = new User (154L, "sd", "fsedf", new Date());
		
	}

}
