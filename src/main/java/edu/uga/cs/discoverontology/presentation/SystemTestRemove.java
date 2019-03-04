package edu.uga.cs.discoverontology.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import edu.uga.cs.discoverontology.service.SystemTestService;
import edu.uga.cs.discoverontology.service.UnitTestService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@WebServlet("/SystemTestRemove")
public class SystemTestRemove extends HttpServlet{

	private static final long serialVersionUID = 2L;
	final static Logger logger = Logger.getLogger(SystemTestNew.class);


	private String	   	   templatePath = null;
	static  String         templateDir = "WEB-INF/templates";
	static  String         templateName = "systemTestRemoveError.ftl";
	
	private Configuration  cfg; 

	public void init() {

		cfg = new Configuration(Configuration.VERSION_2_3_23);
		cfg.setServletContextForTemplateLoading( getServletContext(), 
				templateDir );
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		remove(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {		
		remove(req, res);
	}
	
	protected void remove(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int systemTestID = 0;

		systemTestID = (!req.getParameter("id").equals(0))? Integer.valueOf(req.getParameter("id")) : systemTestID ;
		if (systemTestID != 0) {
			
			SystemTestService systemTestService = new SystemTestService();
			int result= systemTestService.remove(systemTestID);
			
			if (result == 0)
				errorPage(req,res);
			else
				res.sendRedirect(req.getContextPath() + "/SystemTestList");
		}
		else {
			// TODO Raise an error
			errorPage(req,res);
		}
		
	}
	
	public void errorPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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
		
		root.put("message", "Failed to remove specified system test.");
		
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
