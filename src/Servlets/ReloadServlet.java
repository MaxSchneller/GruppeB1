package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;

import Fehler_Exceptions.KannNichtWuerfelnException;
import Spiel.FarbEnum;
import Spiel.WuerfelErgebnis;
import Spiel.ZugErgebnis;
import Spiel.iBediener;

/**
 * Servlet implementation class ReloadServlet
 */
@WebServlet("/ReloadServlet")
public class ReloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReloadServlet() {
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
		
		HttpSession session = request.getSession();
		Object sessionFarbe = session.getAttribute("farbe");
		
		if (sessionFarbe == null) {
			session.setAttribute("fehlerArg", "Sie sind nicht berechtigt dieses Servlet aufzurufen");
			response.sendRedirect("fehler.jsp");
		} else {
			
			iBediener spiel = (iBediener) request.getServletContext().getAttribute("spiel");
			if (spiel == null) {
				response.sendRedirect("Login_HTML/Willkommen.html");
				return;
			}
			
			if (spiel.isSpielerAmZugKI()) {
				HilfsMethoden.fuegeStatusHinzu(request, "KI Zieht..");
				if(this.lassKIZiehen(spiel, request, response)){
					response.sendRedirect("Gewinner.jsp");
					return;
				}
				
			}
			
			String autoUpdate = request.getParameter("autoUpdate");
			
			String redirectString = "spielfeld.jsp";
			
			if (autoUpdate != null) {
				redirectString += "?autoUpdate=" + autoUpdate;
			}
			response.sendRedirect(redirectString);
		}
	}

	private boolean lassKIZiehen(iBediener spiel, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		FarbEnum kiFarbe = spiel.getSpielerAmZugFarbe();
		String spielerString = "Spieler " + kiFarbe;
		
		HilfsMethoden.fuegeStatusHinzu(request, spielerString + " KI wuerfelt...");

		WuerfelErgebnis ergebnis;
		try {
			
				ergebnis = spiel.sWuerfeln();
			
		} catch (KannNichtWuerfelnException e1) {
			
			return false;
		}

		HilfsMethoden.fuegeStatusHinzu(request, "KI hat " 
		+ ergebnis.getGewuerfelteZahl() + " gewuerfelt");
		
		
		
		//TODO: this.gui.zeigeWuerfel(ergebnis.getGewuerfelteZahl());

		while (!ergebnis.isKannZugAusfuehren()) {
			HilfsMethoden.fuegeStatusHinzu(request, spielerString + " Kein Zug moeglich");

			if (ergebnis.isKannNochmalWuerfeln()) {
				HilfsMethoden.fuegeStatusHinzu(request, spielerString + " KI wuerfelt nochmal...");
				try {
						ergebnis = spiel.sWuerfeln();						
				
				} catch (KannNichtWuerfelnException e1) {
					//TODO: this.gui.zeigeFehler(e1.getMessage());
				}
				HilfsMethoden.fuegeStatusHinzu(request, spielerString + " KI hat " + ergebnis.getGewuerfelteZahl()
						+ " gewuerfelt");
			} else {
				HilfsMethoden.fuegeStatusHinzu(request, spielerString + " Kann nicht nochmal wuerfeln, naechster ist dran.");
				request.getServletContext().setAttribute("spielerAmZugFarbe", spiel.getSpielerAmZugFarbe());
				return false;
			}
		}

		HilfsMethoden.fuegeStatusHinzu(request, spielerString + " KI zieht...");
		ZugErgebnis e = null;
		try {
			e = spiel.ziehen(0);
		} catch (JAXBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (e.isGueltig()) {
				request.getServletContext().setAttribute("positionen", 
						HilfsMethoden.konvertiereFigurenInZeileUndSpalte(spiel.getAlleFigurenPositionen()));

			if (e.isSpielGewonnen()) {
				
					request.getServletContext().setAttribute("spielGewonnen", "ja");
					request.getServletContext().setAttribute("gewinnerName", e.getGewinnerName());
					request.getServletContext().setAttribute("gewinnerFarbe", e.getGewinnerFarbe());
					
					
					request.getServletContext().setAttribute("spiel", null);
					request.getServletContext().setAttribute("anzahlBeitreten", new Integer(0));
					return true;
				
			}
		} else {
			//TODO: HilfsMethoden.fuegeStatusHinzu(request, spielerString + " KI hat ungueltigen Zug errechnet...och noeeee");
			
		}

		if (e.isZugBeendet()) {
			request.getServletContext().setAttribute("spielerAmZugFarbe", spiel.getSpielerAmZugFarbe());
		}
		return false;
	}
}
