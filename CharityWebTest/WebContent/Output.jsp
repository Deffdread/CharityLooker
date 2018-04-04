<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*" %> 
<%@ page import="model.*" %> 
<html> 
<body> 
<% java.util.Date d = new java.util.Date(); %>
<h1 align="center">CharityLooker Output</h1> 

<h2>
Today's date is <%= d.toString() %>. Welcome to CharityLooker.
</h2>
<p> 
<%  

Charity output = (Charity)request.getAttribute("styles"); 
out.println("<br>"+"Organization: "+output.getName());
out.println("<br>"+"Business Number: "+output.getBnum());
out.println("<br>"+"Decription:"+"<br>"+output.getDesc());


%> 
<br>
<br>
<br>
<br>
<br>
<a href="Home.html">Return to home page</a>
</body> 
</html> 