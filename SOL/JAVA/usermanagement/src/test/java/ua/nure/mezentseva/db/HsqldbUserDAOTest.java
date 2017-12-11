package ua.nure.mezentseva.db;

import java.util.Collection;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import ua.nure.mezentseva.User;

public class HsqldbUserDAOTest extends DatabaseTestCase {
	private HsqldbUserDAO dao;
	private ConnectionFactory connectionFactory;
	
	
	protected void setUp() throws Exception {
		super.setUp();
		dao = new HsqldbUserDAO(connectionFactory);
	}
	
	
	//@Test
	public void testCreate(){
		User user = new User();
		user.setFirstName("Dasha");
		user.setLastName("Mezentseva");
		user.setDateOfBirth(new Date());
		assertNull(user.getId());
		try {
			user = dao.create(user);
		} catch (DatabaseException e) {
			
			e.printStackTrace();
			fail(e.getMessage());
		}
		assertNotNull(user);
		assertNotNull(user.getId());
		
	}
	
	public void testFindAll(){
		try {
			Collection<User> collection = dao.findAll();
			assertNotNull(collection);
			assertEquals(2, collection.size());
		} catch (DatabaseException e) {
			
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	

	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImpl();
		return new DatabaseConnection(connectionFactory.createConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(
				getClass()
				.getClassLoader()
				.getResourceAsStream("usersDataSet.xml"));
		return dataSet;
	}

}
