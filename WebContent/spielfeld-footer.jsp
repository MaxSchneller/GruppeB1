
  <div>


    <textarea readonly cols="83" rows="5">
    <%
    String status = (String)request.getServletContext().getAttribute("status");
    if (status != null) {
    	out.println(status);
    }
    %>
    </textarea>
  </div>
