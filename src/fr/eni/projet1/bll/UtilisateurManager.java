package fr.eni.projet1.bll;

import fr.eni.projet1.bo.Enchere;
import fr.eni.projet1.bo.Utilisateur;
import fr.eni.projet1.dal.DALException;
import fr.eni.projet1.dal.dao.DAOFactory;
import fr.eni.projet1.dal.dao.UtilisateurDAO;

public class UtilisateurManager {
private UtilisateurDAO utilisateurDAO;
	
	public UtilisateurManager() {
		this.utilisateurDAO=DAOFactory.getUtilisateurDAO();
	}
	
	
	public void insertUser(Utilisateur utilisateur) throws DALException {
		this.utilisateurDAO.registerUser(utilisateur);
	}
	
	
	public void modifUser (Utilisateur utilisateur)throws DALException {
		this.utilisateurDAO.updateUser(utilisateur);
	}
	
	
	
	public boolean isUniquePseudo (String pseudo)throws DALException {
		return this.utilisateurDAO.isUniquePseudo(pseudo);	
	}
	
	
	public boolean isUniqueMail (String mail)throws DALException {
		return this.utilisateurDAO.isUniqueMail(mail);	
	}
	
	
	public void supprimerUtilisateur(String mail, String password)throws DALException  {
		this.utilisateurDAO.deleteUser(mail, password);
	}
	
	
	
	public void supprimerMesArticlesEnLigne(int noUtilisateur) throws DALException {
		this.utilisateurDAO.supprimMesArticlesEnLigne(noUtilisateur);
	}
	
	
	
	public Utilisateur connectUser(String pseudo, String password)throws DALException {
		return this.utilisateurDAO.connectUser(pseudo, password);
	}
	
	
	
	public Utilisateur getUtilisateurById(int noUtilisateur)throws DALException {
		return this.utilisateurDAO.getUtilisateurById(noUtilisateur);
	}
	
	
	
	
	public void  recrediterUtilisateur(Enchere lastEnchere) throws DALException {
		this.utilisateurDAO.recrediterUtilisateur(lastEnchere);
	}
	
	
	
	
	public void  debiterUtilisateur(int montantEnchere, int noUtilisateur) throws DALException {
		this.utilisateurDAO.debiterUtilisateur(montantEnchere, noUtilisateur);
	}
	
}
