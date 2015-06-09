<%@ page errorPage="fehler.jsp" %>
<%
String farbe = (String)request.getParameter("farbe");
String id  = (String)request.getParameter("id");

if (farbe == null || id == null) {
	session.setAttribute("fehlerArg", "Keine Farbe oder keine ID in zug.jsp erhalten (direkter Aufruf?) " + 
						(farbe == null ? "Farbe null" : farbe) + " " +
						(id == null ? "Id null" : id));
	response.sendRedirect("fehler.jsp");
} else {
	response.sendRedirect("ZugServlet?farbe=" + farbe +"&id="+ id);
}
%>