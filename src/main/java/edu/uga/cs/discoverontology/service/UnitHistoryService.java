package edu.uga.cs.discoverontology.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import org.apache.log4j.Logger;

import edu.uga.cs.discoverontology.data.MySQLConnection;


public class UnitHistoryService {
	final static Logger logger = Logger.getLogger(UnitHistoryService.class);
	
	public int Add(int system_test_history_id, int unit_test_id, String status, String message) {
		MySQLConnection conn = new MySQLConnection();
		Connection c = conn.openConnection();
		int unit_test_history_id= 0;
		try {
			c.setAutoCommit(false);
			Statement stmtObj = c.createStatement();
			

			String query = "INSERT INTO unit_test_history (system_test_history_id,unit_test_id,status,message) VALUES (" + system_test_history_id + "," + unit_test_id + ",'" + status +  "','" + message + "')";
			stmtObj.executeUpdate(query,Statement.RETURN_GENERATED_KEYS); 
			
			ResultSet rs = stmtObj.getGeneratedKeys();
            if(rs.next()){
            	unit_test_history_id=rs.getInt(1);
            }
            

			c.commit();
			conn.closeConnection();
			logger.info("HistoryService.add : new History commited.");
		} catch (Exception sqlException) {
			try {
				c.rollback();
				conn.closeConnection();
				logger.info("HistoryService.add : new History is rolled back.");
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
			logger.error(sqlException.getMessage(), sqlException);
		}
		conn = null;
		return unit_test_history_id;
	}
	

}
