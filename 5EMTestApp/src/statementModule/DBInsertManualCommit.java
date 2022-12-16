package statementModule;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.cez.dbUtil.DBUtil;
import com.cez.dbUtil.TimeMark;
import logModule.WriteLog;

public class DBInsertManualCommit extends DBUtil{
	
	private static Connection conn;
	private static int i =0;
	private static Statement stmt;

	public static void insertDBRecords() throws Exception {
		
		// if cezdb table doesn't exists create one
		conn=getConnection();
		createTable("cezdb",conn);
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		// set a number of records to be inserted 
		while(i<10) {
				
		// add time stamp to the table		
			String tmpStamp = new TimeMark().getTimeStamp();
			System.out.println(tmpStamp);		
			
			String insertCommand = "INSERT INTO cezdb (timemark) VALUES('" + tmpStamp + "') ";
			
			System.out.println("for index: "+ i + "the result is: "+insertCommand);
			stmt.addBatch(insertCommand);
			
		// add time stamp to local text file
			WriteLog.controlLog(tmpStamp, tmpLog);
		// set a time between insertion of records - ms
			Thread.sleep(6000);
			i++;			
		} 
		
		// set for manual commit
		conn.setAutoCommit(false);
		int[] count = stmt.executeBatch();
		conn.commit();
		DBUtil.close(conn);

		
		

	}

}

