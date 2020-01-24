package fr.eni.projet1.bll;

import fr.eni.projet1.bo.Retrait;
import fr.eni.projet1.dal.DALException;
import fr.eni.projet1.dal.dao.DAOFactory;
import fr.eni.projet1.dal.dao.RetraitDAO;

public class RetraitManager {
	
private RetraitDAO retraitDAO;


	public RetraitManager() {
		this.retraitDAO=DAOFactory.getRetraitDAO();
	}
		
	
	
	public void insertAdresseRetrait(Retrait retrait) throws DALException {
		this.retraitDAO.insertAdresseRetrait(retrait);
	}
	
	public Retrait getRetraitById(int idArticle) throws DALException {
		return this.retraitDAO.getRetraitById(idArticle);
	}

}
