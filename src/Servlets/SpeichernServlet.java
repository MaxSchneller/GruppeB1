package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import Speichern_Laden.DatenzugriffCSV;
import Speichern_Laden.DatenzugriffPDF;
import Speichern_Laden.DatenzugriffPDFServer;
import Speichern_Laden.DatenzugriffSerialisiert;
import Speichern_Laden.DatenzugriffXML;
import Speichern_Laden.iDatenzugriff;
import Spiel.iBediener;

/**
 * Servlet implementation class SpeichernServlet
 */
@WebServlet("/SpeichernServlet")
public class SpeichernServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SpeichernServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		String dateiname = request.getParameter("Dateiname");
		String dateiformat = request.getParameter("auswahl");
		
		if(dateiname != null && !dateiname.isEmpty()){
			if(dateiname.contains(".")){
				dateiname = dateiname.substring(0,dateiname.indexOf("."));
			}
			iBediener spiel = (iBediener) request.getServletContext().getAttribute("spiel");
			if(spiel != null){
				iDatenzugriff id = null;
				if(dateiformat.equals("pdf")){
					id = new DatenzugriffPDFServer(request); 
					dateiname += ".pdf";
					try {
						id.spielSpeichern(spiel, "gespeicherteDateien/" + dateiname);
					} catch (JAXBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					request.getSession().setAttribute("dateiname",  request.getServletContext().getRealPath("gespeicherteDateien/" + dateiname));
					response.sendRedirect("SpeichernLaden_HTML/pdf.jsp");
					return;
				} else if(dateiformat.equals("csv")){
					id = new DatenzugriffCSV(); 
					dateiname += ".csv";
					try {
						id.spielSpeichern(spiel, "gespeicherteDateien/" + dateiname);
					} catch (JAXBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if(dateiformat.equals("ser")){
					id = new DatenzugriffSerialisiert(); 
					dateiname += ".ser";
					try {
						id.spielSpeichern(spiel, "gespeicherteDateien/" + dateiname);
					} catch (JAXBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if(dateiformat.equals("xml")){
					id = new DatenzugriffXML(); 
					dateiname += ".xml";
					try {
						id.spielSpeichern(spiel, request.getServletContext().getRealPath("gespeicherteDateien/" + dateiname) );
					} catch (JAXBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
