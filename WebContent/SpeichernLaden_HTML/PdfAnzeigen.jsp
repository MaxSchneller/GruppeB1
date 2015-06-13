<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import="java.io.File" 
    import="java.io.FileInputStream" 
    import = "java.io.FileNotFoundException"
    import="Servlets.JSPHilfsmethoden"%>

<%

String dateiname = (String)request.getSession().getAttribute("dateiname");

if (dateiname != null) {
	
	
	response.setContentType("application/pdf");
	
	File file = null;
	FileInputStream is = null;
	
	try {
		file = new File(dateiname);
		is = new FileInputStream(file);

		byte[] b = new byte[(int) file.length()];
		is.read(b, 0, b.length);
		is.close();
		response.getOutputStream().write(b);
		return;
	} catch (FileNotFoundException e) {
		JSPHilfsmethoden.zeigeFehlerJSP(e.getMessage(), request, response);
		return;
	} finally {
		is.close();
	}
}
%>