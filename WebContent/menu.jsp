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
<input style="position: absolute; left:0px; top: 170px; height: 100px; width: 200px;" 
	type="submit" id="wuerfeln" value="W�rfeln" />
</form>
<form action="ReloadServlet" method="post">
<!--  <input name="autoUpdate" id="autoupdate" type="checkbox" value="shouldUpdate" 
		style="position: absolute; left:0px; top: 490px;" 
		onchange="checkChanged()"
		 <%-- if (request.getParameter("autoUpdate") != null) 
			 out.print("checked");
			--%>> Auto update -->
<input style="position: absolute; left:0px; top: 412px; height: 100px; width: 200px;" 
	type="submit" id="reload" value="Reload" /> 
</form>

<form action="SpeichernLaden_HTML/SpeichernFormular.html" method="post">
<input style="position: absolute; left:0px; top: 532px; height: 30px; width: 200px;"
	type="submit" id="speichern" value="Speichern" />
</form>
<!-- <form action="SpeichernLaden_HTML/LadenFormular.html" method="post"> -->
<!-- <input style="position: absolute; left:0px; top: 572px; height: 30px; width: 200px;" -->
<!-- 	type="submit" id="laden" value="Laden" /> -->
<!-- </form> -->
<img style="position: absolute; height: 100px; width: 100px;top: 280px; left: 50px;" src="images/wuerfel_1.png" />
<%
Integer gewuerfeltInt = (Integer)session.getAttribute("zuletztGewuerfelt");

if (gewuerfeltInt != null) {
	out.println("<img style=\"position: absolute; height: 100px; width: 100px;top: 280px; left: 50px;\"" +
	" src=\"images/wuerfel_" + gewuerfeltInt + ".png\" />");
}
%>
</div>
