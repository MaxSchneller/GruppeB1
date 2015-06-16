package WebSpielAnlegen;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Fehler_Exceptions.SpielerFarbeVorhandenException;
import Kuenstliche_Intelligenz.KiTypEnum;
import Servlets.HilfsMethoden;
import Servlets.JSPHilfsmethoden;
import Spiel.FarbEnum;
import Spiel.iBediener;

/**
 * Servlet implementation class ErstelleSpieler_Servlet
 */
@WebServlet("/ErstelleSpieler_Servlet")
public class ErstelleSpieler_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ErstelleSpieler_Servlet() {
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
		String name = request.getParameter("eigenerName");
		String farbe  = request.getParameter("eigeneFarbe");
		
		if(name==null || farbe == null){
			response.sendRedirect("Login_HTML/Error.html");
		}else{
			iBediener ib= (iBediener) request.getServletContext().getAttribute("spiel");
			
			if (ib == null) {
				response.sendRedirect("Login_HTML/Willkommen.html");
				return;
			}
			FarbEnum farbe1 = FarbEnum.vonString(farbe.toUpperCase());
			Integer anzahlBeitreten = (Integer) request.getServletContext().getAttribute("anzahlBeitreten");
			anzahlBeitreten=anzahlBeitreten+1;
			KiTypEnum kiTyp= (KiTypEnum) request.getServletContext().getAttribute("gegnerTyp"+anzahlBeitreten);
			try {
				ib.spielerHinzufuegen(name, farbe1, kiTyp);
			} catch (SpielerFarbeVorhandenException e) {
				response.sendRedirect("fehler.jsp");
				return;
			}
			request.getServletContext().setAttribute("anzahlBeitreten", anzahlBeitreten);
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("farbe", farbe1);
			
			
			
			
			
			
			Integer spielerAnzahl = (Integer)request.getServletContext().getAttribute("spielerAnzahl");
			
			
			while (anzahlBeitreten < spielerAnzahl) {
				
				int naechsterSpieler = anzahlBeitreten + 1;
				KiTypEnum kiTypNaechsterSpieler = 
						(KiTypEnum)request.getServletContext().getAttribute("gegnerTyp" + naechsterSpieler);
				
				if (kiTypNaechsterSpieler != null) {
					
					iBediener spiel = (iBediener)request.getServletContext().getAttribute("spiel");
					if (spiel == null) {
						response.sendRedirect("Login_HTML/Willkommen.html");
						return;
					}
					try {
						int i = 0;
						
						while (spiel.isFarbeVergeben(FarbEnum.vonInt(i))) {
							++i;
						}
						
						spiel.spielerHinzufuegen("KI", FarbEnum.vonInt(i), kiTypNaechsterSpieler);
						
					} catch (SpielerFarbeVorhandenException e) {
						JSPHilfsmethoden.zeigeFehlerJSP("Konnte KI nicht erstellen (mit menschlichen Gegnern", request, response);
						return;
					}
					
					++anzahlBeitreten;
					request.getServletContext().setAttribute("anzahlBeitreten", anzahlBeitreten);
					
				} else {
					break;
				}
			}
			
			Boolean[] freieFarben = new Boolean[4];
			
			for (int i = 0; i < 4; ++i) {
				
				if (ib.isFarbeVergeben(FarbEnum.vonInt(i))) {
					freieFarben[i] = false;
				} else {
					freieFarben[i] = true;
				}
			}
			request.getServletContext().setAttribute("freieFarben", freieFarben);
			response.sendRedirect("Login_HTML/bitteWarten.html");
			
		}
	}

}
