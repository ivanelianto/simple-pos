package app.view.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDialog;

public class MyDialog extends JDialog
{
	public MyDialog()
	{
		this.setBackground(Color.WHITE);
		this.setTitle("SIVle POS");
		this.setLayout(new BorderLayout());
		this.setModalityType(DEFAULT_MODALITY_TYPE);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(new Dimension(350, 350));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
}
