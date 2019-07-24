package app.repository;

import java.sql.ResultSet;
import java.util.ArrayList;

import app.model.User;

public class UserRepository extends Repository<User>
{
	private final static String TABLE_NAME = "User";

	public static ArrayList<User> getAll()
	{
		ResultSet result = Repository.getAll(TABLE_NAME);
		return Repository.toModel(User.class, result);
	}

	public static ArrayList<User> findUserByUsername(String username)
	{
		String query = String.format("SELECT * FROM %s WHERE username=?", TABLE_NAME);
		ResultSet result = UserRepository.executeQuery(query, username);

		return Repository.toModel(User.class, result);
	}

	public static ArrayList<User> findUserByUsernameAndPassword(String username, String password)
	{
		String query = String.format("SELECT * FROM %s WHERE username=? AND password=?", TABLE_NAME);
		ResultSet result = UserRepository.executeQuery(query, username, password);

		return Repository.toModel(User.class, result);
	}

	public static User findUserById(int id)
	{
		ResultSet resultSet = Repository.get("user", id + "");
		return Repository.toModel(User.class, resultSet).get(0);
	}

	public static void add(User user)
	{
		String query = String.format("INSERT INTO User (name, username, password) VALUES(?, ?, ?)");
		UserRepository.executeUpdate(query, user.getName(), user.getUsername(), user.getPassword());
	}

	public static void update(int id, User user)
	{
		String query = String.format("UPDATE User SET name=?, username=?, password=? WHERE id=?");

		UserRepository.executeUpdate(query, user.getName(), user.getUsername(), user.getPassword(), id + "");
	}

	public static void delete(int id)
	{
		String query = String.format("DELETE FROM User WHERE ID=?");

		UserRepository.executeUpdate(query, id + "");
	}
}
