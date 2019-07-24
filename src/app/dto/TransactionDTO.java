package app.dto;

import java.util.Date;

import javax.swing.table.DefaultTableModel;

public class TransactionDTO
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
