package app.view.dialog.transaction;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;

import app.factory.ButtonFactory;

public class PendingTransactionDialog extends JDialog implements IPendingTransactionDialog
{
	private JTable table;
	
	private  JButton btnRestore, btnCancel;

	@Override
	public JTable getTable()
	{
		if (table == null)
		{
			table = new JTable();
		}
		
		return table;
	}

	@Override
	public JButton getCancelButton()
	{
		if (btnCancel == null)
		{
			btnCancel = ButtonFactory.getInstance().create("Cancel", ButtonFactory.INVERTED_ACCENT_STYLE);
		}
		
		return btnCancel;
	}

	@Override
	public JButton getRestoreButton()
	{
		if (btnRestore == null)
		{
			btnRestore = ButtonFactory.getInstance().create("Restore", ButtonFactory.PRIMARY_STYLE);
		}
		
		return btnRestore;
	}
	
	
}
