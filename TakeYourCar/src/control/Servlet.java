package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import eccezioni.LoginException;
import model.AdminModel;
import model.ClienteModel;
import model.EntrateModel;
import model.Immagine;
import model.ImmagineModel;
import model.Veicolo;
import model.VeicoloModel;
import model.WebUser;

public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String URL = "";
	
	static ClienteModel clienteModel;
	static AdminModel adminModel;
	static ImmagineModel imgModel;
	static VeicoloModel veicolomodel;
	static EntrateModel entrateModel;
	
	static {
		clienteModel = new ClienteModel("TakeYourCarKey12345");
		adminModel = new AdminModel("TakeYorCarKey12345");
		imgModel = new ImmagineModel();
		veicolomodel = new VeicoloModel();
		entrateModel = new EntrateModel();
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		URL = config.getServletContext().getInitParameter("URL");
	}
	
	public void destroy() {
		super.destroy();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet");
		
		String ID = request.getParameter("ID");
		String Targa = request.getParameter("Targa");
		String action = (String) request.getParameter("action");
		String redirect = request.getParameter("redirect");
		if (ID == null) ID = "";
		if (Targa == null) Targa = "";
		if (action == null) action = "";
		if (redirect == null) redirect = "";
		
		if (redirect.equals("home.jsp") || redirect.equals("admin/veicoli.jsp")) {
			veicolo(request, response);
			RequestDispatcher disp = request.getRequestDispatcher(response.encodeURL(redirect));
			disp.forward(request, response);
			return;
		}
		else if (redirect.contains("admin/veicolo.jsp?Targa=")) {
			System.out.println("Contiene");
			immagini(request, response, Targa);
			RequestDispatcher disp = request.getRequestDispatcher(response.encodeURL(redirect));
			disp.forward(request, response);
			return;
		}
		else if (action.equals("logout")) {
			request.getSession(false).removeAttribute("cliente");
			request.getSession(false).removeAttribute("admin");
			request.getSession(false).invalidate();
			response.sendRedirect(response.encodeURL(URL + "login.jsp"));
			return;
		} else if (action.equals("delete")) {
			imgModel.delete(ID);
			response.sendRedirect(response.encodeURL(URL + "admin/veicolo.jsp?Targa=" + Targa));
			return;
		} else if (action.equals("copertina")) {
			veicolomodel.set(ID, Targa);
			response.sendRedirect(response.encodeURL(URL + "admin/veicolo.jsp?Targa=" + Targa));
			return;
		}
		
		WebUser utente = (WebUser) request.getSession().getAttribute("utente");
		
		if (utente != null && !utente.getNome().equals("")) {
			response.sendRedirect(response.encodeURL(URL + "home.jsp"));
			return;			
		}
		else {
			response.sendRedirect(response.encodeURL(URL + "login.jsp"));
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void veicolo(HttpServletRequest request, HttpServletResponse response) {
		try {
			ArrayList<Veicolo> veicoli = veicolomodel.selectAll();
			request.setAttribute("veicoli", veicoli);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public void immagini(HttpServletRequest request, HttpServletResponse response, String targa) {
		try {
			Veicolo veicolo = veicolomodel.selectByKey(targa);
			ArrayList<Immagine> immagini = imgModel.searchByKey(targa);
			request.setAttribute("immagini", immagini);
			request.setAttribute("veicolo", veicolo);
		} catch (SQLException | LoginException ex) {
			System.err.println(ex.getMessage());
		}
	}

}
