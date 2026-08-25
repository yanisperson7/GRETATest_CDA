package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import POJO.ArchiveReservation;
import POJO.Reservation;
import POJO.Utilisateur;
import POJO.Materiel;

public class ArchiveReservationDAO {

    private Connection connect;

    public ArchiveReservationDAO(Connection conn) {
        this.connect = conn;
    }
    public boolean create(ArchiveReservation obj) {
        String sql = "INSERT INTO archivereservation (idReservation, idUtilisateur, idMateriel, dateReservation, heureDebut, heureFin, action, dateAction) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, obj.getReservation().getIdReservation());
            ps.setInt(2, obj.getUtilisateur().getIdUtilisateur());
            ps.setInt(3, obj.getMateriel().getIdMateriel());
            ps.setObject(4, obj.getDateReservation());
            ps.setObject(5, obj.getHeureDebut());
            ps.setObject(6, obj.getHeureFin());
            ps.setString(7, obj.getAction());
            ps.setObject(8, obj.getDateAction());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<ArchiveReservation> findByUtilisateur(int idUtilisateur) {
        ArrayList<ArchiveReservation> listeArchive = new ArrayList<>();
        String sql = "SELECT * FROM archivereservation WHERE idUtilisateur = ?";
        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, idUtilisateur);
            ResultSet rs = ps.executeQuery();
            ReservationDAO reservationDAO = new ReservationDAO(connect);
            UtilisateurDAO utilisateurDAO = new UtilisateurDAO(connect);
            MaterielDAO materielDAO = new MaterielDAO(connect);
            while (rs.next()) {
                Reservation reservation = reservationDAO.find(rs.getInt("idReservation"));
                Utilisateur utilisateur = utilisateurDAO.find(rs.getInt("idUtilisateur"));
                Materiel materiel = materielDAO.find(rs.getInt("idMateriel"));
                listeArchive.add(new ArchiveReservation(
                        rs.getInt("idArchive"),
                        reservation,
                        utilisateur,
                        materiel,
                        rs.getObject("dateReservation", LocalDate.class),
                        rs.getObject("heureDebut", java.time.LocalTime.class),
                        rs.getObject("heureFin", java.time.LocalTime.class),
                        rs.getString("action"),
                        rs.getObject("dateAction", LocalDate.class)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeArchive;
    }

    public ArrayList<ArchiveReservation> findAll() {
        ArrayList<ArchiveReservation> listeArchive = new ArrayList<>();
        String sql = "SELECT * FROM archivereservation";
        try (PreparedStatement ps = connect.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            ReservationDAO reservationDAO = new ReservationDAO(connect);
            UtilisateurDAO utilisateurDAO = new UtilisateurDAO(connect);
            MaterielDAO materielDAO = new MaterielDAO(connect);
            while (rs.next()) {
                Reservation reservation = reservationDAO.find(rs.getInt("idReservation"));
                Utilisateur utilisateur = utilisateurDAO.find(rs.getInt("idUtilisateur"));
                Materiel materiel = materielDAO.find(rs.getInt("idMateriel"));
                listeArchive.add(new ArchiveReservation(
                        rs.getInt("idArchive"),
                        reservation,
                        utilisateur,
                        materiel,
                        rs.getObject("dateReservation", LocalDate.class),
                        rs.getObject("heureDebut", java.time.LocalTime.class),
                        rs.getObject("heureFin", java.time.LocalTime.class),
                        rs.getString("action"),
                        rs.getObject("dateAction", LocalDate.class)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeArchive;
    }
}