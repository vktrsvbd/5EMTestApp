package com.cez.dbUtil;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeMark {

static SimpleDateFormat sdf;
	
	public static Timestamp getTimeStamp() {
		return (new Timestamp(System.currentTimeMillis()));
	}

	public static String getTimeSimpleFormat(Timestamp ts) {
		Date d = ts;
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		return df.format(d).toString();
	}

	public Timestamp stringToTimestamp(String date) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			Date parsedDate = dateFormat.parse(date);
			return new Timestamp(parsedDate.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
			}			
	}
	
	public static Integer calculateDifference(Timestamp date1, Timestamp date2, String value) {

		long milliseconds = date1.getTime()- date2.getTime();
		if(value.equals("milli")) return (int) milliseconds;
		if(value.equals("second")) return (int) (milliseconds/1000);
		if(value.equals("minute")) return (int) (milliseconds/1000/60);		
		return (int) milliseconds;		
	}
}
