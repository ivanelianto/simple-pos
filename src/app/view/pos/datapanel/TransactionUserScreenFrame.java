package app.view.pos.datapanel;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.JFrame;

import app.view.pos.Cart;

public class TransactionUserScreenFrame extends JFrame
{
	public TransactionUserScreenFrame(Cart subject)
	{
		this.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		this.setSize(500, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(new DataPanel(subject, null), BorderLayout.CENTER);
	}
}
