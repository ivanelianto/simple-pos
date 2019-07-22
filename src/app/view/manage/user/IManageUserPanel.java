package app.view.manage.user;

import java.util.ArrayList;

import app.view.custom_component.MyImageButton;

public interface IManageUserPanel {
	MyImageButton getAddButton();
	ArrayList<UserComponent> getUserComponents();
}
