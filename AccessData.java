package BDD;

import java.sql.Connection;
import java.util.ArrayList;

import DAO.*;
import POJO.*;

public class AccessData {

    static Connection con = ConnexionBDD.getInstance();

    public static Role getRole(String idRole) {
        RoleDAO dao = new RoleDAO(con);
        return dao.find(idRole);
    }

    public static ArrayList<Role> getLesRoles() {
        RoleDAO dao = new RoleDAO(con);
        return dao.findAll();
    }

    public static Utilisateur getUtilisateur(int idUtilisateur) {
        UtilisateurDAO dao = new UtilisateurDAO(con);
        return dao.find(idUtilisateur);
    }

    public static ArrayList<Utilisateur> getLesUtilisateurs() {
        UtilisateurDAO dao = new UtilisateurDAO(con);
        return dao.findAll();
    }

    public static Utilisateur connexion(String login, String password) {
        UtilisateurDAO dao = new UtilisateurDAO(con);
        return dao.findByLoginAndPassword(login, password);
    }

    public static Materiel getMateriel(int idMateriel) {
        MaterielDAO dao = new MaterielDAO(con);
        return dao.find(idMateriel);
    }

    public static ArrayList<Materiel> getLesMateriels() {
        MaterielDAO dao = new MaterielDAO(con);
        return dao.findAll();
    }

    public static boolean ajouterMateriel(Materiel materiel) {
        MaterielDAO dao = new MaterielDAO(con);
        return dao.create(materiel);
    }

    public static boolean modifierMateriel(Materiel materiel) {
        MaterielDAO dao = new MaterielDAO(con);
        return dao.update(materiel);
    }

    public static boolean supprimerMateriel(Materiel materiel) {
        MaterielDAO dao = new MaterielDAO(con);
        return dao.delete(materiel);
    }

    public static Reservation getReservation(int idReservation) {
        ReservationDAO dao = new ReservationDAO(con);
        return dao.find(idReservation);
    }
    
    public static ArrayList<Reservation> getLesReservation() {
        ReservationDAO dao = new ReservationDAO(con);
        return dao.findAll();
    }
    
    public static ArrayList<Reservation> getReservationsParUtilisateur(int idUtilisateur) {
        ReservationDAO dao = new ReservationDAO(con);
        return dao.findByUtilisateur(idUtilisateur);
    }

    public static boolean creerReservation(Reservation reservation) {
        ReservationDAO dao = new ReservationDAO(con);
        return dao.create(reservation);
    }

    public static boolean modifierReservation(Reservation reservation) {
        ReservationDAO dao = new ReservationDAO(con);
        return dao.update(reservation);
    }

    public static boolean archiverReservation(ArchiveReservation archive) {
        ArchiveReservationDAO dao = new ArchiveReservationDAO(con);
        return dao.create(archive);
    }

    public static ArrayList<ArchiveReservation> getHistoriqueUtilisateur(int idUtilisateur) {
        ArchiveReservationDAO dao = new ArchiveReservationDAO(con);
        return dao.findByUtilisateur(idUtilisateur);
    }

    public static ArrayList<ArchiveReservation> getHistoriqueComplet() {
        ArchiveReservationDAO dao = new ArchiveReservationDAO(con);
        return dao.findAll();
    }
}