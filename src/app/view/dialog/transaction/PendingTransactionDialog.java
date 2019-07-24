package app.view.dialog.transaction;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import app.dto.TransactionDTO;
import app.factory.ButtonFactory;
import app.view.dialog.MyDialog;
import main.Main;
import util.Formatter;

public class PendingTransactionDialog extends MyDialog
		implements IPendingTransactionDialog, AutoCloseable, ActionListener
{
	private JTable table;

	private JButton btnRestore, btnCancel;

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
			DefaultTableModel tableModel = getRefreshedData();

			table = new JTable(tableModel);
		}

		return table;
	}

	private DefaultTableModel getRefreshedData()
	{
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Transaction Time");
		columnNames.add("Total Item");

		Vector<Vector<String>> transactionBriefs = new Vector<>();

		for (TransactionDTO dto : Main.subject.getPendingTransactions())
		{
			Vector<String> pendingTransactionInfo = new Vector<>();
			pendingTransactionInfo.add(Formatter.formatToHumanReadableDate(dto.getOccurrence()));
			pendingTransactionInfo.add(dto.getTransaction().getRowCount() + "");
			transactionBriefs.add(pendingTransactionInfo);
		}

		DefaultTableModel tableModel = new DefaultTableModel(transactionBriefs, columnNames);
		return tableModel;
	}

	@Override
	public JButton getCancelButton()
	{
		if (btnCancel == null)
		{
			btnCancel = ButtonFactory.getInstance().create("Cancel", ButtonFactory.INVERTED_ACCENT_STYLE);
			btnCancel.setPreferredSize(new Dimension(100, 35));
			btnCancel.addActionListener(this);
		}

		return btnCancel;
	}

	@Override
	public JButton getRestoreButton()
	{
		if (btnRestore == null)
		{
			btnRestore = ButtonFactory.getInstance().create("Restore", ButtonFactory.PRIMARY_STYLE);
			btnRestore.setPreferredSize(new Dimension(100, 35));
			btnRestore.addActionListener(this);
		}

		return btnRestore;
	}

	@Override
	public void close() throws Exception
	{
		this.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == getRestoreButton())
		{
			int row = getTable().getSelectedRow();

			if (row < 0)
			{
				JOptionPane.showMessageDialog(null, "You didn\'t select any pending transaction.", "Stop",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			TransactionDTO dto = Main.subject.getPendingTransactions().get(row);
			Main.subject.setData(dto.getTransaction());
			Main.subject.reopenPendingTrasaction(dto);

			JOptionPane.showMessageDialog(null, "Pending transaction re-open.", "Success",
					JOptionPane.INFORMATION_MESSAGE);

			table.setModel(getRefreshedData());
			
			getCancelButton().doClick();
		}
		else if (e.getSource() == getCancelButton())
		{
			try
			{
				this.close();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}

}
