package com.Sor.Utils;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.update.UpdateAction;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.util.FileManager;

import com.Sor.Model.*;

public class rdfHelper {

	public int getMaxId() {
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		InputStream in = FileManager.get().open(Constants.inputFileName);
		if (in == null) {
			throwException();
		}
		model.read(Constants.inputFileName, "RDF/XML");
		// read the RDF/XML file

		String queryString = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "
				+ "PREFIX foaf:<http://xmlns.com/foaf/0.1/>"
				+ "PREFIX cv:<http://captsolo.net/semweb/resume/0.2/cv.rdf/>"
				+ "PREFIX cvb:<http://captsolo.net/semweb/resume/0.2/base.rdf//>"
				+ "PREFIX acc:<http://www.semanticdesktop.org/ontologies/2011/10/05/dao/v1.0/>"
				+ "select ?person where  {" + "?person a foaf:Person; }";
		Query query = QueryFactory.create(queryString);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		String persID = null;
		int maxId = 0;
		int aux = 0;
		while (results.hasNext()) {
			QuerySolution binding = results.nextSolution();
			Resource subj = (Resource) binding.get("person");
			persID = subj.getURI();
			if (persID != null && persID.contains(Constants.inputFileName)) {
				persID = persID.substring((persID.indexOf("#") + 1));
				aux = Integer.parseInt(persID);
				if (aux > maxId)
					maxId = aux;
			}
		}

		qe.close();
		return maxId;
	}

	public String getId(String userName, String password) {

		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
		InputStream in = FileManager.get().open(Constants.inputFileName);
		if (in == null) {
			throwException();
		}
		model.read(Constants.inputFileName, "RDF/XML");
		// read the RDF/XML file

		String queryString = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "
				+ "PREFIX foaf:<http://xmlns.com/foaf/0.1/>"
				+ "PREFIX cv:<http://captsolo.net/semweb/resume/0.2/cv.rdf/>"
				+ "PREFIX cvb:<http://captsolo.net/semweb/resume/0.2/base.rdf//>"
				+ "PREFIX acc:<http://www.semanticdesktop.org/ontologies/2011/10/05/dao/v1.0/>" +

		"select ?person where  {" + "?person a foaf:Person; " + "acc:userId ?userName;" + "acc:password ?password "
				+ "FILTER  (?userName='" + userName + "' && ?password='" + password + "') }";

		Query query = QueryFactory.create(queryString);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		String persID = null;
		int i = 0;
		while (results.hasNext()) {
			QuerySolution binding = results.nextSolution();
			Resource subj = (Resource) binding.get("person");
			persID = subj.getURI();
			if (persID != null && persID.contains(Constants.inputFileName)) {
				i++;
				persID = persID.substring((persID.indexOf("#") + 1));
			}
		}

		qe.close();
		if (i > 1) {
			throw new IllegalArgumentException("User: " + userName + " has duplicate");
		}
		return persID;
	}

	public static rdfHelper hellper;

	// https://jena.apache.org/tutorials/rdf_api.html#ch-Reading%20RDF
	public void read(String inputFileName) {
		// create an empty model
		Model model = ModelFactory.createDefaultModel();

		// use the FileManager to find the input file
		InputStream in = FileManager.get().open(inputFileName);
		if (in == null) {
			throw new IllegalArgumentException("File: " + inputFileName + " not found");
		}

		// read the RDF/XML file
		model.read(in, null);
		// The second argument to the read() method call is the URI which
		// will be used for resolving relative URI's. As there are no relative
		// URI references
		// in the test file, it is allowed to be empty. vc-db-1.rdf

		// write it to standard out
		model.write(System.out);
	}

	public Person getPerson(String userId) throws ParseException {
		// TODO Auto-generated method stub

		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);

		// read the RDF/XML file
		model.read(Constants.inputFileName, "RDF/XML");

		// Create a new query
		String queryString = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "
				+ "PREFIX foaf:<http://xmlns.com/foaf/0.1/>"
				+ "PREFIX cv:<http://captsolo.net/semweb/resume/0.2/cv.rdf/>"
				+ "PREFIX cvb:<http://captsolo.net/semweb/resume/0.2/base.rdf//>"
				+ "PREFIX acc:<http://www.semanticdesktop.org/ontologies/2011/10/05/dao/v1.0/>" +

