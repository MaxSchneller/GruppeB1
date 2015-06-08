package Servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Spiel.SpielBean;

/**
 * Servlet zum Erstellen eines neuen Spiels
 */
@WebServlet("/NeuesSpielServlet")
public class NeuesSpielServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NeuesSpielServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[][] posis = { {"ROT", "0", "S1 ROT" },{"BLAU", "1", "S1 BLAU"},{"GRUEN", "3", "E2 BLAU"},{"BLAU", "1", "E3 BLAU"}};
		
		request.getServletContext().setAttribute("positionen", posis);
		response.sendRedirect("spielfeld.jsp");
	}

}
