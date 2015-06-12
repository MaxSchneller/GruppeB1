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
<!--  <input name="autoUpdate" id="autoupdate" type="checkbox" value="shouldUpdate" 
		style="position: absolute; left:0px; top: 490px;" 
		onchange="checkChanged()"
		 <%-- if (request.getParameter("autoUpdate") != null) 
			 out.print("checked");
			--%>> Auto update -->
<input style="position: absolute; left:0px; top: 502px; height: 100px; width: 200px;" 
	type="submit" id="reload" value="Reload" /> 
	
<input style="position: absolute; left:0px; top: 632px; height: 30px; width: 200px;"
	type="submit" id="speichern" value="Speichern" />
	
<input style="position: absolute; left:0px; top: 672px; height: 30px; width: 200px;"
	type="submit" id="laden" value="Laden" />
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
