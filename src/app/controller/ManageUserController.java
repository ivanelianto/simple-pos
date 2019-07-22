package app.controller;

import java.util.ArrayList;

import app.model.User;
import app.repository.UserRepository;

public class ManageUserController
{
	public static ArrayList<User> getAllUsers()
	{
		return UserRepository.getAll();
	}
}
