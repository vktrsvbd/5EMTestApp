package controlModule;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.stream.Stream;
import com.cez.dbUtil.DBUtil;

public class Comparator extends DBUtil  {
	
private static ArrayList<String> txtRecords = new ArrayList<>();
	
	// search each txt line in DB table
	public static int txtToDBCompare(String txtPath, String dbTable, Connection conn) throws SQLException{
 		 
			int noOfRecords=0;
			ArrayList<String>txtItem = new ArrayList<>();
			txtItem = txtToStream(txtPath);
		  
		  for(String txt: txtItem) {
			  String SQL_Querry = "SELECT * FROM "+ dbTable + " WHERE timemark = '"+txt+"'";
			  Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL_Querry);
	// check if row exists -if yes add it to count
			while(rs.next()) {
				noOfRecords++;
			}
			  
		  }
		  return noOfRecords;
	  }
	 
	// generates a List of last inserted records in txt File/Log
	public static ArrayList<String> txtToStream(String txtPath) {
		long lc = 0;
		txtRecords.clear();		
		try(
				Stream<String>lines = Files.lines(Paths.get(txtPath))){	
			lc = lines.count();
			for(int i=0; i< linesInsert; i++) {				
				String li = Files.readAllLines(Paths.get(txtPath)).get((int) (lc-(linesInsert-i)));
				
				txtRecords.add(li.substring(20, 39));
				System.out.println("The record: "+ txtRecords.get(i));
				}
			lines.close();
		}catch (Exception e) {
			System.out.println("No such line in Stream");
		}
		
		return txtRecords;
		
		}
	

}
