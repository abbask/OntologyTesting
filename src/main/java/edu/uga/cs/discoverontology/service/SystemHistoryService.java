package edu.uga.cs.discoverontology.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import edu.uga.cs.discoverontology.data.MySQLConnection;
import edu.uga.cs.discoverontology.model.MyTestSystem;
import edu.uga.cs.discoverontology.model.MyUnitTest;
import edu.uga.cs.discoverontology.model.SystemTestHistory;
import edu.uga.cs.discoverontology.model.UnitTestHistory;

public class SystemHistoryService {
	final static Logger logger = Logger.getLogger(SystemHistoryService.class);

	public int Add(int system_test_id) {
		MySQLConnection conn = new MySQLConnection();
		Connection c = conn.openConnection();
		int system_test_history_id= 0;
		try {
			c.setAutoCommit(false);
			Statement stmtObj = c.createStatement();
			
			Date date = new Date();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());

			String query = "INSERT INTO system_test_history (system_test_id,date) VALUES (" + system_test_id + ",'" + sqlDate + "')";
			stmtObj.executeUpdate(query, Statement.RETURN_GENERATED_KEYS); 
			
			ResultSet rs = stmtObj.getGeneratedKeys();
            if(rs.next()){
            	system_test_history_id=rs.getInt(1);
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
		return system_test_history_id;
	}
	
	public ArrayList<SystemTestHistory> ListbyNumber(int numberofRows) {
		MySQLConnection conn = new MySQLConnection();
		Connection c = conn.openConnection();
		
		ArrayList<SystemTestHistory> list = new ArrayList<>();
		
		try {
			c.setAutoCommit(false);
			Statement stmtSys = c.createStatement();
			

			String query = "SELECT sh.ID as systemTestHistoryId, sh.date as date, s.ID as systemTestId, s.Name as systemTestName FROM system_test_history sh Join system_tests s ON sh.system_test_id = s.ID ORDER BY sh.date desc LIMIT " + numberofRows ;
			ResultSet rsSystem = stmtSys.executeQuery(query); 
			
			while(rsSystem.next()) {
				
				int systemTestHistoryId = rsSystem.getInt("systemTestHistoryId");
				
				MyTestSystem systemTest = new MyTestSystem();
				systemTest.setID(rsSystem.getInt("systemTestId"));
				systemTest.setName(rsSystem.getString("systemTestName"));
				
				Statement stmtUnit = c.createStatement();
				ArrayList<UnitTestHistory> unitHistoryList = new ArrayList<>();
				ResultSet rsUnit = stmtUnit.executeQuery("SELECT uh.ID as ID, uh.status as status, uh.message as message, u.name as unitTestName, u.ID as unitTestID FROM unit_test_history uh Join unit_tests u ON u.ID = uh.unit_test_id where uh.system_test_history_id=" + systemTestHistoryId); 
				
				while(rsUnit.next()) {
					UnitTestHistory unitTestHistory = new UnitTestHistory();
					unitTestHistory.setID(rsUnit.getInt("ID"));
					unitTestHistory.setStatus(rsUnit.getString("status"));
					unitTestHistory.setMessage(rsUnit.getString("message"));
					
					MyUnitTest unitTest = new MyUnitTest();
					unitTest.setID(rsUnit.getInt("unitTestID"));
					unitTest.setName(rsUnit.getString("unitTestName"));
					
					unitTestHistory.setMyUnitTest(unitTest);
					
					unitHistoryList.add(unitTestHistory);
					
				}
				
				
				SystemTestHistory systemTestHistory = new SystemTestHistory();
				systemTestHistory.setID(rsSystem.getInt("systemTestHistoryId"));
				systemTestHistory.setDate(rsSystem.getDate("date"));
				systemTestHistory.setMySystemTest(systemTest);
				systemTestHistory.setUnitTestHistorys(unitHistoryList);
				
				
				list.add(systemTestHistory);
				
			}

			c.commit();
			conn.closeConnection();
			logger.info("HistoryService.ListbyNumber : History retrived.");
		} catch (Exception sqlException) {
			try {
				c.rollback();
				conn.closeConnection();
				logger.info("HistoryService.ListbyNumber : retriving History is rolled back.");
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
			logger.error(sqlException.getMessage(), sqlException);
		}
		conn = null;
		return list;
	}
}
