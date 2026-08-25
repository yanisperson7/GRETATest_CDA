package POJO;

public class Materiel {
	private int idMateriel;
	private String nom;
	private String categorie;
	private int quantite;
	private String etat;
	
	public Materiel(int idMateriel, String nom, String categorie, int quantite, String etat) {
		super();
		this.idMateriel = idMateriel;
		this.nom = nom;
		this.categorie = categorie;
		this.quantite = quantite;
		this.etat = etat;
	}

	public int getIdMateriel() {
		return idMateriel;
	}

	public void setIdMateriel(int idMateriel) {
		this.idMateriel = idMateriel;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	@Override
	public String toString() {
		return "Materiel [idMateriel=" + idMateriel + ", nom=" + nom + ", categorie=" + categorie + ", quantite="
				+ quantite + ", etat=" + etat + "]";
	}
	
	

}