		"select ?subject ?property ?object where {<" + Constants.inputFileName + "#" + userId + "> ?property ?object }";
		;

		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		List<String> varNames = results.getResultVars();
		String var2 = varNames.get(1);
		String var3 = varNames.get(2);
		Person person = new Person();
		person.setUserId(userId);
		List<Studied> studied = new ArrayList<Studied>();
		List<Skill> skills = new ArrayList<Skill>();
		List<Worked> worked = new ArrayList<Worked>();
		Worked work = new Worked();
		Skill skill = new Skill();
		Studied stud = new Studied();
		Job job = new Job();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		while (results.hasNext()) {
			QuerySolution sol = results.next();
			String s = sol.getResource(var2).getLocalName();
			RDFNode object = sol.get(var3);
			String o;
			if (object.canAs(Resource.class)) {
				o = object.asResource().getLocalName();
			} else {
				o = object.asLiteral().getString();

			}
			if (s.equals("phone"))
				o = object.toString();
			SetPersonValue(person, s, o);
			if (s.equals("EducationalOrg"))
				stud.setOrganizationId(o);
			if (s.equals("schoolHomepage"))
				stud.setHomepage(o);
			if (s.equals("skillName"))
				skill.setSkillName(o);
			if (s.equals("skillYearsExperience"))
				skill.setExperience(o);
			if (s.equals("jobType"))
				job.setJobName(o);
			// work.setJob(o);
			if (s.equals("startDate"))
				work.setBeginDate(formatter.parse(o));
			if (s.equals("Organization"))
				work.setOrganizationId(o);
			if (s.equals("endDate"))
				work.setEndDate(formatter.parse(o));
			if (s.equals("careerLevel"))
				job.setJobCareerLevel(o);
			if (s.equals("workplaceHomepage"))
				work.setHomepage(o);
		}
		skills.add(skill);
		studied.add(stud);
		work.setJob(job);
		worked.add(work);
		person.setStudied(studied);
		person.setKnowledge(skills);
		qe.close();
		return person;
	}

	// jobTitle,Organization==Endava,,,jobDescription,birthPlace-nu,,,gender,employedIn,skillLevel
	// workInfoHomepage,targetJobDescription,,,skillLastUsed,knows,,,,topic_interest
	private void SetPersonValue(Person person, String propertyName, String o) {
		switch (propertyName) {
		case "hasNationality": {
			person.setNationality(o);
		}
		case "title":
			person.setTitle(o);

		case "userId":
			person.setUserName(o);
		case "family_name":
			person.setFamilyName(o);
		case "givenname":
			person.setGivenName(o);
		case "name":
			person.setName(o);
		case "mbox":
			person.setUserMail(o);
		case "hasDriversLicense":
			person.setDriverLicense(o);
		case "homepage":
			person.setHomepage(o);
		case "password":
			person.setUserPassword(o);
		case "maritalStatus":
			person.setMarried(o);
		case "targetSalary":
			person.setSalary(o);
		case "age":
			person.setAge(o);
		case "phone":
			person.setPhone(o);
		}

	}

	public Organization getOrganization(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public String updatePerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	public String updateOrganization(Organization organization) {
		// TODO Auto-generated method stub
		return null;
	}

	private rdfHelper() {
	}

	public static synchronized rdfHelper getHellper() {
		// TODO Auto-generated method stub
		if (hellper == null) {
			hellper = new rdfHelper();
		}
		return hellper;
	}

	public void insertUser(String userName, String givenName, String familyName, String userMail, String userPassword,
			int id) throws IOException {
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
		InputStream in = FileManager.get().open(Constants.inputFileName);
		FileWriter out = new FileWriter("persons.rdf");
		if (in == null) {
			throwException();
		}

		// FileWriter out = new FileWriter( inputFileName );
		// RDFWriter w = model.getWriter("RDF/XML-ABBREV");
		model.read(Constants.inputFileName, "");
		String insert = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>  "
				+ "PREFIX foaf:<http://xmlns.com/foaf/0.1/>"
				+ "PREFIX cv:<http://captsolo.net/semweb/resume/0.2/cv.rdf/>"
				+ "PREFIX cvb:<http://captsolo.net/semweb/resume/0.2/base.rdf//>"
				+ "PREFIX acc:<http://www.semanticdesktop.org/ontologies/2011/10/05/dao/v1.0/>" +

		"INSERT Data {<" + Constants.inputFileName + "#" + id + "> rdf:type  foaf:Person; foaf:givenname '" + givenName
				+ "';" + "foaf:family_name '" + familyName + "';" + "foaf:mbox '" + userMail + "';" + "acc:userId '"
				+ userName + "';" + "acc:password '" + userPassword + "' ;" + "  foaf:name '" + givenName + " "
				+ familyName + "'. } ";

		UpdateAction.parseExecute(insert, model);
		// RDFWriter w = model.getWriter("RDF/XML-ABBREV");
		model.write(out);// TO-DO ai grija sa dai path-ul correct
	}

	private void throwException() {
		throw new IllegalArgumentException("File: " + Constants.inputFileName + " not found");
	}

	public ArrayList<Person> getRdf() {
		// TODO Auto-generated method stub
		return null;
	}
}
