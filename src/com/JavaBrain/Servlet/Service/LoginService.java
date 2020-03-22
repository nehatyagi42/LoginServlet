package com.JavaBrain.Servlet.Service;




import java.util.HashMap;

import com.JavaBrain.Servlet.dto.User;

import sun.text.normalizer.ICUBinary.Authenticate;

public class LoginService {
	/*Authenticate method would connect to a database where we have stored the UserId And Password 	
	 *  we have to check here is password is null or not
	 *  if Password is null and if the password contains only blank spaces and no actual character then i wil say authentication is false 	
	 *  And if password is not null and some character in it i will make the Authentication successfull 
	 * 

	 * */
	
	HashMap <String, String> users=  new HashMap <String, String> ();	
	public LoginService() {
		
		users.put("neha", "neha tyagi");
		users.put("rinku", "rinku tyagi");
		users.put("amit", "amit tyagi");
	}
	
	public boolean authenticate(String userid,String password){
		if(password == null ||password.trim()==""){
			return false;
		}
		return true;

	}
	/* Implement the Method in the business Service and use the controller to Acess that Method.
	 * so method is */
	public  User getUserDetails(String userId) {
		User user=new User();
		user.setUsername(users.get(userId));
		user.setUserId(userId);
return 	user;
	}

	

}
