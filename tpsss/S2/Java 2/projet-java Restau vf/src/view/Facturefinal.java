package view;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;
import dao.CommandeDAO;
import dao.PlatscommandeDAO;
import model.Platscommande;

public class Facturefinal extends JFrame {
    private int idC;
    private JTable tablePlatsCommandes;
    private JLabel lblPrixTotal;

    public Facturefinal(int idC) {
        this.idC = idC;
        initializeUI();
        loadFactureData();
    }

    private void initializeUI() {
        setTitle("Facture - Gestion Restaurant");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(245, 245, 245));

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180)); // Steel blue
        JLabel titleLabel = new JLabel("FACTURE - COMMANDE #" + idC, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Table model
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        
        model.addColumn("ID Plat");
        model.addColumn("Nom du Plat");
        model.addColumn("Prix Unitaire (DT)");
        model.addColumn("Quantité");
        model.addColumn("Total (DT)");

        // Create table with model
        tablePlatsCommandes = new JTable(model);
        styleTable(tablePlatsCommandes);

        // Scroll pane for table
        JScrollPane scrollPane = new JScrollPane(tablePlatsCommandes);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Bottom panel combining total and button
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        bottomPanel.setBackground(new Color(245, 245, 245));
        
        // Total panel on the left
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        totalPanel.setBackground(new Color(245, 245, 245));
        
        JLabel lblTotal = new JLabel("Total à payer : ");
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 16));
        
        lblPrixTotal = new JLabel("0.00 DT");
        lblPrixTotal.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblPrixTotal.setForeground(new Color(220, 0, 0));
        
        totalPanel.add(lblTotal);
        totalPanel.add(lblPrixTotal);

        // Button panel on the right
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 20));
        buttonPanel.setBackground(new Color(245, 245, 245));

        JButton btnValider = createButton("Valider la Facture", new Color(76, 175, 80));
        btnValider.addActionListener(this::validerFacture);

        // Add components
        buttonPanel.add(btnValider);
        
        bottomPanel.add(totalPanel, BorderLayout.WEST);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private void loadFactureData() {
        DefaultTableModel model = (DefaultTableModel) tablePlatsCommandes.getModel();
        model.setRowCount(0); // Clear existing data
        
        try {
            CommandeDAO cdao = new CommandeDAO();
            int idU = cdao.getIdClientCP(idC);
            PlatscommandeDAO pdao = new PlatscommandeDAO();
            
            double total = 0;
            List<Platscommande> platsCommandes = pdao.getPlatCommande(idU);
            
            for (Platscommande pc : platsCommandes) {
                int idPlat = pc.getIdPlat();
                double prixUnitaire = pdao.getPrixPlat(idPlat);
                int quantite = pc.getQuantite();
                double totalLigne = prixUnitaire * quantite;
                
                model.addRow(new Object[]{
                    idPlat, 
                    pc.getNomPlat(),
                    String.format("%.2f", prixUnitaire),
                    quantite,
                    String.format("%.2f", totalLigne)
                });
                
                total += totalLigne;
            }
            
            // Update total price in DB and UI
            cdao.mettreprix(idC, total);
            lblPrixTotal.setText(String.format("%.2f DT", total));
            
        } catch (SQLException e) {
            showError("Erreur lors de la génération de la facture : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void validerFacture(ActionEvent e) {
        int confirm = JOptionPane.showConfirmDialog(
            this, 
            "Confirmer la validation de cette facture?\nCette action est irréversible.", 
            "Confirmation", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                CommandeDAO cmnd = new CommandeDAO();
                int idU = cmnd.getIdClientCP(idC);
                
                // Update command status
                cmnd.updateCommandeState(idC, "servie");
                
                // Clear ordered dishes
                PlatscommandeDAO pc = new PlatscommandeDAO();
                pc.supprimerPlatsCommande(idU);
                
                JOptionPane.showMessageDialog(
                    this, 
                    "La commande a été marquée comme servie avec succès", 
                    "Succès", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                // Open command list and close this window
                new CommandeRecu().setVisible(true);
                dispose();
                
            } catch (SQLException ex) {
                showError("Erreur lors de la validation : " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    private void styleTable(JTable table) {
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setShowGrid(true);

        // Center align all columns except name
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (i == 1) { // Name column
                table.getColumnModel().getColumn(i).setCellRenderer(leftRenderer);
            } else {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.brighter());
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        
        return button;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
            this,
            "<html><b>" + message + "</b></html>",
            "Erreur",
            JOptionPane.ERROR_MESSAGE
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                new Facturefinal(0).setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
