package INTERFACE;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import POJO.*;
import BDD.*;

public class INTGestionMateriels extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Utilisateur utilisateurConnecte;
	private JTable table;
	private DefaultTableModel modeleTable;

	public INTGestionMateriels(Utilisateur utilisateur) {
		this.utilisateurConnecte = utilisateur;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1004, 805);
		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel panelRole = new JPanel();
		panelRole.setBounds(10, 10, 399, 66);
		contentPane.add(panelRole);
		panelRole.setLayout(null);

		JLabel label = new JLabel(
				utilisateurConnecte.getPrenom() + " " + utilisateurConnecte.getNom()
				+ " (" + utilisateurConnecte.getRole().getIdRole() + ")"
		);
		label.setBounds(10, 20, 130, 30);
		panelRole.add(label);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.setBounds(880, 15, 100, 30);
		contentPane.add(btnRetour);
		btnRetour.addActionListener(e -> {
		    new INTMenu(utilisateurConnecte).setVisible(true);
		    this.dispose();
		});

		JLabel lblTitre = new JLabel("Gestion du matériel");
		lblTitre.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblTitre.setBounds(346, 30, 400, 60);
		contentPane.add(lblTitre);

		modeleTable = new DefaultTableModel(new Object[]{"ID", "Nom", "Catégorie", "Quantité", "État"}, 0);
		table = new JTable(modeleTable);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(40, 120, 900, 300);
		contentPane.add(scrollPane);

		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnAjouter.setBounds(80, 450, 150, 40);
		contentPane.add(btnAjouter);

		JButton btnModifier = new JButton("Modifier");
		btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnModifier.setBounds(280, 450, 150, 40);
		contentPane.add(btnModifier);

		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnSupprimer.setBounds(480, 450, 150, 40);
		contentPane.add(btnSupprimer);

		btnAjouter.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String nom = JOptionPane.showInputDialog("Nom :");
		        String categorie = JOptionPane.showInputDialog("Catégorie :");
		        String saisieQuantite = JOptionPane.showInputDialog("Quantité :");
		        int quantite = Integer.parseInt(saisieQuantite);
		        String etat = JOptionPane.showInputDialog("État :");
		        
		        Materiel m = new Materiel(0, nom, categorie, quantite, etat);
		        AccessData.ajouterMateriel(m);
		        chargerTableau();
		    }
		});

		btnModifier.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int ligne = table.getSelectedRow();
		        if (ligne != -1) {
		            int id = (int) modeleTable.getValueAt(ligne, 0);
		            String nom = JOptionPane.showInputDialog("Nom :", modeleTable.getValueAt(ligne, 1));
		            String categorie = JOptionPane.showInputDialog("Catégorie :", modeleTable.getValueAt(ligne, 2));
		            
		            String valeurQuantite = modeleTable.getValueAt(ligne, 3).toString();
		            String saisieQuantite = JOptionPane.showInputDialog("Quantité :", valeurQuantite);
		            int quantite = Integer.parseInt(saisieQuantite);
		            
		            String etat = JOptionPane.showInputDialog("État :", modeleTable.getValueAt(ligne, 4));
		            
		            Materiel m = new Materiel(id, nom, categorie, quantite, etat);
		            AccessData.modifierMateriel(m);
		            chargerTableau();
		        }
		    }
		});

		btnSupprimer.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int ligne = table.getSelectedRow();
		        if (ligne != -1) {
		            int id = (int) modeleTable.getValueAt(ligne, 0);
		            String nom = (String) modeleTable.getValueAt(ligne, 1);
		            String categorie = (String) modeleTable.getValueAt(ligne, 2);
		            int quantite = (int) modeleTable.getValueAt(ligne, 3);
		            String etat = (String) modeleTable.getValueAt(ligne, 4);
		            
		            Materiel m = new Materiel(id, nom, categorie, quantite, etat);
		            AccessData.supprimerMateriel(m);
		            chargerTableau();
		        }
		    }
		});

		chargerTableau();
	}

	private void chargerTableau() {
	    modeleTable.setRowCount(0);
	    ArrayList<Materiel> materiels = AccessData.getLesMateriels();
	    for (int i = 0; i < materiels.size(); i++) {
	        Materiel m = materiels.get(i);
	        Object[] ligne = new Object[]{
	            m.getIdMateriel(), 
	            m.getNom(), 
	            m.getCategorie(), 
	            m.getQuantite(), 
	            m.getEtat()
	        };
	        modeleTable.addRow(ligne);
	    }
	}
}