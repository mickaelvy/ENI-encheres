package fr.eni.projet1.bo;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
	private int noUtilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String codePostal;
	private String ville;
	private String password;
	

	private int credit;
	private byte administrateur;
	private List <ArticleVendu> achatArticle;
	private List <ArticleVendu> venteArticle;
	private List <Enchere> encheresUser;
	
	
	public Utilisateur() {
		
	}
	

	public Utilisateur(int pNoUtilisateur,String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville) {
		super();
		this.noUtilisateur = pNoUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	
	}
	
	
	
	public Utilisateur (int pNoUtilisateur, String pPseudo, String pNom, String pPrenom, String pEmail, String pTelephone, String pRue, 
			String pCodePostal, String pVille, String pPassword,int pCredit, byte pAdministrateur) {
		this.noUtilisateur = pNoUtilisateur;
		this.pseudo = pPseudo;
		this.nom = pNom;
		this.prenom = pPrenom;
		this.email = pEmail;
		this.telephone = pTelephone;
		this.rue = pRue;
		this.codePostal = pCodePostal;
		this.ville = pVille;
		this.password = pPassword;
		this.credit = pCredit;
		this.administrateur = pAdministrateur;
		achatArticle = new ArrayList <> ();
		venteArticle = new ArrayList <> ();
		encheresUser = new ArrayList <> ();
	}

	
	
	

	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String password) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.password = password;
	}

	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String password, int credit) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.password = password;
		this.credit = credit;
	}
	
	
	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String password) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.password = password;
	}

	
	
	








	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public byte getAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(byte administrateur) {
		this.administrateur = administrateur;
	}

	public List<ArticleVendu> getAchatArticle() {
		return achatArticle;
	}

	public void setAchatArticle(List<ArticleVendu> achatArticle) {
		this.achatArticle = achatArticle;
	}

	public List<ArticleVendu> getVenteArticle() {
		return venteArticle;
	}

	public void setVenteArticle(List<ArticleVendu> venteArticle) {
		this.venteArticle = venteArticle;
	}

	public List<Enchere> getEncheresUser() {
		return encheresUser;
	}

	public void setEncheresUser(List<Enchere> encheresUser) {
		this.encheresUser = encheresUser;
	}

	@Override
	public String toString() {
		return "Utilisateur [noUtilisateur=" + noUtilisateur + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom="
				+ prenom + ", email=" + email + ", telephone=" + telephone + ", rue=" + rue + ", codePostal="
				+ codePostal + ", ville=" + ville + ", password=" + password + ", credit=" + credit
				+ ", administrateur=" + administrateur + ", achatArticle=" + achatArticle + ", venteArticle="
				+ venteArticle + ", encheresUser=" + encheresUser + "]";
	}




	
	
	
}
	

