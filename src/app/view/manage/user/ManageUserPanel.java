package app.view.manage.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;

import app.controller.ManageUserController;
import app.model.User;

public class ManageUserPanel extends JPanel implements IManageUserPanel
{
	private ArrayList<UserComponent> userComponents;
	
	public ManageUserPanel()
	{
		JPanel mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setOpaque(false);
		JScrollPane scrollPane = new JScrollPane(mainPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,  
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(5);
		scrollPane.setLayout(new ScrollPaneLayout());
		this.setLayout(new BorderLayout());
		this.add(scrollPane, BorderLayout.CENTER);
		
		this.setBackground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();
//		c.weightx = 1;
//		c.weighty = 1;
		c.gridx = 0;
		c.fill = GridBagConstraints.BOTH;
		
		ArrayList<User> users = ManageUserController.getAllUsers();
		
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
	public ArrayList<UserComponent> getUserComponents()
	{
		if (userComponents == null)
			this.userComponents = new ArrayList<>();
		
		return userComponents;
	}
	
}
