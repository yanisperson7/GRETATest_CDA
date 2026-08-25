package POJO;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
	private int idReservation;
	private Utilisateur utilisateur;
	private Materiel materiel;
	private LocalDate dateReservation;
	private LocalTime heureDebut;
	private LocalTime heureFin;
	
	
	public Reservation(int idReservation, Utilisateur utilisateur, Materiel materiel, LocalDate dateReservation,
			LocalTime heureDebut, LocalTime heureFin) {
		super();
		this.idReservation = idReservation;
		this.utilisateur = utilisateur;
		this.materiel = materiel;
		this.dateReservation = dateReservation;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}


	public int getIdReservation() {
		return idReservation;
	}


	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}


	public Materiel getMateriel() {
		return materiel;
	}


	public void setMateriel(Materiel materiel) {
		this.materiel = materiel;
	}


	public LocalDate getDateReservation() {
		return dateReservation;
	}


	public void setDateReservation(LocalDate dateReservation) {
		this.dateReservation = dateReservation;
	}


	public LocalTime getHeureDebut() {
		return heureDebut;
	}


	public void setHeureDebut(LocalTime heureDebut) {
		this.heureDebut = heureDebut;
	}


	public LocalTime getHeureFin() {
		return heureFin;
	}


	public void setHeureFin(LocalTime heureFin) {
		this.heureFin = heureFin;
	}


	@Override
	public String toString() {
		return "Reservation [idReservation=" + idReservation + ", utilisateur=" + utilisateur + ", materiel=" + materiel
				+ ", dateReservation=" + dateReservation + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin
				+ "]";
	}

}
