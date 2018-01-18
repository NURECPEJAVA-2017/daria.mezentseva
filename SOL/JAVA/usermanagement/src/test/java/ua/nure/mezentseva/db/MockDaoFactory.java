package ua.nure.mezentseva.db;

import com.mockobjects.dynamic.Mock;

import ua.nure.mezentseva.User;

public class MockDaoFactory extends DaoFactory {

	private Mock MockUserDao;
	
	public Mock getMockUserDao() {
		return MockUserDao;
	}
	
	public MockDaoFactory(){
		MockUserDao = new Mock(Dao.class);
	}
	
	@Override
	public Dao<User> getUserDao() {
		
		return (Dao<User>) MockUserDao.proxy();
	}

}
