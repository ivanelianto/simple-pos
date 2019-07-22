package app.view.main;

import javax.swing.JLabel;

import app.view.custom_component.MyImageButton;

public interface IMainFrame
{
	JLabel getTitleLabel();
	
	MyImageButton getHomeButton();

	MyImageButton getManageUserButton();

	MyImageButton getManageProductButton();

	MyImageButton getPOSButton();

	MyImageButton getReportButton();

	MyImageButton getLogoutButton();

	MyImageButton getCastToCustomerViewScreenButton();

	MyImageButton getRestorePendingTransactionButton();
}
