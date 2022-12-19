package statementModule;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.cez.dbUtil.DBUtil;
import com.cez.dbUtil.TimeMark;
import logModule.WriteLog;

public class DBInsertManualCommit extends DBUtil{
	
	private static String tmStamp;
	private static Connection conn;
	private static int i;
	private static Statement stmt;
	private static List<String> tempStamps =  new ArrayList<>();
	private static long stampdiffer, sysMillis; 
	

	public static void insertDBRecords() throws Exception {
		tempStamps.clear();
		
		try {
			
		conn=getConnection();
		
		// if cezdb table doesn't exists create one
		createTable("cezdb",conn);
		
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		i=0;
		// set a number of records to be inserted 
		while(i<10) {
				
		// add time stamp to the table		
			tmStamp = new TimeMark().getTimeStamp();
			sysMillis = new TimeMark().getSysMilliTime();

			tempStamps.add(tmStamp);
			
			if (i>=1) { stampdiffer = new TimeMark().calculateDifference(tempStamps.get(i), tempStamps.get(i-1), "milli");
				
			System.out.println("Tady je value of tempStamps "+tempStamps.get(i));
			System.out.println("Record No. "+ (i+1) +" Timestamp is: "+tmStamp+" The difference is: "+ String.valueOf(stampdiffer));
				}

			String insertCommand = "INSERT INTO cezdb (timemark) VALUES('" + tmStamp + "') ";

			stmt.addBatch(insertCommand);
			
		// add time stamp to local text file
			if(i==0) {
			WriteLog.controlLog(tmStamp , statLog); 
			}else {
				WriteLog.controlLog(tmStamp + "The difference in seconds is: "+ String.valueOf(stampdiffer), statLog);
			}
			
		// set a time between insertion of records - ms
			Thread.sleep(6000);
			i++;			
		} 
		i=0;
		// set for manual commit
		conn.setAutoCommit(false);
		int[] count = stmt.executeBatch();
		
			conn.commit();
			System.out.println("The batch of records was inserted in to the DB");
			WriteLog.controlLog("Record inserted to the DB \n", statLog);
		} catch (SQLException e) {
			doRollback(conn);
			System.out.println("Rollback");
			WriteLog.controlLog("No data inserted - rolledBack \n", statLog);
		}		
		conn.setAutoCommit(true);		
		DBUtil.close(conn);
	}
}







