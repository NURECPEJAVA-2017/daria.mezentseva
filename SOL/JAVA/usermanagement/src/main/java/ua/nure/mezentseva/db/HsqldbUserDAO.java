package ua.nure.mezentseva.db;

import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import ua.nure.mezentseva.User;

class HsqldbUserDAO implements UserDAO {

	private static final String SELECT_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users";
	private static final String INSERT_QUERY = "INSERT INTO users (firstname, lastname, dateOfBirth) VALUES (?, ?, ?)";
	private static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";
	private static final String UPDATE_QUERY = "UPDATE users SET firstname=?, lastname=?, dateofbirth =? WHERE id = ?";
	private ConnectionFactory connectionFactory;

	public HsqldbUserDAO(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public HsqldbUserDAO() {

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
			PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, new Date(user.getDateOfBirth().getTime()));
			int n = statement.executeUpdate();
			if (n != 1) {
				throw new DatabaseException("Number of the inserted rows:" + n);
			}
			CallableStatement callableStatement = connection.prepareCall("call IDENTITY()");
			ResultSet id = callableStatement.executeQuery();
			if (id.next()) {
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

	public void delete(User user) throws DatabaseException {
		try {
			//User resultUser = new User(user);
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
			statement.setLong(1, user.getId());
			int n = statement.executeUpdate();
			if (n != 1) {
				throw new DatabaseException("Number of the deleted rows:" + n);
			}
			statement.close();
			connection.close();
			//return resultUser;

		} catch (SQLException e) {
			throw new DatabaseException(e);
		}

	}

	public void update(User user) throws DatabaseException {
		try {
			//User resultUser = new User(user);
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
			statement.setString( 1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, new Date (user.getDateOfBirth().getTime()));
			statement.setLong(4, user.getId());
			int n = statement.executeUpdate();
			if (n != 1) {
				throw new DatabaseException("Number of the updated rows:" + n);
			}

			statement.close();
			connection.close();
			//return resultUser;

		} catch (SQLException e) {
			throw new DatabaseException(e);
		}

	}
	
	public Collection<User> findAll() throws DatabaseException {

		Collection<User> result = new LinkedList<User>();
		try {
			Connection connection = connectionFactory.createConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_QUERY);

			while (resultSet.next()) {
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
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
		return result;
	}

	public User find(Long id) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

}
