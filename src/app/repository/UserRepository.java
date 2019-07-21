package app.repository;

import java.sql.ResultSet;
import java.util.ArrayList;

import app.model.User;

public class UserRepository extends Repository<User>
{
	public static ArrayList<User> findUserByUsernameAndPassword(String username, String password)
	{
		String query = "SELECT * FROM User WHERE username=? AND password=?";
		ResultSet result = UserRepository.executeStatement(query, username, password);
		
		return toModel(User.class, result);
	}
}
