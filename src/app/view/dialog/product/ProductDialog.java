package app.view.dialog.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.controller.ProductController;
import app.factory.ButtonFactory;
import app.factory.LabelFactory;
import app.factory.TextFieldFactory;
import app.model.Product;
import app.validator.Validator;
import app.validator.rule.product.PriceRule;
import app.validator.rule.product.StockRule;
import app.validator.rule.user.NameRule;
import app.view.dialog.MyDialog;

public class ProductDialog extends MyDialog implements ActionListener, AutoCloseable, IProductDialog {
	public final static int INSERT_MODE = 0;
	public final static int UPDATE_MODE = 1;

	private JLabel lblName;
	private JLabel lblStock;
	private JLabel lblPrice;
	private JTextField txtName;
	private JTextField txtStock;
	private JTextField txtPrice;
	private JButton btnCancel;
	private JButton btnSave;
	private JPanel mainPanel;
	private GridBagConstraints c = new GridBagConstraints();

	private Product product;

	private int currentMode = INSERT_MODE;

	public ProductDialog() {
		initializeComponent();
	}

	public ProductDialog(Product product) {
		this.product = product;
		currentMode = UPDATE_MODE;
		initializeComponent();
		getNameField().setText(product.getName());
		getStockField().setText(String.valueOf(product.getStock()));
		getPriceField().setText(String.valueOf(product.getPrice()));
	}

	private void initializeComponent() {
		mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBackground(Color.WHITE);
		this.add(mainPanel, BorderLayout.CENTER);

		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;

		JPanel namePanel = getWrappedInput(getNameLabel(), getNameField());
		mainPanel.add(namePanel, c);

		JPanel stockPanel = getWrappedInput(getStockLabel(), getStockField());
		mainPanel.add(stockPanel, c);

		JPanel pricePanel = getWrappedInput(getPriceLabel(), getPriceField());
		mainPanel.add(pricePanel, c);

		JPanel actionButtonPanel = new JPanel(new FlowLayout());
		actionButtonPanel.setOpaque(false);
		actionButtonPanel.add(getCancelButton());
		actionButtonPanel.add(getSaveButton());
		mainPanel.add(actionButtonPanel, c);
	}

	private JPanel getWrappedInput(JComponent labelComponent, JComponent fieldComponent) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setOpaque(false);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.add(labelComponent, BorderLayout.NORTH);
		panel.add(fieldComponent, BorderLayout.CENTER);
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getSaveButton()) {
			String message = "";
			
			boolean isValid = Validator.validate(new NameRule(getNameField().getText()),
					new StockRule(getStockField().getText()),
					new PriceRule(getPriceField().getText()));
			
			if (currentMode == INSERT_MODE) {
				if (isValid) {
					String name = getNameField().getText();
					int stock = Integer.parseInt(getStockField().getText());
					double price = Double.parseDouble(getPriceField().getText());

					ProductController.add(name, stock, price);
					message = "New product added.";
				} else {
					JOptionPane.showMessageDialog(null, Validator.getErrorMessages().get(0), "Stop",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			} else {
				if (isValid) {
					ProductController.update(product.getId(), 
							getNameField().getText(), 
							Integer.parseInt(getStockField().getText()),
							Double.parseDouble(getPriceField().getText()));
					message = "Product data updated.";
				} else {
					JOptionPane.showMessageDialog(null, Validator.getErrorMessages().get(0), "Stop",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}

			JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);

			try {
				this.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == getCancelButton()) {
			try {
				this.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void close() throws Exception {
		this.dispose();
	}

	@Override
	public JLabel getNameLabel() {
		if (lblName == null) {
			lblName = LabelFactory.getInstance().create("Name");
		}

		return lblName;
	}

	@Override
	public JLabel getStockLabel() {
		if (lblStock == null) {
			lblStock = LabelFactory.getInstance().create("Stock");
		}

		return lblStock;
	}

	@Override
	public JLabel getPriceLabel() {
		if (lblPrice == null) {
			lblPrice= LabelFactory.getInstance().create("Price");
		}

		return lblPrice;
	}

	@Override
	public JTextField getStockField() {
		if (txtStock == null) {
			txtStock = TextFieldFactory.getInstance().create();
		}
		
		return txtStock;
	}

	@Override
	public JTextField getPriceField() {
		if (txtPrice == null) {
			txtPrice = TextFieldFactory.getInstance().create();
		}
		
		return txtPrice;
	}

	public JTextField getNameField() {
		if (txtName == null) {
			txtName = TextFieldFactory.getInstance().create(150, 25);
		}

		return txtName;
	}

	@Override
	public JButton getCancelButton() {
		if (btnCancel == null) {
			btnCancel = ButtonFactory.getInstance().create("Cancel", ButtonFactory.INVERTED_ACCENT_STYLE);
			btnCancel.addActionListener(this);
		}

		return btnCancel;
	}

	@Override
	public JButton getSaveButton() {
		if (btnSave == null) {
			btnSave = ButtonFactory.getInstance().create("Save", ButtonFactory.PRIMARY_STYLE);
			btnSave.addActionListener(this);
		}

		return btnSave;
	}

}
