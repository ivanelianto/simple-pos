package util;

import java.io.File;
import java.io.FileWriter;

import app.dto.CartDTO;

public class FileHelper
{
	public static String getAssetsPath()
	{
		return System.getProperty("user.dir") + "/assets";
	}
	
	public static String getProductsPath()
	{
		return System.getProperty("user.dir") + "/products";
	}

	public static void writeFile(String path, String data)
	{
		try
		{
			File directory = new File(path);
			
			if (!directory.exists())
				directory.mkdir();
			
			String id = data.split("#")[CartDTO.ID_INDEX];
			
			FileWriter writer = new FileWriter(String.format("%s/%s.iv", path, id));
			writer.write(data);
			writer.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
