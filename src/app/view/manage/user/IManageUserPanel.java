package app.view.manage.user;

import java.util.ArrayList;

import javax.swing.JPanel;

import app.view.custom_component.MyImageButton;

public interface IManageUserPanel {
	JPanel getMainPanel();
	MyImageButton getAddButton();
	void refreshData();
}
