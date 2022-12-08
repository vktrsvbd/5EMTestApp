package statementModule;

import java.sql.Connection;
import com.cez.dbUtil.DBUtil;

public class DBInsertRecords extends DBUtil {

	private static int countTemp;
	private static Connection conn;
	
	public static void recordsInsertion() throws Exception {
		
		conn = getConnection();
		// if temp table doesn't exists create one
		createTable("cezDB",conn);
		
		// get number of rows in temp table
		countTemp = countRows("ceztemp", conn);
		System.out.println("Number of rows in temprary table is: "+countTemp);
		
		table2Table("ceztemp", "cezDB", conn);
		System.out.println("Number of rows in cezDB table is: "+countTemp);
	}
	
}
