package ua.nure.mezentseva.db;

import java.beans.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.Callable;


import ua.nure.mezentseva.User;

public class HsqldbUserDAO implements UserDAO {
	
	private static final String SELECT_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users";
	private static final String INSERT_QUERY = "INSERT INTO users (firstname, lastname, dateOfBirth) VALUES (?, ?, ?)";
	private ConnectionFactory connectionFactory;

	public HsqldbUserDAO(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}
	
	public HsqldbUserDAO(){
		
	}

	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}



	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}



	public User create(User user) throws DatabaseException {
		try {
			User resultUser = new User(user);
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(
					 INSERT_QUERY);
			statement.setString( 1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, new Date (user.getDateOfBirth().getTime()));
			int n = statement.executeUpdate();
			if (n !=1) {
				throw new DatabaseException("Number of the inserted rows:" + n);
			}
				CallableStatement callableStatement = connection.prepareCall("call IDENTITY()"); 
				ResultSet id = callableStatement.executeQuery();
				if (id.next()){
					resultUser.setId(new Long(id.getLong(1)));
					
				}
				id.close();
				callableStatement.close();
				statement.close();
				connection.close();
				return resultUser;
				
				
				
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
		
	}

	public void delete(User t) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	public void update(User t) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	public Collection<User> findAll() throws DatabaseException {
		
		Collection<User> result = new LinkedList<User>();
		try{
			Connection connection = connectionFactory.createConnection();
			Statement statement = (Statement) connection.createStatement();
			ResultSet resultSet = ((java.sql.Statement) statement).executeQuery(SELECT_QUERY);
			
			while(resultSet.next()){
				User user = new User();
				user.setId(new Long(resultSet.getLong(1)));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirth(resultSet.getDate(4));
				result.add(user);
			}
			resultSet.close();
			((java.sql.Statement) statement).close();
			connection.close();
		}
		catch(SQLException e){
			throw new DatabaseException(e);
		}
		return result;
	}

	public User find(Long id) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

}
