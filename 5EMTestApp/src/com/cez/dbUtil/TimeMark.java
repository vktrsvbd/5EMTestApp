package com.cez.dbUtil;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeMark {

private static	long millis;
static SimpleDateFormat sdf;
	
	public static Timestamp getTimeStamp() {
		millis = System.currentTimeMillis();
		return  new Timestamp(millis);	
	}
	
	public static String getTimeSimpleFormat(Timestamp ts) {
		sdf = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss");
		System.out.println("Tady v Simple"+ (sdf.format(ts)).toString());
		return  (sdf.format(ts)).toString();	
		
	}
	
	public Timestamp stringToTimestamp(String date) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd HH:mm:ss");
			Date parsedDate = dateFormat.parse(date);
			return new Timestamp(parsedDate.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}			
	}
	
	public static int timeDifference(Timestamp ts1, Timestamp ts2) {
		return ts1.compareTo(ts2);		
	}
	
	public static Integer calculateDifference(Timestamp date1, Timestamp date2, String value) {

		long milliseconds = date1.getTime()- date2.getTime();
		
		System.out.println("here is thevalue of dete_1: "+ date1.getTime());
		System.out.println("here is thevalue of dete_2: "+ date2.getTime());

		if(value.equals("milli")) return (int) milliseconds;
		if(value.equals("second")) return (int) (milliseconds/1000);
		if(value.equals("minute")) return (int) (milliseconds/1000/60);
		
		return (int) milliseconds;
		
	}
}
