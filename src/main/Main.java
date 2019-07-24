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
		LoginFrame loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
		
		// TODO: Pas Add Product, Create File Dengan Format {}#{}#{}#{}
		// TODO: Save Ke Folder "products"
		// TODO: Restore Pending Transaction
		// TODO: Search Transaction
		// TODO: Research Tentang Chart

//		new MainDialog().setVisible(true);
		
//		ArrayList<CartDTO> carts = new ArrayList<>();
//		CartDTO dto = new CartDTO();
//		dto.setId(1);
//		dto.setName("Ganteng");
//		dto.setQuantity(1);
//		dto.setPrice(1000);
//		carts.add(dto);
//		TransactionRepository.add(carts);
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new Main();
			}
		});
	}
}
