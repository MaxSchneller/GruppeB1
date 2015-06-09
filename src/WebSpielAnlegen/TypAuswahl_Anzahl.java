package WebSpielAnlegen;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet, der Spieleranzahl prueft, Ersten Spieler erstellt
 * @author gruppe B1
 *
 */
@WebServlet("/TypAuswahl_Anzahl")
public class TypAuswahl_Anzahl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * Konstruktor
	 */
    public TypAuswahl_Anzahl() {
        super();
    }

    /**
     * Nimmt die Resorcen der der neuesSpiel.jsp und gibt sie der doPost Methode
     */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * Verarbeitet die Resorcen der neuesSpiel.jsp
	 * durch "weiter..." wird geprueft wie viele Spieler teilnehmen 
	 * und eine neue Html geoeffnet wer Spieler1 begruesst und ihn
	 * auffordert den Typ der Gegner zu waehlen
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String anzahlSpielerString = request.getParameter("spieleranzahl");
		String nameSpieler1 = request.getParameter("eigenerName");
		String farbe = request.getParameter("eigeneFarbe");
		String typ = request.getParameter("spieler1");
		response.setContentType("text/html;charset=ISO-8859-1");
		PrintWriter out = response.getWriter();
		
		int anzahlSpieler = Integer.parseInt(anzahlSpielerString);
		
		if (anzahlSpieler > 1){
			//Head
			out.println("<html><head><title>Gegner erstellen</title><style type='text/css'>body {background-color: #F5F5F5;font-family: Arial;position: absolute;}</style></head><body>");
		
			//Ueberschrift
			out.println("<h1>Hallo " + nameSpieler1 +"!</h1><h2>Gegnertypen</h1><p>Entscheiden Sie welche Typer Ihre Gegner sein sollten.</p>");
			
			//2 Spieler
			if (anzahlSpieler == 2){
				out.println("<p><label for='spieler2'>Spieler 2</label><input type='radio' name='spieler2' value='keineKI' checked>Keine KI</input><input type='radio' name='spieler2' value='aggressiveKI'>Aggressive KI</input><input type='radio' name='spieler2' value='defensiveKI'>Defensive KI</input></p>");
			}
			//3 Spieler
			if (anzahlSpieler == 2){
				out.println("<p><label for='spieler2'>Spieler 2</label><input type='radio' name='spieler2' value='keineKI' checked>Keine KI</input><input type='radio' name='spieler2' value='aggressiveKI'>Aggressive KI</input><input type='radio' name='spieler2' value='defensiveKI'>Defensive KI</input></p>");
				out.println("<p><label for='spieler3'>Spieler 3</label><input type='radio' name='spieler3' value='keineKI' checked>Keine KI</input><input type='radio' name='spieler3' value='aggressiveKI'>Aggressive KI</input><input type='radio' name='spieler3' value='defensiveKI'>Defensive KI</input></p>");
			}
			//4 Spieler
			if (anzahlSpieler == 3){
				out.println("<p><label for='spieler2'>Spieler 2</label><input type='radio' name='spieler2' value='keineKI' checked>Keine KI</input><input type='radio' name='spieler2' value='aggressiveKI'>Aggressive KI</input><input type='radio' name='spieler2' value='defensiveKI'>Defensive KI</input></p>");
				out.println("<p><label for='spieler3'>Spieler 3</label><input type='radio' name='spieler3' value='keineKI' checked>Keine KI</input><input type='radio' name='spieler3' value='aggressiveKI'>Aggressive KI</input><input type='radio' name='spieler3' value='defensiveKI'>Defensive KI</input></p>");
				out.println("<p><label for='spieler4'>Spieler 4</label><input type='radio' name='spieler4' value='keineKI' checked>Keine KI</input><input type='radio' name='spieler4' value='aggressiveKI'>Aggressive KI</input><input type='radio' name='spieler4' value='defensiveKI'>Defensive KI</input></p>");

			}
			
			
			
		} else{
			//Spiel beginnen !!!Alleine
		}
		out.close();
		

	}

}
