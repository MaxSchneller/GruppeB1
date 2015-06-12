package WebSpielAnlegen;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Fehler_Exceptions.SpielerFarbeVorhandenException;
import Kuenstliche_Intelligenz.KiTypEnum;
import Servlets.HilfsMethoden;
import Spiel.FarbEnum;
import Spiel.iBediener;

/**
 * Servlet implementation class GegnertypVerwaltung_Servlet
 */
@WebServlet("/GegnertypVerwaltung_Servlet")
public class GegnertypVerwaltung_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GegnertypVerwaltung_Servlet() {
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
		
		String gegnerTyp2 = request.getParameter("spieler2");
		String gegnerTyp3 = request.getParameter("spieler3");
		String gegnerTyp4 = request.getParameter("spieler4");
		Integer spielerAnzahl = (Integer) request.getServletContext().getAttribute("spielerAnzahl");
		
		if(spielerAnzahl == 2 && gegnerTyp2==null){
			response.sendRedirect("Login_HTML/Error.html");
			return;
		}else if(spielerAnzahl==3 && (gegnerTyp3==null || gegnerTyp2==null)){
			response.sendRedirect("Login_HTML/Error.html");
			return;
		}else if (spielerAnzahl ==4 && ( gegnerTyp2 == null|| gegnerTyp3==null || gegnerTyp4==null)){
			response.sendRedirect("Login_HTML/Error.html");
			return;
		}
		
		boolean spieler2IstKI = false;
		boolean spieler3IstKI = false;
		boolean spieler4IstKI = false;
		
		
		
		if(gegnerTyp2 != null && !gegnerTyp2.equals("keineKI")){
			KiTypEnum kiTyp = KiTypEnum.vonString(gegnerTyp2.toUpperCase());
			request.getServletContext().setAttribute("gegnerTyp2", kiTyp);
			spieler2IstKI = true;
		}
		if(gegnerTyp3 != null && !gegnerTyp3.equals("keineKI")){
			KiTypEnum kiTyp = KiTypEnum.vonString(gegnerTyp3.toUpperCase());
			request.getServletContext().setAttribute("gegnerTyp3", kiTyp);
			spieler3IstKI = true;
		}
		if(gegnerTyp4 != null && !gegnerTyp4.equals("keineKI")){
			KiTypEnum kiTyp = KiTypEnum.vonString(gegnerTyp4.toUpperCase());
			request.getServletContext().setAttribute("gegnerTyp4", kiTyp);
			spieler4IstKI = true;
		}
		
		if (spielerAnzahl == 2 && 
				spieler2IstKI) {
			
			try {
				iBediener spiel = (iBediener) request.getServletContext().getAttribute("spiel");
				
				KiTypEnum[] gegner = new KiTypEnum[1];
				gegner[0] = KiTypEnum.vonString(gegnerTyp2.toUpperCase());
				fuegeKIHinzu(gegner, spiel);
				
				request.getServletContext().setAttribute("positionen",
						HilfsMethoden.konvertiereFigurenInZeileUndSpalte(spiel.getAlleFigurenPositionen()));
				
				request.setAttribute("spielerAmZugFarbe", spiel.getSpielerAmZugFarbe());
				
				response.sendRedirect("spielfeld.jsp");
				return;
			} catch (SpielerFarbeVorhandenException e) {
				HilfsMethoden.zeigeFehlerJSP("Konnte keine KI erstellen", request, response);
				return;
			}
		} else if (spielerAnzahl == 3 && 
				spieler2IstKI) {
			
			try {
				iBediener spiel = (iBediener) request.getServletContext().getAttribute("spiel");
				
				int numKI = spieler3IstKI ? 2 : 1;
				KiTypEnum[] gegner = new KiTypEnum[numKI];
				gegner[0] = KiTypEnum.vonString(gegnerTyp2.toUpperCase());
				
				if (numKI == 2)
					gegner[1] = KiTypEnum.vonString(gegnerTyp3.toUpperCase());
				
				fuegeKIHinzu(gegner, spiel);
				
				request.getServletContext().setAttribute("positionen",
						HilfsMethoden.konvertiereFigurenInZeileUndSpalte(spiel.getAlleFigurenPositionen()));
				
				request.setAttribute("spielerAmZugFarbe", spiel.getSpielerAmZugFarbe());
				
				response.sendRedirect("spielfeld.jsp");
				return;
			} catch (SpielerFarbeVorhandenException e) {
				HilfsMethoden.zeigeFehlerJSP("Konnte keine KI erstellen", request, response);
				return;
			}
		} else if (spielerAnzahl == 4 && 
				spieler2IstKI &&
				spieler3IstKI &&
				spieler4IstKI) {
			
			try {
				iBediener spiel = (iBediener) request.getServletContext().getAttribute("spiel");
				
				KiTypEnum[] gegner = new KiTypEnum[3];
				gegner[0] = KiTypEnum.vonString(gegnerTyp2.toUpperCase());
				gegner[1] = KiTypEnum.vonString(gegnerTyp3.toUpperCase());
				gegner[2] = KiTypEnum.vonString(gegnerTyp4.toUpperCase());
				fuegeKIHinzu(gegner, spiel);
				
				request.getServletContext().setAttribute("positionen",
						HilfsMethoden.konvertiereFigurenInZeileUndSpalte(spiel.getAlleFigurenPositionen()));
				
				request.setAttribute("spielerAmZugFarbe", spiel.getSpielerAmZugFarbe());
				
				response.sendRedirect("spielfeld.jsp");
				return;
			} catch (SpielerFarbeVorhandenException e) {
				HilfsMethoden.zeigeFehlerJSP("Konnte keine KI erstellen", request, response);
				return;
			}
		}
		
		response.sendRedirect("Login_HTML/bitteWarten.html");
	}

	private void fuegeKIHinzu(KiTypEnum[] gegner, iBediener spiel)
			throws SpielerFarbeVorhandenException {
		
		for (int i = 0; i < gegner.length; i++) {
			
			int j = 0;
			while (spiel.isFarbeVergeben(FarbEnum.vonInt(j))) {
				++j;
			}
			spiel.spielerHinzufuegen("KI", FarbEnum.vonInt(j), gegner[i]);
		}
	}

}
