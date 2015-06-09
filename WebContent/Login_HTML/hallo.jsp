<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Willkommen bei Mensch �rgere dich nicht</title>
</head>
<body>
<h1 style="color:red">Achtung: Seite leitet noch direkt an Servlet weiter</h1>
<%
	if (request.getServletContext().getAttribute("spielExistiert") == null) {
		out.println("<h1>Es existiert noch kein Spiel</h1>");
		out.println("<a href=\"../NeuesSpielServlet\">Neues Spiel</a>");
	} else {
		ServletContext ctx = request.getServletContext();
		
		Integer erstellteSpieler = (Integer) ctx.getAttribute("anzahlErstellterSpieler");
		Integer maximaleSpieler = (Integer) ctx.getAttribute("maximaleSpieleranzahl");
		
		if (erstellteSpieler >= maximaleSpieler) {
			session.setAttribute("fehlerArg", "Es k�nnen keine weiteren Spieler beitreten");
			response.sendRedirect("fehler.jsp");
		}
	}
%>

</body>
</html>