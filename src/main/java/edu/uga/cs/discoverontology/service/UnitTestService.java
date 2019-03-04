package edu.uga.cs.discoverontology.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.uga.cs.discoverontology.data.MySQLConnection;
import edu.uga.cs.discoverontology.model.*;
import edu.uga.cs.discoverontology.presentation.SystemTestNew;

public class UnitTestService {
	
	final static Logger logger = Logger.getLogger(UnitTestService.class);
	
	public void Add(String name, String query, String assertType,ExpectedValue[][] expectedValues, String  message, int systemTestID) {
		MySQLConnection conn = new MySQLConnection();
		Connection c = conn.openConnection();
		
		try {
			c.setAutoCommit(false);
//			Statement stmtObj = c.createStatement();
			
			//String queryString = "INSERT INTO unit_tests (name,query,assert_type,message,system_test_id) VALUES ('" + name + "','" + query + "', '" + assertType + "','" +  message + "'," + systemTestID + ")";
			String queryString = "INSERT INTO unit_tests (name,query,assert_type,message,system_test_id) VALUES (?,?,?,?,?)";
			PreparedStatement statement= c.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1,name);
			statement.setString(2,query);
			statement.setString(3,assertType);
			statement.setString(4,message);
			statement.setInt(5,systemTestID);
			statement.executeUpdate();
			
			//stmtObj.executeUpdate(queryString); 
			ResultSet rs = statement.getGeneratedKeys();
			int unit_test_id = 0;
            if(rs.next()){
            	unit_test_id=rs.getInt(1);
            }
            
			for(ExpectedValue[] ex : expectedValues) {
				String queryString2 = "INSERT INTO expected_value_group (unit_test_id) VALUES ('" + unit_test_id + "')";
				statement.executeUpdate(queryString2,Statement.RETURN_GENERATED_KEYS); 
				ResultSet rs2 = statement.getGeneratedKeys();
				int expected_value_group_id = 0;
	            if(rs2.next()){
	            	expected_value_group_id = rs2.getInt(1);
	            }
	            
	            for(ExpectedValue e : ex) {
	            	System.out.println(e.getValue());
	            	String queryString3 = "INSERT INTO expected_values (originalName, useName, indx, value, expected_value_group_id) VALUES ('" + e.getOriginalName() + "','" + e.getUseName() + "','" + e.getIndex() + "','" + e.getValue() + "'," + expected_value_group_id +")";
	            	statement.executeUpdate(queryString3); 
	            }
			}

			c.commit();
			conn.closeConnection();
			logger.info("UnitTestService.add : new unit_test commited.");
		} catch (Exception sqlException) {
			try {
				c.rollback();
				logger.info("UnitTestService.add : new unit_test is rolled back.");
				conn.closeConnection();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
			logger.error(sqlException.getMessage(), sqlException);
		}
		conn = null;
	}
	
	public ArrayList<MyUnitTest> listAll() {
		
		ArrayList<MyUnitTest> list = new ArrayList<>();
		MySQLConnection conn = new MySQLConnection();
		try {

//		PreparedStatement prepStatement = conn.openConnection().prepareStatement("SELECT u.id,u.name,u.query,u.message, u.system_test_id, s.name as system_test_name  FROM unit_tests u Inner join system_tests s on u.system_test_id = s.ID Order by system_test_name ");
		Statement stmt = conn.createStatement();
		ResultSet resObj = stmt.executeQuery("SELECT u.id,u.name,u.query,u.message, u.system_test_id, s.name as system_test_name  FROM unit_tests u Inner join system_tests s on u.system_test_id = s.ID Order by system_test_name ");
		while(resObj.next()) {
			int id = resObj.getInt("id");
			MyUnitTest myUnitTest = new MyUnitTest();
			myUnitTest.setID(resObj.getInt("id"));
			myUnitTest.setName(resObj.getString("name"));
			myUnitTest.setQuery(resObj.getString("query"));
			myUnitTest.setMessage(resObj.getString("message"));
			
			MyTestSystem systemTest = new MyTestSystem();
			systemTest.setID(resObj.getInt("system_test_id"));
			systemTest.setName(resObj.getString("system_test_name"));
			myUnitTest.setSystemTest(systemTest);
			
//			PreparedStatement groupStatement = conn.openConnection().prepareStatement("SELECT ID, unit_test_id  from expected_value_group where unit_test_id=" + id);
			Statement stmtGroupRes = conn.createStatement();
			ResultSet groupRes = stmtGroupRes.executeQuery("SELECT ID, unit_test_id  from expected_value_group where unit_test_id=" + id);
			ArrayList<ExpectedValuesGroup> expectedValuesGroups = new ArrayList<>();
			while(groupRes.next()) {
				ExpectedValuesGroup expectedValuesGroup = new ExpectedValuesGroup();
				expectedValuesGroup.setID(groupRes.getInt("ID"));
				
//				PreparedStatement valuesStatement = conn.openConnection().prepareStatement("SELECT 	ID, originalName,useName, indx,value,expected_value_group_id  from expected_values where expected_value_group_id=" + expectedValuesGroup.getID());
				Statement stmtValueRes = conn.createStatement();
				ResultSet valueRes = stmtValueRes.executeQuery("SELECT 	ID, originalName,useName, indx,value,expected_value_group_id  from expected_values where expected_value_group_id=" + expectedValuesGroup.getID());
				ArrayList<ExpectedValue> expectedValues = new ArrayList<>();
				
				while(valueRes.next()) {
					ExpectedValue expectedValue = new ExpectedValue();
					expectedValue.setID(valueRes.getInt("ID"));
					expectedValue.setOriginalName(valueRes.getString("originalName"));
					expectedValue.setUseName(valueRes.getString("useName"));
					expectedValue.setIndex(valueRes.getString("indx"));
					expectedValue.setValue(valueRes.getString("value"));
					expectedValue.setGroupId(valueRes.getInt("expected_value_group_id"));
					expectedValues.add(expectedValue);
				}
				
				expectedValuesGroup.setExpectedValues(expectedValues);
			}
			
			

			myUnitTest.setExpectedValueGroups(expectedValuesGroups);
			
			list.add(myUnitTest);
        }
		conn.closeConnection();
		
		} catch (Exception sqlException) {
			logger.error(sqlException.getMessage(), sqlException);
		}
		logger.info("UnitTestService.listAll :  unit_tests retrieved.");
		conn = null;
		return list;
	}
	
