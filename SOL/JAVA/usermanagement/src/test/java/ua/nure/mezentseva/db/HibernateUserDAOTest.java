package ua.nure.mezentseva.db;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.hibernate.Session;
import org.junit.Test;

import ua.nure.mezentseva.User;

public class HibernateUserDAOTest extends DatabaseTestCase {
	private HibernateUserDAO dao;
	private Session currentSession;
	
    private static final String TEST_LASTNAME = "Gates";
    private static final String TEST_NAME = "Bill";
    private static final Long TEST_FIND_DELETE_ID = 1000L;
    private static final String UPDATED_NAME = "Helen";
    
	
	
	protected void setUp() throws Exception {
		
		dao = new HibernateUserDAO();
		currentSession = HibernateUtils.getSessionFactory().getCurrentSession();
		currentSession.beginTransaction();
		super.setUp();
		currentSession.getTransaction().commit();
		
		
	}
	
	
	@Test
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
	
	@Test
    public void testFind() throws DatabaseException {
        User userToCheck = dao.find(TEST_FIND_DELETE_ID);
        assertNotNull(userToCheck);
        assertEquals(TEST_NAME, userToCheck.getFirstName());
        assertEquals(TEST_LASTNAME, userToCheck.getLastName());
    }
	
	
	
	
	
	
	@Test
	public void testDelete(){
		User deletedUser = new User();
        deletedUser.setId(TEST_FIND_DELETE_ID);
        try {
            dao.delete(deletedUser);
            dao.find(TEST_FIND_DELETE_ID);
            fail();
        } catch (DatabaseException e) {
            assertThat(e.getMessage(), containsString(TEST_FIND_DELETE_ID.toString()));
        }
	}
	
	
	@Test
	public void testUpdate() throws DatabaseException {
		User glenElg = dao.find(TEST_FIND_DELETE_ID);
        assertNotNull(glenElg);
        glenElg.setFirstName(UPDATED_NAME);
        dao.update(glenElg);
        User updatedUser = dao.find(TEST_FIND_DELETE_ID);
        assertEquals(glenElg.getFirstName(), updatedUser.getFirstName());
		
	}
	
	
	@Test
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

		return new DatabaseConnection(currentSession.connection());
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
