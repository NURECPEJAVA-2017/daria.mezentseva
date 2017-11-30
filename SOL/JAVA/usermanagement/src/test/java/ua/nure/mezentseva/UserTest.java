package ua.nure.mezentseva;

import org.junit.Test;

import junit.framework.TestCase;

public class UserTest extends TestCase {
	@Test
	public void testFullName(){
		User user = new User();
		user.setFirstName("Dasha");
		user.setLastName("Mezentseva");
		String fullName = user.getFullName();
		assertEquals("Dasha Mezentseva", fullName);
	}
	
}
