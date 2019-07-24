package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class Encryptor
{
	private Encryptor()
	{

	}

	public static String hashSHA256(String plainText)
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
	
	public static String encodeBase64(String plainText)
	{
		Encoder encoder = Base64.getEncoder();
		return new String(encoder.encode(plainText.getBytes()));
	}
	
	public static String decodeBase64(String encodedText)
	{
		Decoder decoder = Base64.getDecoder();
		return new String(decoder.decode(encodedText));
	}
}
