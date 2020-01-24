package fr.eni.projet1.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.eni.projet1.bo.ArticleVendu;
import fr.eni.projet1.bo.Enchere;
import fr.eni.projet1.dal.DALException;
import fr.eni.projet1.dal.dao.ArticlesVendusDAO;
import fr.eni.projet1.dal.dao.DAOFactory;
import fr.eni.projet1.dal.dao.EncheresDAO;
import fr.eni.projet1.dal.dao.UtilisateurDAO;

/**
 * Servlet implementation class Encherir
 */
@WebServlet("/Encherir")
public class Encherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	
    public Encherir() {
        super();
        // TODO Auto-generated constructor stub
    }


    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Ici on recup le formulaire envoy�e depuis la jsp DetailsVente.jsp
		
		HttpSession session  = request.getSession();
		String urlDetailsVente = "/DetailsVente.jsp";
		String urlHommeConnectedPage = "/HomePageConnecte.jsp";
		String urlLoginPage = "/Login.jsp";
		
		//On r�cup�re le numero de l'article en question et le montant proposé
		int noArticle = Integer.valueOf(request.getParameter("noArticle").trim());
		System.out.println(noArticle);
		int montantEnchere = Integer.valueOf(request.getParameter("montantEnchere").trim());
		//On récuère le numero de l'Utilisateur qui enchérit
		int noUtilisateur = (int) session.getAttribute("numeroUtilisateur");
		Date dateEnchere;
		EncheresDAO encheresDAO= DAOFactory.getEncheresDAO();
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();
		
		
		int credit = (int) session.getAttribute("credit");
		//Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		
		
		//On appelle la méthode getMeilleureOffreSurUnArticle pour récuperer la meilleure offre actuelle 
		int meilleurOffreActuelle = 0;
		try {
			meilleurOffreActuelle = encheresDAO.getMeilleureOffreSurUnArtcile(noArticle);
		} catch (DALException e) {
			request.setAttribute("messageErreur", "Une erreur est survenue, veuillez nous contacter au 06-06-06-06-06");
			e.printStackTrace();
		}
		
		//On R�cup�re la liste des encheres sur l'article en question (vide ou non)
		List<Enchere> encheres = null;
		try {
			encheres = encheresDAO.listEncheresSurUnArticle(noArticle);
		} catch (DALException e) {
			request.setAttribute("messageErreur", "Une erreur est survenue, veuillez nous contacter au 06-06-06-06-06");
			e.printStackTrace();
		}
		
		if (credit>=montantEnchere) 
		{
			if (!encheres.isEmpty()) 
			{
				
					//On compare l'enchere faite � la meilleure offre actuelle
					if (meilleurOffreActuelle>=montantEnchere) 
					{
						//TODO faire de sorte que dans ce cas ci les details de l'article restent sur la page
						request.setAttribute("enchereInferieureMessage", "Vote offre doit �tre sup�rieure � la meilleure actuelle.");
						getServletContext().getRequestDispatcher(urlDetailsVente).forward(request, response);
					}
					
					else 
					{
						//On récupère la session en cours
					
						String status = (String) session.getAttribute("status");
						
						if (status!=null) 
						{
								switch (status) 
								{
								case "notConnected":
									// Rediriger vers la page de connecxion
									getServletContext().getRequestDispatcher(urlLoginPage).forward(request, response);
									break;
								case "connected":
									//On récuère le numero de l'Utilisateur qui enchérit
									 noUtilisateur = (int) session.getAttribute("numeroUtilisateur");
									// Inserer l'enchère							
									Enchere enchere = new Enchere(montantEnchere, noArticle, noUtilisateur);
							
									
									try {
										//On recup lenchere correspondant au meilleur offre
										Enchere enchere1 = encheresDAO.enchereMeilleurOffre(meilleurOffreActuelle, noArticle);
										encheresDAO.insertEnchere(enchere);
										//On débite l'utilisateur du montant qu'il a enchérit
										utilisateurDAO.debiterUtilisateur(montantEnchere,noUtilisateur);
										
										//On recredite l'utilisateur
										utilisateurDAO.recrediterUtilisateur(enchere1);
										
					
									} catch (DALException e) {
										request.setAttribute("messageErreur", "Une erreur est survenue, veuillez nous contacter au 06-06-06-06-06");
										e.printStackTrace();
									}
									//Récupération de tous les articles en vente
									ArticlesVendusDAO articlesVendusDAO = DAOFactory.getArticlesVendusDAO();
									List<ArticleVendu> articles = null;
									try {
										articles = articlesVendusDAO.showAllArticles();
									} catch (DALException e) {
										request.setAttribute("messageErreur", "Une erreur est survenue, veuillez nous contacter au 06-06-06-06-06");
										e.printStackTrace();
									}
									request.setAttribute("articles", articles);
									getServletContext().getRequestDispatcher(urlHommeConnectedPage).forward(request, response);
									
									break;
						
								default:
									// Rediriger vers la page de connecxion
									getServletContext().getRequestDispatcher(urlLoginPage).forward(request, response);
									break;
								}
							
							}
						else 
						    {
							// Rediriger vers la page de connecxion
							getServletContext().getRequestDispatcher(urlLoginPage).forward(request, response);
						    }
					}
				
			}
			
			else 
			{
				//On récuère le numero de l'Utilisateur qui enchérit
				
				noUtilisateur = (int) session.getAttribute("numeroUtilisateur");
				// Inserer l'enchère
				Enchere enchere = new Enchere(montantEnchere, noArticle, noUtilisateur);
				try {
					encheresDAO.insertEnchere(enchere);
				} catch (DALException e) {
					request.setAttribute("messageErreur", "Une erreur est survenue, veuillez nous contacter au 06-06-06-06-06");
					e.printStackTrace();
				}
				//Récupération de tous les articles en vente
				ArticlesVendusDAO articlesVendusDAO = DAOFactory.getArticlesVendusDAO();
				List<ArticleVendu> articles = null;
				try {
					articles = articlesVendusDAO.showAllArticles();
				} catch (DALException e) {
					request.setAttribute("messageErreur", "Une erreur est survenue, veuillez nous contacter au 06-06-06-06-06");
					e.printStackTrace();
				}
				request.setAttribute("articles", articles);
				getServletContext().getRequestDispatcher(urlHommeConnectedPage).forward(request, response);	
			}
			
		}
		
		else 
		{
			request.setAttribute("creditPasSuffisantMessage", "Votre cr�dit est insuffisant.");
			getServletContext().getRequestDispatcher(urlDetailsVente).forward(request, response);		
		}			
	}

}
