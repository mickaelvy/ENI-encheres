package fr.eni.projet1.dal.dao;

import fr.eni.projet1.bo.Retrait;
import fr.eni.projet1.dal.DALException;

public interface RetraitDAO {
	
	public void insertAdresseRetrait(Retrait retrait) throws DALException;
	public Retrait getRetraitById(int idArticle) throws DALException;
		
	

}
