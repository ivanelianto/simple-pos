package app.repository;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.Driver;

import app.annotation.Table;

public class Repository<T>
{
	private final static String DB_SERVER = "localhost";
	private final static int DB_PORT = 3306;
	private final static String DB_NAME = "javah2bp";
	private final static String DB_USERNAME = "root";
	private final static String DB_PASSWORD = "";
	private static String connectionString = String.format("jdbc:mysql://%s:%d/%s", DB_SERVER, DB_PORT, DB_NAME);

	private static Connection conn;

	private static Connection getConnection()
	{
		if (conn == null)
		{
			try
			{
				DriverManager.registerDriver(new Driver());

				conn = (Connection) DriverManager.getConnection(connectionString, DB_USERNAME, DB_PASSWORD);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		return conn;
	}

	protected static ResultSet executeStatement(String query, String... params)
	{
		try
		{
			PreparedStatement statement = getConnection().prepareStatement(query);

			for (int i = 1; i <= params.length; i++)
			{
				statement.setString(i, params[i - 1]);
			}

			return statement.executeQuery();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	public static ResultSet getAll(String tableName)
	{
		String query = "SELECT * FROM ?";

		return executeStatement(query, tableName);
	}

	public static ResultSet get(String tableName, String id)
	{
		String query = "SELECT * FROM ? WHERE id=?";

		return executeStatement(query, tableName, id);
	}

	public static <T> ArrayList<T> toModel(Class<T> type, ResultSet resultSet)
	{
		ArrayList<T> models = new ArrayList<>();

		try
		{
			while (resultSet.next())
			{
				T classType = type.newInstance();

				Field[] fields = classType.getClass().getDeclaredFields();
				for (Field field : fields)
				{
					field.setAccessible(true);
					Table column = field.getAnnotation(Table.class);

					Object value = resultSet.getObject(column.columnName());

					Class<?> fieldType = field.getType();

					if (isPrimitive(fieldType))
					{
						Class<?> boxed = boxPrimitiveClass(fieldType);

						value = boxed.cast(value);
					}

					field.set(classType, value);
				}

				models.add(classType);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return models;
	}

	private static boolean isPrimitive(Class<?> type)
	{
		return (type == int.class || type == long.class || type == double.class || type == float.class
				|| type == boolean.class || type == byte.class || type == char.class || type == short.class);
	}

	private static Class<?> boxPrimitiveClass(Class<?> type)
	{
		if (type == int.class)
		{
			return Integer.class;
		}
		else if (type == long.class)
		{
			return Long.class;
		}
		else if (type == double.class)
		{
			return Double.class;
		}
		else if (type == float.class)
		{
			return Float.class;
		}
		else if (type == boolean.class)
		{
			return Boolean.class;
		}
		else if (type == byte.class)
		{
			return Byte.class;
		}
		else if (type == char.class)
		{
			return Character.class;
		}
		else if (type == short.class)
		{
			return Short.class;
		}
		else
		{
			String string = "Class '" + type.getName() + "' is not a primitive";
			throw new IllegalArgumentException(string);
		}
	}
}
