package ua.nure.mezentseva.db;

import junit.framework.TestCase;
import ua.nure.mezentseva.User;

public class DaoFactoryTest extends TestCase {

	public void testGetUserDao() {
		DaoFactory daoFactory = DaoFactory.getInstance();
		assertNotNull(daoFactory);
		Dao<User> dao = daoFactory.getUserDao();
		assertNotNull(dao);

	}
}
