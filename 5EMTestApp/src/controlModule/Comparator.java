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
	
private static long noOfLines =0;
protected static ArrayList<String> txtRecords = new ArrayList<>();
	
	// search each txt record in DB table
	public static void txtToDBCompare(String txtPath, String dbTable, Connection conn) throws SQLException{
 		  ArrayList<String>txtItem = new ArrayList<>();
		  txtItem = txtToStream(txtPath);
		  
		  for(String txt: txtItem) {
			  String SQL_Querry = "SELECT * FROM "+ dbTable + " WHERE timemark = '"+txt+"'";
			  Statement stmt = conn.createStatement();
			  ResultSet rs = stmt.executeQuery(SQL_Querry);
		  }
	  }
	 
	// generates a List of last inserted records in txt File/Log
	public static ArrayList<String> txtToStream(String txtPath) {
		
		txtRecords.clear();
		
		try(	//  linesNo = Files.lines(Paths.get(txtPath)).count();
				Stream<String>lines = Files.lines(Paths.get(txtPath))){
			noOfLines = lines.count();
			for(int i=0; i< linesInsert; i++) {
				String li = Files.readAllLines(Paths.get(txtPath)).get((int) (noOfLines-(linesInsert-i)));
				txtRecords.add(li.substring(20, 39));
				System.out.println("The record: "+ txtRecords.get(i));
				}
		}catch (Exception e) {
			System.out.println("No such line in Stream");
		}
		return txtRecords;
		
		}
	

}
