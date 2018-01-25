package ua.nure.mezentseva.web;

public class AddServletTest extends EditServletTest {

	@Override
	public void setUp() throws Exception {
		super.setUp();
		createServlet(AddServlet.class);
		
	}

}