	public ArrayList<MyUnitTest> listBySystemTest(int system_test_id) {
		
		ArrayList<MyUnitTest> list = new ArrayList<>();
		MySQLConnection conn = new MySQLConnection();
		try {

		PreparedStatement prepStatement = conn.createPreparedStatement("SELECT u.id,u.name,u.query,u.message, u.system_test_id, s.name as system_test_name, s.Endpoint as endpoint, s.Graph as graph  FROM unit_tests u Inner join system_tests s on u.system_test_id = s.ID where system_test_id=" + system_test_id);
		
		ResultSet resObj = prepStatement.executeQuery();
		while(resObj.next()) {
			int id = resObj.getInt("id");
			MyUnitTest myUnitTest = new MyUnitTest();
			myUnitTest.setID(resObj.getInt("id"));
			myUnitTest.setName(resObj.getString("name"));
			myUnitTest.setQuery(resObj.getString("query"));
			myUnitTest.setMessage(resObj.getString("message"));
			
			MyTestSystem systemTest = new MyTestSystem();
			systemTest.setID(resObj.getInt("system_test_id"));
			systemTest.setName(resObj.getString("system_test_name"));
			systemTest.setEndPoint(resObj.getString("endpoint"));
			systemTest.setGraph(resObj.getString("graph"));
			myUnitTest.setSystemTest(systemTest);
			
			PreparedStatement groupStatement = conn.createPreparedStatement("SELECT ID, unit_test_id  from expected_value_group where unit_test_id=" + id);
			ResultSet groupRes = groupStatement.executeQuery();
			ArrayList<ExpectedValuesGroup> expectedValuesGroups = new ArrayList<>();
			while(groupRes.next()) {
				ExpectedValuesGroup expectedValuesGroup = new ExpectedValuesGroup();
				expectedValuesGroup.setID(groupRes.getInt("ID"));
				
				PreparedStatement valuesStatement = conn.createPreparedStatement("SELECT 	ID, originalName,useName, indx,value,expected_value_group_id  from expected_values where expected_value_group_id=" + expectedValuesGroup.getID());
				ResultSet valueRes = valuesStatement.executeQuery();
				ArrayList<ExpectedValue> expectedValues = new ArrayList<>();
				
				while(valueRes.next()) {
					ExpectedValue expectedValue = new ExpectedValue();
					expectedValue.setID(valueRes.getInt("ID"));
					expectedValue.setOriginalName(valueRes.getString("originalName"));
					expectedValue.setUseName(valueRes.getString("useName"));
					expectedValue.setIndex(valueRes.getString("indx"));
					expectedValue.setValue(valueRes.getString("value"));
					expectedValue.setGroupId(valueRes.getInt("expected_value_group_id"));
					expectedValues.add(expectedValue);
				}
				
				expectedValuesGroup.setExpectedValues(expectedValues);
			}
			
			

			myUnitTest.setExpectedValueGroups(expectedValuesGroups);
			list.add(myUnitTest);
        }
		conn.closeConnection();
		
		} catch (Exception sqlException) {
			logger.error(sqlException.getMessage(), sqlException);
		}
		logger.info("UnitTestService.listBySystemTest :  unit_tests retrieved by system_test_id.");
		conn = null;
		return list;
	}
	
