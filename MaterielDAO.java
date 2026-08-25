package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import POJO.Materiel;

public class MaterielDAO {
	
	private Connection connect;
	
	public MaterielDAO(Connection con) {
        this.connect = con;
    }
	public Materiel find(int idMateriel) {
        String sql = "SELECT * FROM materiel WHERE idMateriel = ?";
        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, idMateriel);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Materiel(
                		rs.getInt("idMateriel"),
                		rs.getString("nom"),
                		rs.getString("categorie"), 
                		rs.getInt("quantite"), 
                		rs.getString("etat"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public ArrayList<Materiel> findAll() {
        ArrayList<Materiel> listeMateriel = new ArrayList<>();
        String sql = "SELECT * FROM materiel";
        try (PreparedStatement ps = connect.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                listeMateriel.add(new Materiel(
                		rs.getInt("idMateriel"),
                		rs.getString("nom"),
                		rs.getString("categorie"), 
                		rs.getInt("quantite"), 
                		rs.getString("etat")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeMateriel;
    }
	
	public boolean create(Materiel obj) {
	    String sql = "INSERT INTO materiel (nom, categorie, quantite, etat) VALUES (?, ?, ?, ?)";
	    try (PreparedStatement ps = connect.prepareStatement(sql)) {
	        ps.setString(1, obj.getNom());
	        ps.setString(2, obj.getCategorie());
	        ps.setInt(3, obj.getQuantite());
	        ps.setString(4, obj.getEtat());
	        return ps.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public boolean update(Materiel obj) {
	    String sql = "UPDATE materiel SET nom = ?, categorie = ?, quantite = ?, etat = ? WHERE idMateriel = ?";
	    try (PreparedStatement ps = connect.prepareStatement(sql)) {
	        ps.setString(1, obj.getNom());
	        ps.setString(2, obj.getCategorie());
	        ps.setInt(3, obj.getQuantite());
	        ps.setString(4, obj.getEtat());
	        ps.setInt(5, obj.getIdMateriel());
	        return ps.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public boolean delete(Materiel obj) {
	    String sql = "DELETE FROM materiel WHERE idMateriel = ?";
	    try (PreparedStatement ps = connect.prepareStatement(sql)) {
	        ps.setInt(1, obj.getIdMateriel());
	        return ps.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}
