package WebSpielAnlegen;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		Integer anzahlBeigetreten = (Integer)ctx.getAttribute("anzahlBeigetreten");
		Integer spielerAnzahl = (Integer)ctx.getAttribute("spielerAnzahl");
		
		if (anzahlBeigetreten >= spielerAnzahl) {
			response.sendRedirect("spielfeld.jsp");
		} else {
			response.sendRedirect("Login_HTML/bitteWarten.html");
		}
	}

}
