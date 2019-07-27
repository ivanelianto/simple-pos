package app.view.manage.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingWorker;

import app.controller.UserController;
import app.factory.ButtonFactory;
import app.factory.LabelFactory;
import app.model.User;
import app.view.custom_component.MyImageButton;
import app.view.dialog.user.UserDialog;
import util.FileHelper;
import util.MyFormatter;

public class ManageUserPanel extends JPanel implements ActionListener, IManageUserPanel
{
	private JLabel lblTotalLoadedUser;
	
	private JPanel mainPanel;

	private MyImageButton btnAdd;
	
	private int lastLoadedUserPage = 1, totalUser = 0;

	public ManageUserPanel()
	{
		this.setLayout(new BorderLayout());

		JPanel addButtonPanel = new JPanel(new BorderLayout());
		addButtonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 10));
		addButtonPanel.setOpaque(false);
		addButtonPanel.add(getTotalLoadedUserLabel(), BorderLayout.WEST);
		addButtonPanel.add(getAddButton(), BorderLayout.EAST);
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
	public JLabel getTotalLoadedUserLabel()
	{
		if (lblTotalLoadedUser == null)
		{
			lblTotalLoadedUser = LabelFactory.getInstance().create("Total Loaded User : 0 / " 
					+ MyFormatter.formatToNumberWithSeparator(totalUser));
		}
		
		return lblTotalLoadedUser;
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
			scrollPane.getVerticalScrollBar().setUnitIncrement(25);
			scrollPane.setBackground(Color.WHITE);
			scrollPane.getViewport().setBackground(Color.WHITE);
			scrollPane.setBorder(BorderFactory.createEmptyBorder());
			JScrollBar scrollbar = scrollPane.getVerticalScrollBar();
			scrollbar.addAdjustmentListener(new AdjustmentListener()
			{
				@Override
				public void adjustmentValueChanged(AdjustmentEvent e)
				{
					if (!e.getValueIsAdjusting())
					{
						if (e.getValue() == scrollbar.getMaximum() - scrollbar.getVisibleAmount())
						{
							if (totalUser != getMainPanel().getComponentCount())
							{
								lastLoadedUserPage++;
								refreshData();
							}
						}
					}
				}
			});
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
		totalUser = UserController.getTotalUser();

		new UserDataFetcher().execute();
	}

	class UserDataFetcher extends SwingWorker<Void, User>
	{
		private GridBagConstraints c;
		
		public UserDataFetcher()
		{
			c = new GridBagConstraints();
			c.gridx = 0;
			c.fill = GridBagConstraints.BOTH;
		}

		@Override
		protected Void doInBackground() throws Exception
		{
			ArrayList<User> users = UserController.getUsersPerPage(lastLoadedUserPage);

			for (User user : users)
				publish(user);

			return null;
		}

		@Override
		protected void process(List<User> chunks)
		{
			for (int i = 0; i < chunks.size(); i++)
			{
				User user = chunks.get(i);

				UserComponent userItem = new UserComponent(user, ManageUserPanel.this);
				userItem.setPreferredSize(new Dimension(500, 80));
				userItem.getIDButton().setText(user.getId() + "");
				userItem.getNameLabel().setText(user.getName());
				userItem.getUsernameLabel().setText(user.getUsername());
				getMainPanel().add(userItem, c);
			}
		}

		@Override
		protected void done()
		{
			getTotalLoadedUserLabel().setText("Total Loaded User : " + getMainPanel().getComponentCount() + " / "
					+ MyFormatter.formatToNumberWithSeparator(totalUser));
			getMainPanel().revalidate();
			getMainPanel().repaint();
		}
	}
}
