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

/**
 * Servlet implementation class MesAchats
 */
@WebServlet("/MesAchats")
public class MesAchats extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	
    public MesAchats() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Ici on reï¿½ois le post venant de la selection du menu  deroulant achats depuis la HomePageConnecte.jsp
		String urlConnectedHomePage = "/HomePageConnecte.jsp";
		String choix = request.getParameter("selectAchat");
		//System.out.println(choix);
		HttpSession session = request.getSession();
		int noUtilisateur =   (int) session.getAttribute("numeroUtilisateur");
		
		//Affichage des articles suivant la categorie choisie dans la jsp
		ArticlesVendusDAO articlesVendusDAO = DAOFactory.getArticlesVendusDAO();
		
		List<ArticleVendu> articles = null;
		try {
			articles = articlesVendusDAO.showAchatsByUser(choix, noUtilisateur);
		} catch (DALException e) {
			request.setAttribute("messageErreur", "Une erreur est survenue, veuillez nous contacter au 06-06-06-06-06");
			e.printStackTrace();
		}
		
		if (!articles.isEmpty()) {
			request.setAttribute("articles", articles);
			getServletContext().getRequestDispatcher(urlConnectedHomePage).forward(request, response);
		}
		else {
			request.setAttribute("rechercheVidemessage", "Vous n'avez aucune ventes dans cette catégorie.");
			getServletContext().getRequestDispatcher(urlConnectedHomePage).forward(request, response);
		}
		
	
	}

}
