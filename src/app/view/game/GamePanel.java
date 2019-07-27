package app.view.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import app.factory.ButtonFactory;
import app.view.custom_component.MyImageButton;
import app.view.game.rz.RZMain;
import util.FileHelper;

public class GamePanel extends JPanel implements IGamePanel, ActionListener
{
	private MyImageButton btnRZGame, btnKEGame;

	public GamePanel()
	{
		GamePanel.this.setBackground(Color.WHITE);
		GamePanel.this.setLayout(new BorderLayout());

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;

		panel.add(getRZGameButton(), c);

		GamePanel.this.add(panel, BorderLayout.CENTER);
	}

	@Override
	public MyImageButton getRZGameButton()
	{
		if (btnRZGame == null)
		{
			try
			{
				Image image = ImageIO.read(new File(FileHelper.getAssetsPath() + "/Adventing WaRZ Thumbnail.png"));

				btnRZGame = ButtonFactory.getInstance().create("", MyImageButton.TOP, image,
						ButtonFactory.INVERTED_PRIMARY_STYLE);

				btnRZGame.addActionListener(this);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		return btnRZGame;
	}

	@Override
	public MyImageButton getKEGameButton()
	{
		if (btnKEGame == null)
		{

		}

		return btnKEGame;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnRZGame)
		{
			Thread thread = new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					new RZMain();
				}
			});
			thread.start();
		}
		else if (e.getSource() == btnKEGame)
		{

		}
	}
}
