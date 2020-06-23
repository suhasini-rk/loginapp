<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login to application</title>
</head>
<body style="background-color:powderblue;">
<%
		String userName = null;
		HttpSession userSession = request.getSession();
		if(userSession !=null){
			  userName =(String)userSession.getAttribute("user");
		}
		if(userName == null) response.sendRedirect("login.html");
		%>
		<h3>Welcome  <%=userName %>, You are successfully loggedin!!!.</h3>
		<br>
		<form action="LogoutServlet" method="post">
		<input type="submit" value="Logout" >
</body>
</html>