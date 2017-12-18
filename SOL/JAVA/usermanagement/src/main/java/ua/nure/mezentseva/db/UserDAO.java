package ua.nure.mezentseva.db;

import java.util.Collection;

import ua.nure.mezentseva.User;


public interface UserDAO {
	User create(User t) throws DatabaseException;
	
	void delete(User t) throws DatabaseException;
	
	void update(User t) throws DatabaseException;
	
	Collection<User> findAll() throws DatabaseException;
	
	User find(Long id) throws DatabaseException;
	
	public void setConnectionFactory(ConnectionFactory connectionFactory);

}
