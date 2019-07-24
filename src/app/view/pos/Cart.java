package app.view.pos;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class Cart implements ICartSubject
{
	private ArrayList<ObservableTransactionPanel> observablePanels;
	
	private DefaultTableModel data;
	
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
		{
			Vector<String> columnNames = new Vector<>();
			columnNames.add("ID");
			columnNames.add("Name");
			columnNames.add("Quantity");
			columnNames.add("Price");
			columnNames.add("Subtotal");

			data = new DefaultTableModel(null, columnNames);
		}
		
		return data;
	}

	@Override
	public void setData(DefaultTableModel tableModel)
	{
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
