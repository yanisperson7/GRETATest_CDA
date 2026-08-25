package POJO;

public class Utilisateur {
	private int idUtilisateur;
	private Role role;
	private String nom;
	private String prenom;
	private String login;
	private String password;


	public Utilisateur(int idUtilisateur, Role role, String nom, String prenom, String login, String password) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.role = role;
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.password = password;
	}


	public int getIdUtilisateur() {
		return idUtilisateur;
	}


	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
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


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "Utilisateur [idUtilisateur=" + idUtilisateur + ", role=" + role + ", nom=" + nom + ", prenom="
				+ prenom + ", login=" + login + ", password=" + password + "]";
	}

}