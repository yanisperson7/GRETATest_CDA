package INTERFACE;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;

import POJO.*;

public class INTMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private Utilisateur utilisateurConnecte;
	private JPanel contentPane;

	public INTMenu(Utilisateur utilisateur) {
		this.utilisateurConnecte = utilisateur;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1004, 805);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelRole = new JPanel();
		panelRole.setBounds(26, 23, 399, 66);
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
		    new INTLogin().setVisible(true);
		    this.dispose();
		});

		JPanel panel = new JPanel();
		panel.setBounds(294, 193, 367, 308);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnGestionReservation = new JButton("Gestion des réservations");
		btnGestionReservation.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnGestionReservation.setBounds(37, 44, 278, 40);
		panel.add(btnGestionReservation);

		JButton btnGestionDuMatriel = new JButton("Gestion du matériel");
		btnGestionDuMatriel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnGestionDuMatriel.setBounds(37, 129, 278, 40);
		panel.add(btnGestionDuMatriel);

		JButton btnConsultationEtHistorique = new JButton("Consultation et historique");
		btnConsultationEtHistorique.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnConsultationEtHistorique.setBounds(37, 216, 278, 40);
		panel.add(btnConsultationEtHistorique);

		boolean estAdmin = utilisateurConnecte.getRole().getIdRole().equals("ADM");
		btnGestionDuMatriel.setEnabled(estAdmin);
		
		btnGestionReservation.addActionListener(e -> {
		    new INTFormulaireReservation(utilisateurConnecte).setVisible(true);
		});

		btnGestionDuMatriel.addActionListener(e -> {
		    new INTGestionMateriels(utilisateurConnecte).setVisible(true);
		});
		
		btnConsultationEtHistorique.addActionListener(e -> {
		    new INTConsultationHistorique(utilisateurConnecte).setVisible(true);
		});
	}
}
