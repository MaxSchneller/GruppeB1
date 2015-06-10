package WebSpielAnlegen;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Servlets.HilfsMethoden;
import Spiel.iBediener;

/**
 * Servlet implementation class BitteWarten_Servlet
 */
@WebServlet("/BitteWarten_Servlet")
public class BitteWarten_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BitteWarten_Servlet() {
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
		
		ServletContext ctx = request.getServletContext();
		
		iBediener spiel = (iBediener)ctx.getAttribute("spiel");
		String[][]felder = new String [spiel.getAlleFigurenPositionen().length][4];
		
		String[][]fields = spiel.getAlleFigurenPositionen();
		
		for(int i = 0; i < fields.length; i ++){
			felder[i][0] = fields[i][0];
			felder[i][1] = fields[i][1];
			felder[i][2] = String.format("%d",HilfsMethoden.getSpalteFeld(fields[i][2]));
			felder[i][3] = String.format("%d",HilfsMethoden.getReiheFeld(fields[i][2]));
		}
		
		Integer anzahlBeigetreten = (Integer)ctx.getAttribute("anzahlBeitreten");
		Integer spielerAnzahl = (Integer)ctx.getAttribute("spielerAnzahl");
		
		if (anzahlBeigetreten >= spielerAnzahl) {
			ctx.setAttribute("positionen", felder);
			response.sendRedirect("spielfeld.jsp");
		} else {
			response.sendRedirect("Login_HTML/bitteWarten.html");
		}
	}

}
