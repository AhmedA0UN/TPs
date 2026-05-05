package view;

import dao.CommandeDAO;
import model.Commande;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;
import java.text.SimpleDateFormat;

public class CommandeRecu extends JFrame {
    private JTable tableCommandes;
    private int userId;
    public CommandeRecu() {
        this(0);
    }

    public CommandeRecu(int userId) {
        this.userId = userId;
        setTitle("Commandes Prêtes - Gestion Restaurant");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        initializeUI();
    }

    private void initializeUI() {
        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(245, 245, 245));

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel titleLabel = new JLabel("COMMANDES PRÊTES À SERVIR", SwingConstants.CENTER);
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
        
        model.addColumn("ID Commande");
        model.addColumn("ID Client");
        model.addColumn("État");
        model.addColumn("Date");
        model.addColumn("Montant");

        // Load data
        try {
            CommandeDAO cdao = new CommandeDAO();
            List<Commande> commandes = cdao.getCommandesByState("prête");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            
            for (Commande commande : commandes) {
                model.addRow(new Object[]{
                    commande.getIdCommande(),
                    commande.getIdClient(),
                    commande.getEtat(),
                    dateFormat.format(commande.getDate()),
                    String.format("%.2f €", commande.getPrixC())
                });
            }
        } catch (SQLException e) {
            showError("Erreur lors du chargement des commandes : " + e.getMessage());
        }

        // Create table with model
        tableCommandes = new JTable(model);
        tableCommandes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tableCommandes.setRowHeight(25);
        tableCommandes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tableCommandes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableCommandes.setFillsViewportHeight(true);

        // Center align all columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tableCommandes.getColumnCount(); i++) {
            tableCommandes.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Scroll pane for table
        JScrollPane scrollPane = new JScrollPane(tableCommandes);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(new Color(245, 245, 245));

        JButton btnFacture = createButton("Générer Facture", new Color(76, 175, 80));
        btnFacture.addActionListener(this::genererFacture);

        JButton btnRetour = createButton("Retour", new Color(150, 150, 150));
        btnRetour.addActionListener(e -> {
            new Serveur(userId).setVisible(true);
            dispose();
        });

        buttonPanel.add(btnFacture);
        buttonPanel.add(btnRetour);

        // Add components to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private void genererFacture(ActionEvent e) {
        int selectedRow = tableCommandes.getSelectedRow();
        if (selectedRow == -1) {
            showError("Veuillez sélectionner une commande");
            return;
        }

        int idCommande = (int) tableCommandes.getValueAt(selectedRow, 0);
        Facture facture = new Facture();
        facture.setIdCommande(idCommande); // Utilisation de la nouvelle méthode
        facture.setVisible(true);
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        
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
        
        return button;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, 
            message, 
            "Erreur", 
            JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new CommandeRecu().setVisible(true);
        });
    }
}
