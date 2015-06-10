<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true"%>
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
String nachricht = request.getParameter("fehler");
if (nachricht == null) {
	nachricht = (String) session.getAttribute("fehlerArg");
}
if (nachricht != null) {
	out.println("Nachricht: " + nachricht);
}

if (exception != null) {
	out.println("Exception: " + exception.getMessage());
}
%></p>
</body>
</html>