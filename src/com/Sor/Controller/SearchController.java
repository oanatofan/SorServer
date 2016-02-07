package com.Sor.Controller;

import java.text.ParseException;
import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import com.Sor.Model.*;
import com.Sor.Search.SimpleSearch;
import com.Sor.Search.UserHelpper;
@Path("/search")
public class SearchController {
	
	private SimpleSearch search;

	public SearchController() {
		 search=new SimpleSearch();
	}
	@GET
	@Path("/searchOrganization")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Organization> getSearchOrganization(List<RequestedInfo> requestedInfo,
			@Context SecurityContext securityContext) throws NotFoundException {
		Organization org = new Organization();
		List<Organization> response = new ArrayList<Organization>();
		response.add(org);
		return response;
		// return
		// delegate.searchSearchOrganizationGet(requestedInfo,securityContext);
	}

	@GET
	@Path("/searchPerson")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getSearchPerson(List<RequestedInfo> requestedInfo, @Context SecurityContext securityContext)
			throws NotFoundException {
		Person per = new Person();
		List<Person> response = new ArrayList<Person>();
		response.add(per);
		return response;
		// return delegate.searchSearchPersonGet(requestedInfo,securityContext);
	}

	// suggest organizations for the persons
	@GET
	@Path("/suggestOrganizations")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Organization> getSuggestOrganization(@QueryParam("userId") String userId,
			@Context SecurityContext securityContext) throws NotFoundException {
		Organization org = new Organization();
		List<Organization> response = new ArrayList<Organization>();
		response.add(org);
		return response;
		// return
		// delegate.searchSuggestOrganizationsGet(userId,userType,securityContext);
	}
//http://localhost:8080/SorServer/rest/search/suggestPersons?userId=1
	// suggest persons for an organizations(type is organization) and friends
	// for persons(type is person)
	@GET
	@Path("/suggestPersons")
	@Produces(MediaType.TEXT_PLAIN)
	public String getSuggestPerson(@QueryParam("userId") String userId, @QueryParam("type") String type,
			@Context SecurityContext securityContext) throws NotFoundException, ParseException {
		//Person per = new Person();
		List<Person> response = new ArrayList<Person>();
		response=search.sugestPerson(userId);
		System.out.println(response);
		//response.add(per);
		return response.toString();
		// return
		// delegate.searchSuggestPersonGet(userId,userType,securityContext);
	}
}
