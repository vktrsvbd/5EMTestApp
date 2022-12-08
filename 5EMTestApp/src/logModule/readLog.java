package logModule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class readLog {
	
	
	
	public static void getTextLine() throws IOException {
	
		List<String>allLinew = Files.readAllLines(Paths.get("sample.txt"));
		
		// for(String line:allLines){
		// System.out.println(line);}
		
	
	
	}
	
}
