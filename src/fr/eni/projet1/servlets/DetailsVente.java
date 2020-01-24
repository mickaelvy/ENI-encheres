package fr.eni.projet1.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet1.bll.UtilisateurManager;
import fr.eni.projet1.bo.ArticleVendu;
import fr.eni.projet1.bo.Retrait;
import fr.eni.projet1.bo.Utilisateur;
import fr.eni.projet1.dal.DALException;
import fr.eni.projet1.dal.dao.ArticlesVendusDAO;
import fr.eni.projet1.dal.dao.DAOFactory;
import fr.eni.projet1.dal.dao.RetraitDAO;

/**
 * Servlet implementation class DetailsVente
 */
@WebServlet("/DetailsVente")
public class DetailsVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    
	
    public DetailsVente() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//Ici on recoit ttes les requettes de clic sur le lien "Enchérir"
		String url = "/DetailsVente.jsp";
		String urlLogin = "/Login.jsp";
		HttpSession session = request.getSession();
		String status = (String) session.getAttribute("status");
		int noArticle = Integer.valueOf(request.getParameter("noArticle"));

		//On vï¿½rifie si l'utilisateur est toujours connectï¿½
		
		if (status!=null) 
		{
				if (status.equals("connected")) 
				{
					ArticleVendu article = null;
					ArticlesVendusDAO articleDao = DAOFactory.getArticlesVendusDAO();
					try {
						article = articleDao.selectArticleById(noArticle);
					} catch (DALException e) {
						request.setAttribute("messageErreur", "Impossible de visualiser l'article, veuillez nous contacter au 06-06-06-06-06");
						e.printStackTrace();
					}
					
					//On rï¿½cupere le nom du vendeur ï¿½ partir de son  identifiant
					Utilisateur utilisateur = new Utilisateur();
					UtilisateurManager utilisateurManager = new UtilisateurManager();
					try {
						utilisateur = utilisateurManager.getUtilisateurById(article.getNoUtilisateurVendeur());
					} catch (DALException e) {
						e.printStackTrace();
						request.setAttribute("messageErreur", "Impossible de visualiser l'article, veuillez nous contacter au 06-06-06-06-06");
					}
					String pseudoVendeur = utilisateur.getPseudo();
					
					//On rï¿½cupere les attribut de l'adresse de retrait ï¿½ partir du nomero de l'article
					Retrait retrait = new Retrait();
					RetraitDAO retraitDAO = DAOFactory.getRetraitDAO();
					try {
						retrait = retraitDAO.getRetraitById(noArticle);
					} catch (DALException e) {
						request.setAttribute("messageErreur2", "Une erreur est survenue, veuillez nous contacter au 06-06-06-06-06");
						e.printStackTrace();
					}
					String rue = retrait.getRue();
					String codePostal = retrait.getCodePostal();
					String ville = retrait.getVille();
					
					
					request.setAttribute("numeroArticle", noArticle);
					request.setAttribute("nomArticle", article.getNomArticle());
					request.setAttribute("description", article.getDescription());
					request.setAttribute("no_categorie", article.getNoCategorie());
					request.setAttribute("meilleurOffre", article.getPrixVente());
					request.setAttribute("PrixInitial", article.getPrixInitial());
					request.setAttribute("dateFinEnchere", article.getDateFinEncheres());
					request.setAttribute("pseudoVendeur", pseudoVendeur);
					
					request.setAttribute("rueRetrait", rue);
					request.setAttribute("codePostalRetrait", codePostal);
					request.setAttribute("villeDeRetrait", ville);
				
			
					getServletContext().getRequestDispatcher(url).forward(request, response);
	
				}
	
				else {
					
					request.setAttribute("messagePourSeReconnecter", "Pour visualiser les détails d'une vente, vous devez être connecté.");
					getServletContext().getRequestDispatcher(urlLogin).forward(request, response);
					
				}
			
		}
		
		else {
			
			request.setAttribute("messagePourSeReconnecter", "Pour visualiser les détails d'une vente, vous devez être connecté.");
			getServletContext().getRequestDispatcher(urlLogin).forward(request, response);
			
		}
	}

}
