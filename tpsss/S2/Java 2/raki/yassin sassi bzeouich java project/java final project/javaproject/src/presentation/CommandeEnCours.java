package presentation;

import dao.CommandeDAO;
import metier.Commande;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CommandeEnCours extends JFrame {
    private JTable tableCommandes;

    public CommandeEnCours() {
        setTitle("Toutes les commandes en cours");
        setSize(450, 301);
        setLocationRelativeTo(null);

        // model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Commande");
        model.addColumn("ID Client");
        model.addColumn("etat");

        // charger model
        try {
        	CommandeDAO cdao= new CommandeDAO();
            List<Commande> commandes = cdao.getCommandesByState("en cours"); // Supposons que vous avez une méthode pour récupérer toutes les commandes en cours de la base de données
            for (Commande commande : commandes) {
                model.addRow(new Object[]{commande.getIdCommande(), commande.getIdClient(), commande.getEtat()});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la récupération des commandes en cours : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        // table de model
        tableCommandes = new JTable(model);
        tableCommandes.setPreferredScrollableViewportSize(new Dimension(500, 300));
        tableCommandes.setFillsViewportHeight(true);

        //JScrollPane
        JScrollPane scrollPane = new JScrollPane(tableCommandes);

        getContentPane().add(scrollPane);
        
        };
        
       
        
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CommandeEnCours commandes = new CommandeEnCours();
            commandes.setVisible(true);
        });
    }
}
