package app.view.dialog.user;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public interface IUserDialog
{
	JLabel getNameLabel();
	
	JLabel getUsernameLabel();
	
	JLabel getOldPasswordLabel();
	
	JLabel getNewPasswordLabel();
	
	JTextField getNameField();
	
	JTextField getUsernameField();
	
	JTextField getOldPasswordField();
	
	JTextField getNewPasswordField();
	
	JButton getCancelButton();
	
	JButton getSaveButton();
}
