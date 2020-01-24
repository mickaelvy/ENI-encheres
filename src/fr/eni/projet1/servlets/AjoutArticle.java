package fr.eni.projet1.servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.eni.projet1.bll.ArticlesVendusManager;
import fr.eni.projet1.bll.RetraitManager;
import fr.eni.projet1.bo.ArticleVendu;
import fr.eni.projet1.bo.Retrait;
import fr.eni.projet1.dal.DALException;
import fr.eni.projet1.dal.dao.ArticlesVendusDAO;
import fr.eni.projet1.dal.dao.DAOFactory;


@WebServlet("/AjoutArticle")
public class AjoutArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public AjoutArticle() {
        super();
       
        
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//ici passent tte les requette voulant acceder à "AjoutArticle.jsp"
		//pour vérifier si l'utilisateru est connecté
		//TODO : le code qu'il faut pour savoir si oui ou non l'utilisateur est connecté
		
		String url = "/HomePage.jsp";
		String urlNewVente = "/AjoutArticle.jsp";
		String rue = null;
		String codePostal = null;
		String ville = null;
		HttpSession session = request.getSession();
		
		
		if (session!=null) {
			rue =(String) session.getAttribute("rue");
			codePostal = (String) session.getAttribute("codePostal");
			ville = (String) session.getAttribute("ville");
			
			 request.setAttribute("rue", rue);
			 request.setAttribute("codePostal", codePostal);
			 request.setAttribute("ville", ville);
			
			getServletContext().getRequestDispatcher(urlNewVente).forward(request, response);
			
		}
		else {
			
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}
	
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String url = "/HomePageConnecte.jsp";
		String urlAjoutArticle = "/AjoutArticle.jsp";
		//Récuperation des infos sur l'article
		String nomArticle = request.getParameter("nom_article").trim();
		String description = request.getParameter("description").trim();
		String categorie = request.getParameter("categorie").trim();
		String cheminImage = request.getParameter("filename").trim();
		int prixInitial = Integer.valueOf(request.getParameter("prix_initial").trim());
		String dateDebut = request.getParameter("date_debut_encheres").trim();
		String dateFin = request.getParameter("date_fin_encheres").trim();
		//Récupération de l'adresse de retrait
		String rue = request.getParameter("rue").trim();
		String codePostal = request.getParameter("code_postal").trim();
		String ville = request.getParameter("ville").trim();
		
		
		
		int noUtilsateur = 0;
		
		
		LocalDate date1 = LocalDate.parse(dateDebut);
		LocalDate date2 = LocalDate.parse(dateFin);
	
		Date date_debut = java.sql.Date.valueOf(date1);
		Date date_fin = java.sql.Date.valueOf(date2);
		
		HttpSession session = request.getSession();
		if (session!=null) {
			
			noUtilsateur=(int) session.getAttribute("numeroUtilisateur");
			
		}
		

		int noCategorie;
		
		if (categorie.equalsIgnoreCase("Ameublement")) {
			noCategorie = 1;
		} else if (categorie.equalsIgnoreCase("Informatique")) {
			noCategorie = 2;
		} else if (categorie.equalsIgnoreCase("Sports&Loisirs")) {
			noCategorie = 3;
		} else {
			noCategorie = 4;
		}
		
		
		
		//TODO Vérifier ici si l'utilisateur est tjrs connecté
		
		if (!nomArticle.isEmpty() && !description.isEmpty() && !categorie.isEmpty() && !request.getParameter("prix_initial").isEmpty() && !dateDebut.isEmpty() && !dateFin.isEmpty() &&
				!rue.isEmpty() && !codePostal.isEmpty() && !ville.isEmpty()) 
		{
				if ( (date_debut.compareTo(date_fin)<0) || !(date_debut.compareTo(date_fin)==0)) {
			
			ArticleVendu article = new ArticleVendu(nomArticle, description, cheminImage,date_debut, date_fin, prixInitial);
			ArticlesVendusManager articlesVendusManager = new ArticlesVendusManager();
			try {
				articlesVendusManager.ajouterArticles(article,noUtilsateur,noCategorie);
			} catch (DALException e) {
				request.setAttribute("messageErreur", "Impossible d'ajouter votre article, veuillez nous contacter au 06-06-06-06-06");
				e.printStackTrace();
			}
			
			//Persister la donnée retrait
			Retrait retrait = new Retrait(rue, codePostal, ville, article.getNoArticle());
			RetraitManager retraitManager = new RetraitManager();
			try {
				retraitManager.insertAdresseRetrait(retrait);
			} catch (DALException e1) {
				request.setAttribute("messageErreur2", "Une erreur est survenue, veuillez nous contacter au 06-06-06-06-06");
				e1.printStackTrace();
			}
			
			//System.out.println(article.getNoArticle());
			
			
			
			//Pour retriver tous les articles en ventes sur la page d'accueil
			ArticlesVendusDAO articlesVendusDAO = DAOFactory.getArticlesVendusDAO();
			List<ArticleVendu> articles = null;
			try {
				articles = articlesVendusDAO.showAllArticles();
			} catch (DALException e) {
				request.setAttribute("messageErreur2", "Une erreur est survenue, veuillez nous contacter au 06-06-06-06-06");
				e.printStackTrace();
			}
			request.setAttribute("articles", articles);
			
			
			
			getServletContext().getRequestDispatcher(url).forward(request, response);
				} else {
					 request.setAttribute("incorrectDate", "La date de d�but de l'ench�re ne peut pas commencer avant, ou le m�me jour que la date de fin d'ench�re");
					 request.setAttribute("nom_article", nomArticle);
					 request.setAttribute("description", description);
					 request.setAttribute("categorie", categorie);
					 request.setAttribute("filename",cheminImage);
					 request.setAttribute("prix_initial",prixInitial);
					 request.setAttribute("date_debut_encheres",dateDebut);
					 request.setAttribute("date_fin_encheres",dateFin);
					 request.setAttribute("rue",rue);
					 request.setAttribute("codePostal",codePostal);
					 request.setAttribute("ville",ville);
					 getServletContext().getRequestDispatcher(urlAjoutArticle).forward(request, response);
				}
		}
		
		
		else {
			
			 request.setAttribute("allRiquredFieldsMessage", "Les champs avec le signe * sont obligatoires.");
			 //Conservation des champs déjà renseignés pour éviter de retaper.
			 request.setAttribute("nom_article", nomArticle);
			 request.setAttribute("description", description);
			 request.setAttribute("categorie", categorie);
			 request.setAttribute("filename",cheminImage);
			 request.setAttribute("prix_initial",prixInitial);
			 request.setAttribute("date_debut_encheres",dateDebut);
			 request.setAttribute("date_fin_encheres",dateFin);
			 request.setAttribute("rue",rue);
			 request.setAttribute("codePostal",codePostal);
			 request.setAttribute("ville",ville);
			getServletContext().getRequestDispatcher(urlAjoutArticle).forward(request, response);
		}
		
	
	}

}
