package Servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import Spiel.FarbEnum;
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
		
		String gewinnerFarbe = (String)request.getParameter("gewinnerFarbe");
		String gewinnerName = (String)request.getParameter("gewinnerName");
		String farbe = (String)request.getParameter("farbe");
		String id = (String)request.getParameter("id");
		iBediener spiel = (iBediener)request.getServletContext().getAttribute("spiel");
		HttpSession session = request.getSession();
		
		FarbEnum sessionFarbe = (FarbEnum) session.getAttribute("farbe");
		String sessionName = (String) session.getAttribute("name");
		
		
		
		if (farbe == null || id == null) {
				request.getSession().setAttribute("fehlerArg", "Keine Farbe oder keine ID in ZugServlet erhalten (direkter Aufruf?)");
				response.sendRedirect("fehler.jsp");
		} else if (spiel == null) {
			request.getSession().setAttribute("fehlerArg", "ZugServlet wurde ohne existierendes Spiel aufgerufen");
			response.sendRedirect("fehler.jsp");
		} else if (sessionFarbe == null || sessionName == null) {
			session.setAttribute("fehlerArg", "Session hat keine Farbe also ist dieser Client kein Spieler");
			response.sendRedirect("fehler.jsp");
		} else {
			FarbEnum figurFarbe = FarbEnum.vonString(farbe.toUpperCase());
			
			if (sessionFarbe != spiel.getSpielerAmZugFarbe()) {
				HilfsMethoden.fuegeStatusHinzu(request, "Spieler " + sessionFarbe + " kann nicht ziehen, da er nicht dran ist");
				response.sendRedirect("spielfeld.jsp");
				return;
			} else if (sessionFarbe != figurFarbe) {
				response.sendRedirect("spielfeld.jsp");
				return;
			}
			
			int figurID = -1;
			
			try {
				figurID = Integer.parseInt(id);
			} catch(NumberFormatException e) {
				response.sendRedirect("fehler.jsp?fehler=" + "Konnte%20ID%20nicht%20in%20Integer%20konvertieren");
			}
			ZugErgebnis ergebnis = spiel.ziehen(figurID);
			
			ServletContext ctx = request.getServletContext();
			
			String[][] figuren = HilfsMethoden.konvertiereFigurenInZeileUndSpalte(spiel.getAlleFigurenPositionen());
			
			ctx.setAttribute("positionen", figuren);
			ctx.setAttribute("spielerAmZugFarbe", spiel.getSpielerAmZugFarbe());
			
			String zugNachricht = "Spieler " + sessionFarbe;
			
			if (ergebnis.isGueltig() && ergebnis.getGeaenderteFiguren() != null) {
				for (String[] figur : ergebnis.getGeaenderteFiguren()) {
					zugNachricht += "  " + figur[0] + " " + figur[1]
							+ " ist auf Feld " + figur[2] + ",";
				}if(ergebnis.isSpielGewonnen()){
					ctx.setAttribute("spielGewonnen", "ja");
					ctx.setAttribute("gewinnerName", ergebnis.getGewinnerName());
					ctx.setAttribute("gewinnerFarbe", ergebnis.getGewinnerFarbe());
					response.sendRedirect("Gewinner.jsp");
				}
				
				
			}
			else  {
				zugNachricht += " " + ergebnis.getNachricht();
			}if(ergebnis.isSpielGewonnen()){
				ctx.setAttribute("spielGewonnen", "ja");
				ctx.setAttribute("gewinnerName", ergebnis.getGewinnerName());
				ctx.setAttribute("gewinnerFarbe", ergebnis.getGewinnerFarbe());
				response.sendRedirect("Gewinner.jsp");
				return;
			}
			
			HilfsMethoden.fuegeStatusHinzu(request, zugNachricht);
			response.sendRedirect("spielfeld.jsp");
		}
	}
	
	

}
