package app.view.manage.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.Callable;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.controller.UserController;
import app.factory.ButtonFactory;
import app.factory.LabelFactory;
import app.model.User;
import app.view.custom_component.MyColor;
import app.view.custom_component.MyImageButton;
import app.view.dialog.MyDialog;
import app.view.dialog.user.UserDialog;
import util.FileHelper;
import util.MessageBox;
import util.Speaker;

public class UserComponent extends JPanel implements ActionListener, IUserComponent
{
	private static final long serialVersionUID = 3341570976079231957L;
	private JButton btnID;
	private JLabel lblName, lblUsername;
	private MyImageButton btnEdit, btnDelete;
	private Callable<Void> onChange;
	private User user;

	public UserComponent(User user, Callable<Void> onChange)
	{
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.add(getIDButton(), BorderLayout.WEST);
		this.setPreferredSize(new Dimension(500, 80));
		this.onChange = onChange;
		this.user = user;
		
		getIDButton().setText(user.getId() + "");
		getNameLabel().setText(user.getName());
		getUsernameLabel().setText(user.getUsername());

		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.setOpaque(false);
		contentPanel.setBorder(new EmptyBorder(9, 10, 9, 0));
		contentPanel.add(getNameLabel(), BorderLayout.NORTH);
		contentPanel.add(getUsernameLabel(), BorderLayout.SOUTH);

		this.add(contentPanel, BorderLayout.CENTER);

		JPanel actionButtonPanel = new JPanel(new FlowLayout());
		actionButtonPanel.setOpaque(false);
		actionButtonPanel.add(getEditButton());
		actionButtonPanel.add(getDeleteButton());

		this.add(actionButtonPanel, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == getEditButton())
		{
			try (UserDialog dialog = new UserDialog(this.user))
			{
				dialog.setVisible(true);
				
				if (dialog.getDialogResult() == MyDialog.UPDATE_MODE)
					onChange.call();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		else if (e.getSource() == getDeleteButton())
		{
			String question = "Are you sure ?";
			Speaker.speak(question);
			int confirmationResult = MessageBox.confirmation(question);

			if (confirmationResult == JOptionPane.YES_OPTION)
			{
				UserController.delete(Integer.parseInt(getIDButton().getText()));

				String message = "User data deleted.";
				Speaker.speak(message);
				MessageBox.success(message);
				
				try
				{
					onChange.call();
				}
				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		}
	}

	@Override
	public JButton getIDButton()
	{
		if (this.btnID == null)
		{
			btnID = ButtonFactory.getInstance().create("", ButtonFactory.ACCENT_STYLE);
			btnID.setPreferredSize(new Dimension(50, 50));

			String currentText = btnID.getText();

			btnID.setText(String.format("<html><center>%s</center></html>", currentText));
		}

		return btnID;
	}

	@Override
	public JLabel getNameLabel()
	{
		if (this.lblName == null)
		{
			lblName = LabelFactory.getInstance().create("");
			lblName.setFont(new Font("Arial", Font.BOLD, 16));
			lblName.setForeground(MyColor.getDarkBlueGrayBackground());
			lblName.setOpaque(false);
		}

		return lblName;
	}

	@Override
	public JLabel getUsernameLabel()
	{
		if (this.lblUsername == null)
		{
			lblUsername = LabelFactory.getInstance().create("");
			lblUsername.setForeground(Color.decode("#616263"));
			lblUsername.setOpaque(false);
		}

		return lblUsername;
	}

	@Override
	public MyImageButton getEditButton()
	{
		if (btnEdit == null)
		{
			try
			{
				Image image = ImageIO.read(new File(FileHelper.getAssetsPath() + "/edit-icon.png"));
				btnEdit = ButtonFactory.getInstance().create("", MyImageButton.LEFT, image,
						ButtonFactory.INVERTED_ACCENT_STYLE);
				btnEdit.setImageSize(24, 24);
				btnEdit.setPreferredSize(new Dimension(50, 50));
				btnEdit.addActionListener(this);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}

		return btnEdit;
	}

	@Override
	public MyImageButton getDeleteButton()
	{
		if (btnDelete == null)
		{
			try
			{
				Image image = ImageIO.read(new File(FileHelper.getAssetsPath() + "/delete-icon.png"));
				btnDelete = ButtonFactory.getInstance().create("", MyImageButton.LEFT, image,
						ButtonFactory.INVERTED_PRIMARY_STYLE);
				btnDelete.setImageSize(24, 24);
				btnDelete.setPreferredSize(new Dimension(50, 50));
				btnDelete.addActionListener(this);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}

		}

		return btnDelete;
	}

}
