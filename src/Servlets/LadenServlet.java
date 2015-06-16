package Servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;

import Fehler_Exceptions.SpielerFarbeVorhandenException;
import Fehler_Exceptions.SpielerNichtGefundenException;
import Speichern_Laden.DatenzugriffCSV;
import Speichern_Laden.DatenzugriffPDF;
import Speichern_Laden.DatenzugriffPDFServer;
import Speichern_Laden.DatenzugriffSerialisiert;
import Speichern_Laden.DatenzugriffXML;
import Speichern_Laden.iDatenzugriff;
import Spiel.FarbEnum;
import Spiel.iBediener;

/**
 * Servlet implementation class LadenServlet
 */
@WebServlet("/LadenServlet")
public class LadenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LadenServlet() {
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
		String dateiname = request.getParameter("Dateiname");
		String dateiformat = request.getParameter("format");
		
		if (dateiformat == null || dateiname == null) {
			JSPHilfsmethoden.zeigeFehlerJSP("Laden servlet kann nicht einzeln aufgerufen werden", "../", request, response);
			return;
		}
		
		if (dateiname.contains(".")) {
			dateiname = dateiname.substring(0, dateiname.indexOf("."));
		}
		
		iDatenzugriff dz = null;
		
		if (dateiformat.equals("csv")) {
			dz = new DatenzugriffCSV();
		} else if (dateiformat.equals("pdf")) {
			dz = new DatenzugriffPDFServer(request);
		} else if (dateiformat.equals("ser")) {
			dz = new DatenzugriffSerialisiert();
		} else if (dateiformat.equals("xml")) {
			dz = new DatenzugriffXML();
		}
		
		try {
			ServletContext servletContext = request.getServletContext();
			HttpSession session = request.getSession();
			
			String realerName = servletContext.getRealPath("gespeicherteDateien/" + dateiname + "." + dateiformat);
			iBediener spiel = (iBediener) dz.spielLaden(realerName);
			
			
			
			servletContext.setAttribute("spiel", spiel);
			
			servletContext.setAttribute("spielerAnzahl", new Integer(spiel.getSpieler().length));
			servletContext.setAttribute("positionen",	HilfsMethoden.konvertiereFigurenInZeileUndSpalte(spiel.getAlleFigurenPositionen()));
			servletContext.setAttribute("spielWurdeGeladen", new Boolean(true));
			servletContext.setAttribute("spielerAmZugFarbe", spiel.getSpielerAmZugFarbe());
			
			FarbEnum[] vorhandeneFarben = new FarbEnum[spiel.getSpieler().length];
			
			for (int i = 0; i < spiel.getSpieler().length; ++i){
				 
				String[] spieler = spiel.getSpieler()[i].split(" ; ");
				
				vorhandeneFarben[i] = FarbEnum.vonString(spieler[1]);
			}
			
			Integer anzahlBeitreten = new Integer(1);
			
			for (int i = 1; i < spiel.getSpieler().length; ++i) {
				String[] spieler = spiel.getSpieler()[i].split(" ; ");
				
				if (spieler[2].equals("AGGRESIV") || spieler[2].equals("DEFENSIV")) {
					++anzahlBeitreten;
				} else {
					break;
				}
			}
			
			servletContext.setAttribute("vorhandeneFarben", vorhandeneFarben);
			servletContext.setAttribute("anzahlBeitreten", anzahlBeitreten);
			
			String[] ersterSpieler = spiel.getSpieler()[0].split(" ; ");
			
			session.setAttribute("name", ersterSpieler[0]);
			session.setAttribute("farbe", FarbEnum.vonString(ersterSpieler[1].toUpperCase()));
			
			
			if (spiel.getSpieler().length == 1) {
				response.sendRedirect("spielfeld.jsp");
			} else {
				response.sendRedirect("Login_HTML/bitteWarten.html");
			}
			
		} catch (ClassNotFoundException | SpielerFarbeVorhandenException
				| SpielerNichtGefundenException | JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
