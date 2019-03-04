package edu.uga.cs.discoverontology.presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import edu.uga.cs.discoverontology.data.EndpointConnection;
import edu.uga.cs.discoverontology.model.ExpectedValue;
import edu.uga.cs.discoverontology.model.ExpectedValuesGroup;
import edu.uga.cs.discoverontology.model.MyUnitTest;
import edu.uga.cs.discoverontology.service.UnitHistoryService;
import edu.uga.cs.discoverontology.service.UnitTestService;


@WebServlet("/RunUnitTest")
public class RunUnitTest extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	final static Logger logger = Logger.getLogger(OntologyTest.class);

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		loadPage(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {		

		loadPage(req, res);
		
	}
	
	public void loadPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		UnitTestService unitTestService = new UnitTestService();
		
		int unit_test_id = 0;
		int system_test_history_id = 0;
		
		unit_test_id = (!req.getParameter("unit_test_id").equals(""))? Integer.valueOf(req.getParameter("unit_test_id")) : unit_test_id ;
		system_test_history_id = (!req.getParameter("system_test_history_id").equals(""))? Integer.valueOf(req.getParameter("system_test_history_id")) : system_test_history_id ;
		
		
		MyUnitTest unitTest = unitTestService.findByID(unit_test_id);
		
		String queryString  = unitTest.getQuery();
		System.out.println(queryString);
		EndpointConnection endpoint = new EndpointConnection ( unitTest.getSystemTest().getEndPoint(),unitTest.getSystemTest().getGraph());
		
		ArrayList<HashMap<String,String>> result =  endpoint.executeQueryForCol(queryString);
		
	    ArrayList<ExpectedValuesGroup> expectedValueGroups =	unitTest.getExpectedValueGroups();

	    String message = "";
	    
	    Boolean success = false;
	    
	    if (result.size() == 1) {
	    	
	    	
	    	String key = expectedValueGroups.get(0).getExpectedValues().get(0).getUseName();
	    	
	    	int retrieveValue = Integer.valueOf(result.get(0).get(key));
	    	int expectedValue = Integer.valueOf(expectedValueGroups.get(0).getExpectedValues().get(0).getValue());
	    	
	    	//System.out.println("E: " + expectedValue + ", R: " + retrieveValue);
	    	
		    switch (unitTest.getAssertType()) {
		    case "EQUAL": 
		    	if (retrieveValue == expectedValue) {
		    		success = true;
		    		message ="Test passed.";
		    	}
		    	break;
		    case "GREATER": 
		    	if (retrieveValue > expectedValue) {
		    		success = true;
		    		message ="Test passed.";
		    	}
		    	break;
		    	
		    case "LESS":
		    	if (retrieveValue < expectedValue) {
		    		success = true;
		    		message ="Test passed.";
		    	}
		    	break;
		    
		    }

		    
	    }
	    else
	    {
		    if (expectedValueGroups.size() == result.size()) {
			    for (ExpectedValuesGroup expectedValueGroup : expectedValueGroups) {
			    	ArrayList<ExpectedValue> expectedValues = expectedValueGroup.getExpectedValues();
	
			    	for (HashMap<String, String> map : result) {
			    		if (findInList(map, expectedValues) == true) {
			    			success = true;
			    			break;
			    		}
			    	}
			    }
			    if (success != true) {
			    	message = "Test failed. Although the number of records matched but it is was not the exact same as what expected.";
			    }
			    else {
			    	message = "Test passed.";
			    }
		    }
		    else {
		    	success = false;
		    	message = "Test failed. " + expectedValueGroups.size() + " record expected but " + result.size() + " was retrieved.";
		    }
	    }
		
	    String json = new Gson().toJson(unit_test_id);
	    
	    res.setContentType("application/json");
	    res.setCharacterEncoding("UTF-8");
	    
	    
	    if (success) {
	    	res.setStatus(HttpServletResponse.SC_OK); 
	    }
	    else {
	    	res.setStatus(HttpServletResponse.SC_NOT_FOUND);
	    	
	    }
	    
	    String status = "failed";
	    if (success) {
	    	status = "passed";
	    }
	    
	    UnitHistoryService unitHistoryService = new UnitHistoryService();
	    unitHistoryService.Add(system_test_history_id, unit_test_id, status , message);
	    
	    res.getWriter().write(json);
	    logger.info("RunUnitTest.loadPage : Found unit test and its expected values.[Ajax Call]");
		
	}
	
	private boolean findInList(HashMap<String,String> map, ArrayList<ExpectedValue> expectedValues) {
		
		int numberofChecks = expectedValues.size();
		
		Boolean[] checks = new Boolean[numberofChecks];
		int count = 0;
		for (ExpectedValue expectedValue : expectedValues) {
    		String key = expectedValue.getOriginalName().replace("?", "").replace(")","");
    		String value = expectedValue.getValue();
    		checks[count] = false;
    		if (map.containsKey(key)) {
    			if (map.get(key).equals(value)) {
    				checks[count] = true;
    			}
    		}
    		count++;
		}
		
		boolean found = true;
		for(int i = 0 ; i < numberofChecks ; i++) {
			if (checks[i] == false)
				found = false;
		}
		return found;
	}
	
	private ArrayList<HashMap<String,String>> createHashMap(ArrayList<ExpectedValuesGroup> expectedValuesGroups){
		ArrayList<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();
		
		for (ExpectedValuesGroup expectedValuesGroup : expectedValuesGroups ) {
			ArrayList<ExpectedValue> expectedValues = expectedValuesGroup.getExpectedValues();
			HashMap<String, String> map = new HashMap<String, String>();
			for(ExpectedValue expectedValue : expectedValues) {
				
//				String originalName = expectedValue.getOriginalName().replace("?", "").replace(")","");
				String originalName = expectedValue.getUseName();
				String value = expectedValue.getValue();
				map.put(originalName, value);
			}
			result.add(map);
		}
		
		return result;
	}

}
