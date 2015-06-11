<%@ page import="Spiel.FarbEnum" %>
<div>
Spieler
<img src="images/
<%
FarbEnum farbe = (FarbEnum)request.getServletContext().getAttribute("spielerAmZugFarbe");
out.print(farbe.name().toLowerCase());
%>
.png">
ist am Zug
<form action="WuerfelnServlet" method="get">
<input style="position: absolute; left:0px; top: 250px; height: 100px; width: 200px;" 
	type="submit" id="wuerfeln" value="Würfeln" />
</form>
<form action="ReloadServlet" method="post">
<input style="position: absolute; left:0px; top: 502px; height: 100px; width: 200px;" 
	type="submit" id="reload" value="Reload" /> 
</form>
<img style="position: absolute; height: 100px; width: 100px;top: 350px; left: 50px;" src="images/wuerfel_1.png" />
<%
Integer gewuerfeltInt = (Integer)session.getAttribute("zuletztGewuerfelt");

if (gewuerfeltInt != null) {
	out.println("<img style=\"position: absolute; height: 100px; width: 100px;top: 350px; left: 50px;\"" +
	" src=\"images/wuerfel_" + gewuerfeltInt + ".png\" />");
}
%>
</div>
