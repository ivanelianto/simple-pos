package app.view.pos.datapanel;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public interface IDataPanel
{
	JTable getMainTable();
	
	void setTableView(DefaultTableModel data);
}
