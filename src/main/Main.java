package main;

import javax.swing.SwingUtilities;

import app.model.User;
import app.view.login.LoginFrame;
import app.view.pos.Cart;

public class Main
{
	public static Cart subject = new Cart();

	public static User currentUser = null;

	public Main()
	{
		/**
		 * Generate Products File
		 */
//		ArrayList<Product> products = ProductRepository.getAllProducts();
//		for (Product product : products)
//		{
//			String data = String.format("%d#%s#%d#%f", 
//					product.getId(),
//					product.getName(),
//					1,
//					product.getPrice());
//			FileHelper.writeFile(FileHelper.getProductsPath(), data, true);
//		}
		
		/**
		 * Comment For Debugging
		 */
		LoginFrame loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
		
		/**
		 * Uncomment For Debugging
		 */
//		MainDialog dialog = new MainDialog();
//		dialog.addWindowListener(new WindowAdapter()
//		{
//			@Override
//			public void windowClosed(WindowEvent e)
//			{
//				System.exit(0);
//			}
//		});
//		dialog.setVisible(true);
	}

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new Main();
			}
		});
	}
}
