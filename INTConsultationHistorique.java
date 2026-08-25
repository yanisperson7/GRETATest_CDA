package INTERFACE;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;

import POJO.*;
import BDD.*;

public class INTConsultationHistorique extends JFrame {

	private static final long serialVersionUID = 1L;
	private Utilisateur utilisateurConnecte;
	private JTable tableReservations;
	private JTable tableHistorique;
	private DefaultTableModel modeleReservations;
	private DefaultTableModel modeleHistorique;

	public INTConsultationHistorique(Utilisateur utilisateur) {
		this.utilisateurConnecte = utilisateur;
		boolean estAdmin = utilisateurConnecte.getRole().getIdRole().equals("ADM");
		
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
		
		JLabel lblTitre = new JLabel("Consultation et historique");
		lblTitre.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblTitre.setBounds(327, 77, 334, 50);
		contentPane.add(lblTitre);

		JLabel lblReservations = new JLabel("Réservations");
		lblReservations.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblReservations.setBounds(40, 137, 300, 30);
		contentPane.add(lblReservations);

		modeleReservations = new DefaultTableModel(
				new Object[]{"ID", "Utilisateur", "Matériel", "Date", "Heure début", "Heure fin"}, 0
		);
		tableReservations = new JTable(modeleReservations);
		JScrollPane scrollReservations = new JScrollPane(tableReservations);
		scrollReservations.setBounds(40, 188, 900, 250);
		contentPane.add(scrollReservations);

		JLabel lblHistorique = new JLabel("Historique");
		lblHistorique.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblHistorique.setBounds(40, 448, 300, 30);
		contentPane.add(lblHistorique);

		modeleHistorique = new DefaultTableModel(
				new Object[]{"ID", "Utilisateur", "Matériel", "Date", "Action"}, 0
		);
		tableHistorique = new JTable(modeleHistorique);
		JScrollPane scrollHistorique = new JScrollPane(tableHistorique);
		scrollHistorique.setBounds(40, 488, 900, 250);
		contentPane.add(scrollHistorique);

		chargerDonnees(estAdmin);
	}

	private void chargerDonnees(boolean estAdmin) {
	    modeleReservations.setRowCount(0);
	    modeleHistorique.setRowCount(0);

	    ArrayList<Reservation> reservations;
	    if (estAdmin) {
	        reservations = AccessData.getLesReservation();
	    } else {
	        int idUser = utilisateurConnecte.getIdUtilisateur();
	        reservations = AccessData.getReservationsParUtilisateur(idUser);
	    }

	    for (int i = 0; i < reservations.size(); i++) {
	        Reservation r = reservations.get(i);
	        String nomComplet = r.getUtilisateur().getPrenom() + " " + r.getUtilisateur().getNom();
	        
	        Object[] ligne = new Object[]{
	            r.getIdReservation(),
	            nomComplet,
	            r.getMateriel().getNom(),
	            r.getDateReservation(),
	            r.getHeureDebut(),
	            r.getHeureFin()
	        };
	        modeleReservations.addRow(ligne);
	    }

	    ArrayList<ArchiveReservation> historique;
	    if (estAdmin) {
	        historique = AccessData.getHistoriqueComplet();
	    } else {
	        int idUser = utilisateurConnecte.getIdUtilisateur();
	        historique = AccessData.getHistoriqueUtilisateur(idUser);
	    }

	    for (int j = 0; j < historique.size(); j++) {
	        ArchiveReservation a = historique.get(j);
	        String nomComplet = a.getUtilisateur().getPrenom() + " " + a.getUtilisateur().getNom();
	        
	        Object[] ligne = new Object[]{
	            a.getIdArchive(),
	            nomComplet,
	            a.getMateriel().getNom(),
	            a.getDateReservation(),
	            a.getAction()
	        };
	        modeleHistorique.addRow(ligne);
	    }
	}

}
