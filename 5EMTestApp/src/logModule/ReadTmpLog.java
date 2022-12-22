package logModule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.cez.dbUtil.DBUtil;

public class ReadTmpLog extends DBUtil {
	
	
	
	public static void getTextLine(String path, int lineIndex) throws IOException {
	
		//List<String>allLinew = Files.readAllLines(Paths.get(tmpLog));		
		// for(String line:allLines){
		// System.out.println(line);}
		FileReader file = new FileReader(path);
		BufferedReader input = new BufferedReader(file);
		String lineTxt = input.readLine();
				
	
	}
	
}
