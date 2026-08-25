package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import POJO.Role;

public class RoleDAO {

    private Connection connect;

    public RoleDAO(Connection con) {
        this.connect = con;
    }

    public Role find(String idRole) {
        String sql = "SELECT * FROM role WHERE idRole = ?";
        try (PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setString(1, idRole);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Role(rs.getString("idRole"), rs.getString("libelle"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Role> findAll() {
        ArrayList<Role> listeRole = new ArrayList<>();
        String sql = "SELECT * FROM role";
        try (PreparedStatement ps = connect.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
            	listeRole.add(new Role(rs.getString("idRole"), rs.getString("libelle")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeRole;
    }
}