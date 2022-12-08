package com.cez.dbUtil;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimeMark {

private	long millis;
private	Timestamp timeStamp;
    	SimpleDateFormat sdf;
	
	public  String getTimeStamp() {
		millis = System.currentTimeMillis();
		timeStamp = new Timestamp(millis);
		sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:ms");
		return  sdf.format(timeStamp);
		
	}
}
