package app.view.manage.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;

import app.controller.ProductController;
import app.factory.ButtonFactory;
import app.model.Product;
import app.view.custom_component.MyImageButton;
import util.FilePathHelper;

public class ManageProductPanel extends JPanel implements IManageProductPanel
{
	private MyImageButton btnAdd;
	
	private ArrayList<ProductComponent> productComponents;
	
	public ManageProductPanel()
	{
		this.setLayout(new BorderLayout());
		
		JPanel addButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		addButtonPanel.setOpaque(false);
		addButtonPanel.add(getAddButton());
		this.add(addButtonPanel, BorderLayout.NORTH);
		
		JPanel mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setOpaque(false);
		JScrollPane scrollPane = new JScrollPane(mainPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,  
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(5);
		scrollPane.setLayout(new ScrollPaneLayout());
		scrollPane.getViewport().setBackground(Color.WHITE);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		this.add(scrollPane, BorderLayout.CENTER);
		
		this.setBackground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.fill = GridBagConstraints.BOTH;
		
		ArrayList<Product> Products = ProductController.getAllProducts();
		
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
	public MyImageButton getAddButton()
	{
		if (btnAdd == null)
		{
			try
			{
				Image image = ImageIO.read(new File(FilePathHelper.getAssetsPath() + "/add-icon.png"));
				
				btnAdd = ButtonFactory.getInstance().create(
						"Add", 
						MyImageButton.LEFT, 
						image);
				
				btnAdd.setPreferredSize(new Dimension(100, 50));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		return btnAdd;
	}

	@Override
	public ArrayList<ProductComponent> getProductComponents()
	{
		if (productComponents == null)
			this.productComponents = new ArrayList<>();
		
		return productComponents;
	}
	
}
