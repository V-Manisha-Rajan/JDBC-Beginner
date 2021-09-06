package banktransaction;
import java.sql.*;
import java.util.*;
import java.io.*;
//To open and close Connection
public class DBConnection
{
	
	static Connection con=null;
	static 
	{
		try
		{
			Properties p=new Properties();
			FileReader fr=new FileReader("transaction.properties");
			p.load(fr);
			con=DriverManager.getConnection(p.getProperty("url"),p.getProperty("uname"),p.getProperty("pass"));
		}
		catch(Exception E)
		{
			System.out.println(E);
		}
	}
	
	public static void closeConnection() throws Exception
	{ 
		con.close();
	}
}

