package statementModule;
import java.sql.Connection;
import com.cez.dbUtil.DBUtil;
import com.cez.dbUtil.TimeMark;

import controlModule.Comparator;
import logModule.WriteLog;

public class DBInsertRecords extends DBUtil {

	private static int countTemp;
	private static Connection conn;
	
	public static void recordsInsertion() throws Exception {
		
		conn = getConnection();
		// if temp table doesn't exists create one
		createTable("cezdb",conn);
		
		// get number of rows in temp table
		  countTemp = countRows("ceztemp", conn);
		  System.out.println("Number of rows in temprary table is: "+countTemp);
		 
		
		table2Table("ceztemp", "cezdb", conn);
		
		String rowCount = Comparator.twoTableCompare("ceztemp", "cezdb", conn);
		
		String tmpStamp = new TimeMark().getTimeStamp();
		System.out.println(tmpStamp);
		
		WriteLog.controlLog("Number of same records: "+ rowCount+ " @ time: "+tmpStamp, statLog);
		
		dropTable("ceztemp", conn);
		DBUtil.close(conn);
	}
	
}
