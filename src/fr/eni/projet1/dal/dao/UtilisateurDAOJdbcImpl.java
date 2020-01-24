package fr.eni.projet1.dal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet1.bo.Enchere;
import fr.eni.projet1.bo.Utilisateur;
import fr.eni.projet1.dal.ConnectionProvider;
import fr.eni.projet1.dal.DALException;


public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{
	
	
	
	//Inscription d'un utilisateur
	@Override
	public void registerUser(Utilisateur utilisateur)throws DALException {
		// TODO Auto-generated method stub
		
		String requette = "insert into UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values (?,?,?,?,?,?,?,?,?,?,?);";

		// Utilisation du try with ressources (apparu avec Java 7): permet de définir une ressource qui sera automatiquement fermée à la fin de l'exécution du bloc de code de l'instruction.
		try (Connection connection = ConnectionProvider.getConnection()){
				try (PreparedStatement jetonDexecutionDeRequette=connection.prepareStatement(requette, Statement.RETURN_GENERATED_KEYS)){
				
				
					jetonDexecutionDeRequette.setString(1, utilisateur.getPseudo());
					jetonDexecutionDeRequette.setString(2, utilisateur.getNom());
					jetonDexecutionDeRequette.setString(3, utilisateur.getPrenom()); 
					jetonDexecutionDeRequette.setString(4, utilisateur.getEmail());
					jetonDexecutionDeRequette.setString(5, utilisateur.getTelephone());
					jetonDexecutionDeRequette.setString(6, utilisateur.getRue());
					jetonDexecutionDeRequette.setString(7, utilisateur.getCodePostal());
					jetonDexecutionDeRequette.setString(8, utilisateur.getVille());
					jetonDexecutionDeRequette.setString(9, utilisateur.getPassword());
					jetonDexecutionDeRequette.setInt(10, 100);
					jetonDexecutionDeRequette.setInt(11, 1);
					jetonDexecutionDeRequette.executeUpdate();
				
				
					 try (ResultSet generatedKeys = jetonDexecutionDeRequette.getGeneratedKeys()) {
				            if (generatedKeys.next()) {
				                utilisateur.setNoUtilisateur(generatedKeys.getInt(1));
				                 
				            }
				            else {
				            	
				                throw new SQLException("Le système n'a pas pu attribuer un numéro à l'utilisateur.");
				            }
				        }
				
			} catch (SQLException e){
				throw new DALException("Erreur lors de l'ajout d'un utilisateur : " + e.getMessage());
			}
		
		}catch(SQLException e) {
			throw new DALException("Erreur de connexion à la BDD : "+e.getMessage());
		}
		
	}
	
	
//Suppression utilisateur
	@Override
	public int deleteUser(String mail, String password)throws DALException {
		
		String requette="delete from utilisateurs where email = ? and mot_de_passe = ?;";
		int nbreDeLigne = 0;
		
		
			try (Connection connection = ConnectionProvider.getConnection()){
				
					try (PreparedStatement jetonDexecutionDeRequette = connection.prepareStatement(requette)) {
					
						jetonDexecutionDeRequette.setString(1, mail);
						jetonDexecutionDeRequette.setString(2, password);
						nbreDeLigne = jetonDexecutionDeRequette.executeUpdate();
						//return nbreDeLigne;
					
					} catch (SQLException e) {	
						throw new DALException("Erreur lors de la suppression d'un utilisateur : " + e.getMessage());	
					} 
				
			} catch (SQLException e) {	
				throw new DALException("Erreur de connexion à la BDD : "+e.getMessage());
			}
			
			return nbreDeLigne;
	}
	
	
	// Modification du profil de l'utilisateur
	@Override
	public void updateUser(Utilisateur utilisateur)throws DALException {
		
		String requette = "update UTILISATEURS  set pseudo=?, nom=? ,prenom=?,email=?,telephone=?,rue=?,code_postal=?,ville=?,mot_de_passe=?  where no_utilisateur=?";

		try (Connection connection = ConnectionProvider.getConnection()){
				try (PreparedStatement jetonDexecutionDeRequette=connection.prepareStatement(requette)){
				
				
					jetonDexecutionDeRequette.setInt(10, utilisateur.getNoUtilisateur());
					jetonDexecutionDeRequette.setString(1, utilisateur.getPseudo());
					jetonDexecutionDeRequette.setString(2, utilisateur.getNom());
					jetonDexecutionDeRequette.setString(3, utilisateur.getPrenom()); 
					jetonDexecutionDeRequette.setString(4, utilisateur.getEmail());
					jetonDexecutionDeRequette.setString(5, utilisateur.getTelephone());
					jetonDexecutionDeRequette.setString(6, utilisateur.getRue());
					jetonDexecutionDeRequette.setString(7, utilisateur.getCodePostal());
					jetonDexecutionDeRequette.setString(8, utilisateur.getVille());
					jetonDexecutionDeRequette.setString(9, utilisateur.getPassword());
					
					jetonDexecutionDeRequette.executeUpdate();
				
				} catch (SQLException e){
					throw new DALException("Erreur lors de la modification du profil d'un utilisateur : " + e.getMessage());
				}	
				
		} catch (SQLException e) {	
			throw new DALException("Erreur de connexion à la BDD : "+e.getMessage());
		}		
	}


	@Override
	public boolean isUniquePseudo(String pseudo) throws DALException {
		
		String requete = "select * from utilisateurs where pseudo = ?;";
		boolean pseudoUnique= false;
		
		try (Connection connection = ConnectionProvider.getConnection()){
				try(PreparedStatement jetonDexecutionDeRequete = connection.prepareStatement(requete)){
				
						jetonDexecutionDeRequete.setString(1, pseudo);
						
						try (ResultSet result = jetonDexecutionDeRequete.executeQuery()){
					
							if (result.next() ) {
								pseudoUnique= false;
							} else {
								pseudoUnique= true;
							}
							
						}catch (SQLException e){
							throw new DALException ("Erreur lors de la vérification de l'unicité du pseudo :" + e.getMessage());
						}
				
				} catch (SQLException e) {
					throw new DALException("Erreur lors de l'instruction SQL : " + e.getMessage());
				} 
				
		} catch (SQLException e) {
			throw new DALException("Erreur de connexion à la BDD : "+ e.getMessage());	
		}
	
		return pseudoUnique;
	
	}
	
	
	
	@Override
	public boolean isUniqueMail(String mail) throws DALException {
		
		String requete = "select * from utilisateurs where email = ?;";
		boolean mailUnique= false;
		
			try (Connection connection = ConnectionProvider.getConnection()){
					try (PreparedStatement jetonDexecutionDeRequete = connection.prepareStatement(requete);){
				
							jetonDexecutionDeRequete.setString(1, mail);
							
							try (ResultSet result = jetonDexecutionDeRequete.executeQuery()){
					
								if (result.next() ) {
									mailUnique= false;
								} else {
									mailUnique= true;
								}
							
							} catch (SQLException e) {
								throw new DALException ("Erreur lors de la vérification de l'unicité de l'email :" + e.getMessage());
							}
				
					} catch (SQLException e) {
						throw new DALException("Erreur lors de l'instruction SQL : " + e.getMessage());	
					} 
					
			} catch (SQLException e) {
				throw new DALException("Erreur de connexion à la BDD : "+ e.getMessage());	
			}
		
		return mailUnique;
	
	}
	




	@Override
	public Utilisateur connectUser(String pseudo, String password) throws DALException {
		// TODO Auto-generated method stub
		
		Utilisateur utilisateur = null;
		
		String requete = "select * from utilisateurs where pseudo = ? and mot_de_passe = ?;";
		
		
		try (Connection connection = ConnectionProvider.getConnection() ){
				try(PreparedStatement jetonDexecutionDeRequete = connection.prepareStatement(requete)){
			
						jetonDexecutionDeRequete.setString(1, pseudo);
						jetonDexecutionDeRequete.setString(2, password);
			
						try (ResultSet result = jetonDexecutionDeRequete.executeQuery()){
			
							if (result.next() ) {
								
								utilisateur = new Utilisateur();
								utilisateur.setNoUtilisateur(result.getInt(1));
								utilisateur.setPseudo(result.getString(2));
								utilisateur.setNom(result.getString(3));
								utilisateur.setPrenom(result.getString(4));
								utilisateur.setEmail(result.getString(5));
								utilisateur.setTelephone(result.getString(6));
								utilisateur.setRue(result.getString(7));
								utilisateur.setCodePostal(result.getString(8));
								utilisateur.setVille(result.getString(9));
								utilisateur.setPassword(result.getString(10));
								utilisateur.setCredit(result.getInt(11));
								
							} else {
								System.out.println("Cet utilisateur n'éxiste pas.");
								utilisateur=null;
							}
							
								
						} catch(SQLException e) {
							throw new DALException ("Erreur lors de la récupération de l'utilisateur :" + e.getMessage());
						}
			
				} catch (SQLException e) {
					throw new DALException("Erreur lors de l'instruction SQL : " + e.getMessage());	
				} 
				
		} catch (SQLException e) {	
			throw new DALException("Erreur de connexion à la BDD : "+ e.getMessage());
		}
		
		return utilisateur;
	}

	
	
	// Supprimer les articles correspondant à un utilisateur
	@Override
	public void supprimMesArticlesEnLigne(int noUtilisateur) throws DALException{
		
		String requette="delete from ARTICLES_VENDUS where no_utilisateur = ?;";
		//int nbreDeLigne = 0;
		
			try (Connection connection = ConnectionProvider.getConnection()){
					try (PreparedStatement jetonDexecutionDeRequette = connection.prepareStatement(requette)){
				
						jetonDexecutionDeRequette.setInt(1, noUtilisateur);
						jetonDexecutionDeRequette.executeUpdate();
						//nbreDeLigne = jetonDexecutionDeRequette.executeUpdate();
						//return nbreDeLigne;
				
					} catch (SQLException e) {
						throw new DALException("Erreur lors de la suppression des articles de l'utilisateur : " + e.getMessage());
					} 
					
			}catch (SQLException e){
				throw new DALException("Erreur de connexion à la BDD : "+ e.getMessage());
			}	
	}


	@Override
	public Utilisateur getUtilisateurById(int noUtilisateur) throws DALException  {
	
		String requete = "select * from utilisateurs where no_utilisateur = ?;";
		Utilisateur utilisateur = null;
		
			try (Connection connection = ConnectionProvider.getConnection()) {
					try (PreparedStatement jetonDexecutionDeRequete = connection.prepareStatement(requete)){
				
						jetonDexecutionDeRequete.setInt(1, noUtilisateur);
						try (ResultSet result = jetonDexecutionDeRequete.executeQuery()) {
							utilisateur = new Utilisateur();
							
							if (result.next()) {
							utilisateur.setPseudo(result.getString("pseudo"));
							utilisateur.setNom(result.getString("nom"));
							utilisateur.setPrenom(result.getString("prenom"));
							utilisateur.setEmail(result.getString("email"));
							utilisateur.setTelephone(result.getString("telephone"));
							utilisateur.setRue(result.getString("rue"));
							utilisateur.setCodePostal(result.getString("code_postal"));
							utilisateur.setVille(result.getString("ville"));
							utilisateur.setCredit(result.getInt("credit"));
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
			
		
	
		return utilisateur;
	}
	
	@Override
	public List<Utilisateur> findAllUsers() throws DALException{
		
		String requette = "select * FROM UTILISATEURS;";
		List<Utilisateur> utilisateurs = new ArrayList<>();

		try (Connection connection = ConnectionProvider.getConnection()){
				try(PreparedStatement jetonDexecutionDeRequette=connection.prepareStatement(requette)){
						try (ResultSet result = jetonDexecutionDeRequette.executeQuery()){
			
							while (result != null && result.next()) {
								Utilisateur utilisateur = new Utilisateur();
				
								utilisateur.setNoUtilisateur(result.getInt("no_utilisateur"));
								utilisateur.setPseudo(result.getString("pseudo"));
								utilisateur.setPrenom(result.getString("prenom"));
								utilisateur.setNom(result.getString("nom"));
								utilisateur.setEmail(result.getString("email"));
								utilisateur.setTelephone(result.getString("telephone"));
								utilisateur.setRue(result.getString("rue"));
								utilisateur.setCodePostal(result.getString("code_postal"));
								utilisateur.setVille(result.getString("ville"));
								utilisateur.setPassword(result.getString("mot_de_passe"));
								utilisateur.setCredit(result.getInt("credit"));
								utilisateur.setAdministrateur(result.getByte("administrateur"));
				
								utilisateurs.add(utilisateur);
							}
			
			
			
						} catch (SQLException e) {
							throw new DALException ("Erreur lors de la récupération des utilisateurs :" + e.getMessage());
						}
						
					} catch (SQLException e) {
						throw new DALException("Erreur lors de l'instruction SQL : " + e.getMessage());	
					} 
					
			} catch (SQLException e) {	
				throw new DALException("Erreur de connexion à la BDD : "+ e.getMessage());
			}
		
		return utilisateurs;
	}

	@Override
	public void  recrediterUtilisateur(Enchere lastEnchere) throws DALException {
		String requete =  "UPDATE UTILISATEURS SET credit+=? WHERE no_utilisateur=?;";
		
		try (Connection connection = ConnectionProvider.getConnection()){
				try(PreparedStatement jetonDexecutionDeRequette=connection.prepareStatement(requete)){
					
					jetonDexecutionDeRequette.setInt(1,lastEnchere.getMontantEnchere());
					jetonDexecutionDeRequette.setInt(2, lastEnchere.getNoUtilisateur());
					jetonDexecutionDeRequette.executeUpdate();
	
				} catch (SQLException e) {
					throw new DALException("Erreur, impossible de recréditer l'utilisateur : " + e.getMessage());
				} 
			
		}catch (SQLException e){
			throw new DALException("Erreur de connexion à la BDD : "+ e.getMessage());
		}	
	}
	
	@Override
	public void  debiterUtilisateur(int montantEnchere, int noUtilisateur) throws DALException {
		String requete =  "UPDATE UTILISATEURS SET credit-=? WHERE no_utilisateur=?;";
		
		try (Connection connection = ConnectionProvider.getConnection()){
				try(PreparedStatement jetonDexecutionDeRequette=connection.prepareStatement(requete)){
					
					jetonDexecutionDeRequette.setInt(1,montantEnchere);
					jetonDexecutionDeRequette.setInt(2, noUtilisateur);
					jetonDexecutionDeRequette.executeUpdate();
	
					
				} catch (SQLException e) {
					throw new DALException("Erreur, impossible de recréditer l'utilisateur : " + e.getMessage());
				} 
			
		}catch (SQLException e){
			throw new DALException("Erreur de connexion à la BDD : "+ e.getMessage());
		}	
	}

	
	@Override
	public void logoutUser(int userId) throws DALException {	
	}

}
