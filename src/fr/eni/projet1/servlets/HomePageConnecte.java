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
 * Servlet implementation class forEachTest
 */
@WebServlet("/HomePageConnecte")
public class HomePageConnecte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public HomePageConnecte() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		//Ici on  reçoit toutes les requettes venant des clics sur le logo

		String urlConnectedHomePage = "/HomePageConnecte.jsp";
		String urlHomePage = "/HomePage.jsp";
		HttpSession session = request.getSession();
		String status = (String) session.getAttribute("status");
		
		ArticlesVendusDAO articlesVendusDAO = DAOFactory.getArticlesVendusDAO();
		List<ArticleVendu> articles = null;
		
		if (session!=null && status!=null) 
		{
			if (status.equals("connected"))
			{
				try {
					articles = articlesVendusDAO.showAllArticles();
				} catch (DALException e) {
					request.setAttribute("messageErreur", "Une erreur est survenue, veuillez nous contacter au 06-06-06-06-06");
					e.printStackTrace();
				}
				request.setAttribute("articles", articles);
				getServletContext().getRequestDispatcher(urlConnectedHomePage).forward(request, response);
	
			}
			
			if (status.equals("notConnected") || session==null)
			{	
				try {
					articles = articlesVendusDAO.showAllArticles();
				} catch (DALException e) {
					request.setAttribute("messageErreur", "Une erreur est survenue, veuillez nous contacter au 06-06-06-06-06");
					e.printStackTrace();
				}
				request.setAttribute("articles", articles);
				getServletContext().getRequestDispatcher(urlHomePage).forward(request, response);
			}
			
		}
		
		else 
		{
			try {
				articles = articlesVendusDAO.showAllArticles();
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("articles", articles);
			getServletContext().getRequestDispatcher(urlHomePage).forward(request, response);
		}
		
		
		
		
		
		
						
	}

	
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Ici on reï¿½ois le post venant de la selection d'une catï¿½gorie depuis la HomePageConnecte.jsp
		String urlConnectedHomePage = "/HomePageConnecte.jsp";
		int choix = Integer.valueOf(request.getParameter("selectCategorie"));
		//System.out.println(choix);

		
		//Affichage des articles suivant la categorie choisie dans la jsp
		ArticlesVendusDAO articlesVendusDAO = DAOFactory.getArticlesVendusDAO();
		List<ArticleVendu> articles = null;
		try {
			articles = articlesVendusDAO.showArticlesByCat(choix);
		} catch (DALException e) {
			request.setAttribute("messageErreur", "Une erreur est survenue, veuillez nous contacter au 06-06-06-06-06");
			e.printStackTrace();
		}
		request.setAttribute("articles", articles);
		getServletContext().getRequestDispatcher(urlConnectedHomePage).forward(request, response);
		
	}

	
	
}
