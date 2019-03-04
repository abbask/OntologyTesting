package edu.uga.cs.discoverontology.service;


import org.apache.jena.query.Query;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.log4j.Logger;
import org.topbraid.spin.arq.ARQ2SPIN;
import org.topbraid.spin.arq.ARQFactory;
import org.topbraid.spin.model.Select;

import edu.uga.cs.discoverontology.data.EndpointConnection;
import edu.uga.cs.discoverontology.model.ExpectedValue;

public class UnitSelfTestService {
	
final static Logger logger = Logger.getLogger(UnitSelfTestService.class);
	
	/*
	 * http://users.jyu.fi/~olkhriye/ties4520/lectures/Lecture02.pdf ->  Storing and querying RDF data Jena and Fuseki
	 * 
	 */

	public void Add(String name, String queryString, String assertType,ExpectedValue[][] expectedValues, String  message) {
		Model model = ModelFactory.createDefaultModel();
		//Query arqQuery = ARQFactory.get().createQuery(model, query);
		Query arqQuery = ARQFactory.get().createQuery(model, queryString);
		
		
		ARQ2SPIN arq2SPIN = new ARQ2SPIN( model );
		Select sparqlQuery = (Select) arq2SPIN.createQuery( arqQuery, null );
		String serviceURI = "http://localhost:8890/sparql";
		String graphName = "http://www.semanticweb.org/abbas/ontologies/2015/2/oscar";
		EndpointConnection con = new EndpointConnection(serviceURI, graphName);
		try {
			con.executeQueryForSPIN(model);
			System.out.println("Added from UnitSelfTestService.");
		} catch (Exception e) {
			System.out.println("Error adding from UnitSelfTestService.");
		}
		
		
				
	}

}
