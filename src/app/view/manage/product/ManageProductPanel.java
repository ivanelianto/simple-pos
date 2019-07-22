package app.view.manage.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;

import app.controller.ManageProductController;
import app.model.Product;

public class ManageProductPanel extends JPanel implements IManageProductPanel
{
	private ArrayList<ProductComponent> productComponents;
	
	public ManageProductPanel()
	{
		JPanel mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setOpaque(false);
		JScrollPane scrollPane = new JScrollPane(mainPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,  
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(5);
		scrollPane.setLayout(new ScrollPaneLayout());
		this.setLayout(new BorderLayout());
		this.add(scrollPane, BorderLayout.CENTER);
		
		this.setBackground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.fill = GridBagConstraints.BOTH;
		
		ArrayList<Product> Products = ManageProductController.getAllProducts();
		
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		
		for (int i = 0; i < Products.size(); i++)
		{
			Product product = Products.get(i);
			
			ProductComponent productItem = new ProductComponent();
			productItem.setSize(this.getWidth(), 100);
			
			productItem.getIDButton().setText(product.getId() + "");
			productItem.getNameLabel().setText(product.getName());
			productItem.getPriceLabel().setText(formatter.format(product.getPrice()));
			productItem.getStockLabel().setText("Stock : " + product.getStock());
			
			getProductComponents().add(productItem);
			c.gridy = i; 
			mainPanel.add(productItem, c);
		}
	}

	@Override
	public ArrayList<ProductComponent> getProductComponents()
	{
		if (productComponents == null)
			this.productComponents = new ArrayList<>();
		
		return productComponents;
	}
	
}
