package INTERFACE;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;

import POJO.*;
import BDD.*;

public class INTFormulaireReservation extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Utilisateur utilisateurConnecte;
	private JComboBox<Materiel> comboMateriel;
	private JSpinner spinnerDate;
	private JSpinner spinnerHeureDebut;
	private JSpinner spinnerHeureFin;
	private JTable table;
	private DefaultTableModel modeleTable;

	public INTFormulaireReservation(Utilisateur utilisateur) {
		this.utilisateurConnecte = utilisateur;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1004, 805);
		JPanel contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel panelRole = new JPanel();
		panelRole.setBounds(10, 10, 268, 66);
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

		JLabel lblTitre = new JLabel("Formulaire des réservations");
		lblTitre.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblTitre.setBounds(300, 20, 500, 50);
		contentPane.add(lblTitre);

		JLabel lblMateriel = new JLabel("Matériel :");
		lblMateriel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblMateriel.setBounds(40, 90, 100, 25);
		contentPane.add(lblMateriel);

		comboMateriel = new JComboBox<>();
		comboMateriel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		comboMateriel.setBounds(150, 90, 250, 25);
		contentPane.add(comboMateriel);
		ArrayList<Materiel> materiels = AccessData.getLesMateriels();
		for (Materiel m : materiels) {
			comboMateriel.addItem(m);
		}

		JLabel lblDate = new JLabel("Date :");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblDate.setBounds(40, 130, 100, 25);
		contentPane.add(lblDate);

		spinnerDate = new JSpinner(new SpinnerDateModel());
		spinnerDate.setFont(new Font("Tahoma", Font.PLAIN, 22));
		spinnerDate.setBounds(150, 130, 150, 25);
		spinnerDate.setEditor(new JSpinner.DateEditor(spinnerDate, "dd/MM/yyyy"));
		contentPane.add(spinnerDate);

		JLabel lblHeureDebut = new JLabel("Heure début :");
		lblHeureDebut.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblHeureDebut.setBounds(40, 170, 150, 25);
		contentPane.add(lblHeureDebut);

		spinnerHeureDebut = new JSpinner(new SpinnerDateModel());
		spinnerHeureDebut.setFont(new Font("Tahoma", Font.PLAIN, 22));
		spinnerHeureDebut.setBounds(210, 170, 100, 25);
		spinnerHeureDebut.setEditor(new JSpinner.DateEditor(spinnerHeureDebut, "HH:mm"));
		contentPane.add(spinnerHeureDebut);

		JLabel lblHeureFin = new JLabel("Heure fin :");
		lblHeureFin.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblHeureFin.setBounds(40, 210, 150, 25);
		contentPane.add(lblHeureFin);

		spinnerHeureFin = new JSpinner(new SpinnerDateModel());
		spinnerHeureFin.setFont(new Font("Tahoma", Font.PLAIN, 22));
		spinnerHeureFin.setBounds(210, 210, 100, 25);
		spinnerHeureFin.setEditor(new JSpinner.DateEditor(spinnerHeureFin, "HH:mm"));
		contentPane.add(spinnerHeureFin);

		JButton btnReserver = new JButton("Réserver");
		btnReserver.setBounds(150, 290, 150, 35);
		contentPane.add(btnReserver);

		JLabel lblListe = new JLabel("Mes réservations");
		lblListe.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblListe.setBounds(40, 350, 300, 30);
		contentPane.add(lblListe);

		modeleTable = new DefaultTableModel(new Object[]{"ID", "Matériel", "Date", "Heure début", "Heure fin"}, 0);
		table = new JTable(modeleTable);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(40, 390, 900, 250);
		contentPane.add(scrollPane);

		JButton btnModifier = new JButton("Modifier");
		btnModifier.setBounds(300, 660, 150, 35);
		contentPane.add(btnModifier);

		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(500, 660, 150, 35);
		contentPane.add(btnSupprimer);

		btnReserver.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Materiel materiel = (Materiel) comboMateriel.getSelectedItem();
		        
		        Date dateSelectionnee = (Date) spinnerDate.getValue();
		        LocalDate date = dateSelectionnee.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		        
		        Date heureDebutSelectionnee = (Date) spinnerHeureDebut.getValue();
		        LocalTime debut = heureDebutSelectionnee.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
		        
		        Date heureFinSelectionnee = (Date) spinnerHeureFin.getValue();
		        LocalTime fin = heureFinSelectionnee.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();

		        Reservation nouvelleReservation = new Reservation(0, utilisateurConnecte, materiel, date, debut, fin);
		        AccessData.creerReservation(nouvelleReservation);
		        
		        chargerTableau();
		    }
		});

		btnModifier.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int ligne = table.getSelectedRow();
		        if (ligne != -1) {
		            int id = (int) modeleTable.getValueAt(ligne, 0);
		            Materiel materiel = (Materiel) comboMateriel.getSelectedItem();
		            
		            Date dateSelectionnee = (Date) spinnerDate.getValue();
		            LocalDate date = dateSelectionnee.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		            
		            Date heureDebutSelectionnee = (Date) spinnerHeureDebut.getValue();
		            LocalTime debut = heureDebutSelectionnee.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
		            
		            Date heureFinSelectionnee = (Date) spinnerHeureFin.getValue();
		            LocalTime fin = heureFinSelectionnee.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();

		            Reservation reservationModifiee = new Reservation(id, utilisateurConnecte, materiel, date, debut, fin);
		            AccessData.modifierReservation(reservationModifiee);
		            
		            chargerTableau();
		        }
		    }
		});

		btnSupprimer.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int ligne = table.getSelectedRow();
		        if (ligne != -1) {
		            int id = (int) modeleTable.getValueAt(ligne, 0);
		            Reservation r = AccessData.getReservation(id);
		            AccessData.archiverReservation(null);
		            
		            chargerTableau();
		        }
		    }
		});

		chargerTableau();
	}

	private void chargerTableau() {
	    modeleTable.setRowCount(0);
	    
	    ArrayList<Reservation> reservations;
	    
	    if (utilisateurConnecte.getRole().getIdRole().equals("ADM")) {
	        reservations = AccessData.getLesReservation();
	    } else {
	        reservations = AccessData.getReservationsParUtilisateur(utilisateurConnecte.getIdUtilisateur());
	    }

	    for (Reservation r : reservations) {
	        Object[] ligne = new Object[]{
	            r.getIdReservation(), 
	            r.getMateriel().getNom(), 
	            r.getDateReservation(), 
	            r.getHeureDebut(),
	            r.getHeureFin()
	        };
	        modeleTable.addRow(ligne);
	    }
	}
}