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
	
	public static ArrayList<User> findUserByUsernameAndPassword(String username, String password)
	{
		String query = String.format("SELECT * FROM %s WHERE username=? AND password=?", TABLE_NAME);
		ResultSet result = UserRepository.executeStatement(query, username, password);
		
		return toModel(User.class, result);
	}
}
