package MAIN;

import java.sql.Connection;
import BDD.ConnexionBDD;
import INTERFACE.INTLogin;

public class main {
    
    public static void main(String[] args) {
        Connection con = ConnexionBDD.getInstance();
        
        INTLogin fenetre = new INTLogin();
        fenetre.setVisible(true);
    }
}