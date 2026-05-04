package view;

import dao.PlatscommandeDAO;
import model.Platscommande;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ViewP extends JFrame {
    private JTable tableCommandes;

    public ViewP(int idU) {
        setTitle("Détail des Plats Commandés");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(245, 245, 245));

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180)); // Steel blue
        JLabel titleLabel = new JLabel("PLATS COMMANDÉS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Table model
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        
        model.addColumn("Nom du Plat");
        model.addColumn("Quantité");
        model.addColumn("Prix Unitaire");
        model.addColumn("Total");

        // Load data
        try {
            PlatscommandeDAO pc = new PlatscommandeDAO();
            List<Platscommande> lpc = pc.getPlatCommande(idU);
            for (Platscommande p : lpc) {
                double prixUnitaire = 0.0; // À remplacer par le prix réel depuis la base
                double total = p.getQuantite() * prixUnitaire;
                model.addRow(new Object[]{
                    p.getNomPlat(),
                    p.getQuantite(),
                    String.format("%.2f €", prixUnitaire),
                    String.format("%.2f €", total)
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Erreur lors de la récupération des plats commandés : " + e.getMessage(), 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
        }

        // Create table with model
        tableCommandes = new JTable(model);
        tableCommandes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tableCommandes.setRowHeight(25);
        tableCommandes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tableCommandes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableCommandes.setFillsViewportHeight(true);

        // Center align all columns except the first one
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 1; i < tableCommandes.getColumnCount(); i++) {
            tableCommandes.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Left align for the first column
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        tableCommandes.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);

        // Scroll pane for table
        JScrollPane scrollPane = new JScrollPane(tableCommandes);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Add components to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add total panel
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.setBackground(new Color(245, 245, 245));
        JLabel totalLabel = new JLabel("Total: 00.00 €");
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        totalPanel.add(totalLabel);
        mainPanel.add(totalPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new ViewP(0).setVisible(true); // ID de test
        });
    }
}
