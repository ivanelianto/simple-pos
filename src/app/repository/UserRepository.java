package app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.IntStream;

import app.model.User;

public class UserRepository extends Repository<User>
{
	private final static int ITEM_PER_PAGE = 10;

	private final static String TABLE_NAME = "User";

	private final static ArrayList<User> users = new ArrayList<User>();

	public static ArrayList<User> getAllUsers()
	{
		if (users.size() < 1)
			users.addAll(getUsersPerPage(1));

		return users;
	}

	public static ArrayList<User> getUsersPerPage(int page)
	{
		String query = String.format("SELECT * FROM `User` LIMIT %d,%d", (page - 1) * ITEM_PER_PAGE, ITEM_PER_PAGE);
		ResultSet result = Repository.executeQuery(query);

		ArrayList<User> loadedUsers = Repository.toModel(User.class, result);
		users.addAll(loadedUsers);

		return loadedUsers;
	}

	public static int getTotalUser()
	{
		String query = String.format("SELECT COUNT(*) FROM User");

		ResultSet result = Repository.executeQuery(query);

		try
		{
			result.next();
			return result.getInt(1);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return -1;
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

		ResultSet result = UserRepository.executeUpdate(true, query, user.getName(), user.getUsername(),
				user.getPassword());
		try
		{
			if (result.next())
			{
				int generatedId = result.getInt(1);

				user.setId(generatedId);

				users.add(user);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public static void update(int id, User user)
	{
		int updatedUserIndex = IntStream.range(0, users.size())
				.filter(x -> Integer.valueOf(id).equals(users.get(x).getId())).findFirst().getAsInt();

		users.set(updatedUserIndex, user);

		String query = String.format("UPDATE User SET name=?, username=?,password=? WHERE id=?");
		UserRepository.executeUpdate(query, user.getName(), user.getUsername(), user.getPassword(), id + "");
	}

	public static void delete(int id)
	{
		User deletedUser = users.stream().filter(x -> x.getId() == id).findAny().orElse(null);

		if (deletedUser != null)
			users.remove(deletedUser);

		String query = String.format("DELETE FROM User WHERE ID=?");
		UserRepository.executeUpdate(query, id + "");
	}
}
