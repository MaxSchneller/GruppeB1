<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<style type="text/css">
			body {
				background-color: #F5F5F5;
				font-family: Arial;
				position: absolute;	
			}
		</style>	
	</head>
	<body>
		<h1>Spiel beitreten</h1>
		<p>Es existiert ein Spiel, wechlem Sie beitreten können. Sie sind Spieler 2</p>
		
		<form action="../ErstelleSpieler_Servlet" method="post">
					
			<p>
				<label for="eigenerName"><b>Eigener Name</b></label>
				<input name="eigenerName" value="Spieler 2" />
				<br/>
			</p>

			<p>
				<label for="eigeneFarbe"><b>Eigene Farbe</b></label>
				<input type="radio" name="eigeneFarbe" value="rot" style="background:#FF0000" checked>Rot</input>
				<input type="radio" name="eigeneFarbe" value="blau" style="background:#0000FF">Blau</input>
				<input type="radio" name="eigeneFarbe" value="gruen" style="background:#00FF00">Grün</input>
				<input type="radio" name="eigeneFarbe" value="gelb" style="background:#FFFF00">Gelb</input>	
			</p>
			
			<br/>
			<input type="submit" value="spielBeitreten" />
		</form>

</body>
</html>