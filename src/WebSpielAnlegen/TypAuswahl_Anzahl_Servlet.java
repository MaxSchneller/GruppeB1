package WebSpielAnlegen;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Kuenstliche_Intelligenz.KiTypEnum;
import Servlets.HilfsMethoden;
import Spiel.FarbEnum;
import Spiel.SpielBean;
import Spiel.iBediener;

/**
 * Servlet, der Spieleranzahl prueft, Ersten Spieler erstellt
 * 
 * @author gruppe B1
 *
 */
@WebServlet("/TypAuswahl_Anzahl_Servlet")
public class TypAuswahl_Anzahl_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor
	 */
	public TypAuswahl_Anzahl_Servlet() {
		super();
	}

	/**
	 * Nimmt die Resorcen der der neuesSpiel.jsp und gibt sie der doPost Methode
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * Verarbeitet die Resorcen der neuesSpiel.jsp durch "weiter..." wird
	 * geprueft wie viele Spieler teilnehmen und eine neue Html geoeffnet wer
	 * Spieler1 begruesst und ihn auffordert den Typ der Gegner zu waehlen
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String anzahlSpielerString = request.getParameter("spieleranzahl");
		String nameSpieler1 = request.getParameter("eigenerName");
		String farbe = request.getParameter("eigeneFarbe");
		String typ = request.getParameter("spieler1");
		response.setContentType("text/html;charset=ISO-8859-1");
		PrintWriter out = response.getWriter();

		int anzahlSpieler = Integer.parseInt(anzahlSpielerString);

		if (anzahlSpielerString == null || nameSpieler1 == null
				|| farbe == null || typ == null) {
			response.sendRedirect("Login_HTML/Error.html");
		} else {
			
			KiTypEnum kiTyp = KiTypEnum.vonString(typ.toUpperCase());
			
			iBediener spiel = new SpielBean (nameSpieler1 , FarbEnum.vonString(farbe.toUpperCase()) , kiTyp);
			request.getServletContext().setAttribute("spiel", spiel);
			ServletContext ctx = request.getServletContext();
			ctx.setAttribute("spielerAnzahl", anzahlSpieler);
			ctx.setAttribute("anzahlBeitreten", new Integer(1));
			
			Boolean[] freieFarben = new Boolean[4];
			
			for (int i = 0; i < 4; ++i) {
				
				if (spiel.isFarbeVergeben(FarbEnum.vonInt(i))) {
					freieFarben[i] = false;
				} else {
					freieFarben[i] = true;
				}
			}
			
			request.getServletContext().setAttribute("freieFarben", freieFarben);
			
			
			String[][] figuren = HilfsMethoden.konvertiereFigurenInZeileUndSpalte(spiel.getAlleFigurenPositionen());
			
			ctx.setAttribute("positionen", figuren);
			ctx.setAttribute("spielerAmZugFarbe", spiel.getSpielerAmZugFarbe());
			ctx.setAttribute("spieler1Farbe", FarbEnum.vonString(farbe.toUpperCase()));
			request.getSession().setAttribute("farbe", FarbEnum.vonString(farbe.toUpperCase()));
			request.getSession().setAttribute("name", nameSpieler1);
			
			if (anzahlSpieler > 1) {

				// 2 Spieler
				if (anzahlSpieler == 2) {
					response.sendRedirect("Login_HTML/TypAuswahl_2Spieler.html");
				}
				// 3 Spieler
				else if (anzahlSpieler == 3) {
					response.sendRedirect("Login_HTML/TypAuswahl_3Spieler.html");
				}
				// 4 Spieler
				else if (anzahlSpieler == 4) {
					response.sendRedirect("Login_HTML/TypAuswahl_4Spieler.html");
				}

			} else {
				
				response.sendRedirect("spielfeld.jsp");
			}
			out.close();
		}

	}

}
