package app.view.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDialog;

public abstract class MyDialog extends JDialog
{
	public final static int CANCELED = -1;
	public final static int INSERT_MODE = 0;
	public final static int UPDATE_MODE = 1;
	
	protected int dialogResult = CANCELED;

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

	public int getDialogResult()
	{
		return this.dialogResult;
	}
}
