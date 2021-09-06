package banktransaction;
//Class to Store the database Values
public class Transaction 
{
	private int transactionId;
	private String clientName;
	private int accountNumber;
	private String transactionType;
	private String debitOrCredit;
	private int amount;
	public Transaction(int transactionId,String clientName,int accountNumber,String transactionType,String debitOrCredit,int amount)
	{
		this.transactionId=transactionId;
		this.clientName=clientName;
		this.accountNumber=accountNumber;
		this.transactionType=transactionType;
		this.debitOrCredit=debitOrCredit;
		this.amount=amount;
	}
	public int getTransactionId()
	{
		return this.transactionId;
	}
	public int getAmount()
	{
		return this.amount;
	}
	public int getAccountNumber()
	{
		return this.accountNumber;
	}
	public String getClientName()
	{
		return this.clientName;
	}
	public String getTransactionType()
	{
		return this.transactionType;
	}
	public String getDebitOrCredit()
	{
		return this.debitOrCredit;
	}
	public void setTransactionId(int transactionId)
	{
		this.transactionId=transactionId;
	}
	public void setAccountNumber(int accountNumber)
	{
		this.accountNumber=accountNumber;
	}
	public void setAmount(int amount)
	{
		this.amount=amount;
	}
	public void setClientName(String clientName)
	{
		this.clientName=clientName;
	}
	public void setDebitOrCredit(String debitOrCredit)
	{
		this.debitOrCredit=debitOrCredit;
	}
	public void setTransactionType(String transactionType)
	{
		this.transactionType=transactionType;
	}
}
