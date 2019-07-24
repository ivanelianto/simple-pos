package app.view.dialog.transaction;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import app.dto.TransactionDTO;
import app.factory.ButtonFactory;
import app.view.dialog.MyDialog;
import main.Main;

public class PendingTransactionDialog extends MyDialog implements IPendingTransactionDialog, AutoCloseable
{
	private JTable table;
	
	private  JButton btnRestore, btnCancel;
	
	public PendingTransactionDialog()
	{
		JScrollPane scrollPane = new JScrollPane(getTable());
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getViewport().setBackground(Color.WHITE);
		this.add(scrollPane, BorderLayout.CENTER);
		
		JPanel actionButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		actionButtonPanel.setBackground(Color.WHITE);
		actionButtonPanel.add(getCancelButton());
		actionButtonPanel.add(getRestoreButton());
		this.add(actionButtonPanel, BorderLayout.SOUTH);
	}

	@Override
	public JTable getTable()
	{
		if (table == null)
		{
			Vector<String> columnNames = new Vector<String>();
			columnNames.add("Transaction Time");
			columnNames.add("Total Item");
			
			Vector<Vector<String>> transactionBriefs = new Vector<>();
			
			for (TransactionDTO dto : Main.subject.getPendingTransactions())
			{
				Vector<String> pendingTransactionInfo = new Vector<>();
				pendingTransactionInfo.add(dto.getOccurrence().toString());
				pendingTransactionInfo.add(dto.getTransaction().getRowCount() + "");
				transactionBriefs.add(pendingTransactionInfo);
			}
			
			DefaultTableModel tableModel = new DefaultTableModel(transactionBriefs, columnNames); 
			
			table = new JTable(tableModel);
		}
		
		return table;
	}

	@Override
	public JButton getCancelButton()
	{
		if (btnCancel == null)
		{
			btnCancel = ButtonFactory.getInstance().create("Cancel", ButtonFactory.INVERTED_ACCENT_STYLE);
			btnCancel.setPreferredSize(new Dimension(100, 25));
		}
		
		return btnCancel;
	}

	@Override
	public JButton getRestoreButton()
	{
		if (btnRestore == null)
		{
			btnRestore = ButtonFactory.getInstance().create("Restore", ButtonFactory.PRIMARY_STYLE);
			btnRestore.setPreferredSize(new Dimension(100, 25));
		}
		
		return btnRestore;
	}

	@Override
	public void close() throws Exception
	{
		this.dispose();
	}
	
}
