package Servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Fehler_Exceptions.KannNichtWuerfelnException;
import Spiel.FarbEnum;
import Spiel.WuerfelErgebnis;
import Spiel.iBediener;

/**
 * Servlet implementation class WuerfelnServlet
 */
@WebServlet("/WuerfelnServlet")
public class WuerfelnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WuerfelnServlet() {
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
		ServletContext ctx = request.getServletContext();
		FarbEnum spielerFarbe = (FarbEnum)session.getAttribute("farbe");
		String spielerName = (String)session.getAttribute("name");
		iBediener spiel = (iBediener)ctx.getAttribute("spiel");
		
		if (spielerFarbe == null || spielerName == null) {
			session.setAttribute("fehlerArg", "WuerfelnServlet wurde von nicht teilnehmendem Spieler aufgerufen"
					+ " oder Spiel ist noch nicht erstellt");
			response.sendRedirect("fehler.jsp");
		} else {
			if (spielerFarbe != spiel.getSpielerAmZugFarbe()) {
				HilfsMethoden.fuegeStatusHinzu(request, "Spieler: " + spielerFarbe + " hat versucht zu wuerfeln,"
						+ " obwohl er nicht am Zug ist");
				response.sendRedirect("spielfeld.jsp");
			} else if (spiel.isSpielerAmZugKI()) {
				HilfsMethoden.fuegeStatusHinzu(request, "Spieler: " + spielerFarbe + " kann nicht wuerfeln,"
						+ " da er eine KI ist");
				response.sendRedirect("spielfeld.jsp");
			}else {
				WuerfelErgebnis ergebnis = null;
				
				try {
					ergebnis = spiel.sWuerfeln();
				} catch (KannNichtWuerfelnException e) {
					HilfsMethoden.fuegeStatusHinzu(request, "Spieler: " + spielerFarbe + " muss zuerst ziehen,"
							+ " bevor gewürfelt werden kann");
					response.sendRedirect("spielfeld.jsp");
				}
				
				if (ergebnis != null) {
					session.setAttribute("zuletztGewuerfelt", ergebnis.getGewuerfelteZahl());
					session.setAttribute("kannNochmalWuerfeln", ergebnis.isKannNochmalWuerfeln());
					session.setAttribute("kannZugAusfuehren", ergebnis.isKannZugAusfuehren());
					
					String wuerfelnNachricht = "Spieler: " + spielerFarbe + " hat " + ergebnis.getGewuerfelteZahl() + " gewuerfelt";
					
					if (ergebnis.isKannNochmalWuerfeln()) {
						wuerfelnNachricht = "Spieler : " + spielerFarbe + " darf nocheinmal wuerfeln! \n" + wuerfelnNachricht;
					}
					
					HilfsMethoden.fuegeStatusHinzu(request, wuerfelnNachricht);
					ctx.setAttribute("spielerAmZugFarbe", spiel.getSpielerAmZugFarbe());
					
					response.sendRedirect("spielfeld.jsp");
				}
			}
		}
	}
	
	
	

}
