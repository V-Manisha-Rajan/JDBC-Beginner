package banktransaction;
import java.util.*;
public class Main
{
	static Scanner sc=new Scanner(System.in);
	static ArrayList<Transaction> allTransactions;
	static DataUpdate d;
	static 
	{
		try
		{
			d=new DataUpdate();
			allTransactions=new ArrayList<Transaction>();
		}
		catch(Exception E)
		{
			System.out.println(E);
		}
	}
	//Void main function to call the getInput Method;
	public static void main(String[] args) throws Exception
	{
		Main.allTransactions=d.getAll();
		GetInput();
	}
	//This is used to give the choices to get Input from the user
	public static void GetInput()  throws Exception
	{
		System.out.println("The following can be done using this Java Application:");
		System.out.println("1.Add a row to the transaction table of banktransaction database.");
		System.out.println("2.Remove a row from the transaction table of the banktransaction database.");
		System.out.println("3.Get All the rows in the transaction table");
		System.out.println("4.Get the row based on the column names:");
		System.out.println("5.update the row based on the column names:");
		String opt="y";
		while(opt.equalsIgnoreCase("y"))
		{
			System.out.print("Enter your Choice:");
			int choice=sc.nextInt();
			sc.nextLine();
			System.out.println();
			Process(choice);
			System.out.println();
			System.out.print("Do you still want to continue? y/n");
			opt=sc.nextLine();
			System.out.println();
		}
		//Checks if connection is closed.
		if(!Main.d.isClosed())
			System.out.println("Connection is still open...");
		DBConnection.closeConnection();
		if(Main.d.isClosed())
			System.out.println("Connection is closed");
		//Close Scanner object
		sc.close();
	}
	//Process Output
	public static void Process(int choice) throws Exception
	{
		ArrayList<Transaction> t=new ArrayList<Transaction>();
		int flag=0;
		//if condition to get input based on choice and process output
		if(choice==3|| choice==4)
		{
			if(choice==3)
				t=Main.allTransactions;
			else if(choice==4)
			{
				System.out.println("Choose from the column Names and enter a value separated by space:");
				DataUpdate.printColumnNames();
				System.out.println();
				t=Main.d.getRow(sc.nextLine());
			}
			for(Transaction t1:t)
			{
				if(t1==null)
					break;
				System.out.println(t1.getTransactionId()+" "+t1.getClientName()+" "+t1.getAccountNumber()+" "+
						t1.getTransactionType()+" "+t1.getDebitOrCredit()+" "+t1.getAmount());
				flag=1;
			}
			if(flag==0)
			{
				System.out.println("No Such Record !!!!");
			}
		}
		else
		{
			if(choice==1)
			{
				System.out.println("Enter the details separated by space: ");
				String input=sc.nextLine();
				int val=Main.d.addRow(input);
				System.out.println("Rows affected"+val);
			}
			else if(choice==2)
			{
				System.out.println("Enter the transaction id to be deleted: ");
				String input=sc.nextLine();
				int val=Main.d.removeRow(input);
				System.out.println("Rows affected"+val);
			}
			else if(choice==5)
			{
				System.out.println("Enter the transaction Id and choose from the column Names and enter a value All separated by space:");
				DataUpdate.printColumnNames();
				System.out.println();
				int val=Main.d.updateRow(sc.nextLine());
				System.out.println("Field Afftected: "+val);
			}
			else
			{
				System.out.println("Invalid Choice!!!");
			}
		}
			
	}
}
