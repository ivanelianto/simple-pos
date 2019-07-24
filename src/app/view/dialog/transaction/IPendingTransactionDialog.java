package app.view.dialog.transaction;

import javax.swing.JButton;
import javax.swing.JTable;

public interface IPendingTransactionDialog
{
	JTable getTable();
	
	JButton getCancelButton();
	
	JButton getRestoreButton();
}
