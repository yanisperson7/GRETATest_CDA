package BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBDD {
	
	public static final String URL = "jdbc:mysql://localhost:3306/bd_testcda";
	public static final String UTILISATEUR = "root";
	public static final String MOT_DE_PASSE = "";

	public static Connection con = null;

    public static Connection getInstance() {
        try {
            con = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void close() {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("problème lors de la fermeture");
        }
   }

}
