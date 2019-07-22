package app.view.login;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public interface ILoginFrame
{
	JButton getLoginButton();

	JLabel getTitleLabel();

	JLabel getUsernameLabel();

	JLabel getPasswordLabel();

	JTextField getUsernameField();

	JPasswordField getPasswordField();
}
