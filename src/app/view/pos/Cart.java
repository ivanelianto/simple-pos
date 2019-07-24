package app.view.pos;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import app.view.pos.datapanel.ObservableTransactionPanel;

public class Cart implements ICartSubject
{
	private ArrayList<ObservableTransactionPanel> observablePanels;

	private DefaultTableModel data;

	private ArrayList<Transaction> pendingTransactions = new ArrayList<>();

	public ArrayList<Transaction> getPendingTransactions()
	{
		return this.pendingTransactions;
	}

	public void pendingTransaction(DefaultTableModel data)
	{
		Transaction transaction = new Transaction();
		transaction.setOccurrence(new Date());
		transaction.setTransaction(data);
		pendingTransactions.add(transaction);
	}

	private DefaultTableModel getDefaultTableModel()
	{
		Vector<String> columnNames = new Vector<>();
		columnNames.add("ID");
		columnNames.add("Name");
		columnNames.add("Quantity");
		columnNames.add("Price");
		columnNames.add("Subtotal");
		return new DefaultTableModel(null, columnNames);
	}

	@Override
	public ArrayList<ObservableTransactionPanel> getObservablePanels()
	{
		if (observablePanels == null)
			observablePanels = new ArrayList<>();

		return observablePanels;
	}

	@Override
	public DefaultTableModel getData()
	{
		if (data == null)
			data = getDefaultTableModel();

		return data;
	}

	@Override
	public void setData(DefaultTableModel tableModel)
	{
		if (tableModel == null)
		{
			this.data = getDefaultTableModel();
		}
		else
			this.data = tableModel;

		announce();
	}

	@Override
	public void add(ObservableTransactionPanel observer)
	{
		this.getObservablePanels().add(observer);
	}

	@Override
	public void announce()
	{
		for (ObservableTransactionPanel panel : getObservablePanels())
		{
			panel.update();
		}
	}

	class Transaction
	{
		private Date occurrence;
		private DefaultTableModel transaction;

		public Date getOccurrence()
		{
			return occurrence;
		}

		public void setOccurrence(Date occurrence)
		{
			this.occurrence = occurrence;
		}

		public DefaultTableModel getTransaction()
		{
			return transaction;
		}

		public void setTransaction(DefaultTableModel transaction)
		{
			this.transaction = transaction;
		}
	}
}
