package web;

import javax.servlet.*; 
import javax.servlet.http.*;

import model.*;

import java.io.*; 
import java.util.*;


public class Controller extends HttpServlet { 
	
	
	ClientExperimental clientServer = new ClientExperimental();     
	int name = 1;
	int bn = 2;
	int input = 9; 
	  
	public void doPost(HttpServletRequest request, HttpServletResponse response)	throws IOException, ServletException {         
		String c = request.getParameter("command"); 
		ClientExperimental input = new ClientExperimental();         
		//List result = input.getBrands(c); 
		List result = null;
		request.setAttribute("command", result); 
		RequestDispatcher view = request.getRequestDispatcher(".jsp"); 
		view.forward(request, response);     
		} 
		  
	  public Controller() {
	  }
	  
	  public int get() {
		  return input; 
	  }

	  
	  public void select(String input) {

	    int in;
	    try {
	      in = Integer.parseInt(input);
	    }
	    catch (NumberFormatException e) {
	      in = -1;
	    }

	    if (in == name) {
	    	//
	    }
	    else if (in == bn) {
	    	//
	    }
	  }
	  
	  
	  public void nameSearch(HttpServletRequest request, HttpServletResponse response, String name)	throws IOException, ServletException {         
			String c = request.getParameter("name");      
			Charity result = clientServer.name(c); 
			request.setAttribute("name", result); 
			RequestDispatcher view = request.getRequestDispatcher("name.jsp"); 
			view.forward(request, response);     
			} 
	  
	  public void bnSearch(HttpServletRequest request, HttpServletResponse response, String bnum)	throws IOException, ServletException {         
			String c = request.getParameter("bnum");         
			Charity result = clientServer.bnum(c); 
			request.setAttribute("bnum", result); 
			RequestDispatcher view = request.getRequestDispatcher("bnum.jsp"); 
			view.forward(request, response);     
			} 
	  
	  public void all(HttpServletRequest request, HttpServletResponse response)	throws IOException, ServletException {         
			String c = request.getParameter("command");       
			List result = clientServer.all(); 
			request.setAttribute("all", result); 
			RequestDispatcher view = request.getRequestDispatcher("all.jsp"); 
			view.forward(request, response);     
			} 

}	
	

