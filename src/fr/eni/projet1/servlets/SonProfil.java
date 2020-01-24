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



@WebServlet("/SonProfil")
public class SonProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	
    public SonProfil() {
        super();
        // TODO Auto-generated constructor stub
    }



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
	}


	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "/SonProfil.jsp";
		String urlLogin = "/Login.jsp";
		HttpSession session = request.getSession();
		String status = (String) session.getAttribute("status");
		int noUtilisateur = Integer.valueOf(request.getParameter("noUtilisateur"));


		//On vÃ©rifie si l'utilisateur est toujours connectÃ©
		
		if (status!=null) 
		{
				if (status.equals("connected")) {
					UtilisateurManager utilisateurManager = new UtilisateurManager();
					Utilisateur utilisateur = null;
					try {
						utilisateur = utilisateurManager.getUtilisateurById(noUtilisateur);
					} catch (DALException e) {
						request.setAttribute("messageErreur", "Impossible d'afficher votre profil, veuillez nous contacter au 06-06-06-06-06");
						e.printStackTrace();
					}
	
					request.setAttribute("pseudo", utilisateur.getPseudo());
					request.setAttribute("nom", utilisateur.getNom());
					request.setAttribute("prenom", utilisateur.getPrenom());
					request.setAttribute("email", utilisateur.getEmail());
					request.setAttribute("telephone", utilisateur.getTelephone());
					request.setAttribute("ville", utilisateur.getVille());
					//request.setAttribute("codePostal", utilisateur.getCodePostal());
					//request.setAttribute("rue", utilisateur.getRue());
				
	
					getServletContext().getRequestDispatcher(url).forward(request, response);
	
				}
	
				else {
					
					request.setAttribute("messagePourSeReconnecter", "Pour visualiser un profil, vous devez être connecté.");
					getServletContext().getRequestDispatcher(urlLogin).forward(request, response);
					
				}
			
		}
		
		else {
			
			request.setAttribute("messagePourSeReconnecter", "Pour voir un profil, vous devez être connecté.");
			getServletContext().getRequestDispatcher(urlLogin).forward(request, response);
			
		}
		
		
		
	}

}
