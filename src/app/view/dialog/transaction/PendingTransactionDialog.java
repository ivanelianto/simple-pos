package app.view.dialog.transaction;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;

import app.factory.ButtonFactory;
import app.view.custom_component.MyColor;

public class PendingTransactionDialog extends JDialog implements IPendingTransactionDialog
{
	private JTable table;
	
	private  JButton btnRestore, btnCancel;

	@Override
	public JTable getTable()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JButton getCancelButton()
	{
		if (btnCancel == null)
		{
			btnCancel = ButtonFactory.getInstance().create("Cancel");
		}
		
		return btnCancel;
	}

	@Override
	public JButton getRestoreButton()
	{
		if (btnRestore == null)
		{
			btnRestore = ButtonFactory.getInstance().create("Restore");
			btnRestore.setBackground(MyColor.getPrimaryBackground());
			btnRestore.setForeground(Color.WHITE);
		}
		
		return btnRestore;
	}
	
	
}
