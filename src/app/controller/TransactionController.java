package app.controller;

import java.util.ArrayList;

import app.dto.CartDTO;
import app.repository.TransactionRepository;

public class TransactionController
{
	public static void add(ArrayList<CartDTO> transactions)
	{
		TransactionRepository.add(transactions);
	}
}
