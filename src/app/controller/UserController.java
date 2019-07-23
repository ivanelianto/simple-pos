package app.controller;

import java.util.ArrayList;

import app.model.User;
import app.repository.UserRepository;
import util.Hasher;

public class UserController
{
	public static ArrayList<User> getAllUsers()
	{
		return UserRepository.getAll();
	}
	
	public static void add(String name, String username, String password)
	{
		String hashedPassword = Hasher.hash(password);
		
		User user = new User();
		user.setName(name);
		user.setUsername(username);
		user.setPassword(hashedPassword);
		UserRepository.add(user);
	}
	
	public static void update(int id, User user)
	{
		
	}
}
