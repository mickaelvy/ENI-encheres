package fr.eni.projet1.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet1.bll.UtilisateurManager;
import fr.eni.projet1.bo.Utilisateur;
import fr.eni.projet1.dal.DALException;

/**
 * Servlet implementation class MonProfil
 */
@WebServlet("/MonProfil")
public class MonProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public MonProfil() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Ici passent tte les requette voulant acceder à la page user_profil.jsp

		String url = "/MonProfil.jsp";
		String urlLogin = "/Login.jsp";
		HttpSession session = request.getSession();
		String status = (String) session.getAttribute("status");

		//On vérifie si l'utilisateur est toujours connecté
		
		if (status!=null) 
		{
			if (status.equals("connected")) {
	
					int no = (int) session.getAttribute("numeroUtilisateur");
					
					UtilisateurManager utilisateurManager = new UtilisateurManager();
					Utilisateur utilisateur = null;
					try {
						utilisateur = utilisateurManager.getUtilisateurById(no);
					} catch (DALException e) {
						request.setAttribute("messageErreur", "Une erreur est survenue, veuillez nous appeler au 06-06-06-06-06");
						e.printStackTrace();
					}
					
				
//					String pseudo = (String) session.getAttribute("pseudo");
//					String nom = (String) session.getAttribute("nom");
//					String prenom = (String) session.getAttribute("prenom");
//					String email = (String) session.getAttribute("email");
//					String telephone = (String) session.getAttribute("telephone");
//					String codePostale =  (String) session.getAttribute("codePostal");
//					String rue = (String) session.getAttribute("rue");
//					String ville = (String) session.getAttribute("ville");
//					int credit = (int) session.getAttribute("credit");
	
					
					request.setAttribute("pseudo", utilisateur.getPseudo());
					request.setAttribute("nom", utilisateur.getNom());
					request.setAttribute("prenom", utilisateur.getPrenom());
					request.setAttribute("email", utilisateur.getEmail());
					request.setAttribute("telephone", utilisateur.getTelephone());
					request.setAttribute("codePostal", utilisateur.getCodePostal());
					request.setAttribute("rue", utilisateur.getRue());
					request.setAttribute("ville", utilisateur.getVille());
					request.setAttribute("credit", utilisateur.getCredit());
	
					getServletContext().getRequestDispatcher(url).forward(request, response);
	
				}
	
				else {
					
					request.setAttribute("messagePourSeReconnecter", "Vous �tes d�connect�. Reconnectez-vous pour acc�der � votre profil.");
					getServletContext().getRequestDispatcher(urlLogin).forward(request, response);
				}
			
		}
		
		 else {
			 request.setAttribute("messagePourSeReconnecter", "Vous �tes d�connect�. Reconnectez-vous pour acc�der � votre profil.");
				getServletContext().getRequestDispatcher(urlLogin).forward(request, response);		
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
