<%@ page import="Spiel.FarbEnum" %>
  <div>

	<p>Sie sind 
	<% 
		String namefooter = (String) session.getAttribute("name");
		FarbEnum farbefooter = (FarbEnum) session.getAttribute("farbe");
		out.print(namefooter);
 	%>
 	<img src="<%out.print("images/" + farbefooter.name().toLowerCase() + ".png"); %>"/>
 	</p>
    <textarea readonly cols="83" rows="5">
    <%
    String status = (String)request.getServletContext().getAttribute("status");
    if (status != null) {
    	out.println(status);
    }
    %>
    </textarea>
  </div>
