package app.view.main;

import javax.swing.JLabel;

public interface IMainFrame
{
	JLabel getTitleLabel();

	SidePanel getSidePanel();

	ContentPanel getContentPanel();
	
	void close();
}
