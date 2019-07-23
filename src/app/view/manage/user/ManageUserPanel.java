package app.view.manage.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import app.controller.UserController;
import app.factory.ButtonFactory;
import app.model.User;
import app.view.custom_component.MyImageButton;
import app.view.dialog.user.UserDialog;
import util.FilePathHelper;

public class ManageUserPanel extends JPanel implements ActionListener, IManageUserPanel
{
	private MyImageButton btnAdd;
	
	private ArrayList<UserComponent> userComponents;
	
	public ManageUserPanel()
	{
		this.setLayout(new BorderLayout());
		
		JPanel addButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		addButtonPanel.setOpaque(false);
		addButtonPanel.add(getAddButton());
		this.add(addButtonPanel, BorderLayout.NORTH);
		
		JPanel mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setOpaque(false);
		JScrollPane scrollPane = new JScrollPane(mainPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,  
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(5);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.getViewport().setBackground(Color.WHITE);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		this.add(scrollPane, BorderLayout.CENTER);
		
		this.setBackground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.fill = GridBagConstraints.BOTH;
		
		ArrayList<User> users = UserController.getAllUsers();
		
		for (int i = 0; i < users.size(); i++)
		{
			User user = users.get(i);
			
			UserComponent userItem = new UserComponent();
			
			userItem.getIDButton().setText(user.getId() + "");
			userItem.getNameLabel().setText(user.getName());
			userItem.getUsernameLabel().setText(user.getUsername());
			
			getUserComponents().add(userItem);
			c.gridy = i; 
			mainPanel.add(userItem, c);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnAdd)
		{
			try (UserDialog dialog = new UserDialog())
			{
				dialog.setVisible(true);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}

	@Override
	public MyImageButton getAddButton()
	{
		if (btnAdd == null)
		{
			try
			{
				Image image = ImageIO.read(new File(FilePathHelper.getAssetsPath() + "/add-icon.png"));
				
				btnAdd = ButtonFactory.getInstance().create(
						"Add", 
						MyImageButton.LEFT, 
						image);
				
				btnAdd.setPreferredSize(new Dimension(100, 50));
				
				btnAdd.addActionListener(this);
				
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		return btnAdd;
	}

	@Override
	public ArrayList<UserComponent> getUserComponents()
	{
		if (userComponents == null)
			this.userComponents = new ArrayList<>();
		
		return userComponents;
	}
	
}
