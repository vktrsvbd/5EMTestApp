package com.cez.dbUtil;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {

	private static String jdbcUrl;
	private static String jdbcUser;
	private static String jdbcPass;
	private static boolean isInitialized;
	private static String driverClass;
	public static String tmpLog;
	
	private static void initialize() {
		if(!isInitialized) {
			
			try {
				Properties properties = new Properties();
				InputStream source = DBUtil.class.getResourceAsStream("jdbc.properties");
				properties.load(source);
				source.close();
				
				jdbcUser = properties.getProperty("jdbcUser");
				jdbcPass = properties.getProperty("jdbcPass");
				jdbcUrl = properties.getProperty("jdbcUrl");
				driverClass = properties.getProperty("driverClass");
				tmpLog = properties.getProperty("tmpLog");
				isInitialized = true;
				
				} catch (IOException e) {
					System.err.println("Error while initializing JDBC...");
				}			 
		}
	}
	
	public static Connection getConnection() throws Exception {
		
		if(!isInitialized)
			initialize();
		try { Class.forName(driverClass); }catch (Exception e) {
			System.out.println(e.toString()); }
		return DriverManager.getConnection(jdbcUrl,jdbcUser,jdbcPass);
		
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
		String insertCommand = "CREATE TABLE IF NOT EXISTS "+ tableName+"(id int NOT NULL AUTO_INCREMENT, timemark varchar(30), PRIMARY KEY(id))";
		PreparedStatement prstm = conn.prepareStatement(insertCommand);
		int nom = prstm.executeUpdate();
		System.out.println("Table Ok" + nom);		
	}
	
	public static void table2Table(String tableFromName, String tableToName, Connection conn) throws SQLException {
		String insertCommand = "INSERT INTO "+tableToName+" SELECT * FROM "+tableFromName;
		PreparedStatement prstm = conn.prepareStatement(insertCommand);
		int nom = prstm.executeUpdate();
		System.out.println("Table Ok" + nom);
	}
	
	public static void dropTable(String tableName, Connection conn) throws SQLException {
		String insertCommand = "DROP TABLE "+tableName;
		PreparedStatement prstm = conn.prepareStatement(insertCommand);
		int nom = prstm.executeUpdate();
		System.out.println("Table Ok" + nom);
		
	}
	
	// returns no of lines
	public static int findDBRecord(String tableName, Connection conn, String text) throws SQLException {		
		String insertCommand = "SELECT "+ text+" FROM "+ tableName;
		PreparedStatement prstm = conn.prepareStatement(insertCommand);
		int nom = prstm.executeUpdate();
		return nom;
		
	}
}
