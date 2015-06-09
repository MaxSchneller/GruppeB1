<div>
Spieler
<img src="images/blau.png">
ist am Zug
<button style="position: absolute; left:0px; top: 250px; height: 100px; width: 200px;" type="button" id="wuerfeln">Würfeln</button>
<img style="position: absolute; height: 100px; width: 100px;top: 350px; left: 50px;" src="images/wuerfel_1.png" />
<%
Integer gewuerfeltInt = (Integer)session.getAttribute("zuletztGewuerfelt");

if (gewuerfeltInt != null) {
	out.println("<img style=\"position: absolute; height: 100px; width: 100px;top: 350px; left: 50px;\"" +
	" src=\"images/wuerfel_" + gewuerfeltInt + ".png\" />");
}
%>
</div>
