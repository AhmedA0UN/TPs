package view;

import controller.PlatController;
import model.Plat;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class MenuAll extends JFrame {
    private JTable tablePlats;
    private DefaultTableModel model;
    private JButton refreshButton;
    private JButton closeButton;

    public MenuAll() {
        setTitle("Gestion des Plats - Menu Complet");
        setSize(1000, 700); // Augmenté la taille pour accommoder plus de colonnes
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Création du panneau principal avec BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(245, 245, 245));
        
        // Header avec titre
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(new Color(70, 130, 180));
        
        JLabel titleLabel = new JLabel("LISTE COMPLÈTE DES PLATS");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        
        // Modèle de table
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Rendre la table non éditable
            }
        };
        
        model.addColumn("ID");
        model.addColumn("Nom du Plat");
        model.addColumn("Type Menu"); // Renommé de "Type" à "Type Menu"
        model.addColumn("Type Plat"); // Nouvelle colonne
        model.addColumn("Prix (DH)"); // Changé l'unité monétaire
        model.addColumn("Description"); // Nouvelle colonne
        
        // Charger les données
        loadPlatsData();
        
        // Création de la table avec le modèle
        tablePlats = new JTable(model);
        tablePlats.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablePlats.setRowHeight(30);
        tablePlats.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablePlats.setAutoCreateRowSorter(true);
        
        // Style de l'en-tête de la table
        JTableHeader header = tablePlats.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(new Color(70, 130, 180));
        header.setForeground(Color.WHITE);
        
        // Centrer le contenu des colonnes (sauf description)
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tablePlats.getColumnCount(); i++) {
            if (i != 5) { // Ne pas centrer la colonne Description (index 5)
                tablePlats.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }
        
        // Ajuster la largeur des colonnes
        tablePlats.getColumnModel().getColumn(5).setPreferredWidth(250); // Description plus large
        
        // ScrollPane pour la table
        JScrollPane scrollPane = new JScrollPane(tablePlats);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        // Panneau de boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(245, 245, 245));
        
        refreshButton = new JButton("Actualiser");
        styleButton(refreshButton, new Color(70, 130, 180));
        refreshButton.addActionListener(e -> loadPlatsData());
        
        closeButton = new JButton("Fermer");
        styleButton(closeButton, new Color(220, 80, 80));
        closeButton.addActionListener(e -> dispose());
        
        buttonPanel.add(refreshButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(closeButton);
        
        // Assemblage des composants
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Ajout du panneau principal à la JFrame
        add(mainPanel);
    }
    
    private void loadPlatsData() {
        // Vider le modèle actuel
        model.setRowCount(0);
        
        try {
            PlatController pctrl = new PlatController();
            List<Plat> plats = pctrl.getAllPlats();
            
            if (plats.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Aucun plat disponible dans la base de données.", 
                    "Information", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                for (Plat plat : plats) {
                    model.addRow(new Object[]{
                        plat.getIdPlat(), 
                        plat.getNomPlat(), 
                        plat.getTypeMenu(), // Changé de getType() à getTypeMenu()
                        plat.getTypePlat(), // Nouvelle colonne
                        String.format("%.2f", plat.getPrix()),
                        plat.getDescription() // Nouvelle colonne
                    });
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Erreur lors de la récupération des plats : " + e.getMessage(), 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void styleButton(JButton button, Color bgColor) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(bgColor.darker(), 1),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        
        // Effet hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuAll menuAll = new MenuAll();
            menuAll.setVisible(true);
        });
    }
}
