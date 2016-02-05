package com.Sor.Controller;

import java.io.IOException;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import com.Sor.Model.*;
import com.Sor.Search.UserHelpper;
import com.Sor.Utils.DatabaseCalls;

@Path("/user")
public class UserController {
	DatabaseCalls dc=new DatabaseCalls();
	UserHelpper  user=new UserHelpper();
	// http://localhost:8080/SorServer/rest/user/login?userName=Examples&userPassword=09709
	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public LoginResponse getUserLogin(@QueryParam("userName") String userName,
			@QueryParam("userPassword") String userPassword, @Context SecurityContext securityContext)
					throws NotFoundException {
		// return
		// viewPerson.getUserLogin(userName,userPassword,securityContext);
		LoginResponse response = new LoginResponse();
		response=user.verifyLogin(userName,userPassword);		
		return response;
	}

	// http://localhost:8080/SorServer/rest/user/login?userName=Examples&userMail=mail@yahoo.com&userPassword=09709&userType=person
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	public RegisterResponse postUserRegister(@QueryParam("userName") String userName,@QueryParam("givenName") String givenName,
			@QueryParam("familyName") String familyName,
			@QueryParam("userMail") String userMail, @QueryParam("userPassword") String userPassword,
			@QueryParam("userType") String userType, @Context SecurityContext securityContext)
					throws NotFoundException, IOException {
		// return
		// delegate.userRegisterPost(userName,userMail,userPassword,userType,securityContext);
		RegisterResponse response = new RegisterResponse();
		response=user.registerUser(userName,givenName,familyName,userMail,userPassword,userType);	
		return response;
	}

	// uita-te
	// http://examples.javacodegeeks.com/enterprise-java/rest/jersey/json-example-with-jersey-jackson/
	//https://www.nabisoft.com/tutorials/java-ee/producing-and-consuming-json-or-xml-in-java-rest-services-with-jersey-and-jackson
	@PUT
	@Path("/editPerson")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String putEditPerson(Person person, @Context SecurityContext securityContext) throws NotFoundException {
		return user.updatePerson(person);
		// return delegate.userEditPersonPut(person,securityContext);
	}

	@GET
	@Path("/viewPerson")
	@Produces(MediaType.APPLICATION_JSON)
	public Person getViewPerson(@QueryParam("userId") String userId, @Context SecurityContext securityContext)
			throws NotFoundException {
		Person response = user.getPerson(userId);
		return response;
		// return delegate.userViewPersonGet(userId,userType,securityContext);
	}

	// uita-te
	// http://examples.javacodegeeks.com/enterprise-java/rest/jersey/json-example-with-jersey-jackson/
	@PUT
	@Path("/editOrganization")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String putEditOrganization(Organization organization, @Context SecurityContext securityContext)
			throws NotFoundException {
		return user.updateOrganization(organization);
		// return delegate.userEditOrganizationPut(person,securityContext);
	}

	@GET
	@Path("/viewOrganization")
	@Produces(MediaType.APPLICATION_JSON)
	public Organization getViewOrganization(@QueryParam("userId") String userId,
			@Context SecurityContext securityContext) throws NotFoundException {
		Organization response =user.getOrganization(userId);
		return response;
		// return
		// delegate.userViewOrganizationGet(userId,userType,securityContext);
	}

}
