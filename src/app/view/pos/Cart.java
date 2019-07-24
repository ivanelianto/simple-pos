package app.view.pos;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import app.dto.TransactionDTO;
import app.view.pos.datapanel.ObservableTransactionPanel;

public class Cart implements ICartSubject
{
	private ArrayList<ObservableTransactionPanel> observablePanels;

	private DefaultTableModel data;

	private ArrayList<TransactionDTO> pendingTransactions = new ArrayList<>();

	public ArrayList<TransactionDTO> getPendingTransactions()
	{
		return this.pendingTransactions;
	}

	public void pendingTransaction(DefaultTableModel data)
	{
		TransactionDTO dto = new TransactionDTO();
		dto.setOccurrence(new Date());
		dto.setTransaction(data);
		pendingTransactions.add(dto);
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
}
