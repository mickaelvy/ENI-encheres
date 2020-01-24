package fr.eni.projet1.dal.dao;



public class DAOFactory {
	

	public static ArticlesVendusDAO getArticlesVendusDAO () {
		return(ArticlesVendusDAO) new ArticlesVendusDAOJdbcImpl();
	}
	
	
	
	public static  UtilisateurDAO getUtilisateurDAO() {

		return (UtilisateurDAO) new UtilisateurDAOJdbcImpl();
		
	}
	

	public static  RetraitDAO getRetraitDAO() {

		return  (RetraitDAO) new RetraitDAOJdbcImpl();
	}
	
	

	public static  EncheresDAO getEncheresDAO() {

		return  (EncheresDAO) new EncheresDAOJdbcImpl();
	}
	
	

	
	
	
	
	
	
}
