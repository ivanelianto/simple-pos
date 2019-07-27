package app.view.manage.product;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.view.custom_component.MyImageButton;

public interface IManageProductPanel
{
	JLabel getTotalLoadedProductLabel();
	JLabel getSearchLabel();
	
	JTextField getSearchField();
	
	MyImageButton getAddButton();

	JPanel getMainPanel();
	
	void refreshData();
}
