<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Neues Spiel starten</h1>
<form action="NeuesSpielServlet" method="get">
<label for="spieleranzahl">Spieler Anzahl</label>
<input type="text" name="spieleranzahl" value="1"/>
<input type="submit" value="Spiel erstellen"/>
</form>

</body>
</html>