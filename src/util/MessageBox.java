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
	
	public static int confirmation(String text)
	{
		return JOptionPane.showConfirmDialog(null, text, "Confirmation",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
	}
}
