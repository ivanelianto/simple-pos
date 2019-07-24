package app.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import app.dto.CartDTO;
import main.Main;
import util.Formatter;

public class TransactionRepository extends Repository<CartDTO>
{
	public static void add(ArrayList<CartDTO> products)
	{
		int headerId = -1;

		String headerQuery = String.format("INSERT INTO transaction_header (occurrence, user_id) VALUES (?, ?)");

		ResultSet generatedKeys = ProductRepository.executeUpdate(true, headerQuery,
				Formatter.formatToSQLDate(new Date()), Main.currentUser.getId() + "");

		try
		{
			if (generatedKeys.next())
			{
				headerId = generatedKeys.getInt(1);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		String detailQuery = String
				.format("INSERT INTO transaction_detail (header_id, product_id, quantity, price) VALUES(?, ?, ?, ?)");

		for (CartDTO product : products)
		{
			ProductRepository.executeUpdate(detailQuery, String.valueOf(headerId), String.valueOf(product.getId()),
					String.valueOf(product.getQuantity()), String.valueOf(product.getPrice()));
		}
	}
}
