package com.Sor.Controller;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import com.Sor.Model.*;

@Path("/messages")
public class MessageController {
	
	
	
	//http://localhost:8080/SorServer/rest/messages/get?userId=1&messageId=1
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@QueryParam("userId") String userId, @QueryParam("messageId") String messageId,
			@Context SecurityContext securityContext) throws NotFoundException {
		// return delegate.messagesGetGet(userId,messageId,securityContext);
		 Message response =new Message(); //dc.getMessage(userId, messageId);		
		return response;
	}
	//http://localhost:8080/SorServer/rest/messages/getAll?userId=1
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getAllMessage(@QueryParam("userId") String userId, @Context SecurityContext securityContext)
			throws NotFoundException {
		// return delegate.messagesGetGet(userId,messageId,securityContext);
		 List<Message> response =new ArrayList<Message>(); //dc.getMessages(userId);		
			return response;
		
	}

	@POST
	@Path("/send")
	@Produces(MediaType.TEXT_PLAIN)
	public String messagesSendPost(@QueryParam("userId") String userId, @QueryParam("userDestName") String userDestName,
			@QueryParam("message") String message, @Context SecurityContext securityContext) throws NotFoundException {
		//dc.sendMessage(userId,userDestName,message);
		return "fail"; //dc.sendMessage(userId,userDestName,message);
	}
}
