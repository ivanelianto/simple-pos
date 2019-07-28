package app.view.manage.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingWorker;

import app.controller.ProductController;
import app.factory.ButtonFactory;
import app.factory.LabelFactory;
import app.model.Product;
import app.view.custom_component.MyImageButton;
import app.view.dialog.product.ProductDialog;
import util.FileHelper;
import util.MyFormatter;

public class ManageProductPanel extends JPanel implements ActionListener, IManageProductPanel
{
	private JLabel lblTotalLoadedProduct;

	private MyImageButton btnAdd;

	private JPanel mainPanel;

	private int lastLoadedProductPage = 1, totalProduct = 0;

	public ManageProductPanel()
	{
		this.setLayout(new BorderLayout());

		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 10));
		headerPanel.setBackground(Color.WHITE);
		headerPanel.add(getTotalLoadedProductLabel(), BorderLayout.WEST);
		headerPanel.add(getAddButton(), BorderLayout.EAST);
		this.add(headerPanel, BorderLayout.NORTH);

		refreshData();
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
	public JLabel getTotalLoadedProductLabel()
	{
		if (lblTotalLoadedProduct == null)
		{
			lblTotalLoadedProduct = LabelFactory.getInstance()
					.create("Total Loaded Product : 0 / " + MyFormatter.formatToNumberWithSeparator(totalProduct));
		}
		return lblTotalLoadedProduct;
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
			scrollPane.getVerticalScrollBar().setUnitIncrement(35);
			scrollPane.setLayout(new ScrollPaneLayout());
			scrollPane.getViewport().setBackground(Color.WHITE);
			scrollPane.setBorder(BorderFactory.createEmptyBorder());
			JScrollBar scrollbar = scrollPane.getVerticalScrollBar();
			scrollbar.addAdjustmentListener(new AdjustmentListener()
			{
				@Override
				public void adjustmentValueChanged(AdjustmentEvent e)
				{
					if (!e.getValueIsAdjusting())
					{
						if (e.getValue() == scrollbar.getMaximum() - scrollbar.getVisibleAmount())
						{
							if (totalProduct != getMainPanel().getComponentCount())
							{
								lastLoadedProductPage++;
								refreshData();
							}
						}
					}
				}
			});
			this.add(scrollPane, BorderLayout.CENTER);
		}

		return mainPanel;
	}

	@Override
	public void refreshData()
	{
		totalProduct = ProductController.getTotalProduct();
		new ProductDataFetcher().execute();
	}

	public void refreshData(boolean isFetchAll, boolean shouldClearPanel) throws Exception
	{
		if (!isFetchAll)
			throw new Exception("Argument should be true.");

		if (shouldClearPanel)
			getMainPanel().removeAll();

		totalProduct = ProductController.getTotalProduct();
		new ProductDataFetcher(isFetchAll).execute();
	}

	class ProductDataFetcher extends SwingWorker<Void, Product>
	{
		private boolean isFetchAll = false;

		private GridBagConstraints c;

		public ProductDataFetcher()
		{
			c = new GridBagConstraints();
			c.gridx = 0;
			c.fill = GridBagConstraints.BOTH;
		}

		public ProductDataFetcher(boolean isFetchAll)
		{
			this();
			this.isFetchAll = isFetchAll;
		}

		@Override
		protected Void doInBackground() throws Exception
		{
			ArrayList<Product> products = null;

			if (!isFetchAll)
				products = ProductController.getProductsPerPage(lastLoadedProductPage);
			else
				products = ProductController.getAllProducts();

			for (Product product : products)
				publish(product);

			return null;
		}

		@Override
		protected void process(List<Product> chunks)
		{
			for (int i = 0; i < chunks.size(); i++)
			{
				Product product = chunks.get(i);
				ProductComponent productItem = new ProductComponent(product, () ->
				{
					refreshData(true, true);
					return null;
				});
				productItem.setPreferredSize(new Dimension(500, 100));
				productItem.getIDButton().setText(product.getId() + "");
				productItem.getNameLabel().setText(product.getName());
				productItem.getPriceLabel().setText(MyFormatter.formatToCurrency(product.getPrice()));
				productItem.getStockLabel().setText("Stock : " + product.getStock());
				getMainPanel().add(productItem, c);
			}
		}

		@Override
		protected void done()
		{
			getTotalLoadedProductLabel().setText("Total Loaded Product : " + getMainPanel().getComponentCount() + " / "
					+ MyFormatter.formatToNumberWithSeparator(totalProduct));
			getMainPanel().revalidate();
			getMainPanel().repaint();
		}
	}
}
