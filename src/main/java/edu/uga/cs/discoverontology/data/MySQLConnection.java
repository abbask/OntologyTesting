package edu.uga.cs.discoverontology.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class MySQLConnection {
	
	final static Logger logger = Logger.getLogger(MySQLConnection.class);
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	//static final String JDBC_DB_URL = "jdbc:mysql://localhost:3306/discover_ontology?useUnicode=yes&characterEncoding=UTF-8"; //http://localhost/phpmyadmin/
	static final String JDBC_DB_URL = "jdbc:mysql://localhost:3306/discover_ontology"; //http://localhost/phpmyadmin/
	
	static final String JDBC_USER = "root";
	static final String JDBC_PASS = "coolerthanever";
	
	Connection connObj;
	Statement statement;
	PreparedStatement preparedStatement;
	
	public MySQLConnection() {
		connObj = null;
		statement = null;
		preparedStatement =null;
		openConnection();
	}

	public Connection openConnection() {
		
		try {
			Class.forName(JDBC_DRIVER);  
//			connObj = DriverManager.getConnection(JDBC_DB_URL + "?user=" + JDBC_USER);
			connObj = DriverManager.getConnection(JDBC_DB_URL + "?user=" + JDBC_USER + "&password=" + JDBC_PASS + "&serverTimezone=UTC&useSSL=false");
			//conn =  DriverManager.getConnection("jdbc:mysql://localhost/test?user=minty&password=greatsqldb")
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			logger.error("MySQLConnection: sqlException error - Connection openning error");			
		}
		return connObj;
	}
	
	public PreparedStatement createPreparedStatement(String sql) {
		
		try {
			
			preparedStatement = connObj.prepareStatement(sql);
			
		} catch (SQLException e) {
			logger.error("MySQLConnection: sqlException error - Connection openning error");	
			closeConnection();
		}
		return preparedStatement ;
	}
	
	public Statement createStatement() {
		
		try {
			
			statement = connObj.createStatement();
			
		} catch (SQLException e) {
			logger.error("MySQLConnection: sqlException error - Connection openning error");	
			closeConnection();
		}
		return statement ;
	}
	
	public void closeConnection() {
		try {
			connObj.close();
		} catch (SQLException e) {
			logger.error("MySQLConnection: sqlException error - Connection closing error");	
			e.printStackTrace();
		}
	}
}
