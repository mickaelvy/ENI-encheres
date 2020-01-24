package fr.eni.projet1.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projet1.bo.ArticleVendu;
import fr.eni.projet1.dal.DALException;
import fr.eni.projet1.dal.dao.ArticlesVendusDAO;
import fr.eni.projet1.dal.dao.DAOFactory;

/**
 * Servlet implementation class RechercherArticleParMotClef
 */
@WebServlet("/RechercherArticleParMotClef")
public class RechercherArticleParMotClef extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public RechercherArticleParMotClef() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//Ici on re�ois le post venant d'une recherche par mot cle depuis la HomePageConnecte.jsp
		String urlConnectedHomePage = "/HomePageConnecte.jsp";
		String motCle = request.getParameter("motCle").trim();
		System.out.println(motCle);
		
		if (motCle!=null && !motCle.isEmpty()) 
		{
			//Affichage des articles suivant la categorie choisie dans la jsp
			ArticlesVendusDAO articlesVendusDAO = DAOFactory.getArticlesVendusDAO();
			List<ArticleVendu> articles = null;
			try {
				articles = articlesVendusDAO.showArticlesMotCle(motCle);
			} catch (DALException e) {
				request.setAttribute("messageErreur", "Une erreur est survenue, veuillez nous contacter au 06-06-06-06-06");
				e.printStackTrace();
			}
			request.setAttribute("articles", articles);
			
			//Faire en sorte que le mot tap� reste sur dans le champs.
			request.setAttribute("motClef",motCle );
			getServletContext().getRequestDispatcher(urlConnectedHomePage).forward(request, response);	
		}
		
		else 
		{
			//Affichage de tous les articles
			ArticlesVendusDAO articlesVendusDAO = DAOFactory.getArticlesVendusDAO();
			List<ArticleVendu> articles = null;
			try {
				articles = articlesVendusDAO.showAllArticles();
			} catch (DALException e) {
				request.setAttribute("messageErreur", "Une erreur est survenue, veuillez nous contacter au 06-06-06-06-06");
				e.printStackTrace();
			}
			request.setAttribute("articles", articles);
			request.setAttribute("aucunMotTapeMessage", "Vous n'avez rien tap�.");
			getServletContext().getRequestDispatcher(urlConnectedHomePage).forward(request, response);	
		}

	}

}
