package fr.eni.projet1.bo;

/**
 * @author mvergond2019
 *
 */
public class Retrait {

	private String rue;
	private String codePostal;
	private String ville;
	private int noArticle;
	
	
	public Retrait() {
	
	}
	public Retrait(String rue, String codePostal, String ville, int noArticle) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.noArticle = noArticle;
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



	public int getNoArticle() {
		return noArticle;
	}



	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	
	
	
	
	
	
	
}