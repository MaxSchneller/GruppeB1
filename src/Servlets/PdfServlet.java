package Servlets;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.util.converter.ByteStringConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.pdf.parser.ContentByteUtils;

/**
 * Servlet implementation class PdfServlet
 */
@WebServlet("/PdfServlet")
public class PdfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PdfServlet() {
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
		
		String dateiname = (String)request.getSession().getAttribute("dateiname");
		
		if (dateiname != null) {
			
			
			response.setContentType("application/pdf");
			
			File file = null;
			FileInputStream is = null;
			
			try {
				file = new File(dateiname);
				is = new FileInputStream(file);

				byte[] b = new byte[(int) file.length()];
				is.read(b, 0, b.length);
				is.close();
				response.getOutputStream().write(b);
				return;
			} catch (FileNotFoundException e) {
				JSPHilfsmethoden.zeigeFehlerJSP(e.getMessage(), request, response);
				return;
			} finally {
				is.close();
			}
		}
	}

}
