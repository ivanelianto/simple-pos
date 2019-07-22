package app.view.manage.product;

import javax.swing.JButton;
import javax.swing.JLabel;

import app.view.custom_component.MyImageButton;

public interface IProductComponent
{
	JButton getIDButton();

	JLabel getNameLabel();

	JLabel getStockLabel();
	
	JLabel getPriceLabel();

	MyImageButton getEditButton();

	MyImageButton getDeleteButton();
}
