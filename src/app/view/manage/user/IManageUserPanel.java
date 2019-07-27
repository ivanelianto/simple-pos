package app.view.manage.user;

import javax.swing.JLabel;
import javax.swing.JPanel;

import app.view.custom_component.MyImageButton;

public interface IManageUserPanel
{
	JLabel getTotalLoadedUserLabel();
	
	JPanel getMainPanel();

	MyImageButton getAddButton();

	void refreshData();
}
