package com.cez.dbUtil;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeMark {

private static	long millis;
private	Timestamp timeStamp;
    	SimpleDateFormat sdf;
	
	public  String getTimeStamp() {
		millis = System.currentTimeMillis();
		timeStamp = new Timestamp(millis);
		sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:ms");
		return  sdf.format(timeStamp);
		
	}
	
	public long getSysMilliTime() {
		return millis;
		
	}
	
	public Timestamp stringToTimestamp(String date) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:ms");
			Date parsedDate = dateFormat.parse(date);
			return new Timestamp(parsedDate.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}			
	}
	
	public Long calculateDifference(String date1, String date2, String value) {
		Timestamp date_1 = stringToTimestamp(date1);
		Timestamp date_2 = stringToTimestamp(date2);
		long milliseconds = date_1.getTime()- date_2.getTime();
		
		if(value.equals("milli")) return milliseconds;
		if(value.equals("second")) return milliseconds/1000;
		if(value.equals("minute")) return milliseconds/1000/60;
		
		return milliseconds;
		
	}
}
