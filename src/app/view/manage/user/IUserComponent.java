package app.view.manage.user;

import javax.swing.JButton;
import javax.swing.JLabel;

import app.view.custom_component.MyImageButton;

public interface IUserComponent
{
	JButton getIDButton();

	JLabel getNameLabel();

	JLabel getUsernameLabel();

	MyImageButton getEditButton();

	MyImageButton getDeleteButton();
}
