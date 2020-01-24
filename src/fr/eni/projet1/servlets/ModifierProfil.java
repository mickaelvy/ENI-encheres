package fr.eni.projet1.servlets;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet1.bll.UtilisateurManager;
import fr.eni.projet1.bo.Utilisateur;
import fr.eni.projet1.dal.DALException;





@WebServlet("/ModifierProfil")
public class ModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ModifierProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
	
		
		String url = "/ModifierProfil.jsp";
		String urlLogin = "/Login.jsp";
		HttpSession session = request.getSession();

		if (session != null) {

			int no = (int) session.getAttribute("numeroUtilisateur");
			String pseudo = (String) session.getAttribute("pseudo");
			String nom = (String) session.getAttribute("nom");
			String prenom = (String) session.getAttribute("prenom");
			String email = (String) session.getAttribute("email");
			String telephone = (String) session.getAttribute("telephone");
			String codePostale =  (String) session.getAttribute("codePostal");
			String rue = (String) session.getAttribute("rue");
			String ville = (String) session.getAttribute("ville");

			request.setAttribute("pseudo", pseudo);
			request.setAttribute("nom", nom);
			request.setAttribute("prenom", prenom);
			request.setAttribute("email", email);
			request.setAttribute("telephone", telephone);
			request.setAttribute("codePostal", codePostale);
			request.setAttribute("rue", rue);
			request.setAttribute("ville", ville);

			getServletContext().getRequestDispatcher(url).forward(request, response);

		}

		else {
			getServletContext().getRequestDispatcher(urlLogin).forward(request, response);
		}
				
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		HttpSession session = request.getSession();
		int no = (int) session.getAttribute("numeroUtilisateur");
		String mdpActuel = (String) session.getAttribute("password");
		System.out.println(mdpActuel);
		
		String url = "/MonProfil.jsp";
		String urlModif = "/ModifierProfil.jsp";
		
		//On récupére tous les paramétres et on enlève les espaces avant et après.
		String pseudo = request.getParameter("pseudo").trim();
		String nom = request.getParameter("nom").trim();
		String prenom = request.getParameter("prenom").trim();
		String email = request.getParameter("email").trim();
		String telephone = request.getParameter("telephone").trim();
		String rue = request.getParameter("rue").trim();
		String codePostal = request.getParameter("code_postal").trim();
		String ville = request.getParameter("ville").trim();
		String password = request.getParameter("mot_de_passe").trim();
		String newPassword = request.getParameter("new_mot_de_passe").trim();
		String confPassword = request.getParameter("conf_mot_de_passe").trim();
		

		request.setAttribute("pseudo", pseudo);
		request.setAttribute("nom", nom);
		request.setAttribute("prenom", prenom);
		request.setAttribute("email", email);
		request.setAttribute("telephone", telephone);
		request.setAttribute("rue", rue);
		request.setAttribute("codePostal", codePostal);
		request.setAttribute("ville", ville);
		request.setAttribute("password", password);

		
		//Utilisateur user = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal,
				//ville, password);
		
		String regex = "^[a-zA-Z0-9]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(pseudo);
		
		if ((!pseudo.isEmpty() && !nom.isEmpty() && !prenom.isEmpty() && !email.isEmpty() && !telephone.isEmpty() && !rue.isEmpty() &&
				!codePostal.isEmpty() && !ville.isEmpty() && !password.isEmpty() && !newPassword.isEmpty() && !confPassword.isEmpty())) {
			
			if (matcher.matches()==true) {
			
				if (mdpActuel.equals(password)) {
				
					if (newPassword.equals(confPassword)) {
							
						Utilisateur user = new Utilisateur(no, pseudo, nom, prenom, email, telephone, rue, codePostal,ville, newPassword);
						UtilisateurManager utilisateurManager = new UtilisateurManager();
						try {
							utilisateurManager.modifUser(user);
						} catch (DALException e) {
							e.printStackTrace();
							request.setAttribute("messageErreur", "Impossible de modifier votre profil, veuillez nous contacter au 06-06-06-06-06");
						}
						getServletContext().getRequestDispatcher(url).forward(request, response);
						
							} else {
								request.setAttribute("notMatchingPassword", "Les nouveaux mots de passe ne sont pas identiques.");
								//Retenir les saisies déjà effectuées pour revenir à la situation de départ, si le nouveau mdp ne correpond pas à la confirmation du mdp
								request.setAttribute("pseudo",pseudo);
								request.setAttribute("nom", nom);
								request.setAttribute("prenom",prenom);
								request.setAttribute("email",email);
								request.setAttribute("telephone",telephone);
								request.setAttribute("rue",rue);
								request.setAttribute("code_postal",codePostal);
							    request.setAttribute("ville",ville);
							
							    getServletContext().getRequestDispatcher(urlModif).forward(request, response);
							}
					}else {
						request.setAttribute("incorrectPassword", "Mot de passe incorrect.");
						//Retenir les saisies déjà effectuées pour revenir à la situation de départ, si le nouveau mdp ne correpond pas à la confirmation du mdp
						request.setAttribute("pseudo",pseudo);
						request.setAttribute("nom", nom);
						request.setAttribute("prenom",prenom);
						request.setAttribute("email",email);
						request.setAttribute("telephone",telephone);
						request.setAttribute("rue",rue);
						request.setAttribute("code_postal",codePostal);
					    request.setAttribute("ville",ville);
					
					    getServletContext().getRequestDispatcher(urlModif).forward(request, response);
					}
			} else {
				request.setAttribute("nonRespectAlphNumMessage","Le pseudo ne doit pas comporter des caract�res autre que de type alpha num�rique.");
				//Retenir les saisies déjà effectuées pour revenir à la situation de départ, si le nouveau mdp ne correpond pas à la confirmation du mdp
				request.setAttribute("pseudo",pseudo);
				request.setAttribute("nom", nom);
				request.setAttribute("prenom",prenom);
				request.setAttribute("email",email);
				request.setAttribute("telephone",telephone);
				request.setAttribute("rue",rue);
				request.setAttribute("code_postal",codePostal);
			    request.setAttribute("ville",ville);
			
			    getServletContext().getRequestDispatcher(urlModif).forward(request, response);
			}
					
		} else if ((!pseudo.isEmpty() && !nom.isEmpty() && !prenom.isEmpty() && !email.isEmpty() && !telephone.isEmpty() && !rue.isEmpty() &&
					!codePostal.isEmpty() && !ville.isEmpty() && !password.isEmpty())) {
		
			if (matcher.matches()==true) {
				
				if (mdpActuel.equals(password)) {
				
					Utilisateur user = new Utilisateur(no, pseudo, nom, prenom, email, telephone, rue, codePostal,ville,password);
					UtilisateurManager utilisateurManager = new UtilisateurManager();
					try {
						utilisateurManager.modifUser(user);
					} catch (DALException e) {
						e.printStackTrace();
						request.setAttribute("messageErreur", "Impossible de modifier votre profil, veuillez nous contacter au 06-06-06-06-06");
					}
					getServletContext().getRequestDispatcher(url).forward(request, response);
					
				}else {
					request.setAttribute("incorrectPassword", "Mot de passe incorrect.");
					//Retenir les saisies déjà effectuées pour revenir à la situation de départ, si le nouveau mdp ne correpond pas à la confirmation du mdp
					request.setAttribute("pseudo",pseudo);
					request.setAttribute("nom", nom);
					request.setAttribute("prenom",prenom);
					request.setAttribute("email",email);
					request.setAttribute("telephone",telephone);
					request.setAttribute("rue",rue);
					request.setAttribute("code_postal",codePostal);
				    request.setAttribute("ville",ville);
				
				    getServletContext().getRequestDispatcher(urlModif).forward(request, response);
				}
			
			} else {
				request.setAttribute("nonRespectAlphNumMessage","Le pseudo ne doit pas comporter des caract�res autre que de type alpha num�rique.");
				//Retenir les saisies déjà effectuées pour revenir à la situation de départ, si le nouveau mdp ne correpond pas à la confirmation du mdp
				request.setAttribute("pseudo",pseudo);
				request.setAttribute("nom", nom);
				request.setAttribute("prenom",prenom);
				request.setAttribute("email",email);
				request.setAttribute("telephone",telephone);
				request.setAttribute("rue",rue);
				request.setAttribute("code_postal",codePostal);
			    request.setAttribute("ville",ville);
			
			    getServletContext().getRequestDispatcher(urlModif).forward(request, response);
			}
			
			} else {
	
				request.setAttribute("allFieldsRequiredMessage", "Tous les champs sont obligatoires, dont le mot de passe pour confirmer la modification de votre profil.");
				//Retenir les saisies déjà effectuées pour revenir à la situation de départ, si le mot de passe n'a pas été confirmé
				request.setAttribute("pseudo",pseudo);
				request.setAttribute("nom", nom);
				request.setAttribute("prenom",prenom);
				request.setAttribute("email",email);
				request.setAttribute("telephone",telephone);
				request.setAttribute("rue",rue);
				request.setAttribute("code_postal",codePostal);
			    request.setAttribute("ville",ville);
		
		
		getServletContext().getRequestDispatcher(urlModif).forward(request, response);
		}
		
		
		
	
	
	}

}
