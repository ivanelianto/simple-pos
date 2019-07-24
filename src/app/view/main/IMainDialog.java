package app.view.main;

import javax.swing.JLabel;

public interface IMainDialog
{
	JLabel getTitleLabel();

	SidePanel getSidePanel();

	ContentPanel getContentPanel();
	
	void close();
}
