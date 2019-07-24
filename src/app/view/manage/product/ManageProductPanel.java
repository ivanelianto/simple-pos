package app.view.manage.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
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
import app.view.dialog.product.ProductDialog;
import util.FileHelper;
import util.Formatter;

public class ManageProductPanel extends JPanel implements ActionListener, IManageProductPanel
{
	private MyImageButton btnAdd;

	private JPanel mainPanel;

	public ManageProductPanel()
	{
		this.setLayout(new BorderLayout());

		JPanel addButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		addButtonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 10));
		addButtonPanel.setOpaque(false);
		addButtonPanel.add(getAddButton());
		this.add(addButtonPanel, BorderLayout.NORTH);

		this.setBackground(Color.WHITE);

		this.refreshData();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == getAddButton())
		{
			try (ProductDialog dialog = new ProductDialog())
			{
				dialog.setVisible(true);
				this.refreshData();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}

	@Override
	public MyImageButton getAddButton()
	{
		if (btnAdd == null)
		{
			try
			{
				Image image = ImageIO.read(new File(FileHelper.getAssetsPath() + "/add-icon.png"));

				btnAdd = ButtonFactory.getInstance().create("Add", MyImageButton.LEFT, image,
						ButtonFactory.INVERTED_PRIMARY_STYLE);
				btnAdd.setPreferredSize(new Dimension(80, 35));
				btnAdd.setImageSize(20, 20);
				btnAdd.addActionListener(this);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		return btnAdd;
	}

	@Override
	public JPanel getMainPanel()
	{
		if (mainPanel == null)
		{
			mainPanel = new JPanel(new GridBagLayout());
			mainPanel.setOpaque(false);
			JScrollPane scrollPane = new JScrollPane(mainPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
					ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.getVerticalScrollBar().setUnitIncrement(5);
			scrollPane.setLayout(new ScrollPaneLayout());
			scrollPane.getViewport().setBackground(Color.WHITE);
			scrollPane.setBorder(BorderFactory.createEmptyBorder());
			this.add(scrollPane, BorderLayout.CENTER);
		}

		return mainPanel;
	}

	@Override
	public void refreshData()
	{
		getMainPanel().removeAll();

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.fill = GridBagConstraints.BOTH;

		ArrayList<Product> products = ProductController.getAllProducts();

		for (int i = 0; i < products.size(); i++)
		{
			Product product = products.get(i);

			ProductComponent productItem = new ProductComponent(product, ManageProductPanel.this);
			productItem.setPreferredSize(new Dimension(500, 100));

			productItem.getIDButton().setText(product.getId() + "");
			productItem.getNameLabel().setText(product.getName());
			productItem.getPriceLabel().setText(Formatter.formatToCurrency(product.getPrice()));
			productItem.getStockLabel().setText("Stock : " + product.getStock());

			c.gridy = i;
			getMainPanel().add(productItem, c);
		}
		getMainPanel().revalidate();
		getMainPanel().repaint();
	}

}
