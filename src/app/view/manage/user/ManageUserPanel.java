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
import javax.swing.SwingWorker;

import app.controller.UserController;
import app.factory.ButtonFactory;
import app.model.User;
import app.view.custom_component.MyImageButton;
import app.view.dialog.user.UserDialog;
import util.FileHelper;

public class ManageUserPanel extends JPanel implements ActionListener, IManageUserPanel
{
	private JPanel mainPanel;

	private MyImageButton btnAdd;

	public ManageUserPanel()
	{
		this.setLayout(new BorderLayout());

		JPanel addButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		addButtonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 10));
		addButtonPanel.setOpaque(false);
		addButtonPanel.add(getAddButton());
		this.add(addButtonPanel, BorderLayout.NORTH);

		this.setBackground(Color.WHITE);

		this.refreshData();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnAdd)
		{
			try (UserDialog dialog = new UserDialog())
			{
				dialog.setVisible(true);
				this.refreshData();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}

	@Override
	public JPanel getMainPanel()
	{
		if (mainPanel == null)
		{
			mainPanel = new JPanel(new GridBagLayout());
			mainPanel.setOpaque(false);
			JScrollPane scrollPane = new JScrollPane(mainPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.getVerticalScrollBar().setUnitIncrement(5);
			scrollPane.setBackground(Color.WHITE);
			scrollPane.getViewport().setBackground(Color.WHITE);
			scrollPane.setBorder(BorderFactory.createEmptyBorder());
			this.add(scrollPane, BorderLayout.CENTER);
		}

		return mainPanel;
	}

	@Override
	public MyImageButton getAddButton()
	{
		if (btnAdd == null)
		{
			try
			{
				Image image = ImageIO.read(new File(FileHelper.getAssetsPath() + "/add-icon.png"));
				btnAdd = ButtonFactory.getInstance().create("Add", MyImageButton.LEFT, image,
						ButtonFactory.INVERTED_PRIMARY_STYLE);
				btnAdd.setPreferredSize(new Dimension(80, 35));
				btnAdd.setImageSize(20, 20);
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
	public void refreshData()
	{
		getMainPanel().removeAll();

		new UserDataFetcher().execute();
	}

	class UserDataFetcher extends SwingWorker<Void, User>
	{
		@Override
		protected Void doInBackground() throws Exception
		{
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = 0;
			c.fill = GridBagConstraints.BOTH;

			ArrayList<User> users = UserController.getAllUsers();

			for (int i = 0; i < users.size(); i++)
			{
				User user = users.get(i);

				UserComponent userItem = new UserComponent(user, ManageUserPanel.this);
				userItem.setPreferredSize(new Dimension(500, 80));

				userItem.getIDButton().setText(user.getId() + "");
				userItem.getNameLabel().setText(user.getName());
				userItem.getUsernameLabel().setText(user.getUsername());

				c.gridy = i;
				getMainPanel().add(userItem, c);
			}

			getMainPanel().revalidate();
			getMainPanel().repaint();

			return null;
		}
	}
}
