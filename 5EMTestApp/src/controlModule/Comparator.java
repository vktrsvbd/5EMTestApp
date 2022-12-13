package controlModule;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.cez.dbUtil.DBUtil;

public class Comparator extends DBUtil {
	
	
	// table 1 is tempDB , table 2 is db
	public static String twoTableCompare(String table1, String table2, Connection conn)throws Exception {
		Statement stm = conn.createStatement();
		String insertCommand = "SELECT COUNT(*) FROM "+ table1 +" INNER JOIN "+ table2 +" ON "+table1+".timemark = "+table2+".timemark";
		ResultSet count = stm.executeQuery(insertCommand);
		count.next();	
		System.out.println("Number of same records is: "+count.getInt(1));
		return String.valueOf(count.getInt(1));
				
	}

}
