package app.controller;

import java.util.ArrayList;

import app.model.User;
import app.repository.UserRepository;
import util.Encryptor;

public class UserController
{
	public static ArrayList<User> getAllUsers()
	{
		return UserRepository.getAllUsers();
	}
	
	public static ArrayList<User> getUsersPerPage(int page)
	{
		return UserRepository.getUsersPerPage(page);
	}
	
	public static int getTotalUser()
	{
		return UserRepository.getTotalUser();
	}

	public static void add(String name, String username, String password)
	{
		String hashedPassword = Encryptor.hashSHA256(password);

		User user = new User();
		user.setName(name);
		user.setUsername(username);
		user.setPassword(hashedPassword);
		UserRepository.add(user);
	}

	public static void update(int id, String name, String username, String newPassword, boolean updatePassword)
	{
		String hashedPassword = Encryptor.hashSHA256(newPassword);

		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setUsername(username);
		user.setPassword(hashedPassword);
		UserRepository.update(id, user, updatePassword);
	}

	public static void delete(int id)
	{
		UserRepository.delete(id);
	}
}
