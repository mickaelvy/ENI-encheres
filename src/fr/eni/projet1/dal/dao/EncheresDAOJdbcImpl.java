package fr.eni.projet1.dal.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet1.bo.ArticleVendu;
import fr.eni.projet1.bo.Enchere;
import fr.eni.projet1.bo.Utilisateur;
import fr.eni.projet1.dal.ConnectionProvider;
import fr.eni.projet1.dal.DALException;

public class EncheresDAOJdbcImpl implements EncheresDAO{

	
	
	@Override
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
	
	
	//Liste des encheres sur un article donné
		@Override
		public List <Enchere>  listEncheresSurUnArticle(int noArticle) throws DALException {
			
			String requette = "select * from ENCHERES where no_article = ?;";
			Enchere enchere;
			List<Enchere> encheres = null ;
			
			try (Connection connection = ConnectionProvider.getConnection()) {
					try (PreparedStatement jetonDexecutionDeRequette=connection.prepareStatement(requette);){
				
						encheres = new ArrayList<Enchere>();
						jetonDexecutionDeRequette.setInt(1, noArticle);
						
						try (ResultSet rst = jetonDexecutionDeRequette.executeQuery()){
				
							while (rst.next()) {
								enchere = new Enchere(rst.getDate("date_enchere"), rst.getInt("montant_enchere"), rst.getInt("no_article"), rst.getInt("no_utilisateur"));
								encheres.add(enchere);
							}
							
						}catch (SQLException e){
							throw new DALException ("Erreur lors de la récupération des enchères sur un article :" + e.getMessage());
						}
				
					} catch (SQLException e) {
						throw new DALException("Erreur lors de l'instruction SQL : " + e.getMessage());
					} 
					
			} catch (SQLException e) {
				throw new DALException("Erreur de connexion à la BDD : "+ e.getMessage());	
			}
			
			return encheres;
			
			
		}


		@Override
		public void insertEnchere(Enchere enchere) throws DALException{
			
			String requete = "insert into ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) values (?,?,?,?);";  
			
			try (Connection connection = ConnectionProvider.getConnection()){
				 	try (PreparedStatement jetonDexecutionDeRequete=connection.prepareStatement(requete)){
				
				
				jetonDexecutionDeRequete.setInt(1, enchere.getNoUtilisateur());
				jetonDexecutionDeRequete.setInt(2, enchere.getNoArticle());
				jetonDexecutionDeRequete.setDate(3, Date.valueOf(LocalDate.now())); 
				jetonDexecutionDeRequete.setInt(4, enchere.getMontantEnchere());
				jetonDexecutionDeRequete.executeUpdate();
				
			 
					} catch (SQLException e) {
						throw new DALException("Erreur lors de l'insertion d'une enchère : " + e.getMessage());
					} 
					
			} catch (SQLException e) {
				throw new DALException("Erreur de connexion à la BDD : "+ e.getMessage());	
			}
				
		}
			
		
	@Override
	public Enchere enchereMeilleurOffre (int meilleurOffre, int noArticle) throws DALException {
		
		
		String requete = "select * from ENCHERES where no_article = ? and montant_enchere = ?;";
		Enchere enchere = null;
		
		try (Connection connection = ConnectionProvider.getConnection()){
		 	try (PreparedStatement jetonDexecutionDeRequete=connection.prepareStatement(requete)){
		 		jetonDexecutionDeRequete.setInt(1, noArticle);
		 		jetonDexecutionDeRequete.setInt(2, meilleurOffre);
		 		
		 		try (ResultSet result = jetonDexecutionDeRequete.executeQuery()){
					while (result != null && result.next()) {
						enchere = new Enchere();
						
						enchere.setDateEnchere(result.getDate("date_enchere"));
						enchere.setMontantEnchere(result.getInt("montant_enchere"));
						enchere.setNoArticle(result.getInt("no_article"));
						enchere.setNoUtilisateur(result.getInt("no_utilisateur"));
					}
		 		
		 		}catch (SQLException e){
					throw new DALException ("Erreur lors de la récupération de la meilleure enchère sur un article :" + e.getMessage());
				}
		
			} catch (SQLException e) {
				throw new DALException("Erreur lors de l'instruction SQL : " + e.getMessage());
			} 
			
		} catch (SQLException e) {
			throw new DALException("Erreur de connexion à la BDD : "+ e.getMessage());	
		}
		 		
		return enchere;
		
	}
	
	
	
  //Methode destin�e � Ench�reTerminees
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
	
	
	
	
	public List <ArticleVendu>  EncheresTerminees() throws DALException {
		
		String requette = "select * from ARTICLES_VENDUS where date_fin_encheres = CAST(getdate() AS date)";
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
		
}
