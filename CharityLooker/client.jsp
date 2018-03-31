<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "web.Controller" %>

<jsp:useBean id="input" class="web.Controller" scope="session"/>
<jsp:setProperty name="input" property="*"/>

<html>
<head><title>CharityLooker</title></head>
<body bgcolor="white">
<font size=4>

<% if (input.get() == 1) { %>

  Search by Name
  Please enter name of the Charity<p>

  <form method=get>
  Console: <input type=text name=name>
  <input type=submit value="Submit">
  </form>
  
<% else if (input.get() == 2) { %>

  Search by Business Number
  Please enter Business Number of the Charity<p>

  <form method=get>
  Console: <input type=text name=bnum>
  <input type=submit value="Submit">
  </form>

<% } else if (input.get() == 3) { %> 

<%   
List styles = (List)request.getAttribute("name"); 
Iterator it = styles.iterator();   
	while (it.hasNext()) { 
		out.print(it);   
	} 
%> 


<% } else if (input.get() == 9) { %>

  Welcome to CharityLooker, the best one stop shop for charity data!.<p>

  Commands (Number)<br>
  1 Search by Name <br>
  2 Search by Business Number <br>
  3 View all Charities <br>
  9 Home <br><p>

  <form method=get>
  Console: <input type=text name=input>
  <input type=submit value="Submit">
  </form>

<% } else { %>

  Please Enter something valid</b>.

  Commands (Number)<br>
  1 Search by Name <br>
  2 Search by Business Number <br>
  3 View all Names <br>
  2 Search by Business Number <br>

  <form method=get>
  Console: <input type=text name=input>
  <input type=submit value="Submit">
  </form>

<% } %>


</font>
</body>
</html>