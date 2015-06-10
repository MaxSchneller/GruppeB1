package Servlets;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Stellt oefter genutzte Funktionen bereit
 */
public class HilfsMethoden {
	
	/**
	 * Sucht den ServletContext nach "status" ab und fuegt am Anfang die gewuenschte Nachricht hinzu
	 * @param request Der erhaltene HttpRequest des Servlets
	 * @param nachricht Die Nachricht, die eingefuegt werden soll
	 */
	public static void fuegeStatusHinzu(HttpServletRequest request, String nachricht) {
		ServletContext ctx = request.getServletContext();
		String status = (String)ctx.getAttribute("status");
		
		if (status == null) {
			status = "";
		}
		
		String neuerStatus = nachricht + "<br/>" + status;
		
		ctx.setAttribute("status", neuerStatus);
		
	}

}
