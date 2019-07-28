package app.view.main;

import javax.swing.JLabel;

import app.view.custom_component.MyImageButton;

public interface IHomePanel
{
	JLabel getCurrentUserNameLabel();
	
	MyImageButton getCastToCustomerViewScreenButton();

	MyImageButton getRestorePendingTransactionButton();
}
