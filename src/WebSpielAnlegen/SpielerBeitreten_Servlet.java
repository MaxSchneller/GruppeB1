package WebSpielAnlegen;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SpielerBeitreten_Servlet
 */
@WebServlet("/SpielerBeitreten_Servlet")
public class SpielerBeitreten_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SpielerBeitreten_Servlet() {
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
		Integer spielerAnzahl = (Integer) request.getServletContext().getAttribute("spielerAnzahl");
		Integer anzahlBeitreten = (Integer) request.getServletContext().getAttribute("anzahlBeitreten");
		
		if(anzahlBeitreten>=spielerAnzahl){
			response.sendRedirect("Login_HTML/Error.html");
		}else{
			response.sendRedirect("Login_HTML/Spieler.jsp");
		}
		
	}

}
