package fr.eni.projet1.bll;

import java.util.List;

import fr.eni.projet1.bo.Enchere;
import fr.eni.projet1.dal.DALException;
import fr.eni.projet1.dal.dao.DAOFactory;
import fr.eni.projet1.dal.dao.EncheresDAO;

public class EncheresManager {
	
	private EncheresDAO encheresDAO;

	public EncheresManager () {
		this.encheresDAO = DAOFactory.getEncheresDAO();
	}
	
	public int getMeilleureOffreSurUnArtcile(int noArticle) throws DALException {
		return this.encheresDAO.getMeilleureOffreSurUnArtcile(noArticle);
	}
	
	public List<Enchere> listEncheresSurUnArticle(int noArticle) throws DALException {
		return this.encheresDAO.listEncheresSurUnArticle(noArticle);
	}
	
	public void  insertEnchere(Enchere enchere) throws DALException {
		this.encheresDAO.insertEnchere(enchere);
	}
	
	public Enchere enchereMeilleurOffre(int meilleurOffre, int noArticle) throws DALException {
		return this.encheresDAO.enchereMeilleurOffre(meilleurOffre, noArticle);
	}
}
