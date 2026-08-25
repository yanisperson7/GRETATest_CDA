package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import POJO.Role;
import POJO.Utilisateur;

public class UtilisateurDAO {
	private Connection connect;

    public UtilisateurDAO(Connection con) {
        this.connect = con;
    }
    
    public Utilisateur find(int idUtilisateur) {
        String sql = "SELECT * FROM utilisateur WHERE idUtilisateur = ?";
        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, idUtilisateur);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	RoleDAO roleDAO = new RoleDAO(connect);
                Role role = roleDAO.find(rs.getString("idRole"));
                return new Utilisateur(
                		rs.getInt("idUtilisateur"), 
                		role, 
                		rs.getString("nom"),
                		rs.getString("prenom"), 
                		rs.getString("login"), 
                		rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Utilisateur> findAll() {
        ArrayList<Utilisateur> listeUtilisateur = new ArrayList<>();
        String sql = "SELECT * FROM utilisateur";
        try (PreparedStatement ps = connect.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
            	RoleDAO roleDAO = new RoleDAO(connect);
                Role role = roleDAO.find(rs.getString("idRole"));
                listeUtilisateur.add(new Utilisateur(
                		rs.getInt("idUtilisateur"),
                		role,
                		rs.getString("nom"),
                		rs.getString("prenom"), 
                		rs.getString("login"), 
                		rs.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeUtilisateur;
    }
    
    public Utilisateur findByLoginAndPassword(String login, String password) {
        String sql = "SELECT * FROM utilisateur WHERE login = ? AND password = ?";
        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	RoleDAO roleDAO = new RoleDAO(connect);
                Role role = roleDAO.find(rs.getString("idRole"));
                return new Utilisateur(
                    rs.getInt("idUtilisateur"),
                    role,
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("login"),
                    rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
