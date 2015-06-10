package WebSpielAnlegen;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Kuenstliche_Intelligenz.KiTypEnum;

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
		
		if(gegnerTyp2 != null && !gegnerTyp2.equals("keineKI")){
			KiTypEnum kiTyp = KiTypEnum.vonString(gegnerTyp2.toUpperCase());
			request.getServletContext().setAttribute("gegnerTyp2", kiTyp);
		}
		if(gegnerTyp3 != null && !gegnerTyp3.equals("keineKI")){
			KiTypEnum kiTyp = KiTypEnum.vonString(gegnerTyp3.toUpperCase());
			request.getServletContext().setAttribute("gegnerTyp3", kiTyp);
		}
		if(gegnerTyp4 != null && !gegnerTyp4.equals("keineKI")){
			KiTypEnum kiTyp = KiTypEnum.vonString(gegnerTyp4.toUpperCase());
			request.getServletContext().setAttribute("gegnerTyp4", kiTyp);
		}
		
		response.sendRedirect("Login_HTML/bitteWarten.html");
	}

}
