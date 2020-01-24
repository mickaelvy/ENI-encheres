package fr.eni.projet1.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet1.bo.ArticleVendu;
import fr.eni.projet1.dal.DALException;
import fr.eni.projet1.dal.dao.ArticlesVendusDAO;
import fr.eni.projet1.dal.dao.DAOFactory;


@WebServlet("/Deconnexion")
public class Deconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Deconnexion() {
        super();
     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String homePage = "/HomePage.jsp";
		 //Ici on recoit la requette de deconnection de toutes les jsp.
		   // Récupération et destruction de la session en cours
		  request.getSession().invalidate();;
	       //création d'une nouvelle session pour donner un statut "notConnected"
		  HttpSession session = request.getSession(true);
		  session.setAttribute("status", "notConnected");
	        // Redirection vers la page d'accueil du mode déconnecté
			ArticlesVendusDAO articlesVendusDAO = DAOFactory.getArticlesVendusDAO();
			List<ArticleVendu> artilces = null;
			try {
				artilces = articlesVendusDAO.showAllArticles();
			} catch (DALException e) {
				// TODO Auto-generated catch block
				request.setAttribute("messageErreur", "Une erreur est survenue, veuillez nous contacter au 06-06-06-06-06");
				getServletContext().getRequestDispatcher(homePage).forward(request, response);
				e.printStackTrace();
			}
			request.setAttribute("articles", artilces);
			getServletContext().getRequestDispatcher(homePage).forward(request, response);
				
	        //response.sendRedirect(homePage);    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	//	doGet(request, response);
	}

}
