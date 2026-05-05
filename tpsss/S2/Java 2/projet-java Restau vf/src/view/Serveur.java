package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import dao.PlatscommandeDAO;
import model.Platscommande;

public class Serveur extends JFrame {
    private JTable tablePlatsCommandes;
    private int userId;
    public Serveur() {
        this(0);
    }

    public Serveur(int userId) {
        this.userId = userId;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Interface Serveur - Gestion de Restaurant");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(245, 245, 245));

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180)); // Steel blue
        JLabel titleLabel = new JLabel("COMMANDES CLIENTS", SwingConstants.CENTER);
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
        model.addColumn("ID Client");
        model.addColumn("Nom Plat");
        model.addColumn("Quantité");
        model.addColumn("Statut");

        // Load data
        try {
            PlatscommandeDAO platsCommandeDAO = new PlatscommandeDAO();
            List<Platscommande> platsCommandes = platsCommandeDAO.getPlatCommande();
            for (Platscommande pc : platsCommandes) {
                model.addRow(new Object[]{
                    pc.getIdPlat(), 
                    pc.getIdU(),
                    pc.getNomPlat(),
                    pc.getQuantite(),
                    "Nouvelle" // Default status
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Erreur lors de la récupération des commandes : " + e.getMessage(), 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
        }

        // Create table with model
        tablePlatsCommandes = new JTable(model);
        tablePlatsCommandes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablePlatsCommandes.setRowHeight(25);
        tablePlatsCommandes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablePlatsCommandes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablePlatsCommandes.setFillsViewportHeight(true);

        // Center align all columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tablePlatsCommandes.getColumnCount(); i++) {
            tablePlatsCommandes.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Scroll pane for table
        JScrollPane scrollPane = new JScrollPane(tablePlatsCommandes);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        // Buttons
        JButton btnAjouterCommande = createButton("Nouvelle Commande", new Color(76, 175, 80));
        JButton btnCEncours = createButton("Commandes en Cours", new Color(255, 193, 7));
        JButton btnCP = createButton("Commandes Prêtes", new Color(33, 150, 243));
        JButton btnRefresh = createButton("Actualiser", new Color(158, 158, 158));
        JButton btnMenu = createButton("Menu", new Color(156, 39, 176));
        JButton btnLogout = createButton("Déconnexion", new Color(244, 67, 54));

        // Button actions
        btnAjouterCommande.addActionListener(e -> new Commander().setVisible(true));
        btnCEncours.addActionListener(e -> new CommandeEnCours(userId).setVisible(true));
        btnCP.addActionListener(e -> {
            new CommandeRecu(userId).setVisible(true);
            dispose();
        });
        btnRefresh.addActionListener(e -> refreshTable());
        btnMenu.addActionListener(e -> new MenuAll().setVisible(true));
        btnLogout.addActionListener(e -> {
            new Choisir().setVisible(true);
            dispose();
        });

        // Add buttons to panel with spacing
        buttonPanel.add(btnAjouterCommande);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(btnCEncours);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(btnCP);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(btnRefresh);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(btnMenu);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(btnLogout);

        // Add components to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBackground(color);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.brighter());
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        
        return button;
    }

    private void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) tablePlatsCommandes.getModel();
        model.setRowCount(0); // Clear existing data
        
        try {
            PlatscommandeDAO platsCommandeDAO = new PlatscommandeDAO();
            List<Platscommande> platsCommandes = platsCommandeDAO.getPlatCommande();
            for (Platscommande pc : platsCommandes) {
                model.addRow(new Object[]{
                    pc.getIdPlat(), 
                    pc.getIdU(),
                    pc.getNomPlat(),
                    pc.getQuantite(),
                    "Nouvelle"
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Erreur lors de l'actualisation : " + e.getMessage(), 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Serveur().setVisible(true);
        });
    }
}
