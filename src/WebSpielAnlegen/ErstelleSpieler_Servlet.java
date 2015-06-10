package WebSpielAnlegen;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Fehler_Exceptions.SpielerFarbeVorhandenException;
import Kuenstliche_Intelligenz.KiTypEnum;
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
			FarbEnum farbe1 = FarbEnum.vonString(farbe.toUpperCase());
			Integer anzahlBeitreten = (Integer) request.getServletContext().getAttribute("anzahlBeitreten");
			anzahlBeitreten=anzahlBeitreten+1;
			KiTypEnum kiTyp= (KiTypEnum) request.getServletContext().getAttribute("gegnerTyp"+anzahlBeitreten);
			try {
				ib.spielerHinzufuegen(name, farbe1, kiTyp);
			} catch (SpielerFarbeVorhandenException e) {
				response.sendRedirect("Login_HTML/Error.html");
			}
			request.getServletContext().setAttribute("anzahlBeitreten", anzahlBeitreten);
			response.sendRedirect("Login_HTML/bitteWarten.html");
		}
	}

}
