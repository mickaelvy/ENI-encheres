package fr.eni.projet1.bo;

import java.util.ArrayList;
import java.util.List;

public class Categorie {
	private int noCategorie;
	private String libelle;
	private List <ArticleVendu> articleVendu;
	
	public Categorie (int pNoCategorie, String pLibelle) {
		this.noCategorie = pNoCategorie;
		this.libelle = pLibelle;
		articleVendu = new ArrayList <> ();
	}

	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<ArticleVendu> getArticleVendu() {
		return articleVendu;
	}

	public void setArticleVendu(List<ArticleVendu> articleVendu) {
		this.articleVendu = articleVendu;
	}

	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + ", articleVendu=" + articleVendu
				+ "]";
	}

	
	
}
