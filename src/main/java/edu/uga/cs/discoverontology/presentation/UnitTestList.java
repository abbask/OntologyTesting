package edu.uga.cs.discoverontology.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import edu.uga.cs.discoverontology.model.MyTestSystem;
import edu.uga.cs.discoverontology.model.MyUnitTest;
import edu.uga.cs.discoverontology.service.UnitTestService;
import edu.uga.cs.discoverontology.service.SystemTestService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@WebServlet("/UnitTestList")
public class UnitTestList extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(UnitTestList.class);

	private String	   	   templatePath = null;
	static  String         templateDir = "WEB-INF/templates";
	static  String         templateName = "unitTestList.ftl";

	private Configuration  cfg; 

		public void init() {

		cfg = new Configuration(Configuration.VERSION_2_3_23);
		cfg.setServletContextForTemplateLoading( getServletContext(), 
				templateDir );
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		loadPage(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {		

		filterPage(req, res);
		
	}
	
	public void loadPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Template       					template;
		String         					templatePath = null;
		BufferedWriter 					toClient = null;

		templatePath = templateDir + "/" + templateName;

		// load the template
		try {
			template = cfg.getTemplate(templateName);
		} 
		catch (IOException e) {
			throw new ServletException( 
					"Can't load template " + templateDir + "/" + templateName + ": " + e.toString());
		}
		
		Map<String, Object> root = new HashMap<>();				

		toClient = new BufferedWriter(
				new OutputStreamWriter(res.getOutputStream(), template.getEncoding()));
		res.setContentType("text/html; charset=" + template.getEncoding());
		
		SystemTestService systemTestService = new SystemTestService();
		UnitTestService unitTestService = new UnitTestService();
		
		ArrayList<MyTestSystem> systemTests = systemTestService.listAll();

		//DEFAULT
		//MyTestSystem myTestSystem = systemTests.get(0);
		int systemTestSelect = 0;
		systemTestSelect = (req.getParameter("systemTestSelect") != null)? Integer.valueOf(req.getParameter("systemTestSelect")) : systemTestSelect ;
		
		if (systemTestSelect == 0) {
			systemTestSelect = systemTests.get(0).getID();
		}
		
		ArrayList<MyUnitTest> unitTests = unitTestService.listBySystemTest(systemTestSelect);
		root.put("systemTestId", systemTestSelect);
		root.put("systemTests", systemTests);
		root.put("unitTests", unitTests);
		
		toClient = new BufferedWriter(
				new OutputStreamWriter(res.getOutputStream(), template.getEncoding()));
		res.setContentType("text/html; charset=" + template.getEncoding());

		try {
			template.process(root, toClient);
			toClient.flush();
			
		} catch (TemplateException e) {
			throw new ServletException(
					"Error while processing FreeMarker template", e);
		}

		toClient.close();
	}
	
	public void filterPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Template       					template;
		String         					templatePath = null;
		BufferedWriter 					toClient = null;

		templatePath = templateDir + "/" + templateName;

		// load the template
		try {
			template = cfg.getTemplate(templateName);
		} 
		catch (IOException e) {
			throw new ServletException( 
					"Can't load template " + templateDir + "/" + templateName + ": " + e.toString());
		}
		
		Map<String, Object> root = new HashMap<>();				

		toClient = new BufferedWriter(
				new OutputStreamWriter(res.getOutputStream(), template.getEncoding()));
		res.setContentType("text/html; charset=" + template.getEncoding());
		
		SystemTestService systemTestService = new SystemTestService();
		UnitTestService unitTestService = new UnitTestService();
		
		ArrayList<MyTestSystem> systemTests = systemTestService.listAll();
		int systemTestId = 0;
		systemTestId = (!req.getParameter("systemTestSelect").equals(""))? Integer.valueOf(req.getParameter("systemTestSelect")) : systemTestId ;
		
		ArrayList<MyUnitTest> unitTests = unitTestService.listBySystemTest(systemTestId);

		root.put("systemTestId", systemTestId);
		root.put("systemTests", systemTests);
		root.put("unitTests", unitTests);
		
		toClient = new BufferedWriter(
				new OutputStreamWriter(res.getOutputStream(), template.getEncoding()));
		res.setContentType("text/html; charset=" + template.getEncoding());

		try {
			template.process(root, toClient);
			toClient.flush();
			
		} catch (TemplateException e) {
			throw new ServletException(
					"Error while processing FreeMarker template", e);
		}

		toClient.close();
	}

}
