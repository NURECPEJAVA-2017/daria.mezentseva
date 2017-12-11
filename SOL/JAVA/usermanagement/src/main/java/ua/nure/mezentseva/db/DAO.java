package ua.nure.mezentseva.db;

import java.util.Collection;

public interface DAO<T> {
	T create(T t) throws DatabaseException;
	
	void delete(T t) throws DatabaseException;
	
	void update(T t) throws DatabaseException;
	
	Collection<T> findAll() throws DatabaseException;
	
	T find(Long id) throws DatabaseException;
	
	

}
