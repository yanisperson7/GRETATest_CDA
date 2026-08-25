package POJO;

import java.time.LocalDate;
import java.time.LocalTime;

public class ArchiveReservation {
	private int idArchive;
	private Reservation reservation;
	private Utilisateur utilisateur;
	private Materiel materiel;
	private LocalDate dateReservation;
	private LocalTime heureDebut;
	private LocalTime heureFin;
	private String action;
	private LocalDate dateAction;
	
	
	public ArchiveReservation(int idArchive, Reservation reservation, Utilisateur utilisateur, Materiel materiel,
			LocalDate dateReservation, LocalTime heureDebut, LocalTime heureFin, String action, LocalDate dateAction) {
		super();
		this.idArchive = idArchive;
		this.reservation = reservation;
		this.utilisateur = utilisateur;
		this.materiel = materiel;
		this.dateReservation = dateReservation;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.action = action;
		this.dateAction = dateAction;
	}


	public int getIdArchive() {
		return idArchive;
	}


	public void setIdArchive(int idArchive) {
		this.idArchive = idArchive;
	}


	public Reservation getReservation() {
		return reservation;
	}


	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
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


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public LocalDate getDateAction() {
		return dateAction;
	}


	public void setDateAction(LocalDate dateAction) {
		this.dateAction = dateAction;
	}


	@Override
	public String toString() {
		return "ArchiveReservation [idArchive=" + idArchive + ", reservation=" + reservation + ", utilisateur="
				+ utilisateur + ", materiel=" + materiel + ", dateReservation=" + dateReservation + ", heureDebut="
				+ heureDebut + ", heureFin=" + heureFin + ", action=" + action + ", dateAction=" + dateAction + "]";
	}
}
