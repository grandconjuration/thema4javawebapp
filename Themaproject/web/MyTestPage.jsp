<%-- 
    Document   : newjsp3
    Created on : 23-Apr-2014, 13:43:31
    Author     : simon
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Demo met JSP</title>
</head>
<body>
	<form action = "MyServlet" method = "post">
		<div>
			<input class = "ltf" type = "text" name = "gebruikersnaam" /><br />
			<input type = "submit" name = "knop" value = "Login!" />
		</div>
	</form>
</body>
</html>