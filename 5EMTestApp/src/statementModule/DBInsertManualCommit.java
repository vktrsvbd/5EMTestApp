package statementModule;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.cez.dbUtil.DBUtil;
import com.cez.dbUtil.TimeMark;
import controlModule.Comparator;
import logModule.WriteLog;

public class DBInsertManualCommit extends DBUtil{
	
	private static Timestamp tmStamp;
	private static Connection conn;
	private static int i=0;
	private static Statement stmt;
	private static List<Timestamp> tempStamps =  new ArrayList<>();
	private static List<Integer>timeCompare = new ArrayList<>();
	private static Integer stampdiffer; 
	

	public static void insertDBRecords() throws Exception {
		tempStamps.clear();
		timeCompare.clear();
		int noOfRecordsInDB =0;
		
		try {
			
		conn=getConnection();
		
		System.out.println("Connection OK");
		// if cezdb table doesn't exists create one
		createTable(tableName,conn);
		
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		i=0;
		
		// set a number of records to be inserted 
		while(i<linesInsert) {
				
			
			 // create timeStamp 
			  tmStamp = TimeMark.getTimeStamp() ;

		// save the timestamp to the list	
			tempStamps.add(tmStamp); 
		
		// insert SQL command to the statement - batch
			String insertCommand = "INSERT INTO "+tableName+" (timemark) VALUES('" + TimeMark.getTimeSimpleFormat(tmStamp) + "') ";
			stmt.addBatch(insertCommand);
			
			
		// add the first txt log  - no time difference present
			if(i==0) {
			WriteLog.headerLog(statLog);
			System.out.println("Record No. " + (i+1) +"||\t Timestamp is: ||\t"+ TimeMark.getTimeSimpleFormat(tmStamp) +"\t| The difference is: 0 \t||");
			WriteLog.controlLog("||\tNot commited  |\t "+ TimeMark.getTimeSimpleFormat(tmStamp)+"\t|\t\t\t||", statLog); 
			}
		// add txt log with time difference
			if (i>=1) { stampdiffer = TimeMark.calculateDifference(tempStamps.get(i),tempStamps.get(i-1), "second");
			timeCompare.add(stampdiffer);
			System.out.println("Record No. " + (i+1) +"||\t Timestamp is: ||\t"+ TimeMark.getTimeSimpleFormat(tmStamp) +"\t| The difference is: "+ stampdiffer.toString()+"\t||");
			WriteLog.controlLog("||\tNot commited  |\t " + TimeMark.getTimeSimpleFormat(tmStamp) +  "\t|  Time difference: " + stampdiffer.toString()+"\t||" , statLog);
			}	
			
		// set a time between insertion of records - ms
			Thread.sleep(sleepTime);
			i++;			
		} 

		// set for manual commit
		conn.setAutoCommit(false);
		stmt.executeBatch();
		
		// testing if all values in stmt are of correct time gap 
		for(Integer diff : timeCompare) {
			if(diff != sleepTime/1000) 				
			throw new Exception("Wrong time gap between the records");
		}

		conn.commit();
		
		noOfRecordsInDB= Comparator.txtToDBCompare(statLog, tableName, conn);
		if (noOfRecordsInDB == linesInsert) {
			WriteLog.controlLog(noOfRecordsInDB+" records inserted to the DB - records check with OK result \n", statLog);
		} else throw new Exception("Wrong or missing data in the table");
			
		
		} catch (Exception e)
			{
				doRollback(conn);
				System.out.println("Rollback");
				WriteLog.controlLog("Wrong or missing data in the table \n", statLog);
				WriteLog.controlLog("No data inserted - rolledBack \n", statLog);
			}
		
		conn.setAutoCommit(true);
		DBUtil.close(stmt);
		DBUtil.close(conn);
	}
}