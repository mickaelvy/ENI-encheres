package fr.eni.projet1.bll;

import java.util.List;

import fr.eni.projet1.bo.ArticleVendu;
import fr.eni.projet1.dal.DALException;
import fr.eni.projet1.dal.dao.ArticlesVendusDAO;
import fr.eni.projet1.dal.dao.DAOFactory;


public class ArticlesVendusManager {
private ArticlesVendusDAO articlesDAO;
	
	public ArticlesVendusManager() {
		this.articlesDAO=DAOFactory.getArticlesVendusDAO();
	}
	
	
	
	public void ajouterArticles(ArticleVendu article,int noUtilisateur, int noCategorie) throws DALException{
		this.articlesDAO.insertArticle(article,noUtilisateur,noCategorie);
	}
	
	
	
	public void modifArticles (ArticleVendu article)throws DALException {
		this.articlesDAO.updateArticle(article);
	}
	
	
	
	public void supprimerArticles (int noArticle)throws DALException {
		this.articlesDAO.deleteArticle(noArticle);
	}
	
	
	public ArticleVendu selectArticleById (int noArticle)throws DALException {
		return this.articlesDAO.selectArticleById(noArticle);
	}
	
	
	public  List <ArticleVendu>  showAllArticles () throws DALException {
		return this.articlesDAO.showAllArticles();
	}
	
	
	public  List <ArticleVendu>  showArticlesByCat(int categorie) throws DALException {
		return this.articlesDAO.showArticlesByCat(categorie);
	}
	
	
	public  List <ArticleVendu>  showArticlesMotCle(String motCle) throws DALException {
		return this.articlesDAO.showArticlesMotCle(motCle);
	}
	
		
}
