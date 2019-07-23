package app.controller;

import java.util.ArrayList;

import app.model.User;
import app.repository.UserRepository;

public class UserController
{
	public static ArrayList<User> getAllUsers()
	{
		return UserRepository.getAll();
	}
	
	public static void add(User user)
	{
		
	}
	
	public static void update(int id, User user)
	{
		
	}
}