	public MyUnitTest findByID(int id) {
		MyUnitTest myUnitTest = new MyUnitTest();
		MySQLConnection conn = new MySQLConnection();
		try {

			PreparedStatement prepStatement = conn.createPreparedStatement("SELECT u.id,u.name,u.query,u.message,u.assert_type, u.system_test_id, s.name as system_test_name, s.Endpoint as endpoint, s.Graph as graph  FROM unit_tests u Inner join system_tests s on u.system_test_id = s.ID where u.id=" + id);
			ResultSet resObj = prepStatement.executeQuery();
			while(resObj.next()) {
	//			int id = resObj.getInt("id");
				
				myUnitTest.setID(resObj.getInt("id"));
				myUnitTest.setName(resObj.getString("name"));
				myUnitTest.setQuery(resObj.getString("query"));
				myUnitTest.setAssertType(resObj.getString("assert_type"));
				myUnitTest.setMessage(resObj.getString("message"));
				
				MyTestSystem systemTest = new MyTestSystem();
				systemTest.setID(resObj.getInt("system_test_id"));
				systemTest.setName(resObj.getString("system_test_name"));
				systemTest.setEndPoint(resObj.getString("endpoint"));
				systemTest.setGraph(resObj.getString("graph"));
				myUnitTest.setSystemTest(systemTest);
				
				PreparedStatement groupStatement = conn.createPreparedStatement("SELECT ID, unit_test_id  from expected_value_group where unit_test_id=" + id);
				ResultSet groupRes = groupStatement.executeQuery();
				ArrayList<ExpectedValuesGroup> expectedValuesGroups = new ArrayList<>();
				
				while(groupRes.next()) {
					ExpectedValuesGroup expectedValuesGroup = new ExpectedValuesGroup();
					expectedValuesGroup.setID(groupRes.getInt("ID"));
					
					PreparedStatement valuesStatement = conn.createPreparedStatement("SELECT 	ID, originalName,useName, indx,value,expected_value_group_id  from expected_values where expected_value_group_id=" + expectedValuesGroup.getID());
					ResultSet valueRes = valuesStatement.executeQuery();
					ArrayList<ExpectedValue> expectedValues = new ArrayList<>();
					
					while(valueRes.next()) {
						ExpectedValue expectedValue = new ExpectedValue();
						expectedValue.setID(valueRes.getInt("ID"));
						expectedValue.setOriginalName(valueRes.getString("originalName"));
						expectedValue.setUseName(valueRes.getString("useName"));
						expectedValue.setIndex(valueRes.getString("indx"));
						expectedValue.setValue(valueRes.getString("value"));
						expectedValue.setGroupId(valueRes.getInt("expected_value_group_id"));
						expectedValues.add(expectedValue);
					}
					
					expectedValuesGroup.setExpectedValues(expectedValues);
					expectedValuesGroups.add(expectedValuesGroup);
				}
				myUnitTest.setExpectedValueGroups(expectedValuesGroups);
	        }
		
			conn.closeConnection();
		} catch (Exception sqlException) {
			logger.error(sqlException.getMessage(), sqlException);
		}
		logger.info("UnitTestService.findByID :  unit_test retrieved by id.");
		conn = null;
		return myUnitTest;
	}
	
	public int remove(int id) {
		
		int unitTestId = 0;
		
		MySQLConnection conn = new MySQLConnection();
		Connection c = conn.openConnection();
		
		try {
			c.setAutoCommit(false);
			String queryString = "Select ID from expected_value_group where unit_test_id=?";
			PreparedStatement prepStatement= c.prepareStatement(queryString);
			prepStatement.setInt(1,id);
			ResultSet resObj = prepStatement.executeQuery();
			while(resObj.next()) {
				PreparedStatement prepStatement2 = c.prepareStatement(queryString);
				int expected_value_group_id = resObj.getInt("ID");
				prepStatement2.executeUpdate("Delete FROM expected_values Where expected_value_group_id =" + expected_value_group_id);
			}
			
			prepStatement.executeUpdate("Delete FROM expected_value_group Where unit_test_id =" + id);
			
			prepStatement.executeUpdate("DELETE FROM unit_tests WHERE ID=" + id);
			
			
			c.commit();
			unitTestId = id;
			conn.closeConnection();
			logger.info("UnitTestService.remove : remove unit_test commited.");
		} catch (Exception sqlException) {
			try {
				c.rollback();
				logger.info("UnitTestService.remove : remove unit_test is rolled back.");
				conn.closeConnection();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
			logger.error(sqlException.getMessage(), sqlException);
		}
		conn = null;
		
		return unitTestId;
		
	}

}
