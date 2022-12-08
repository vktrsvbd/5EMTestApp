package com.cez.dbUtil;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {

	private static String jdbcUrl;
	private static boolean isInitialized;
	
	private static void initialize() {
		if(!isInitialized) {
			
			try {
				Properties properties = new Properties();
				InputStream source = DBUtil.class.getResourceAsStream("jdbc.properties");
				properties.load(source);
				source.close();
				
				jdbcUrl = properties.getProperty("jdbcUrl");
				isInitialized = true;
				
			} catch (IOException e) {
				System.err.println("Error while initializing JDBC...");
			}
			 
		}
	}
	
	public static Connection getConnection() throws Exception {
		
		if(!isInitialized)
			initialize();
		try { Class.forName("com.mysql.cj.jdbc.Driver"); }catch (Exception e) {
		System.out.println(e.toString()); }
		// return DriverManager.getConnection(jdbcUrl);
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
		
	}
	
	public static void close(Connection conn) throws Exception {
		if(conn!=null) 
			conn.close();		
	}
	
	public static void close(Statement stm) throws Exception {
		if(stm!=null) 
			stm.close();		
	}
	
	public static int countRows(String table, Connection conn) throws Exception {
	
		Statement stm = conn.createStatement();
		String insertCommand = "SELECT COUNT(*) FROM "+ table;
		ResultSet count = stm.executeQuery(insertCommand);
		count.next();			
		return count.getInt(1);			
	}
	
	public static void createTable(String tableName, Connection conn) throws SQLException {
		Statement stm = conn.createStatement();
		String insertCommand = "CREATE TABLE IF NOT EXISTS "+ tableName+"(id int NOT NULL AUTO_INCREMENT, timemark varchar(30), PRIMARY KEY(id))";
		int nom = stm.executeUpdate(insertCommand);
		System.out.println("Table Ok" + nom);		
	}
	
	public static void table2Table(String tableFromName, String tableToName, Connection conn) throws SQLException {
		Statement stm = conn.createStatement();
		String insertCommand = "INSERT INTO "+tableToName+" SELECT * FROM "+tableFromName;
		int nom = stm.executeUpdate(insertCommand);
		System.out.println("Table Ok" + nom);
	}
	
	public static void dropTable(String tableName, Connection conn) throws SQLException {
		Statement stm = conn.createStatement();
		String insertCommand = "DROP TABLE "+tableName;
		int nom = stm.executeUpdate(insertCommand);
		System.out.println("Table Ok" + nom);
		
	}
	
	// returns no of lines
	public static int findDBRecord(String tableName, Connection conn, String text) throws SQLException {
		Statement stm = conn.createStatement();
		String insertCommand = "SELECT "+ text+" FROM "+ tableName;
		int nom = stm.executeUpdate(insertCommand);
		return nom;
		
	}
}
