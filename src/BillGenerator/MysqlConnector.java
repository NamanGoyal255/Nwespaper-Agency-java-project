package BillGenerator;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConnector {
	static Connection con;
	public static Connection doconnect() 
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/newspaperAgency","root","Naman@3246");
			System.out.println("Connected........##");
		} 
		catch (Exception e) {
		
			e.printStackTrace();
		}
		return con;
		
		
	}
}
