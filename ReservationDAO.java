package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import POJO.Reservation;
import POJO.Utilisateur;
import POJO.Materiel;

public class ReservationDAO {
	
	private Connection connect;

    public ReservationDAO(Connection con) {
        this.connect = con;
    }
    
    public Reservation find(int idReservation) {
        String sql = "SELECT * FROM reservation WHERE idReservartion = ?";
        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, idReservation);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	UtilisateurDAO utilisateurDAO = new UtilisateurDAO(connect);
                Utilisateur utilisateur = utilisateurDAO.find(rs.getInt("idUtilisateur"));
                MaterielDAO materielDAO = new MaterielDAO(connect);
                Materiel materiel = materielDAO.find(rs.getInt("idMateriel"));
                return new Reservation(
                        rs.getInt("idReservation"),
                        utilisateur,
                        materiel,
                        rs.getObject("dateReservation", LocalDate.class),
                        rs.getObject("heureDebut", LocalTime.class),
                        rs.getObject("heureFin", LocalTime.class)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Reservation> findAll() {
        ArrayList<Reservation> listeReservation = new ArrayList<>();
        String sql = "SELECT * FROM reservation";
        try (PreparedStatement ps = connect.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
            	UtilisateurDAO utilisateurDAO = new UtilisateurDAO(connect);
                Utilisateur utilisateur = utilisateurDAO.find(rs.getInt("idUtilisateur"));
                MaterielDAO materielDAO = new MaterielDAO(connect);
                Materiel materiel = materielDAO.find(rs.getInt("idMateriel"));
                listeReservation.add(new Reservation(
                		rs.getInt("idReservation"),
                        utilisateur,
                        materiel,
                        rs.getObject("dateReservation", LocalDate.class),
                        rs.getObject("heureDebut", LocalTime.class),
                        rs.getObject("heureFin", LocalTime.class)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeReservation;
    }
    
    public ArrayList<Reservation> findByUtilisateur(int idUtilisateur) {
        ArrayList<Reservation> liste = new ArrayList<>();
        String sql = "SELECT * FROM reservation WHERE idUtilisateur = ?";
        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, idUtilisateur);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	UtilisateurDAO utilisateurDAO = new UtilisateurDAO(connect);
                Utilisateur utilisateur = utilisateurDAO.find(rs.getInt("idUtilisateur"));
                MaterielDAO materielDAO = new MaterielDAO(connect);
                Materiel materiel = materielDAO.find(rs.getInt("idMateriel"));
                liste.add(new Reservation(
                        rs.getInt("idReservation"),
                        utilisateur,
                        materiel,
                        rs.getObject("dateReservation", LocalDate.class),
                        rs.getObject("heureDebut", LocalTime.class),
                        rs.getObject("heureFin", LocalTime.class)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }
    
    public boolean create(Reservation obj) {
        String sql = "INSERT INTO reservation (idUtilisateur, idMateriel, dateReservation, heureDebut, heureFin) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, obj.getUtilisateur().getIdUtilisateur());
            ps.setInt(2, obj.getMateriel().getIdMateriel());
            ps.setObject(3, obj.getDateReservation());
            ps.setObject(4, obj.getHeureDebut());
            ps.setObject(5, obj.getHeureFin());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Reservation obj) {
        String sql = "UPDATE reservation SET idUtilisateur = ?, idMateriel = ?, dateReservation = ?, heureDebut = ?, heureFin = ? WHERE idReservation = ?";
        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, obj.getUtilisateur().getIdUtilisateur());
            ps.setInt(2, obj.getMateriel().getIdMateriel());
            ps.setObject(3, obj.getDateReservation());
            ps.setObject(4, obj.getHeureDebut());
            ps.setObject(5, obj.getHeureFin());
            ps.setInt(6, obj.getIdReservation());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Reservation obj) {
        String sql = "DELETE FROM reservation WHERE idReservation = ?";
        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, obj.getIdReservation());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
