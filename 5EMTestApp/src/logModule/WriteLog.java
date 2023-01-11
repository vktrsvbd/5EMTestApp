package logModule;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteLog {
	
	// The header of the log	
	public static void headerLog(String filePlace) throws IOException
	{
		fileCreate(filePlace);
		FileWriter myWriter = new FileWriter(filePlace, true);
		try {
			myWriter.write("=========================================================================="+"\n");
			myWriter.write("|| Start of the test"+"                                                    ||\n");
			myWriter.write("||\t\t\t\t\t\t\t\t\t||"+"\n");			
		} catch (IOException e) {
			System.out.println("No record inserted");
		}
		try {
				myWriter.close();
			} catch (IOException e) {
				System.out.println("unable to close the file for writing");
			}
		}

	
	// write into the control log
	public static void controlLog(String record, String filePlace) throws IOException {
	
	fileCreate(filePlace);
		
	FileWriter myWriter = new FileWriter(filePlace, true);
	try {
		myWriter.write(record+"\n");
	} catch (IOException e) {
		System.out.println("No record inserted");
	}
	try {
			myWriter.close();
		} catch (IOException e) {
			System.out.println("unable to close the file for writing");
		}
	}
	
	//create a file
	public static void fileCreate(String filePlace) {
		
		File file = new File(filePlace);
		
		if(!file.exists()) {
		 try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("Unable to create the file at this location");
		}
		}
		
		
	}
	
	public static void fileDelete(File file) {
		
		if(file.exists())
			try {
				file.delete();
				System.out.println("The file was sucessfuly deleted");
			} catch (Exception e) {				
				System.out.println("Unable to delete the file");
				e.printStackTrace();
			}		
	}
	
	public static void fileClear(String filePlace) throws IOException {
		
	FileWriter contentClear = new FileWriter(filePlace);
		
		try {
			contentClear.write("");
			System.out.println("The record was sucessfuly cleared");
		} catch (IOException e) {
			System.out.println("No record cleared");
		}
		try {
			contentClear.close();
			} catch (IOException e) {
				System.out.println("unable to close the file for clearing");
			}
		}
	
	}

