package Servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Spiel.ZugErgebnis;
import Spiel.iBediener;

/**
 * Servlet implementation class ZugServlet
 */
@WebServlet("/ZugServlet")
public class ZugServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZugServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String farbe = (String)request.getParameter("farbe");
		String id = (String)request.getParameter("id");
		iBediener spiel = (iBediener)request.getServletContext().getAttribute("spiel");
		
		if (farbe == null || id == null) {
				request.getSession().setAttribute("fehlerArg", "Keine Farbe oder keine ID in ZugServlet erhalten (direkter Aufruf?)");
				response.sendRedirect("fehler.jsp");
		} else if (spiel == null) {
			request.getSession().setAttribute("fehlerArg", "ZugServlet wurde ohne existierendes Spiel aufgerufen");
			response.sendRedirect("fehler.jsp");
		} else {
			int figurID = -1;
			
			try {
				figurID = Integer.parseInt(id);
			} catch(NumberFormatException e) {
				response.sendRedirect("fehler.jsp?fehler=" + "Konnte%20ID%20nicht%20in%20Integer%20konvertieren");
			}
			ZugErgebnis ergebnis = spiel.ziehen(figurID);
			
			ServletContext ctx = request.getServletContext();
			
			ctx.setAttribute("posis", spiel.getAlleFigurenPositionen());
			
			String status = (String)ctx.getAttribute("status");
			
			if (status == null) {
				status = "";
			}
			
			String neuerStatus = ergebnis.getNachricht() + "<br/>" + status;
			
			ctx.setAttribute("status", neuerStatus);
			
			response.sendRedirect("spielfeld.jsp");
		}
	}

}
