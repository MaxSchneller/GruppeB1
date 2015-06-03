<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Oh shoot</title>
</head>
<body>
<h1>Etwas lief schief ;)</h1>
<p>
<%
String nachricht = (String) session.getAttribute("fehelerArg");
if (nachricht != null)
	out.println("Nachricht: " + nachricht);
%></p>
</body>
</html>