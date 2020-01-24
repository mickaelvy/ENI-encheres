package fr.eni.projet1.dal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet1.bo.ArticleVendu;
import fr.eni.projet1.bo.Utilisateur;
import fr.eni.projet1.dal.ConnectionProvider;
import fr.eni.projet1.dal.DALException;

public class ArticlesVendusDAOJdbcImpl implements ArticlesVendusDAO {
	
	// Ajout de l'article du vendeur
	@Override
	public void insertArticle(ArticleVendu article, int noUtilisateur, int noCategorie)throws DALException {
		
		String requete = "insert into ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) values (?,?,?,?,?,?, ?, ?);";
		
		try (Connection connection = ConnectionProvider.getConnection();)
		{
		try (PreparedStatement jetonDexecutionDeRequete=connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);)
		{		
			jetonDexecutionDeRequete.setString(1, article.getNomArticle());
			jetonDexecutionDeRequete.setString(2, article.getDescription());
			jetonDexecutionDeRequete.setDate(3, article.getDateDebutEncheres()); 
			jetonDexecutionDeRequete.setDate(4, article.getDateFinEncheres());
			jetonDexecutionDeRequete.setInt(5, article.getPrixInitial());
			jetonDexecutionDeRequete.setInt(6, article.getPrixVente());
			jetonDexecutionDeRequete.setInt(7, noUtilisateur);
			jetonDexecutionDeRequete.setInt(8, noCategorie);
			jetonDexecutionDeRequete.executeUpdate();
			
			
			 try (ResultSet generatedKeys = jetonDexecutionDeRequete.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		                article.setNoArticle(generatedKeys.getInt(1));
		                 
		            }
		            else {
		            	
		                throw new SQLException("Le système n'a pas pu attribuer un numéro à l'article.");
		            }
		        }
			
			 
				} catch (SQLException e){
					throw new DALException ("Erreur lors de l'insertion de l'article:" + e.getMessage());
				}
				
		} catch (SQLException e) {
			throw new DALException("Erreur de connexion à la BDD : "+ e.getMessage());	
		}
		
	}
	
	
	//Retrouver un article par son numéro
	@Override
	public ArticleVendu selectArticleById(int idArticle) throws DALException{
		
		String requete = "select * from ARTICLES_VENDUS where no_article = ?;";
		ArticleVendu article = null;
		
			try (Connection connection = ConnectionProvider.getConnection();){
					try(PreparedStatement jetonDexecutionDeRequete = connection.prepareStatement(requete);) {
				
						jetonDexecutionDeRequete.setInt(1, idArticle);
						
						try (ResultSet result=jetonDexecutionDeRequete.executeQuery()){
				
							article = new ArticleVendu();
							
							if (result.next()) {
								article.setNoArticle(result.getInt("no_article"));
								article.setNomArticle(result.getString("nom_article"));
								article.setDescription(result.getString("description"));
								article.setDateDebutEncheres(result.getDate("date_debut_encheres"));
								article.setDateFinEncheres(result.getDate("date_fin_encheres"));
								article.setPrixInitial(result.getInt("prix_initial"));
								article.setPrixVente(result.getInt("prix_vente"));
								article.setNoUtilisateurVendeur(result.getInt("no_utilisateur"));	
								article.setNoCategorie(result.getInt("no_categorie"));	
								}
							
						}catch (SQLException e){
							throw new DALException ("Impossible de retrouver l'article :" + e.getMessage());
						}
				
					} catch (SQLException e) {
						throw new DALException("Erreur lors de l'instruction SQL : " + e.getMessage());
					} 
					
			} catch (SQLException e) {
				throw new DALException("Erreur de connexion à la BDD : "+ e.getMessage());	
			}
			
	return article;
		
	}

	
	// Modification d'un article
	@Override
	public void updateArticle(ArticleVendu article) throws DALException{
		
		String requete = "update ARTICLES_VENDUS set nom_article=?, description=?, date_debut_encheres=?, prix_initial=?  where no_article=?";

		try (Connection connection = ConnectionProvider.getConnection()){
				try (PreparedStatement jetonDexecutionDeRequete=connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS)){
			
						jetonDexecutionDeRequete.setString(1, article.getNomArticle());
						jetonDexecutionDeRequete.setString(2, article.getDescription());
						jetonDexecutionDeRequete.setDate(3, article.getDateDebutEncheres()); 
						jetonDexecutionDeRequete.setInt(4, article.getPrixInitial());
						jetonDexecutionDeRequete.setInt(5, 1);
						jetonDexecutionDeRequete.executeUpdate();
						
			
				} catch (SQLException e){
					throw new DALException ("Impossible de modifier l'article :" + e.getMessage());
				}
				
		} catch (SQLException e) {
			throw new DALException("Erreur de connexion à la BDD : "+ e.getMessage());	
		}	
	}
	
	

	// Suppression d'un article 
	@Override
	public void deleteArticle(int noArticle) throws DALException {
		
		String requette="delete from ARTICLES_VENDUS where no_utilisateur = ? and no_article=?;";
		//int nbreDeLigne = 0;
		
		
			try (Connection connection = ConnectionProvider.getConnection()){
					try (PreparedStatement jetonDexecutionDeRequette = connection.prepareStatement(requette)){
				
				jetonDexecutionDeRequette.setInt(1, 1);
				jetonDexecutionDeRequette.setInt(2, 5);
				jetonDexecutionDeRequette.executeUpdate();
				//nbreDeLigne = jetonDexecutionDeRequette.executeUpdate();
				//return nbreDeLigne;
				
					} catch (SQLException e){
						throw new DALException ("Impossible de supprimer l'article :" + e.getMessage());
					}
					
			} catch (SQLException e) {
				throw new DALException("Erreur de connexion à la BDD : "+ e.getMessage());	
			}			
	}
	
	
	
	
	
	
	//cette méthode existe aussi dans EncheresDAOImpl
	//methode dédiée a showAllArticles
	public int getMeilleureOffreSurUnArtcile(int noArticle) throws DALException{
		
		String requette = "select MAX(montant_enchere) as meilleurOffre from ENCHERES where no_article=?;";
		int meilleureOffre = 0 ;
		
		try (Connection connection = ConnectionProvider.getConnection()) {
				try (PreparedStatement jetonDexecutionDeRequette = connection.prepareStatement(requette)){
			
					jetonDexecutionDeRequette.setInt(1, noArticle);
					
					try (ResultSet rst = jetonDexecutionDeRequette.executeQuery()) {
						
						if (rst.next()) {
							meilleureOffre = rst.getInt("meilleurOffre");
						}
			
					}catch (SQLException e){
						throw new DALException ("Erreur lors de la récupération de la meilleure offre sur un article :" + e.getMessage());
					}
			
				} catch (SQLException e) {
					throw new DALException("Erreur lors de l'instruction SQL : " + e.getMessage());
				} 
				
		} catch (SQLException e) {
			throw new DALException("Erreur de connexion à la BDD : "+ e.getMessage());	
		}
		
		return meilleureOffre;
	}
	
	
	
	//methode dédiée a showAllArticles
	public String getUtilisateurById(int noUtilisateur) throws DALException  {
	
		String requete = "select * from utilisateurs where no_utilisateur = ?;";
		String pseudo = null;
		
			try (Connection connection = ConnectionProvider.getConnection()) {
					try (PreparedStatement jetonDexecutionDeRequete = connection.prepareStatement(requete)){
				
						jetonDexecutionDeRequete.setInt(1, noUtilisateur);
						try (ResultSet result = jetonDexecutionDeRequete.executeQuery()) {
							
							if (result.next()) {
							pseudo = result.getString("pseudo");	
							}
			
				
						} catch (SQLException e) {
							throw new DALException ("Erreur lors de la récupération de l'utilisateur :" + e.getMessage());
						}
						
					} catch (SQLException e) {
						throw new DALException("Erreur lors de l'instruction SQL : " + e.getMessage());	
					} 
					
			} catch (SQLException e) {	
				throw new DALException("Erreur de connexion à la BDD : "+ e.getMessage());
			}
			
		
	
		return pseudo;
	}
	
	
	
	
	


	
	
	//Liste de tous les articles
	@Override
	public List <ArticleVendu>  showAllArticles() throws DALException {
	
		//String requette = "select * from articles_vendus";
		//Cette requette affiche les articles dont la date de fin d'encheres et superiur � la date d'aujourdhui
		String requette = "select * from ARTICLES_VENDUS where date_fin_encheres > CAST(getdate() AS date)";
		ArticleVendu articleVendu;
		List<ArticleVendu> articles = null ;
		
		try (Connection connection = ConnectionProvider.getConnection();){
				try (Statement jetonDexecutionDeRequette=connection.createStatement()){
			
					articles = new ArrayList<ArticleVendu>();
					
					try(ResultSet rst = jetonDexecutionDeRequette.executeQuery(requette)){
			
						while (rst.next()) {
							articleVendu = new ArticleVendu(rst.getInt("no_article"), rst.getString("nom_article"), rst.getString("description"), rst.getDate("date_debut_encheres"), rst.getDate("date_fin_encheres"), rst.getInt("prix_initial"), rst.getInt("prix_vente"), rst.getInt("no_utilisateur"), rst.getInt("no_categorie"));
							articleVendu.setMeilleureOffreActuelle(getMeilleureOffreSurUnArtcile(rst.getInt("no_article")));
							articleVendu.setPseudoVendeur(getUtilisateurById(rst.getInt("no_utilisateur")));
							articles.add(articleVendu);
						}
						
					}catch (SQLException e){
						throw new DALException ("Impossible de sélectionner l'ensemble des articles :" + e.getMessage());
					}
			
				} catch (SQLException e) {
					throw new DALException("Erreur lors de l'instruction SQL : " + e.getMessage());
				} 
				
		} catch (SQLException e) {
			throw new DALException("Erreur de connexion à la BDD : "+ e.getMessage());	
		}
		
		return articles;			
	}


	
	
	@Override
	public List<ArticleVendu> showArticlesByCat(int categorie) throws DALException {
		
				String requette = "select * from articles_vendus where no_categorie =?;";
				
				
				ArticleVendu articleVendu;
				List<ArticleVendu> articles = null ;
				
				try (Connection connection = ConnectionProvider.getConnection()){
						try (PreparedStatement jetonDexecutionDeRequette=connection.prepareStatement(requette)){
					
							articles = new ArrayList<ArticleVendu>();
							jetonDexecutionDeRequette.setInt(1, categorie);
					
							try(ResultSet rst = jetonDexecutionDeRequette.executeQuery()){
					
								while (rst.next()) {
									articleVendu = new ArticleVendu(rst.getInt("no_article"), rst.getString("nom_article"), rst.getString("description"), rst.getDate("date_debut_encheres"), rst.getDate("date_fin_encheres"), rst.getInt("prix_initial"), rst.getInt("prix_vente"), rst.getInt("no_utilisateur"), rst.getInt("no_categorie"));
									articleVendu.setMeilleureOffreActuelle(getMeilleureOffreSurUnArtcile(rst.getInt("no_article")));
									articleVendu.setPseudoVendeur(getUtilisateurById(rst.getInt("no_utilisateur")));
									articles.add(articleVendu);
								}
								
							}catch (SQLException e){
								throw new DALException ("Impossible de sélectionner les articles par catégorie :" + e.getMessage());
							}
					
						} catch (SQLException e) {
							throw new DALException("Erreur lors de l'instruction SQL : " + e.getMessage());
						} 
						
				} catch (SQLException e) {
					throw new DALException("Erreur de connexion à la BDD : "+ e.getMessage());	
				}
				
				return articles;
	}


	@Override
	public List<ArticleVendu> showArticlesMotCle(String motCle) throws DALException {
		
		String requette = "select * from articles_vendus where nom_article = ?;";
		ArticleVendu articleVendu;
		List<ArticleVendu> articles = null ;
		
		try (Connection connection = ConnectionProvider.getConnection()){
				try (PreparedStatement jetonDexecutionDeRequette=connection.prepareStatement(requette);){
			
					articles = new ArrayList<ArticleVendu>();
					jetonDexecutionDeRequette.setString(1, motCle);
					
					try(ResultSet rst = jetonDexecutionDeRequette.executeQuery()){
			
						while (rst.next()) {
							articleVendu = new ArticleVendu(rst.getInt("no_article"), rst.getString("nom_article"), rst.getString("description"), rst.getDate("date_debut_encheres"), rst.getDate("date_fin_encheres"), rst.getInt("prix_initial"), rst.getInt("prix_vente"), rst.getInt("no_utilisateur"), rst.getInt("no_categorie"));
							articles.add(articleVendu);
						}
						
					}catch (SQLException e){
						throw new DALException ("Impossible de sélectionner les articles par leur nom :" + e.getMessage());
					}
			
				} catch (SQLException e) {
					throw new DALException("Erreur lors de l'instruction SQL : " + e.getMessage());
				} 
				
		} catch (SQLException e) {
			throw new DALException("Erreur de connexion à la BDD : "+ e.getMessage());	
		}
		
		return articles;
	}
	
	
	
	
	public List<ArticleVendu> showAchatsByUser(String choix, int noUtilisateur) throws DALException {
	
		 String requette = null ;
		 
		 if (choix.equals("1")) {
			 //Pour mes  ventes en cours
			 requette = "select * from ARTICLES_VENDUS where date_fin_encheres > CAST(getdate() AS date) and date_debut_encheres <= CAST(getdate() AS date)  and no_utilisateur= ?;";
		}
		 
		 else if (choix.equals("2")) {
			 //Pour mes Ventes non d�but�es
			 requette = "select * from ARTICLES_VENDUS where date_debut_encheres > CAST(getdate() AS date) and no_utilisateur= ?;";
			}
		 
		 else if (choix.equals("3")) {
			 //Pour mes Ventes termin�s
			 requette = "select * from ARTICLES_VENDUS where date_fin_encheres <= CAST(getdate() AS date) and no_utilisateur= ?;";
			}
		 
		 else {
			
		}

		ArticleVendu articleVendu;
		List<ArticleVendu> articles = null ;
		
		try (Connection connection = ConnectionProvider.getConnection()){
				try (PreparedStatement jetonDexecutionDeRequette=connection.prepareStatement(requette)){
			
					articles = new ArrayList<ArticleVendu>();
					//jetonDexecutionDeRequette.setString(1, "CAST(getdate() AS date)");
					jetonDexecutionDeRequette.setInt(1, noUtilisateur);
			
					try(ResultSet rst = jetonDexecutionDeRequette.executeQuery()){
			
						while (rst.next()) {
							articleVendu = new ArticleVendu(rst.getInt("no_article"), rst.getString("nom_article"), rst.getString("description"), rst.getDate("date_debut_encheres"), rst.getDate("date_fin_encheres"), rst.getInt("prix_initial"), rst.getInt("prix_vente"), rst.getInt("no_utilisateur"), rst.getInt("no_categorie"));
							articleVendu.setMeilleureOffreActuelle(getMeilleureOffreSurUnArtcile(rst.getInt("no_article")));
							articleVendu.setPseudoVendeur(getUtilisateurById(rst.getInt("no_utilisateur")));
							articles.add(articleVendu);
						}
						
					}catch (SQLException e){
						throw new DALException ("Impossible de sélectionner les articles par catégorie :" + e.getMessage());
					}
			
				} catch (SQLException e) {
					throw new DALException("Erreur lors de l'instruction SQL : " + e.getMessage());
				} 
				
		} catch (SQLException e) {
			throw new DALException("Erreur de connexion à la BDD : "+ e.getMessage());	
		}
		
		return articles;
}
	
	
}
