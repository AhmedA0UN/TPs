package presentation;

import javax.swing.*;

import dao.UtilisateurDAO;
import metier.Utilisateur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginS extends JFrame {
	
	
    private JTextField txtId;
    private JPasswordField txtMdp;

    public LoginS() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //label
        JLabel lblNom = new JLabel("ID : ");
        txtId = new JTextField(10);

        JLabel lblMdp = new JLabel("Mot de passe : ");
        txtMdp = new JPasswordField(10);
        // buttons
        JButton btnLogin = new JButton("Se connecter");
        JButton btnRetour = new JButton("retour");
        
        btnRetour.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		Choisir choix=new Choisir();
        		choix.setVisible(true);
        		dispose();
        				
        	
        	}
        
        });
        
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                String idt = txtId.getText();
                if(idt.matches("-?\\d+(\\.\\d+)?")) {
                	int id=Integer.parseInt(idt);
                    
                    String mdp = new String(txtMdp.getPassword());
                    UtilisateurDAO u = new UtilisateurDAO();

     
                    try {
                        if (u.existeUtilisateur(id) == 1) {
                        	if(u.verifMdpU(id,mdp)) {
                        		if(u.getRole(id,mdp,"serveur")) {
                        		 Serveur serv = new Serveur();
                                 serv.setVisible(true);
                                 dispose(); // Ferme la fenêtre de login
                        		}
                        		else {JOptionPane.showMessageDialog(LoginS.this, "tu n'es pas un Serveur", "Erreur", JOptionPane.ERROR_MESSAGE);
                                }
                             } else {
                                 JOptionPane.showMessageDialog(LoginS.this, "Mot de passe incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
                             }
                         }
                        else {
                             JOptionPane.showMessageDialog(LoginS.this, "Utilisateur non trouvé ", "Erreur", JOptionPane.ERROR_MESSAGE);
                         }
                        }
                     catch (SQLException ex) {
                         
                        JOptionPane.showMessageDialog(LoginS.this, "Erreur lors de login : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                	
                }else
                	JOptionPane.showMessageDialog(LoginS.this, "Id doit etre entier : " , "Erreur", JOptionPane.ERROR_MESSAGE);
                	return;
                }
                
            
        });


        
        //add buttons + lbl
        panel.add(lblNom);
        panel.add(txtId);
        panel.add(lblMdp);
        panel.add(txtMdp);
        panel.add(btnLogin);    
        panel.add(btnRetour);
        

        getContentPane().add(panel);
    }
    
    
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginS login = new LoginS();
            login.setVisible(true);
        });
    }
}

