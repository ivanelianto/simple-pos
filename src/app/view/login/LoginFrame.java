package app.view.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import app.controller.LoginController;
import app.factory.ButtonFactory;
import app.factory.LabelFactory;
import app.factory.TextFieldFactory;
import app.view.custom_component.MyFrame;
import app.view.main.MainFrame;
import util.FilePathHelper;

public class LoginFrame extends MyFrame implements ActionListener, ILoginFrame
{
	JButton btnLogin;
	JLabel lblTitle, lblUsername, lblPassword;
	JTextField txtUsername;
	JPasswordField txtPassword;

	public LoginFrame()
	{
		setSize(500, 300);
		setLocationRelativeTo(null);

		initializeComponent();
	}

	@Override
	public void initializeComponent()
	{
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		JPanel panel = new JPanel(layout);
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EmptyBorder(new Insets(16, 16, 16, 16)));

		/**
		 * Title
		 */
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 7;
		c.weightx = 1;
		panel.add(getTitleLabel(), c);

		/**
		 * Input Fields
		 */
		JPanel usernamePanel = new JPanel(new BorderLayout());
		usernamePanel.setBackground(Color.WHITE);
		usernamePanel.add(getUsernameLabel(), BorderLayout.NORTH);
		usernamePanel.add(Box.createVerticalStrut(5));
		usernamePanel.add(getUsernameField(), BorderLayout.SOUTH);

		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 2;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(usernamePanel, c);

		JPanel passwordPanel = new JPanel(new BorderLayout());
		passwordPanel.setBackground(Color.WHITE);
		passwordPanel.add(getPasswordLabel(), BorderLayout.NORTH);
		passwordPanel.add(Box.createVerticalStrut(5));
		passwordPanel.add(getPasswordField(), BorderLayout.SOUTH);

		c.gridx = 1;
		c.gridy = 4;
		c.gridheight = 2;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(passwordPanel, c);

		c.gridy = 6;
		c.gridheight = 1;
		panel.add(getLoginButton(), c);

		this.add(panel, BorderLayout.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnLogin)
		{
			String errorMessage = LoginController.login(txtUsername.getText(),
					new String(txtPassword.getPassword()));
			
			if (errorMessage.isEmpty())
			{
				this.setVisible(false);
				txtUsername.setText("");
				txtPassword.setText("");
				
				MainFrame frame = new MainFrame();
				frame.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(null, 
						errorMessage, 
						"Stop", 
						JOptionPane.ERROR_MESSAGE);	
			}
		}
	}

	@Override
	public JButton getLoginButton()
	{
		if (btnLogin == null)
		{
			btnLogin = ButtonFactory.getInstance().create("Login");
			btnLogin.addActionListener(this);
		}
		return btnLogin;
	}

	@Override
	public JLabel getTitleLabel()
	{
		if (lblTitle == null)
		{
			lblTitle = new JLabel();
			lblTitle.setIcon(new ImageIcon(FilePathHelper.getAssetsPath() + "/logo.png"));
		}

		return lblTitle;
	}

	@Override
	public JLabel getUsernameLabel()
	{
		if (lblUsername == null)
			lblUsername = LabelFactory.getInstance().create("Username");

		return lblUsername;
	}

	@Override
	public JLabel getPasswordLabel()
	{
		if (lblPassword == null)
			lblPassword = LabelFactory.getInstance().create("Password");

		return lblPassword;
	}

	@Override
	public JTextField getUsernameField()
	{
		if (txtUsername == null)
			txtUsername = TextFieldFactory.getInstance().create();
		
		return txtUsername;
	}

	@Override
	public JPasswordField getPasswordField()
	{
		if (txtPassword == null)
			txtPassword = (JPasswordField) TextFieldFactory.getInstance().create(true);
		
		return txtPassword;
	}

}
