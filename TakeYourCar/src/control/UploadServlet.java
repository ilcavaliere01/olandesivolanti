package control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String URL;
	
	public void init() {
		URL = getServletContext().getInitParameter("URL");
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String targa = request.getParameter("Targa");
		String tabella = request.getParameter("tabella");
		String nome = request.getParameter("Nome");
		if (targa == null) targa  = "";
		if (tabella == null) tabella = "";
		if (nome == null) nome = "";
		
		Part immagine = (Part) request.getPart("file");
		
		if (tabella.equals("immagini")) {
			try {
				ImgServlet.caricaImmagine(targa, immagine);
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		}
		else if (tabella.equals("marca")) {
			try {
				ImgServlet.caricaMarca(nome, immagine);
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		}

		
		response.sendRedirect(response.encodeURL(URL + "admin/veicolo.jsp?Targa=" + targa));
	}
}
