package ua.nure.mezentseva.db;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ua.nure.mezentseva.User;

public class MockUserDao implements Dao<User> {
	
	private Map<Long, User> users = new HashMap<Long, User>();
	private Long id = 0L;
	
	public User create(User entity) throws DatabaseException {
		Long currentId = new Long(++id);
		entity.setId(currentId);
		users.put(currentId, entity);
		return entity;
	}

	public void delete(User entity) throws DatabaseException {
		Long currentId = entity.getId();
		users.remove(currentId);
		
	}

	public void update(User entity) throws DatabaseException {
		Long currentId = entity.getId();
		users.remove(currentId);
		users.put(currentId, entity);
		
	}

	public Collection<User> findAll() throws DatabaseException {
		
		return users.values();
	}

	public User find(Long id) throws DatabaseException {
		
		return users.get(id);
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		// TODO Auto-generated method stub
		
	}

}
