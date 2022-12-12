package logModule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.cez.dbUtil.DBUtil;

public class ReadTmpLog extends DBUtil {
	
	
	
	public static void getTextLine() throws IOException {
	
		List<String>allLinew = Files.readAllLines(Paths.get(tmpLog));
		
		// for(String line:allLines){
		// System.out.println(line);}
		
	
	
	}
	
}
