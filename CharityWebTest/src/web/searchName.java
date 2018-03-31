package web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ClientExperimental;
import javax.servlet.*; 
import javax.servlet.http.*;
import model.*;
import java.io.*; 
import java.util.*;


public class searchName extends HttpServlet { 
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)	throws IOException, ServletException {  
		
		String c = request.getParameter("name"); 
		ClientExperimental client = new ClientExperimental();         
		Charity result = client.getName(c); 
		request.setAttribute("styles", result); 
		RequestDispatcher view = request.getRequestDispatcher("Output.jsp"); 
		view.forward(request, response);      
	} 
}	
