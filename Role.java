package POJO;

public class Role {
	private String idRole;
    private String libelle;
    
    
	public Role(String idRole, String libelle) {
		super();
		this.idRole = idRole;
		this.libelle = libelle;
	}


	public String getIdRole() {
		return idRole;
	}


	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	@Override
	public String toString() {
		return "Role [idRole=" + idRole + ", libelle=" + libelle + "]";
	}
}
