package controlModule;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.cez.dbUtil.DBUtil;
import com.mysql.cj.util.StringUtils;

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
	
//	public static void txtDBCompare(String txtPath, String dbTable, Connection conn)throws Exception{
//		Scanner r = new Scanner(new File(txtPath));
//		String SQL_Querry = "SELECT * FROM "+ dbTable + " WHERE ORIGINATING_SYSTEM_ID = '"
//				while(r.hasNextLine()) {
//					String fields[]= r.nextLine().split("|");
//					if (fields.length > 0 && !"ORIGINATING_SYSTEM_ID".equals(fields[0])){
//						ResultSet rs= stmt.executeQuery(SQL_Query + fields[0]+"'");
//						while(rs.next()) {
//							if(!StringUtils.isNullOrEmpty(fields[0])) && fields[0].equals(rs.getString("ORIGINATING_SYSTEM_ID"))
//				}
//			}
//		}
//	}
	
	

}
