package app.view.dialog.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.controller.UserController;
import app.factory.ButtonFactory;
import app.factory.LabelFactory;
import app.factory.TextFieldFactory;
import app.model.User;
import app.validator.Validator;
import app.validator.rule.IRule;
import app.validator.rule.user.NameRule;
import app.validator.rule.user.PasswordConfirmRule;
import app.validator.rule.user.PasswordRule;
import app.validator.rule.user.UniqueUsernameRule;
import app.validator.rule.user.UsernameRule;
import app.view.dialog.MyDialog;
import util.MessageBox;
import util.Speaker;

public class UserDialog extends MyDialog implements ActionListener, AutoCloseable, IUserDialog
{
	private JLabel lblName;
	private JLabel lblUsername;
	private JLabel lblOldPassword;
	private JLabel lblNewPassword;
	private JTextField txtName;
	private JTextField txtUsername;
	private JTextField txtOldPassword;
	private JTextField txtNewPassword;
	private JButton btnCancel;
	private JButton btnSave;
	private JPanel mainPanel;
	private GridBagConstraints c = new GridBagConstraints();

	private User user;

	private int currentMode = INSERT_MODE;
	
	public UserDialog()
	{
		initializeComponent();
	}

	public UserDialog(User user)
	{
		this.user = user;
		currentMode = UPDATE_MODE;
		initializeComponent();
		getNameField().setText(user.getName());
		getUsernameField().setText(user.getUsername());
	}

	private void initializeComponent()
	{
		getRootPane().setDefaultButton(getSaveButton());
		
		mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBackground(Color.WHITE);
		this.add(mainPanel, BorderLayout.CENTER);

		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;

		JPanel namePanel = getWrappedInput(getNameLabel(), getNameField());
		mainPanel.add(namePanel, c);

		JPanel usernamePanel = getWrappedInput(getUsernameLabel(), getUsernameField());
		mainPanel.add(usernamePanel, c);

		JLabel passwordLabel = getNewPasswordLabel();

		if (currentMode == UPDATE_MODE)
		{
			JPanel oldPasswordPanel = getWrappedInput(getOldPasswordLabel(), getOldPasswordField());
			mainPanel.add(oldPasswordPanel, c);
		}
		else
		{
			passwordLabel.setText("Password");
		}

		JPanel newPasswordPanel = getWrappedInput(getNewPasswordLabel(), getNewPasswordField());
		mainPanel.add(newPasswordPanel, c);

		JPanel actionButtonPanel = new JPanel(new FlowLayout());
		actionButtonPanel.setOpaque(false);
		actionButtonPanel.add(getCancelButton());
		actionButtonPanel.add(getSaveButton());
		mainPanel.add(actionButtonPanel, c);
	}

	private JPanel getWrappedInput(JComponent labelComponent, JComponent fieldComponent)
	{
		JPanel panel = new JPanel(new BorderLayout());
		panel.setOpaque(false);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.add(labelComponent, BorderLayout.NORTH);
		panel.add(fieldComponent, BorderLayout.CENTER);
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == getSaveButton())
		{
			String message = "";

			if (currentMode == INSERT_MODE)
			{
				boolean isValid = Validator.validate(new NameRule(getNameField().getText()),
						new UsernameRule(getUsernameField().getText()),
						new UniqueUsernameRule(getUsernameField().getText()),
						new PasswordRule(getNewPasswordField().getText()));

				if (isValid)
				{
					String name = getNameField().getText();
					String username = getUsernameField().getText();
					String password = getNewPasswordField().getText();

					UserController.add(name, username, password);
					message = "New user added.";
					this.dialogResult = INSERT_MODE;
				}
				else
				{
					String errorMessage = Validator.getErrorMessages().get(0);
					Speaker.speak(errorMessage);
					MessageBox.error(errorMessage);
					return;
				}
			}
			else
			{
				boolean updatePassword = false;
				
				ArrayList<IRule> rules = new ArrayList<>();

				rules.add(new NameRule(getNameField().getText()));
				rules.add(new UsernameRule(getUsernameField().getText()));

				if (!getNewPasswordField().getText().isEmpty() || !getOldPasswordField().getText().isEmpty())
				{
					updatePassword = true;
					rules.add(new PasswordRule(getOldPasswordField().getText()));
					rules.add(new PasswordConfirmRule(user.getId(), getOldPasswordField().getText(),
							getNewPasswordField().getText()));
					rules.add(new PasswordRule(getNewPasswordField().getText()));
				}

				boolean isValid = Validator.validate(rules.toArray(new IRule[rules.size()]));

				if (isValid)
				{
					UserController.update(user.getId(), getNameField().getText(), getUsernameField().getText(),
							getNewPasswordField().getText(), updatePassword);
					message = "User data updated.";
					this.dialogResult = UPDATE_MODE;
				}
				else
				{
					String errorMessage = Validator.getErrorMessages().get(0);
					Speaker.speak(errorMessage);
					MessageBox.error(errorMessage);
					return;
				}
			}

			Speaker.speak(message);
			MessageBox.success(message);

			try
			{
				this.close();
				
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}

		}
		else if (e.getSource() == getCancelButton())
		{
			try
			{
				this.dialogResult = CANCELED;
				this.close();
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void close() throws Exception
	{
		this.dispose();
	}

	@Override
	public JLabel getNameLabel()
	{
		if (lblName == null)
		{
			lblName = LabelFactory.getInstance().create("Name");
		}

		return lblName;
	}

	@Override
	public JLabel getUsernameLabel()
	{
		if (lblUsername == null)
		{
			lblUsername = LabelFactory.getInstance().create("Username");
		}

		return lblUsername;
	}

	@Override
	public JLabel getOldPasswordLabel()
	{
		if (lblOldPassword == null)
		{
			lblOldPassword = LabelFactory.getInstance().create("Old Password");
		}

		return lblOldPassword;
	}

	@Override
	public JLabel getNewPasswordLabel()
	{
		if (lblNewPassword == null)
		{
			lblNewPassword = LabelFactory.getInstance().create("New Password");
		}

		return lblNewPassword;
	}

	@Override
	public JTextField getNameField()
	{
		if (txtName == null)
		{
			txtName = TextFieldFactory.getInstance().create(150, 25);
		}

		return txtName;
	}

	@Override
	public JTextField getUsernameField()
	{
		if (txtUsername == null)
		{
			txtUsername = TextFieldFactory.getInstance().create(150, 25);
		}

		return txtUsername;
	}

	@Override
	public JTextField getOldPasswordField()
	{
		if (txtOldPassword == null)
		{
			txtOldPassword = TextFieldFactory.getInstance().create(true, 150, 25);
		}

		return txtOldPassword;
	}

	@Override
	public JTextField getNewPasswordField()
	{
		if (txtNewPassword == null)
		{
			txtNewPassword = TextFieldFactory.getInstance().create(true, 150, 25);
		}

		return txtNewPassword;
	}

	@Override
	public JButton getCancelButton()
	{
		if (btnCancel == null)
		{
			btnCancel = ButtonFactory.getInstance().create("Cancel", ButtonFactory.INVERTED_ACCENT_STYLE);
			btnCancel.setPreferredSize(new Dimension(100, 35));
			btnCancel.addActionListener(this);
		}

		return btnCancel;
	}

	@Override
	public JButton getSaveButton()
	{
		if (btnSave == null)
		{
			btnSave = ButtonFactory.getInstance().create("Save", ButtonFactory.PRIMARY_STYLE);
			btnSave.setPreferredSize(new Dimension(100, 35));
			btnSave.addActionListener(this);
		}

		return btnSave;
	}

}
