package WebSpielAnlegen;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Spiel.FarbEnum;
import Spiel.iBediener;

/**
 * Servlet implementation class GeladenJoinenServlet
 */
@WebServlet("/GeladenJoinenServlet")
public class GeladenJoinenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GeladenJoinenServlet() {
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
		
		
		Integer anzahlSpieler = (Integer) request.getServletContext().getAttribute("spielerAnzahl");
		Integer anzahlBeitreten = (Integer) request.getServletContext().getAttribute("anzahlBeitreten");
		Integer eigeneSpielerNummer = new Integer(anzahlBeitreten);
		++anzahlBeitreten; // Das ist der Spieler der joinen will
		FarbEnum[] vorhandeneFarbe = (FarbEnum[]) request.getServletContext().getAttribute("vorhandeneFarben");
		
		HttpSession session = request.getSession();
		
		iBediener spiel = (iBediener) request.getServletContext().getAttribute("spiel");
		
		String[] naechsterSpieler = spiel.getSpieler()[anzahlBeitreten].split(" ; ");
		
		
			for (int i = anzahlBeitreten; i < spiel.getSpieler().length; ++i) {
				String[] spieler = spiel.getSpieler()[i].split(" ; ");
				
				if (spieler[2].equals("AGGRESIV") || spieler[2].equals("DEFENSIV")) {
					++anzahlBeitreten;
				} else {
					break;
				}
			}
		
		
		session.setAttribute("name", naechsterSpieler[0]);
		session.setAttribute("farbe", vorhandeneFarbe[eigeneSpielerNummer]);
		
		request.getServletContext().setAttribute("anzahlBeitreten", anzahlBeitreten + 1);
		
		response.sendRedirect("Login_HTML/bitteWarten.html");
	}

}
