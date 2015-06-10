package WebSpielAnlegen;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Dieser Servlet macht: Wenn die Willkommen.html geoeffnet wurde und der Prüfebutton gedrueckt wurde. 
 * Sie prueft ob ein Spiel existiert
 * @author Gruppe B1
 *
 */
@WebServlet("/SpielPruefen_Servlet")
public class SpielPruefen_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	/**
	 * Der Konstruktor. Er ruft die Oberklasse auf.
	 */
    public SpielPruefen_Servlet() {
        super();
    }

    /**
     * Nimmt die Resorcen der Willkommen.html und gibt sie der doPost Methode
     */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

    /**
     * Prueft ob ein Spiel existiert 
     * Wenn Ja: einem Spiel beitreten falls moeglich
     * Wenn Nein: ein Spiel erstellen
     */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pruefen = request.getParameter("pruefen");
		response.setContentType("text/html;charset=ISO-8859-1");
		PrintWriter out = response.getWriter();
		
		//Prüfung für dass noch kein Spiel existiert
		boolean spielExistiert = false;
		
		if (spielExistiert == false){
			response.sendRedirect("Login_HTML/neuesSpiel.html");
		}else{
			if (true) {// Anzahl ueberschritten, Fehler dass man nicht spielen kann darf
			//Fenster um Spiel beitreten
			}
		}
		
		out.close();
		
	}


}
