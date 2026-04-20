package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import metier.Commande;

public class Facture extends JFrame {
    private JTextField txtIdCommande;
    
    public Facture() {
        setTitle("Facture");
        setSize(261, 106);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        //panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        
        //label
        JLabel lblIdCommande = new JLabel("ID de la commande : ");
        txtIdCommande = new JTextField(10);
      
     
        //buttons
        JButton btnCalculer = new JButton("Calculer");
        btnCalculer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(txtIdCommande.getText().matches("-?\\d+(\\.\\d+)?")) {
            	      int id=Integer.parseInt(txtIdCommande.getText());
                      Facturefinal f = new Facturefinal(id);
                      f.setVisible(true);
                      dispose();
            		
            	}
               else {
            	   JOptionPane.showMessageDialog(Facture.this, "Id doit etre entier : " , "Erreur", JOptionPane.ERROR_MESSAGE);
               	return;
            	   
               }
               
            }
        });
        //ajouter panel+lbl+buttons
        panel.add(lblIdCommande);
        panel.add(txtIdCommande);
        panel.add(btnCalculer);
        
        getContentPane().add(panel);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Facture facture = new Facture();
            facture.setVisible(true);
        });
    }
}
