package statementModule;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.cez.dbUtil.DBUtil;
import com.cez.dbUtil.TimeMark;

import logModule.WriteLog;

public class DBInsertTemp extends DBUtil{
	

	private static int countTemp;
	private static Connection conn;

	public static void insertTempRecords() throws Exception {
		
		conn = getConnection();
		
		// if temp table doesn't exists create one
		createTable("ceztemp",conn);
		
		// get number of rows in temp table
		countTemp = countRows("ceztemp", conn);
		
		// set a number of records to be inserted 
		while(countTemp<10) {
			
		if (conn == null || conn.isClosed()) conn=getConnection();	
	
		// add time stamp to the table		
			String tmpStamp = new TimeMark().getTimeStamp();
			System.out.println(tmpStamp);
			
			String insertCommand = "INSERT INTO ceztemp (timemark) VALUES('" + tmpStamp + "') ";
			
			PreparedStatement prstm = conn.prepareStatement(insertCommand);			
			int count = prstm.executeUpdate();
			System.out.println(count + " Row(s) inserted");			
			countTemp++;

			DBUtil.close(conn);
			
		// add time stamp to local text file
			WriteLog.controlLog(tmpStamp, statLog);
		// set a time between insertion of records - ms
			Thread.sleep(6000); 
		} 

	}

}

