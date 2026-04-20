package presentation;

import javax.swing.*;

import dao.UtilisateurDAO;
import metier.Utilisateur;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inscription extends JFrame {
    private JTextField txtId;
    private JTextField txtNom;
    private JPasswordField txtMdp;

    public Inscription() {
        setTitle("Inscription");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //panel
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        //label
        
        JLabel lblId = new JLabel("ID : ");
        txtId = new JTextField(10);
        JLabel lblNom = new JLabel("Nom : ");
        txtNom = new JTextField(10);
        JLabel lblMdp = new JLabel("Mot de passe : ");
        txtMdp = new JPasswordField(10);

        //buttons
        JButton btnInscrire = new JButton("S'inscrire");
        btnInscrire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idt = txtId.getText();
                if (idt.matches("-?\\d+(\\.\\d+)?")){
                	 int id = Integer.parseInt(idt);
                     String nom = txtNom.getText();
                     String mdp = new String(txtMdp.getPassword());
                     UtilisateurDAO u = new UtilisateurDAO();

                     // verif existence utilisateur
                     try {
                         if (u.existeUtilisateur(id) == 1) {
                             JOptionPane.showMessageDialog(Inscription.this, "Cet identifiant est déjà utilisé. Veuillez en choisir un autre.", "Erreur", JOptionPane.ERROR_MESSAGE);
                             return;
                         }

                         Utilisateur ut = new Utilisateur(id, nom, mdp, "client");
                         u.ajouterUtilisateur(ut);
                         JOptionPane.showMessageDialog(Inscription.this, "Inscription réussie pour : " + id + "\nNom : " + nom + "\nMot de passe : " + mdp);
                     } catch (SQLException ex) {
                         ex.printStackTrace(); 
                         JOptionPane.showMessageDialog(Inscription.this, "Erreur lors de l'inscription : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                     }
                     }
                     else {
                     	JOptionPane.showMessageDialog(Inscription.this, "Id doit etre entier : " , "Erreur", JOptionPane.ERROR_MESSAGE);
                    	return;
                    }
                	
                }
               
            
        });

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginC login = new LoginC(); // Modification du nom de la classe à instancier
                login.setVisible(true);
                dispose(); // Ferme la fenêtre d'inscription
            }
        });
        // add buttons
        panel.add(lblId);
        panel.add(txtId);
        panel.add(lblNom);
        panel.add(txtNom);
        panel.add(lblMdp);
        panel.add(txtMdp);
        panel.add(btnInscrire);
        panel.add(btnLogin);

        getContentPane().add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Inscription inscription = new Inscription();
            inscription.setVisible(true);
        });
    }
}

