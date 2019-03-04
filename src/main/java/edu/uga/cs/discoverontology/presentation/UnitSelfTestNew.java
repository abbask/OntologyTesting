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

import com.google.gson.Gson;

import edu.uga.cs.discoverontology.model.ExpectedValue;
import edu.uga.cs.discoverontology.model.MyTestSystem;
import edu.uga.cs.discoverontology.service.SystemTestService;
import edu.uga.cs.discoverontology.service.UnitSelfTestService;
import edu.uga.cs.discoverontology.service.UnitTestService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@WebServlet("/UnitSelfTestNew")
public class UnitSelfTestNew extends HttpServlet{
	
	private static final long serialVersionUID = 2L;
	final static Logger logger = Logger.getLogger(UnitSelfTestNew.class);


	private String	   	   templatePath = null;
	static  String         templateDir = "WEB-INF/templates";
	static  String         templateName = "UnitSelfTestNew.ftl";
	
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

		save(req,res);
		
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
	
	protected void save(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String name = "";
		String assertType = "";
		String query ="";
		String message="";
		String scallar="";
		String triple="";

		
		name = (!req.getParameter("name").equals(""))? req.getParameter("name") : name ;
		query = (!req.getParameter("query").equals(""))? req.getParameter("query") : query ;
		message = (!req.getParameter("message").equals(""))? req.getParameter("message") : message ;
		
		
		assertType = (!req.getParameter("formAssertType").equals(""))? req.getParameter("formAssertType") : assertType ;
		scallar = (!req.getParameter("formValue").equals(""))? req.getParameter("formValue") : scallar ;
		triple = (!req.getParameter("formTripple").equals(""))? req.getParameter("formTripple") : triple ;
		
		
		Gson gson = new Gson();
	    
		//ArrayList<ArrayList<ExpectedValue>> tempExpectedValues = new ArrayList<ArrayList<ExpectedValue>>();
		ExpectedValue[][] tempExpectedValues = new ExpectedValue[1][1];
		
		ExpectedValue[][] expectedValues ;
		if (scallar.isEmpty() || scallar == null) {
			expectedValues = gson.fromJson(triple, ExpectedValue[][].class);
//			assertType = "EQUAL";
		}
		else{
			ExpectedValue expectedValue = new ExpectedValue("scallar", "scallar", "0", scallar);
			tempExpectedValues[0][0]= expectedValue;
			expectedValues = tempExpectedValues;
			
		}
		
		UnitSelfTestService unitSelfTestService = new UnitSelfTestService(); 
		
		unitSelfTestService.Add(name, query,assertType, expectedValues, message);
		
		res.sendRedirect(req.getContextPath() + "/OntologySelfTesting");
		
	}
}
