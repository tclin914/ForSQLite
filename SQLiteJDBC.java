import java.sql.*;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class SQLiteJDBC
{
    public static void main(String args[] ) throws IOException
	{
	    Connection c = null;
		Statement stmt = null;
		String temp="";
		try
		{
		    Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:ProductDataSheet.db3");
			c.setAutoCommit(false);
			System.out.println("Open database successfully");
			
			FileWriter fw = new FileWriter("Part_No.txt");
			BufferedWriter bw = new BufferedWriter(fw);			
			
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ProductLine;");
			while(rs.next())
			{
			    
			    // String id = rs.getString("rowid");
			    // String ProductLine_Level1 = rs.getString("ProductLine_Level1");
			    // String ProductLine_Level2 = rs.getString("ProductLine_Level2");
			    String PartNo = rs.getString("Part_No");
			    // String Density = rs.getString("Density");
			    // String Organization = rs.getString("Organization");
			    // String Package = rs.getString("Package");
				
				bw.write("case \"" + PartNo + "\":" + "\n");
				bw.newLine();
				bw.write("ActionBar.SetTitle(" + "Resource.String." + PartNo + ");" + "\n");
				bw.newLine();
				bw.write("break;");
				bw.newLine();
				
				
			
			}
			rs.close();
			stmt.close();
			c.close();
			
		    bw.close();		
		    fw.close();
		}
		catch(Exception e)
		{
		    System.err.println(e.getClass().getName() + ": " +e.getMessage());
		    System.exit(0);
		}
		
	   
		
		System.out.println("Open database successfully");
	}
}