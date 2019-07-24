package app.view.pos;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import app.view.pos.datapanel.ObservableTransactionPanel;

public interface ICartSubject
{
	ArrayList<ObservableTransactionPanel> getObservablePanels();
	
	DefaultTableModel getData();
	
	void setData(DefaultTableModel tableModel);
	
	void add(ObservableTransactionPanel observer);
	
	void announce();
}
