package fr.eni.projet1.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet1.bll.UtilisateurManager;
import fr.eni.projet1.dal.DALException;



@WebServlet("/SupprimerMonCompte")
public class SupprimerMonCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
    public SupprimerMonCompte() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Ici passe tout clic sur l'onglet "Supprimer mon c ompte"
		
		String url = "/SupprimerMonCompte.jsp";
		String email  = (String) request.getSession().getAttribute("email");
		
		//On pré-remplit l'adresse mail de l'utilisateur
		request.setAttribute("email", email);
		getServletContext().getRequestDispatcher(url).forward(request, response);
			
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	
		String url = "/Login.jsp";
		String mail = request.getParameter("email");
		String password = request.getParameter("mot_de_passe");
		
		//HttpSession session = request.getSession();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		//int noUtilisateur = (int) session.getAttribute("numeroUtilisateur");
		try {
			utilisateurManager.supprimerUtilisateur(mail, password);
		} catch (DALException e) {
			e.printStackTrace();
			request.setAttribute("messageErreur", "Impossible de supprimer votre compte, veuillez nous contacter au 06-06-06-06-06");
		}
		 request.getSession().invalidate();
		 HttpSession session = request.getSession(true);
		 session.setAttribute("status", "notConnected");
			
		 getServletContext().getRequestDispatcher(url).forward(request, response);
		
	}

}
