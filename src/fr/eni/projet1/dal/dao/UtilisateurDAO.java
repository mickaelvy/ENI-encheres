package fr.eni.projet1.dal.dao;

import java.util.List;

import fr.eni.projet1.bo.Enchere;
import fr.eni.projet1.bo.Utilisateur;
import fr.eni.projet1.dal.DALException;

public interface UtilisateurDAO {
	
	public void registerUser(Utilisateur utilisateur)throws DALException ;
	public void logoutUser(int userId)throws DALException ;
	public Utilisateur getUtilisateurById(int noUtilisateur)throws DALException;
	public void updateUser(Utilisateur utilisateur)throws DALException ;
	public int deleteUser(String mail, String password)throws DALException ;
	public boolean isUniquePseudo(String pseudo)throws DALException ;
	public boolean isUniqueMail(String mail)throws DALException ;
	public Utilisateur connectUser(String pseudo, String password) throws DALException;
	public void supprimMesArticlesEnLigne(int noUtilisateur)throws DALException;
	public List<Utilisateur> findAllUsers() throws DALException;
	public void  recrediterUtilisateur(Enchere lastEnchere) throws DALException ;
	public void  debiterUtilisateur(int montantEnchere, int noUtilisateur) throws DALException;
		
}
