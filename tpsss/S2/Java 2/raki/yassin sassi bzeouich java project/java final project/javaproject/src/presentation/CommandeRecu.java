package presentation;

import dao.CommandeDAO;
import metier.Commande;
import presentation.Menu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CommandeRecu extends JFrame {
    private JTable tableCommandes;

    public CommandeRecu() {
        setTitle("Toutes les commandes reçues");
        setSize(600, 400);
        setLocationRelativeTo(null);

        // model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Commande");
        model.addColumn("ID Client");
        model.addColumn("etat");

        // charger model
        try {
        	CommandeDAO cdao= new CommandeDAO();
            List<Commande> commandes = cdao.getCommandesByState("prête"); 
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
        JButton btnFacture = new JButton("Facture");
        btnFacture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 Facture f = new Facture();
                 f.setVisible(true);
                 
                
            }
        });
        // boutons 
        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Serveur choix=new Serveur();
        		choix.setVisible(true);
        		dispose();
            }});
        //ajouter buttons
        JPanel buttonPanel = new JPanel();
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(btnFacture);
        buttonPanel.add(btnRetour);
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CommandeRecu commandes = new CommandeRecu();
            commandes.setVisible(true);
        });
    }
}
