package fr.eni.projet1.dal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.projet1.bo.ArticleVendu;
import fr.eni.projet1.bo.Retrait;
import fr.eni.projet1.dal.ConnectionProvider;
import fr.eni.projet1.dal.DALException;

public class RetraitDAOJdbcImpl  implements RetraitDAO{

	@Override
	public void insertAdresseRetrait(Retrait retrait) throws DALException {
		
		String requette = "insert into RETRAITS (no_article,rue, code_postal, ville) values (?,?,?,?);";
		
		try (Connection connection = ConnectionProvider.getConnection()){
				try(PreparedStatement jetonDexecutionDeRequette=connection.prepareStatement(requette)){
			
					jetonDexecutionDeRequette.setInt(1, retrait.getNoArticle());
					jetonDexecutionDeRequette.setString(2, retrait.getRue());
					jetonDexecutionDeRequette.setString(3, retrait.getCodePostal());
					jetonDexecutionDeRequette.setString(4, retrait.getVille());
					jetonDexecutionDeRequette.executeUpdate();
					
			
				} catch (SQLException e){
					throw new DALException("Erreur lors de l'ajout de l'adresse de retraot : " + e.getMessage());
				}
			
		}catch(SQLException e) {
			throw new DALException("Erreur de connexion à la BDD : "+e.getMessage());
		}
	}

	@Override
	public Retrait getRetraitById(int idArticle) throws DALException {
		
		String requete = "select * from RETRAITS where no_article = ?;";
		Retrait retrait = null;
		
			try (Connection connection = ConnectionProvider.getConnection()) {
					try (PreparedStatement jetonDexecutionDeRequete = connection.prepareStatement(requete);){
				
						jetonDexecutionDeRequete.setInt(1, idArticle);
						
						try (ResultSet result=jetonDexecutionDeRequete.executeQuery()){
				
							retrait = new Retrait();
							
							if (result.next()) {
								retrait.setNoArticle(result.getInt("no_article"));
								retrait.setRue(result.getString("rue"));
								retrait.setCodePostal(result.getString("code_postal"));
								retrait.setVille(result.getString("ville"));
							}

						} catch (SQLException e) {
							throw new DALException ("Erreur lors de la sélection du lieu de retrait :" + e.getMessage());
						}
			
					} catch (SQLException e) {
						throw new DALException("Erreur lors de l'instruction SQL : " + e.getMessage());	
					} 
				
			} catch (SQLException e) {
				throw new DALException("Erreur de connexion à la BDD : "+ e.getMessage());	
			}
			return retrait;
	}
	
	

	
	

}
