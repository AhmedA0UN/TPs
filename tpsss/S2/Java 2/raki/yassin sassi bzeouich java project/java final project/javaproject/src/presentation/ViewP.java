package presentation;

import dao.CommandeDAO;
import dao.PlatscommandeDAO;
import metier.Commande;
import metier.Platscommande;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ViewP extends JFrame {
    private JTable tableCommandes;

    public ViewP(int idU) {
        setTitle("Plats");
        setSize(450, 301);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nom Plat");
        model.addColumn("Quantite");
       

        // charger model
        try {
        	PlatscommandeDAO pc= new PlatscommandeDAO();
            List<Platscommande> lpc = pc.getPlatCommande(idU); // Supposons que vous avez une méthode pour récupérer toutes les commandes en cours de la base de données
            for (Platscommande p : lpc) {
                model.addRow(new Object[]{p.getNomPlat(),p.getQuantite()});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la récupération des commandes en cours : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        // table de model
        tableCommandes = new JTable(model);
        tableCommandes.setPreferredScrollableViewportSize(new Dimension(500, 300));
        tableCommandes.setFillsViewportHeight(true);

        // JScrollPane
        JScrollPane scrollPane = new JScrollPane(tableCommandes);

        getContentPane().add(scrollPane);
        
        };
        
       
        
    
        
        

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	int idU=0;
            ViewP vp = new ViewP(idU);
            vp.setVisible(true);
        });
    }
}
