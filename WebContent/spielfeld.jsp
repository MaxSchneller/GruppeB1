<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="Spiel.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<img style="position: absolute; top: 0px; left: 0px;"src="images/madn.jpg" />

<%@ include file="startfeldPositionen.jsp" %>
<%@ include file="endfeldPositionen.jsp" %>
<%@ include file="feldPositionen.jsp" %>

<%
	String[][] posis = {{"ROT", "1", "10"} , {"BLAU", "1", "E1 BLAU"},
		{"GELB", "1", "E3 GELB"}, {"GRUEN", "1", "E2 GRUEN"}, {"ROT", "1", "S1 ROT"}};
	request.getServletContext().setAttribute("positionen", posis);
%>
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

</div>
<div style="position: absolute; top:650px; left:0px;">
<%@ include file="spielfeld-footer.jsp" %>
</div>
<div style="position: absolute; top:0px; left:650px;">
<%@ include file="menu.jsp" %>
</div>
</body>
</html>