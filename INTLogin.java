package INTERFACE;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

import POJO.*;
import BDD.*;

public class INTLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textIdentifiant;
	private JTextField textMdp;
	private JLabel lblErreur;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					INTLogin frame = new INTLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public INTLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1004, 805);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdentifiant = new JLabel("Identifiant :");
		lblIdentifiant.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblIdentifiant.setBounds(409, 217, 128, 57);
		contentPane.add(lblIdentifiant);
		
		JLabel lblTitre = new JLabel("GRETA TEST CDA");
		lblTitre.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblTitre.setBounds(350, 57, 250, 76);
		contentPane.add(lblTitre);
		
		textIdentifiant = new JTextField();
		textIdentifiant.setBounds(362, 275, 205, 33);
		contentPane.add(textIdentifiant);
		textIdentifiant.setColumns(10);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblMotDePasse.setBounds(390, 334, 147, 57);
		contentPane.add(lblMotDePasse);
		
		textMdp = new JTextField();
		textMdp.setColumns(10);
		textMdp.setBounds(362, 401, 205, 33);
		contentPane.add(textMdp);
		
		JButton btnSeconnecter = new JButton("Se connecter");
		btnSeconnecter.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnSeconnecter.setBackground(new Color(240, 240, 240));
		btnSeconnecter.setBounds(374, 532, 205, 48);
		contentPane.add(btnSeconnecter);
		
		lblErreur = new JLabel(" ");
		lblErreur.setForeground(Color.RED);
		lblErreur.setBounds(362, 470, 300, 30);
		contentPane.add(lblErreur);
		
		btnSeconnecter.addActionListener(e -> seConnecter());

	}
	
	private void seConnecter() {
		String login = textIdentifiant.getText().trim();
		String password = textMdp.getText().trim();

		if (login.isEmpty() || password.isEmpty()) {
			lblErreur.setText("Veuillez remplir tous les champs");
			return;
		}

		Utilisateur utilisateur = AccessData.connexion(login, password);

		if (utilisateur == null) {
			lblErreur.setText("Identifiant ou mot de passe incorrect");
			return;
		}

		this.dispose();
		new INTMenu(utilisateur).setVisible(true);
	}
}
