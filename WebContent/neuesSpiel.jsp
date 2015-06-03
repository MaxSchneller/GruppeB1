s<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Neues Spiel starten</title>
</head>

<body>
<h1 style="color:red">Wichtig: Noch Einzelaufruf behandeln!</h1>
<h1>Neues Spiel starten</h1>
<form action="NeuesSpielServlet" method="get">
<label for="spieleranzahl">Spieler Anzahl</label>
<select name="spieleranzahl">
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
</select>
<p>
<label for="spieler1">Spieler 1</label>
<input type="radio" name="spieler1" value="keineKI" checked>Keine KI</input>
<input type="radio" name="spieler1" value="aggressiveKI">Aggressive KI
</input>
<input type="radio" name="spieler1" value="defensiveKI">Defensive KI
</input>
</p>
<p>
<label for="spieler2">Spieler 2</label>
<input type="radio" name="spieler2" value="keineKI" checked>Keine KI</input>
<input type="radio" name="spieler2" value="aggressiveKI">Aggressive KI
</input>
<input type="radio" name="spieler2" value="defensiveKI">Defensive KI
</input>
</p>
<p>
<label for="spieler3">Spieler 3</label>
<input type="radio" name="spieler3" value="keineKI" checked>Keine KI</input>
<input type="radio" name="spieler3" value="aggressiveKI">Aggressive KI
</input>
<input type="radio" name="spieler3" value="defensiveKI">Defensive KI
</input>
</p>
<p>
<label for="spieler4">Spieler 4</label>
<input type="radio" name="spieler4" value="keineKI" checked>Keine KI</input>
<input type="radio" name="spieler4" value="aggressiveKI">Aggressive KI
</input>
<input type="radio" name="spieler4" value="defensiveKI">Defensive KI
</input>
</p>
<p>
<label for="eigenerName">Eigener Name</label>
<input name="eigenerName" value="Spieler 1" />
<br />
<label for="eigeneFarbe">Eigene Farbe</label>
<select name="eigeneFarbe">
<option value="ROT">Rot</option>
<option value="BLAU">Blau</option>
<option value="GRUEN">Grün</option>
<option value="GELB">Gelb</option>
</select>
</p>
<input type="submit" value="Spiel erstellen" />
</form>

</body>

</html>