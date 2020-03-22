package com.JavaBrain.Servlet;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.JavaBrain.Servlet.Service.LoginService;
import com.JavaBrain.Servlet.dto.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

	/**
	 * @param userId 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response, String userId) throws ServletException, IOException {
		 String Userid=request.getParameter("userid");
		 String Password=request.getParameter("password");
		 
		 LoginService loginservice=new LoginService();
		 boolean result=loginservice.authenticate(Userid, Password);
		 /* Now we have to do Redirect here
		  * we have to JSPs here login and succes.jsps
		  * we do thr redirect by using a Method Response object In this Caese we will send An Instruction to the response object
		  * Idont have content here  but you should redirect to the other page which we will display the content.
		  * If we are print text or html and get the response then we are actually sending Instruction to do an Actually redirection.
		  * Once we pass this response Tomcat will again convert to html code and send it to the user so the user browser knows 
		  * OKK	This si Not An Actuaaly response I actually need to Lookup at another page and then it does the request for the page 
		  * that we will instruct the in the response object.
		  * So Pass the Instruction Depending on the result	  *     
		  *     
		  *     
		  *     */
		 if(result) {
			
			 User user =loginservice.getUserDetails(userId);
			 //Save this user object in session getting the value from the session and printing the value
			 request.getSession().setAttribute("user", user);
			 //The way to do this instead of passing the instruction to the browser to access a different URL doing now
			 //We need to actually Redirect on the Server Side itself
			 //Browser doesnot knw this time that the Redirection is actually Happening to another URL or Servlet Or JSPS .
			 //The way we can do this by using a something called Request DIspatcher.
			 //And the request dispatcher object is available in  The Request Object.
			// response.sendRedirect("Success.jsp");
			RequestDispatcher dispatcher= request.getRequestDispatcher("success.jsp");
	//I can use this dispatcher to transmit the control to this (success.jsp) value that has been defined in the req.dispatcher.
			//So order to transmit the control i need to pass the request and response object .
			//we need to do this because we still consider this as same request so use.
			//dispatcher take this req and response and does a forward to the new resource that we have defined in succes.jsp
			
			dispatcher.forward(request, response);
			 return;
			 
		 }
		 else {
			 response.sendRedirect("LogIn.jsp");
			 return;
		 }
		 
		
	}



	
}
