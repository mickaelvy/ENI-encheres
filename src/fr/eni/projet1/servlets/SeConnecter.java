package fr.eni.projet1.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fr.eni.projet1.bll.UtilisateurManager;
import fr.eni.projet1.bo.ArticleVendu;
import fr.eni.projet1.bo.Utilisateur;
import fr.eni.projet1.dal.DALException;
import fr.eni.projet1.dal.dao.ArticlesVendusDAO;
import fr.eni.projet1.dal.dao.DAOFactory;

@WebServlet("/SeConnecter")
public class SeConnecter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SeConnecter() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Dans cette méthode doGet, passent ttes les requettes voulant acceder à la page Login.jsp
		//String url = "/Login.jsp";
		String url_c_home_page = "/HomePageConnecte.jsp";
		String status;
		
		HttpSession session  = request.getSession();
		status = (String) session.getAttribute("status");
		System.out.println(status);
		
		
		if (status!=null && session != null) {
					switch (status) {
					case "notConnected":
						//Récupération des cookies et remplissage du champ pseudo
						Cookie[] cookies = request.getCookies();
						if (cookies != null) {
							for (Cookie cookie : cookies) {
								if (cookie.getName().equals("pseudo")) {
									//Préremplir le champ pseudo de la jsp "Login.jsp"
									request.setAttribute("pseudo", cookie.getValue());
									getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
								}
							}
						}	
						break;
					case "connected":
						// Si l'utilisateur est déja connecté, on le renvoie direct à la page d'accueil
						// en mode connecté
						getServletContext().getRequestDispatcher(url_c_home_page).forward(request, response);
						break;
			
					default:
						getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
						break;
					}
					
					}
		
		else {
			getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
		}
	}
	
	
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String urlHomePageConnecte = "/HomePageConnecte.jsp";
		String urlLogin = "/Login.jsp";
		String pseudo = request.getParameter("pseudo").trim();
		String password = request.getParameter("mot_de_passe").trim();
		String souvenir = request.getParameter("souvenir");

		// Récupération de la valeur du checbox au cas où il est coché.
		if (souvenir != null) {
			Cookie cookie = new Cookie("pseudo", pseudo);
			cookie.setMaxAge(2147483647);// pour l'âge maxi on a mis la valeur maxi d'un int
			response.addCookie(cookie);
		}

		
		Utilisateur utilisateur = null;
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		try {
			utilisateur = utilisateurManager.connectUser(pseudo, password);
		} catch (DALException e) {
			request.setAttribute("messageErreur", "Une erreur est survenue, veuillez nous contacter au 06-06-06-06-06");
			e.printStackTrace();
		}

		if (utilisateur == null) {
			request.setAttribute("erreurIdentifiant", "Erreur sur l'identifiant et/ou le mot de passe. R�essayez.");
			request.getRequestDispatcher(urlLogin).forward(request, response);
			// Dans cet état on mettra status à "notConnected"
			HttpSession session = request.getSession(true);
			session.setAttribute("status", "notConnected");
		}

		else {

			HttpSession session = request.getSession();
			// On le rhabille de tous ses attributs tout de suite pour en cas de besoin
			// par une autre servlet qui les obtiendra par la methode getAttribut()

			session.setAttribute("numeroUtilisateur", utilisateur.getNoUtilisateur());
			session.setAttribute("pseudo", utilisateur.getPseudo());
			session.setAttribute("nom", utilisateur.getNom());
			session.setAttribute("prenom", utilisateur.getPrenom());
			session.setAttribute("email", utilisateur.getEmail());
			session.setAttribute("telephone", utilisateur.getTelephone());
			session.setAttribute("codePostal", utilisateur.getCodePostal());
			session.setAttribute("rue", utilisateur.getRue());
			session.setAttribute("ville", utilisateur.getVille());
			session.setAttribute("password", utilisateur.getPassword());
			session.setAttribute("credit", utilisateur.getCredit());
			session.setAttribute("status", "connected");
			
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
			
			getServletContext().getRequestDispatcher(urlHomePageConnecte).forward(request, response);

		}

	}

}
