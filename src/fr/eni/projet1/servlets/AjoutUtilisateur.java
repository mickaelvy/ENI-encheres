package fr.eni.projet1.servlets;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

@WebServlet("/AjoutUtilisateur")
public class AjoutUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjoutUtilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
	}

	/**
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String urlHomePageConnecte = "/HomePageConnecte.jsp";
		String urlRegister = "/Sinscrire.jsp";
		
		//on rÃ©cupÃ©re tous les paramÃ©tres et on enlÃ¨ve les espaces avt et aprÃ¨s.
		String pseudo = request.getParameter("pseudo").trim();
		String nom = request.getParameter("nom").trim();
		String prenom = request.getParameter("prenom").trim();
		String email = request.getParameter("email").trim();
		String telephone = request.getParameter("telephone").trim();
		String rue = request.getParameter("rue").trim();
		String codePostal = request.getParameter("code_postal").trim();
		String ville = request.getParameter("ville").trim();
		String password = request.getParameter("mot_de_passe").trim();
		String confPassword = request.getParameter("conf_mot_de_passe").trim();
		int credit =100;
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		boolean isUniquePseudo = false;
		boolean isUniqueMail = false;
		
		
		String regex = "^[a-zA-Z0-9]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(pseudo);
		System.out.println(matcher.matches());
		
		
		
		try {
			isUniquePseudo = utilisateurManager.isUniquePseudo(pseudo);
		} catch (DALException e1) {
			e1.printStackTrace();
			request.setAttribute("messageErreur", "Impossible de créer votre compte, veuillez nous contacter au 06-06-06-06-06");
		}
		
		try {
			isUniqueMail = utilisateurManager.isUniqueMail(email);
		} catch (DALException e1) {
			e1.printStackTrace();
			request.setAttribute("messageErreur", "Impossible de créer votre compte, veuillez nous contacter au 06-06-06-06-06");
		}
		
		
		
		if (!pseudo.isEmpty() && !nom.isEmpty() && !prenom.isEmpty() && !email.isEmpty() && !telephone.isEmpty() && !rue.isEmpty() &&
				!codePostal.isEmpty() && !ville.isEmpty() && !password.isEmpty() && !confPassword.isEmpty()) 
		{
			
		if (matcher.matches()==true) 
		{
			

			if (confPassword.equals(password)) 
			{
					if (isUniquePseudo==true && isUniqueMail==true) 
					{
							Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal,ville, password, credit);
							try {
								utilisateurManager.insertUser(utilisateur);
								//On connecte l'utilisateur une fois inscrit 
								utilisateurManager.connectUser(pseudo, confPassword);
								HttpSession session = request.getSession();
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
							} catch (DALException e) {
								e.printStackTrace();
								request.setAttribute("messageErreur", "Impossible de créer votre compte, veuillez nous contacter au 06-06-06-06-06");
							}
			
							//On redirige l'utilisateur vers la page de connexion aprÃ¨s inscription.
							//request.setAttribute("succesRegister", "Inscription rÃ©ussie. Connectez-vous maintenant.");
							ArticlesVendusDAO articlesVendusDAO = DAOFactory.getArticlesVendusDAO();
							List<ArticleVendu> articles = null;
							try {
								articles = articlesVendusDAO.showAllArticles();
							} catch (DALException e) {
								request.setAttribute("messageErreur2", "Une erreur est survenue, veuillez nous contacter au 06-06-06-06-06");
								e.printStackTrace();
							}
							request.setAttribute("articles", articles);
							getServletContext().getRequestDispatcher(urlHomePageConnecte).forward(request, response);
					}
					
					else {
							if (isUniquePseudo==false) {
								request.setAttribute("messageConflitPseudo", "Le pseudo choisi éxiste déjà , choisissez un autre.");
							}
							
							
							else if (isUniqueMail==false) {
								request.setAttribute("messageConflitMail", "L'adresse mail fournie éxiste déjà  dans notre base.");
							}
							
							//Retenir les saisies dï¿½jï¿½ effectuï¿½es pour ï¿½viter de les retaper
							request.setAttribute("pseudo",pseudo);
							request.setAttribute("nom", nom);
							request.setAttribute("prenom",prenom);
							request.setAttribute("email",email);
							request.setAttribute("telephone",telephone);
							request.setAttribute("rue",rue);
							request.setAttribute("code_postal",codePostal);
						    request.setAttribute("ville",ville);
							getServletContext().getRequestDispatcher(urlRegister).forward(request, response);
						}
				
			}
			else {
				request.setAttribute("notMatchingPassword", "Les mots de passe ne sont pas identiques.");
				//Retenir les saisies dï¿½jï¿½ effectuï¿½es pour ï¿½viter de les retaper	
				request.setAttribute("pseudo",pseudo);
				request.setAttribute("nom", nom);
				request.setAttribute("prenom",prenom);
				request.setAttribute("email",email);
				request.setAttribute("telephone",telephone);
				request.setAttribute("rue",rue);
				request.setAttribute("code_postal",codePostal);
			    request.setAttribute("ville",ville);
				getServletContext().getRequestDispatcher(urlRegister).forward(request, response);
			}
			
			
			
			
		}
		
		else {
			
		    request.setAttribute("nonRespectAlphNumMessage","Le pseudo ne doit pas comporter des caractères autre que de type alpha numérique.");
		    request.setAttribute("pseudo",pseudo);
			request.setAttribute("nom", nom);
			request.setAttribute("prenom",prenom);
			request.setAttribute("email",email);
			request.setAttribute("telephone",telephone);
			request.setAttribute("rue",rue);
			request.setAttribute("code_postal",codePostal);
		    request.setAttribute("ville",ville);
			getServletContext().getRequestDispatcher(urlRegister).forward(request, response);
			
		}

			
		}
		else 
		{
			request.setAttribute("allFieldsRequiredMessage", "Tous les champs sont obligatoires.");
			//Retenir les saisies dÃ©jÃ  effectuÃ©es pour Ã©viter de les retaper
			request.setAttribute("pseudo",pseudo);
			request.setAttribute("nom", nom);
			request.setAttribute("prenom",prenom);
			request.setAttribute("email",email);
			request.setAttribute("telephone",telephone);
			request.setAttribute("rue",rue);
			request.setAttribute("code_postal",codePostal);
		    request.setAttribute("ville",ville);
			
			getServletContext().getRequestDispatcher(urlRegister).forward(request, response);
		}
		
			
	}

}
