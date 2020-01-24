package fr.eni.projet1.dal.dao;

import java.util.List;

import fr.eni.projet1.bo.Enchere;
import fr.eni.projet1.dal.DALException;

public interface EncheresDAO {
	
	public int getMeilleureOffreSurUnArtcile(int noArticle) throws DALException;

	List<Enchere> listEncheresSurUnArticle(int noArticle) throws DALException;
	
	public void  insertEnchere(Enchere enchere) throws DALException;
	
	public Enchere enchereMeilleurOffre(int meilleurOffre, int noArticle) throws DALException;
	


}
