<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="Spiel.FarbEnum" import = "Servlets.JSPHilfsmethoden" %>
    <%--
    
    	Object farbe = session.getAttribute("farbe");
    
    	if (farbe == null) {
    		session.setAttribute("fehlerArg", "Sie können das Spielfeld nicht einsehen, da Sie nicht mitspielen");
    		response.sendRedirect("fehler.jsp");
    	}
    --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>td,table{border-collapse: collapse; padding: 0px;}</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MADN Web</title>

</script>
</head>
<body 
<%
	FarbEnum amZug = (FarbEnum)request.getServletContext().getAttribute("spielerAmZugFarbe");
	FarbEnum sessionFarbe = (FarbEnum) session.getAttribute("farbe");
	
	if (amZug != sessionFarbe) {
		out.println("onload=\"onLoad()\"");
	}
%>>
<img style="position: absolute; top: 0px; left: 0px;"src="images/MADNrahmen.jpg" />
<img style="position: absolute; top: 37px; left: 37px;"src="images/MADNweb.jpg" />

<%@ include file="startfeldPositionen.jsp" %>
<%@ include file="endfeldPositionen.jsp" %>
<%@ include file="feldPositionen.jsp" %>

<div style="position: absolute; top: 0px; left: 0px; width: 650px; height: 650px;">
	
	<table style="position: absolute; top: 38px; left: 38px; border-collapse: collapse;">
<%
	String[][] feld = (String[][])request.getServletContext().getAttribute("positionen");

	for(int i = 0; i < 11; i++){
		out.println("<tr>");
		for(int j = 0; j < 11; j++){
			boolean istGesetzt = false;
			
			for(int h = 0; h < feld.length; h++){
				int feldX = Integer.parseInt(feld[h][2]);
				int feldY = Integer.parseInt(feld[h][3]);
				feldY = Math.abs(feldY-10);
					if(feldX == j && feldY == i){
						out.println("<td><a href=\"zug.jsp?farbe=" + feld[h][0].toUpperCase() +
								"&id=" + feld[h][1] + "\"><img src=\"images/" + feld[h][0].toLowerCase()+ ".png\"/></a></td>");
						istGesetzt = true;
						break;
					} 	
			}
			if(istGesetzt == false){
				if (JSPHilfsmethoden.isZelleSpielfeld(j, i)) {
				out.println("<td><a href=\"leererZug.jsp\"><img src=\"images/leer.png\"/></a></td>");
				} else {
					out.println("<td><img src=\"images/leer.png\"/></td>");
				}
			}
		}
		out.println("</tr>");
	}
%>
	</table>
</div>
<div style="position: absolute; top:602px; left:0px;">
<%@ include file="spielfeld-footer.jsp" %>
</div>
<div style="position: absolute; top:0px; left:650px;">
<%@ include file="menu.jsp" %>
</div>

</body>
<script type="text/javascript">

var interval = null;

<%
	if (request.getParameter("autoUpdate") != null) {
		out.println("checkChanged();");
	}
%>

function onLoad () {
	
	// Untere Zeile einkommentieren um autoupdates einzuschalten
	// Autoupdates werden nur bei Spielern ausgefuehrt, die nicht am Zug sind
	//interval = window.setInterval(function() {clickTheButton()}, 1000);
}

function clickTheButton() {
	
	var button = document.getElementById("reload");
	
	//alert(button);
	button.click();
}

function checkChanged() {
	var checkbox = document.getElementById("autoupdate");
	
	if (checkbox.checked == true) {
		interval = window.setInterval(function() {clickTheButton()}, 1000);
	} else {
		window.clearInterval(interval);
	}
}
</script>
</html>
