package WebSpielAnlegen;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ErsterSpieler")
public class ErsterSpieler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ErsterSpieler() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String einSpieler = request.getParameter("anzahl");
		response.setContentType("text/html;charset=ISO-8859-1");
		PrintWriter out = response.getWriter();
		
		
	}

}
