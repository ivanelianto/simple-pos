package app.view.manage.product;

import javax.swing.JLabel;
import javax.swing.JPanel;

import app.view.custom_component.MyImageButton;

public interface IManageProductPanel
{
	JLabel getTotalLoadedProductLabel();
	
	MyImageButton getAddButton();

	JPanel getMainPanel();
	
	void refreshData();
}
