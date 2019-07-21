package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher
{
	private Hasher() 
	{
		
	}
	
	public static String hash(String plainText)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] messageDigest = md.digest(plainText.getBytes());

			BigInteger no = new BigInteger(1, messageDigest);
			String hashText = no.toString(16);

			while (hashText.length() < 32)
			{
				hashText = "0" + hashText;
			}
			
			return hashText;
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
