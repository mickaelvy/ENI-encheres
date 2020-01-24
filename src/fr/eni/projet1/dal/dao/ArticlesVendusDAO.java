package fr.eni.projet1.dal.dao;

import java.util.List;

import fr.eni.projet1.bo.ArticleVendu;
import fr.eni.projet1.dal.DALException;

public interface ArticlesVendusDAO {

	public void insertArticle(ArticleVendu article,int noUtilisateur, int noCategorie) throws DALException;
	public ArticleVendu selectArticleById (int noArticle)throws DALException;
	public void updateArticle (ArticleVendu article)throws DALException;
	public void deleteArticle (int noArticle)throws DALException;
	public  List <ArticleVendu>  showAllArticles () throws DALException;
	public  List <ArticleVendu>  showArticlesByCat(int categorie)throws DALException;
	public  List <ArticleVendu>  showArticlesMotCle(String motCle)throws DALException;
	public List<ArticleVendu> showAchatsByUser(String choix, int noUtilisateur) throws DALException;

	
//	public List <Article> readAll ();
//	public List <Article> readFromUser (int idUser);
	
	
	
	
}
