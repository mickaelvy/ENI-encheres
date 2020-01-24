package fr.eni.projet1.bo;

public class Enchere {
	
	private java.sql.Date dateEnchere;
	private int montantEnchere;
	private int noArticle;
	private int noUtilisateur;
	
	

	public Enchere() {
	
	}
	
	
	
	


	public Enchere(int montantEnchere, int noArticle, int noUtilisateur) {
		super();
		this.montantEnchere = montantEnchere;
		this.noArticle = noArticle;
		this.noUtilisateur = noUtilisateur;
	}






	public Enchere(java.sql.Date dateEnchere, int montantEnchere, int noArticle, int noUtilisateur) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.noArticle = noArticle;
		this.noUtilisateur = noUtilisateur;
	}






	public java.sql.Date getDateEnchere() {
		return dateEnchere;
	}



	public void setDateEnchere(java.sql.Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}



	public int getMontantEnchere() {
		return montantEnchere;
	}



	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}



	public int getNoArticle() {
		return noArticle;
	}



	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}



	public int getNoUtilisateur() {
		return noUtilisateur;
	}



	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	
	
	
	
	
	
}
