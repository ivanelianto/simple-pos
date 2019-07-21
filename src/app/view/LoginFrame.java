package app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import app.factory.ButtonFactory;
import app.factory.LabelFactory;
import app.factory.TextFieldFactory;

public class LoginFrame extends MyFrame
{
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
		JLabel lblTitle = new JLabel();
		lblTitle.setIcon(new ImageIcon(System.getProperty("user.dir") + "\\assets\\logo.png"));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 7;
		c.weightx = 1;
		panel.add(lblTitle, c);
		
		/**
		 * Input Fields
		 */
		JPanel usernamePanel = new JPanel(new BorderLayout());
		usernamePanel.setBackground(Color.WHITE);
		
		JLabel lblUsername = LabelFactory.getInstance().create("Username");
		usernamePanel.add(lblUsername, BorderLayout.NORTH);
		
		usernamePanel.add(Box.createVerticalStrut(5));

		JTextField txtUsername = TextFieldFactory.getInstance().create();
		usernamePanel.add(txtUsername, BorderLayout.SOUTH);
		
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 2;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(usernamePanel, c);
		
		
		JPanel passwordPanel = new JPanel(new BorderLayout());
		passwordPanel.setBackground(Color.WHITE);
		
		JLabel lblPassword = LabelFactory.getInstance().create("Password");
		passwordPanel.add(lblPassword, BorderLayout.NORTH);
		
		passwordPanel.add(Box.createVerticalStrut(5));
		
		JPasswordField txtPassword = (JPasswordField) TextFieldFactory.getInstance().create(true);
		passwordPanel.add(txtPassword, BorderLayout.SOUTH);
		
		c.gridx = 1;
		c.gridy = 4;
		c.gridheight = 2;
		c.insets = new Insets(10, 10, 10, 10);
		panel.add(passwordPanel, c);
		
		JButton btnLogin = ButtonFactory.getInstance().create("Login");
		c.gridy = 6;
		c.gridheight = 1;
		panel.add(btnLogin, c);
		
		this.add(panel, BorderLayout.CENTER);
	}
	
}
