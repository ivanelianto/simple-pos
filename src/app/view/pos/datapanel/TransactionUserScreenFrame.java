package app.view.pos.datapanel;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import app.view.pos.Cart;
import util.FileHelper;

public class TransactionUserScreenFrame extends JFrame
{
	public TransactionUserScreenFrame(Cart subject)
	{
		this.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		this.setSize(500, 500);
		this.setTitle("SIVle POS");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon(FileHelper.getAssetsPath() + "/logo-colored.png").getImage());
		this.add(new DataPanel(subject), BorderLayout.CENTER);
	}
}
