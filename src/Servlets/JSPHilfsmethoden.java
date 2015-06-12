package Servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Enthaelt Hilfsmethode fuer JSPs 
 * Die hier enthaltenen Methoden duerfen auf keinen Fall mit dem Backend arbeiten
 */
public class JSPHilfsmethoden {

	/**
	 * Berechnet ob die angegebene Zelle ein Spielfeld ist
	 * @param spalte Spalte der Zelle
	 * @param zeile Reihe der Zelle
	 * @return True falls die Zelle ein Spielfeld ist
	 */
	public static boolean isZelleSpielfeld(int spalte, int zeile) {
		
		switch(spalte) {
		case 0:
		case 1:
		case 9:
		case 10: {
			if (HilfsMethoden.inIntervall(0, 1, zeile) ||
					HilfsMethoden.inIntervall(4, 6, zeile) ||
					HilfsMethoden.inIntervall(9, 10, zeile))
				return true;
			return false;
		}
		case 2:
		case 3:
		case 7:
		case 8: {
			if (HilfsMethoden.inIntervall(4, 6, zeile)) 
				return true;
			return false;
		}
		case 4:
		case 6:{
			return true;
		}
		case 5: {
			if (zeile == 5) 
			 return false;
			return true;
		}
		default: {
			throw new RuntimeException("Konnte nicht berechnen ob Zelle (" + spalte + "," + zeile + ") ein Spielfeld ist.");
		}
		}
		
	}
	
	/**
	 * Zeigt die fehler.jsp Seite an
	 * @param nachricht Nachricht, die angezeigt werden soll
	 * @param request Servlet request
	 * @param response Servlet response
	 * @throws IOException 
	 */
	public static void zeigeFehlerJSP(String nachricht,
		HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSPHilfsmethoden.zeigeFehlerJSP(nachricht, "", request, response);
	}

	/**
	 *  Zeigt die fehler.jsp Seite an
	 * @param nachricht Nachricht, die angezeigt werden soll
	 * @param relativerPfad Relativer Pfad zu fehler.jsp ohne Dateiname (z.B. "../")
	 * @param request Servlet request
	 * @param response Servlet response
	 * @throws IOException
	 */
	public static void zeigeFehlerJSP(String nachricht, String relativerPfad, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().setAttribute("fehlerArg", nachricht);
		response.sendRedirect(relativerPfad + "fehler.jsp");
	}
}
