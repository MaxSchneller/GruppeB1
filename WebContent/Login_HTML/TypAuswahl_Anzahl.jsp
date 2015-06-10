<%
String anzahl = request.getParameter("spieleranzahl");
String nameSpieler1 = request.getParameter("eigenerName");
String farbe = request.getParameter("eigeneFarbe");
String typ = request.getParameter("spieler1");

if (anzahl == null || nameSpieler1 == null 
|| farbe == null || typ == null) {
	session.setAttribute("fehlerArg", "TypAuswahl_Anzahl.jsp hat nicht alle notwendigen Parameter erhalten");
	response.sendRedirect("../fehler.jsp");
} else {	
	response.sendRedirect("../TypAuswahl_Anzahl?spieleranzahl="+ anzahl + "&eigenerName="
						+ nameSpieler1 + "&eigeneFarbe=" + farbe + "&spieler1=" + typ);
}
%>