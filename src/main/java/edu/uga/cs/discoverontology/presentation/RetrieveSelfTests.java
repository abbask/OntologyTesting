package edu.uga.cs.discoverontology.presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import edu.uga.cs.discoverontology.model.MyUnitTest;
import edu.uga.cs.discoverontology.service.SystemHistoryService;
import edu.uga.cs.discoverontology.service.UnitTestService;


@WebServlet("/RetrieveSelfTests")
public class RetrieveSelfTests extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(OntologyTest.class);

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		loadPage(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {		

		loadPage(req, res);
		
	}
	
	public void loadPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
//		UnitTestService unitTestService = new UnitTestService();
//		
//		int system_test_id = 0;
//		
//		system_test_id = (!req.getParameter("system_test_id").equals(""))? Integer.valueOf(req.getParameter("system_test_id")) : system_test_id ;
//		ArrayList<MyUnitTest> unitTests = unitTestService.listBySystemTest(system_test_id);
//		
//		SystemHistoryService historyService = new SystemHistoryService();
//		int system_test_history_id = historyService.Add(system_test_id);
		
		Map<String, Object> map = new HashMap<>();		
//		map.put("list", unitTests);
//		map.put("system_test_history_id", system_test_history_id);
		
		
		
	    String json = new Gson().toJson(map);

	    res.setContentType("application/json");
	    res.setCharacterEncoding("UTF-8");
	    res.getWriter().write(json);
	    logger.info("RunSystemTest.loadPage : list of unit tests retrieved.[Ajax Call]");
		
	}

}

