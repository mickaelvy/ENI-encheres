package fr.eni.projet1.bo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;



public class ArticleVendu {
	private int noArticle;
	private String nomArticle;
	private String description;
	private Date dateDebutEncheres;
	private Date dateFinEncheres;
	private int prixInitial;
	private int prixVente;
	private int noUtilisateurVendeur;
	private int noCategorie;
	private String pseudoVendeur;
	int meilleureOffreActuelle;
	
	




	




	private Utilisateur vendeur;
	private String cheminImage;
	private String etatVente;
    private Utilisateur acheteur;
	private Retrait lieuRetrait;
	private Categorie categorieArticle;
	private List <Enchere> encheres;
	
	
	
	public ArticleVendu() {
		
	}
	
	
	
	
	
	public ArticleVendu(int noArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int prixInitial, int prixVente,int noUtilisateurVendeur, int noCategorie) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
		this.noUtilisateurVendeur = noUtilisateurVendeur;
		this.noCategorie = noCategorie;
	}



	



	public ArticleVendu(String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres,
			int prixInitial, int prixVente) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixVente;
	}
	
	
	
	public ArticleVendu(String nomArticle, String description, String cheminImage, Date dateDebutEncheres, Date dateFinEncheres,
			int prixInitial) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.cheminImage = cheminImage;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		
	}
	

	public ArticleVendu (int pNoArticle, String pNomArticle, String pDescription, Date pDateDebutEncheres, Date pDateFinEncheres,
			int pPrixInitial, int pPrixVente, String pEtatVente, Utilisateur pAcheteur, Utilisateur pVendeur, Retrait pLieuRetrait,
			Categorie pCategorieArticle) {
		this.noArticle = pNoArticle;
		this.nomArticle = pNomArticle;
		this.description = pDescription;
		this.dateDebutEncheres = pDateDebutEncheres;
		this.dateFinEncheres = pDateFinEncheres;
		this.prixInitial = pPrixInitial;
		this.prixVente = pPrixVente;
		this.etatVente = pEtatVente;
		this.acheteur = pAcheteur;
		this.vendeur = pVendeur;
		this.lieuRetrait = pLieuRetrait;
		this.categorieArticle = pCategorieArticle;
		encheres = new ArrayList <> ();
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getCheminImage() {
		return cheminImage;
	}

	public void setCheminImage(String cheminImage) {
		this.cheminImage = cheminImage;
	}



	public Date getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(Date dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public Date getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(Date dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public int getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public String getEtatVente() {
		return etatVente;
	}

	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}

	public Utilisateur getAcheteur() {
		return acheteur;
	}

	public void setAcheteur(Utilisateur acheteur) {
		this.acheteur = acheteur;
	}

	public Utilisateur getVendeur() {
		return vendeur;
	}

	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}

	public Retrait getLieuRetrait() {
		return lieuRetrait;
	}

	public void setLieuRetrait(Retrait lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}

	public Categorie getCategorieArticle() {
		return categorieArticle;
	}

	public void setCategorieArticle(Categorie categorieArticle) {
		this.categorieArticle = categorieArticle;
	}

	public List<Enchere> getEncheres() {
		return encheres;
	}

	public void setEncheres(List<Enchere> encheres) {
		this.encheres = encheres;
	}
	
	public int getNoUtilisateurVendeur() {
		return noUtilisateurVendeur;
	}



	public void setNoUtilisateurVendeur(int noUtilisateurVendeur) {
		this.noUtilisateurVendeur = noUtilisateurVendeur;
	}
	
	
	
	public String getPseudoVendeur() {
		return pseudoVendeur;
	}


	public void setPseudoVendeur(String pseudoVendeur) {
		this.pseudoVendeur = pseudoVendeur;
	}
	
	
	
	
	
	
	public int getMeilleureOffreActuelle() {
		return meilleureOffreActuelle;
	}





	public void setMeilleureOffreActuelle(int meilleureOffreActuelle) {
		this.meilleureOffreActuelle = meilleureOffreActuelle;
	}





	public int getNoCategorie() {
		return noCategorie;
	}





	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	

	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", prixInitial="
				+ prixInitial + ", prixVente=" + prixVente + ", noUtilisateurVendeur=" + noUtilisateurVendeur
				+ ", noCategorie=" + noCategorie + "]";
	}

	
	
	
}

