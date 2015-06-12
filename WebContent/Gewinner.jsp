<%@ page import="Spiel.FarbEnum" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
body {
    background-color: 
    <%
    FarbEnum farbe = (FarbEnum)request.getServletContext().getAttribute("gewinnerFarbe");
   if(farbe!=null){
	if(farbe.ordinal()==0){
    	out.print("RED");
    	if(farbe.ordinal()==1){
        	out.print("BLUE");
        	if(farbe.ordinal()==3){
            	out.print("YELLOW");
            	if(farbe.ordinal()==2){
                	out.print("GREEN");
            	}
        	}
    	}
	}
   }
    %>;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
<h1>Herlichen Glückwunsch!!!</h1>
<h2><%

String name = (String)request.getServletContext().getAttribute("gewinnerName");
if(name!=null){
	out.print(name);
}
%> hat gewonnen!!</h2>
<img style="broder" src="images/feuerwerk1.gif" >
</body>
</html>