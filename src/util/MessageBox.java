package util;

import javax.swing.JOptionPane;

public class MessageBox
{
	public static void success(String text)
	{
		JOptionPane.showMessageDialog(null, text, "Success", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void error(String text)
	{
		JOptionPane.showMessageDialog(null, text, "Stop", JOptionPane.ERROR_MESSAGE);
	}
}
