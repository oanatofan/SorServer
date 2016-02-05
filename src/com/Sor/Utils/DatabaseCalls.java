/**
 * 
 */
package com.Sor.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.Sor.Model.LoginResponse;
import com.Sor.Model.Message;
import com.Sor.Model.RegisterResponse;

/**
 * @author nightphonix
 *
 */
public class DatabaseCalls {

	DatabaseConector db;

	public DatabaseCalls() {
		this.db = DatabaseConector.getDbCon();
	}

	public List<Message> getMessages(String userId) {
		String STM = "Select * from messages where (userId LIKE '" + userId + "' OR destId LIKE '" + userId + "')";
		try {
			ResultSet result = db.query(STM);

			if (result.next()) { // Checks for any results and moves cursor to
									// first row,
				List<Message> response = new ArrayList<Message>();

				do { // Use 'do...while' to process the first row, while
						// continuing to process remaining rows
					Message msg = new Message();
					String destId = result.getString("destId");
					msg.setDestId(destId);
					String message = result.getString("message");
					msg.setMessage(message);
					String messageDate = result.getString("messageDate");
					msg.setMessageDate(messageDate);
					String uId = result.getString("userId");
					msg.setUserId(uId);
					Boolean messageRead = result.getBoolean("messageRead");
					msg.setMessageRead(messageRead);

					response.add(msg);

				} while (result.next());
				return response;
			} else {
				return null;
			}
		} catch (SQLException e) {
			Logger.getLogger(DatabaseConector.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		return null;
	}

	public Message getMessage(String userId, String messageId) {
		String STM = "Select * from messages where (messageId LIKE '" + messageId + "' AND (userId LIKE '" + userId
				+ "' OR destId LIKE '" + userId + "'))";
		try {
			ResultSet result = db.query(STM);

			if (result.next()) { // Checks for any results and moves cursor to
									// first row,

				Message msg = new Message();
				String destId = result.getString("destId");
				msg.setDestId(destId);
				String message = result.getString("message");
				msg.setMessage(message);
				String messageDate = result.getString("messageDate");
				msg.setMessageDate(messageDate);
				String uId = result.getString("userId");
				msg.setUserId(uId);
				Boolean messageRead = result.getBoolean("messageRead");
				msg.setMessageRead(messageRead);
				return msg;
			} else {
				return null;
			}
		} catch (SQLException e) {
			Logger.getLogger(DatabaseConector.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		return null;
	}

	public String sendMessage(String userId, String userDestName, String message) {

		String STM = "INSERT INTO  messages (userId,destId ,message) VALUES ('" + userId + "','" + userDestName + "','"
				+ message + "')";
		try {
			String result = db.insert(STM,"messageId");
			if (result!=null)
				return Constants.Succes;

		} catch (SQLException e) {
			Logger.getLogger(DatabaseConector.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		return Constants.Faill;
	}

	public LoginResponse verifyLogin(String userName, String userPassword) {
		String STM = "Select * from registeredusers where (userId LIKE '" + userName + "' AND userpassword LIKE '"
				+ userPassword + "')";
		try {
			ResultSet result = db.query(STM);
			LoginResponse response =new LoginResponse();
			if (result.next()) {
				response.setLogedin(true);
				response.setUserId(userName);
				response.setUserType(result.getString("userType"));

			} else {
				response.setLogedin(false);
				response.setUserId(userName);
				
			}
			return response;
		} catch (SQLException e) {
			Logger.getLogger(DatabaseConector.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		return null;}
	

	public RegisterResponse registerUser(String userName, String givenName, String familyName, String userMail, String userPassword, String userType) {
		String STM = "INSERT INTO  registeredusers (userName,givenName,familyName,userMail ,  userpassword,userType) VALUES ('" + userName + "','" 
	+ givenName+ "','" + familyName+ "','" + userMail + "','"+ userPassword + "','"	+ userType +"')";
		try {
			String result = db.insert(STM,"userId");
			if (result!=null)
			{	
				RegisterResponse response=new RegisterResponse();
				response.setLogedin(true);
				response.setUserId(Integer.parseInt(result));
				response.setUserType(userType);
				return response;
			}
		} catch (SQLException e) {
			Logger.getLogger(DatabaseConector.class.getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		return null;
	}

}
