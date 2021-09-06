package banktransaction;
import java.util.*;
import java.sql.*;

public class DataUpdate 
{
	static int count;
	static ArrayList<String> columnNames;
	//To open connection and to update the column names and count of the rows in database
	public DataUpdate() throws Exception
	{
		Class.forName("banktransaction.DBConnection");
		if(columnNames==null)
			columnNames=new ArrayList<String>();
		DataUpdate.getCount();
	}
	//get the columns in the database table
	public static void printColumnNames()
	{
		for(int i=1;i<=columnNames.size();i++)
			System.out.print(i+". "+columnNames.get(i-1)+" ");
	}
	//get Count of all transactions for transactionArray
	public static int  getCount() throws Exception
	{
		String query="Select Count(*) from transactions";
		Statement st=DBConnection.con.createStatement();
		ResultSet rt=st.executeQuery(query);
		rt.next();
		count=rt.getInt(1);
		rt.close();
		st.close();
		return count;
	}
	// Get values based on condition
	public ArrayList<Transaction> getRow(String cond) throws Exception
	{
		String query=null;
		String[] separated=cond.split(" ");
		if(separated[0].equalsIgnoreCase("TransactionId")||separated[0].equalsIgnoreCase("AccountNumber")||separated[0].equalsIgnoreCase("Amount"))
			query="Select * from transactions where " +separated[0]+" = "+separated[1];
		else
			query="Select * from transactions where " +separated[0]+" = '"+separated[1]+"'";
		Statement st=DBConnection.con.createStatement();
		ResultSet rt=st.executeQuery(query);
		ArrayList<Transaction> transactions=new ArrayList<Transaction>();
		while(rt.next())
		{
			Transaction t=new Transaction(rt.getInt(1),rt.getString(2),rt.getInt(3),rt.getString(4),rt.getString(5),rt.getInt(6));
			transactions.add(t);
		}
		rt.close();
		st.close();
		return transactions;	
	}
	public ArrayList<Transaction> getAll() throws Exception
	{
		ArrayList<Transaction> transactions=new ArrayList<Transaction>();
		String query="Select * from transactions";
		Statement st=DBConnection.con.createStatement();
		ResultSet rt=st.executeQuery(query);
		while(rt.next())
		{
			Transaction t=new Transaction(rt.getInt(1),rt.getString(2),rt.getInt(3),rt.getString(4),rt.getString(5),rt.getInt(6));
			transactions.add(t);
		}
		ResultSetMetaData rm=rt.getMetaData();
		for(int i=1;i<=rm.getColumnCount();i++)
			columnNames.add(rm.getColumnName(i));
		rt.close();
		st.close();
		return transactions;
	}
	// To add a new row through JDBC
	public int addRow(String input) throws Exception
	{
		String[] separated = input.split(" ");
		String query= "insert into transactions values (?,?,?,?,?,?)";
		PreparedStatement pt=DBConnection.con.prepareStatement(query);
		Transaction t= new Transaction(Integer.parseInt(separated[0])
				,separated[1],Integer.parseInt(separated[2]),separated[3],separated[4],Integer.parseInt(separated[5]));
		Main.allTransactions.add(t);
		DataUpdate.count+=1;
		pt.setInt(1, t.getTransactionId());
		pt.setString(2, t.getClientName());
		pt.setInt(3, t.getAccountNumber());
		pt.setString(4, t.getTransactionType());
		pt.setString(5, t.getDebitOrCredit());
		pt.setInt(6, t.getAmount());
		int val =pt.executeUpdate();
		pt.close();
		return val;
	}
	//Remove a row from a database table
	public int removeRow(String input) throws Exception
	{
		String query ="delete from transactions where TransactionId="+input;
		PreparedStatement st= DBConnection.con.prepareStatement(query);
		int val=st.executeUpdate(query);
		DataUpdate.count-=1;
		for(Transaction t:Main.allTransactions)
		{
			if(t.getTransactionId()==Integer.parseInt(input))
			{
				Main.allTransactions.remove(t);
				break;
			}
		}
		st.close();
		return val;
	}
	public int updateRow(String cond)throws Exception
	{
		String[] separated=cond.split(" ");
		String query=null;
		if(separated[1].equalsIgnoreCase("TransactionId")||separated[1].equalsIgnoreCase("AccountNumber")||separated[1].equalsIgnoreCase("Amount"))
			query="Update transactions set "+separated[1]+" = "+separated[2]+" where transactionId = "+separated[0];
		else
			query="Update transactions set "+separated[1]+" = '"+separated[2]+"' where transactionId = "+separated[0];
		Statement st=DBConnection.con.createStatement();
		int val=st.executeUpdate(query);
		DataUpdate d=new DataUpdate();
		Main.allTransactions=d.getAll();
		return val;
	}
	public boolean isClosed() throws Exception
	{
		if(DBConnection.con.isClosed())
			return true;
		return false;
	}
}
