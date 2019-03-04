package edu.uga.cs.discoverontology.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;

import org.apache.log4j.Logger;

import edu.uga.cs.discoverontology.data.MySQLConnection;
import edu.uga.cs.discoverontology.model.MyTestSystem;
import edu.uga.cs.discoverontology.presentation.SystemTestNew;

public class SystemTestService {
	final static Logger logger = Logger.getLogger(SystemTestNew.class);
	
	public void Add(String Name, String EndPoint, String Graph ) {
		MySQLConnection conn = new MySQLConnection();
		Connection c = conn.openConnection();
		
		try {
			c.setAutoCommit(false);
			Statement stmtObj = c.createStatement();

			String query = "INSERT INTO system_tests (Name,Endpoint, Graph) VALUES ('" + Name + "','" + EndPoint + "', '" + Graph + "' )";
			stmtObj.executeUpdate(query); 

			c.commit();
			conn.closeConnection();
			logger.info("SystemTestService.add : new system_test commited.");
		} catch (Exception sqlException) {
			try {
				c.rollback();
				conn.closeConnection();
				logger.info("SystemTestService.add : new system_test is rolled back.");
			} catch (SQLException e) {
//				logger.info("SystemTestService.add : error in rolling back.");
				logger.error(e.getMessage(), e);
			}
//			logger.info("SystemTestService.add : error in committing.");
			logger.error(sqlException.getMessage(), sqlException);
		}
		conn = null;
	}
	
	public ArrayList<MyTestSystem> listAll() {
		
		ArrayList<MyTestSystem> list = new ArrayList<>();
		MySQLConnection conn = new MySQLConnection();
		try {

		PreparedStatement prepStatement = conn.createPreparedStatement("SELECT * FROM system_tests");
		ResultSet resObj = prepStatement.executeQuery();
		while(resObj.next()) {
			MyTestSystem myTestSystem = new MyTestSystem();
			myTestSystem.setID(resObj.getInt("ID"));
			myTestSystem.setName(resObj.getString("name"));
			myTestSystem.setEndPoint(resObj.getString("endpoint"));
			myTestSystem.setGraph(resObj.getString("graph"));
			
			list.add(myTestSystem);
        }
		conn.closeConnection();
		} catch (Exception sqlException) {
//			sqlException.printStackTrace();
			logger.error(sqlException.getMessage(), sqlException);
		}
		logger.info("SystemTestService.listAll :  system_tests retrieved.");
		return list;
	}
	
	public int remove(int id) {
		
		int systemTestId = 0;
		
		MySQLConnection conn = new MySQLConnection();
		Connection c = conn.openConnection();
		
		try {
			c.setAutoCommit(false);
			
			String queryUnit = "Select ID from unit_tests where system_test_id=?";
			PreparedStatement statementUnit= c.prepareStatement(queryUnit);
			statementUnit.setInt(1,id);
			ResultSet resUnit = statementUnit.executeQuery();
			
			while(resUnit.next()) {
				int unit_test_id = resUnit.getInt("ID");
				
				String queryGroup = "Select ID from expected_value_group where unit_test_id=?";
				PreparedStatement statementGroup= c.prepareStatement(queryGroup);
				statementGroup.setInt(1,unit_test_id);
				ResultSet resGroup = statementGroup.executeQuery();
				while(resGroup.next()) {
					int expected_value_group_id = resGroup.getInt("ID");
					PreparedStatement statementValue = c.prepareStatement("Delete FROM expected_values Where expected_value_group_id =" + expected_value_group_id);
					statementValue.executeUpdate();
				}
				
				PreparedStatement statementGroup2 = c.prepareStatement("Delete FROM expected_value_group Where unit_test_id =" + unit_test_id);
				statementGroup2.executeUpdate();
				
				PreparedStatement statementUnit2 = c.prepareStatement("DELETE FROM unit_tests WHERE ID=" + unit_test_id);
				statementUnit2.executeUpdate();
				
			}
			
			PreparedStatement statementSystem = c.prepareStatement("DELETE FROM system_tests WHERE ID=" + id);
			statementSystem.executeUpdate();
			
			c.commit();
			systemTestId = id;
			conn.closeConnection();
			logger.info("SystemTestService.remove : remove system_test commited.");
		} catch (Exception sqlException) {
			try {
				c.rollback();
				logger.info("SystemTestService.remove : remove system_test is rolled back.");
				conn.closeConnection();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
			logger.error(sqlException.getMessage(), sqlException);
		}
		conn = null;
		
		return systemTestId;
		
	}
	
	

}
