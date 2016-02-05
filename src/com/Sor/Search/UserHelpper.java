/**
 * 
 */
package com.Sor.Search;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFWriter;
import org.apache.jena.update.UpdateAction;
import org.apache.jena.util.FileManager;

import com.Sor.Model.*;
import com.Sor.Utils.Constants;
import com.Sor.Utils.rdfHelper;

/**
 * @author nightphonix
 *
 */
public class UserHelpper {

	private rdfHelper helper = null;
	private static int maxId = 0;

	public UserHelpper() {
		helper = rdfHelper.getHellper();
		maxId = helper.getMaxId();
	}

	public Person getPerson(String userId) {
		return helper.getPerson(userId);
	}

	public Organization getOrganization(String userId) {
		return helper.getOrganization(userId);
	}

	public String updatePerson(Person person) {
		// TODO Auto-generated method stub
		return helper.updatePerson(person);
	}

	public String updateOrganization(Organization organization) {
		// TODO Auto-generated method stub
		return helper.updateOrganization(organization);
	}

	public LoginResponse verifyLogin(String userName, String userPassword) {
		// TODO Auto-generated method stub
		LoginResponse response = new LoginResponse();
		String id = helper.getId(userName, userPassword);
		if (id != null) {
			response.setUserId(id);
			response.setUserType(Constants.Person);
			response.setLogedin(true);
		}
		return response;
	}

	public RegisterResponse registerUser(String userName, String givenName, String familyName, String userMail,
			String userPassword, String userType) throws IOException {
		maxId = maxId + 1;
		int id = maxId;
		helper.insertUser(userName, givenName, familyName, userMail, userPassword,id);
		String mId = helper.getId(userName, userPassword);
		RegisterResponse response = new RegisterResponse();
		if (mId == null) {		
		response.setUserId(id);
		response.setUserType(userType);
		response.setLogedin(true);}
		return response;
	}


}
