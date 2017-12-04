package ua.nure.mezentseva;

import org.junit.Test;
import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

public class UserTest extends TestCase {
	
	private static final int ETALONE_AGE = 2017-1999;
	private User user;
	private Date dateOfBirth;
	
	protected void setUp() throws Exception{
		super.setUp();
		user = new User();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(1999, Calendar.DECEMBER,03);
		dateOfBirth = calendar.getTime();
		
	}
	
	@Test
	public void testFullName(){
		
		user.setFirstName("Dasha");
		user.setLastName("Mezentseva");
		String fullName = user.getFullName();
		assertEquals("Dasha Mezentseva", fullName);
	}
	
	
	@Test
	public void testGetAge(){
		user.setDateOfBirth(dateOfBirth);
		assertEquals(ETALONE_AGE, user.getAge());
		
	}
	
}
