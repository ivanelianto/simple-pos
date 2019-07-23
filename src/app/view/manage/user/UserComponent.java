package app.view.manage.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.factory.ButtonFactory;
import app.factory.LabelFactory;
import app.view.custom_component.MyColor;
import app.view.custom_component.MyImageButton;
import util.FilePathHelper;

public class UserComponent extends JPanel implements IUserComponent
{
	private JButton btnID;
	private JLabel lblName, lblUsername;
	private MyImageButton btnEdit, btnDelete;

	public UserComponent()
	{
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.add(getIDButton(), BorderLayout.WEST);

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
	public JButton getIDButton()
	{
		if (this.btnID == null)
		{
			btnID = ButtonFactory.getInstance().create("");
			btnID.setPreferredSize(new Dimension(50, 50));
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
				Image image = ImageIO.read(new File(FilePathHelper.getAssetsPath() + "/edit-icon.png"));
				btnEdit = ButtonFactory.getInstance().create("", MyImageButton.LEFT, image);
				btnEdit.setBackground(MyColor.getAccentBackground());
				btnEdit.setImageSize(24, 24);
				btnEdit.setPreferredSize(new Dimension(50, 50));
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
				Image image = ImageIO.read(new File(FilePathHelper.getAssetsPath() + "/delete-icon.png"));
				btnDelete = ButtonFactory.getInstance().create("", MyImageButton.LEFT, image);
				btnDelete.setImageSize(24, 24);
				btnDelete.setPreferredSize(new Dimension(50, 50));
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
			
		}

		return btnDelete;
	}

}