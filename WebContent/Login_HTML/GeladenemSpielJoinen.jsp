<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="Spiel.FarbEnum"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Beitreten</h1>
<p>Ein Spiel wurde geladen...<br/>

<%
	Integer anzahlSpieler = (Integer) request.getServletContext().getAttribute("spielerAnzahl");
	Integer anzahlBeitreten = (Integer) request.getServletContext().getAttribute("anzahlBeitreten");
	
	FarbEnum[] vorhandeneFarbe = (FarbEnum[]) request.getServletContext().getAttribute("vorhandeneFarben");
	
	if (anzahlBeitreten < anzahlSpieler) {
		out.println("Sie können als Spieler " + vorhandeneFarbe[anzahlBeitreten] + " beitreten <br/>");
		out.println("<a href=\"../GeladenJoinenServlet\">Beitreten</a>");
	} else {
		out.println("Das Spiel ist voll");
	}
%>
</p>
</body>
</html>