package app.view.dialog.product;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public interface IProductDialog
{
	JLabel getNameLabel();

	JLabel getStockLabel();

	JLabel getPriceLabel();

	JTextField getNameField();

	JTextField getStockField();

	JTextField getPriceField();

	JButton getCancelButton();

	JButton getSaveButton();
}
