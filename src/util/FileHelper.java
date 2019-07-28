package util;

import java.io.File;
import java.io.FileWriter;

import app.dto.CartDTO;

public class FileHelper
{
	public static String getAssetsPath()
	{
		try
		{
			File file = new File(System.getProperty("user.dir") + "/assets");
			
			if (!file.exists())
			{
				MessageBox.error("Missing assets. Please ask IV18-1 for assets file. Application will be closed.");
				
				System.exit(0);
			}
		}
		catch (Exception ex)
		{
			
		}
		
		return System.getProperty("user.dir") + "/assets";
	}
	
	public static String getAssetsPath(String fileName)
	{
		String path = getAssetsPath() + "/" + fileName;
		
		try
		{
			File file = new File(path);
			
			if (!file.exists())
			{
				MessageBox.error("Missing assets. Please ask IV18-1 for assets file. Application will be closed.");
				
				System.exit(0);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		return path;
	}
	
	public static String getProductsPath()
	{
		return System.getProperty("user.dir") + "/products";
	}

	public static void writeFile(String path, String data, boolean encodeToBase64String)
	{
		try
		{
			File directory = new File(path);
			
			if (!directory.exists())
				directory.mkdir();
			
			String id = data.split("#")[CartDTO.ID_INDEX];
			
			FileWriter writer = new FileWriter(String.format("%s/%s.iv", path, id));
			
			if (encodeToBase64String)
				data = Encryptor.encodeBase64(data);
			
			writer.write(data);
			writer.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
