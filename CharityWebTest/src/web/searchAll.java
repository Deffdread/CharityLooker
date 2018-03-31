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


public class searchAll extends HttpServlet { 
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)	throws IOException, ServletException {  
		
		ClientExperimental client = new ClientExperimental();         
		Charity[] result = client.getAll(); 
		request.setAttribute("styles", result); 
		RequestDispatcher view = request.getRequestDispatcher("OutputAll.jsp"); 
		view.forward(request, response);      
	} 
}	