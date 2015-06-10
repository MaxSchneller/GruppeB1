<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="Spiel.*" %>
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
<title>Insert title here</title>
</head>
<body>
<img style="position: absolute; top: 0px; left: 0px;"src="images/MADNrahmen.jpg" />
<img style="position: absolute; top: 37px; left: 37px;"src="images/MADNweb.jpg" />

<%@ include file="startfeldPositionen.jsp" %>
<%@ include file="endfeldPositionen.jsp" %>
<%@ include file="feldPositionen.jsp" %>

<div style="position: absolute; top: 0px; left: 0px; width: 650px; height: 650px;">
<%
	ServletContext ctx = request.getServletContext();
	//farbe, id, feld
	String[][] positionen = (String[][])ctx.getAttribute("positionen");
	
	if (positionen != null) {
		
		for (int i = 0; i < positionen.length; ++i) {
			String farbeLC = positionen[i][0].toLowerCase();
			String feldID = positionen[i][2];
			
			FarbEnum en = FarbEnum.vonString(farbeLC.toUpperCase());
			
			int xpos = 0;
			int ypos = 0;
			
			try {
				
				int feldNummer = Integer.parseInt(feldID);
				--feldNummer;
				xpos = felder[feldNummer][0];
				ypos = felder[feldNummer][1];
			} catch (NumberFormatException e) {
				String[] split = feldID.split(" ");
				
				if (split[0].startsWith("S")) {
					
					int feldNummer = Integer.parseInt(split[0].substring(1));
					--feldNummer;
					xpos = startFelder[en.ordinal()][feldNummer][0];
					ypos = startFelder[en.ordinal()][feldNummer][1];
					
				} else {
					int feldNummer = Integer.parseInt(split[0].substring(1));
					--feldNummer;
					xpos = endFelder[en.ordinal()][feldNummer][0];
					ypos = endFelder[en.ordinal()][feldNummer][1];
				}
			}
			
			out.println("<a href='zug.jsp?"
					+ "farbe=" + positionen[i][0]
					+ "&id="+ positionen[i][1] + "'>" 
					+ "<img src='images/" + farbeLC + ".png'"
					+" style='position: absolute; top: " + ypos + "px;"
					+ " left: " + xpos + "px;'/></a>");
		}
	} else {
		out.println("<p>Keine posis!</p>");
	}
	
%>
	
	<table style="position: absolute; top: 38px; left: 38px; border-collapse: collapse;">
	
	<tr>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
	</tr>
	
	<tr>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
	</tr>
	
	<tr>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
	</tr>
	
	<tr>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
	</tr>
	
	<tr>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
	</tr>
	
	<tr>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
	</tr>
	
	<tr>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
	</tr>
	
	<tr>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
	</tr>
	
	<tr>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
	</tr>
	
	<tr>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
	</tr>
	
	<tr>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
		<td><img src="images/rot.png"></td>
	</tr>
	
	</table>
</div>
<div style="position: absolute; top:650px; left:0px;">
<%@ include file="spielfeld-footer.jsp" %>
</div>
<div style="position: absolute; top:0px; left:650px;">
<%@ include file="menu.jsp" %>
</div>

</body>
</html>
