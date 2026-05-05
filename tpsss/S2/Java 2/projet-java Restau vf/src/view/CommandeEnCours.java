package view;

import dao.CommandeDAO;
import model.Commande;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CommandeEnCours extends JFrame {
    private JTable tableCommandes;
    private JButton btnRefresh;
    private JButton btnValider;
    private JButton btnRetour;
    private int userId;
    public CommandeEnCours() {
        this(0);
    }

    public CommandeEnCours(int userId) {
        this.userId = userId;
        setTitle("Commandes en Cours - Gestion Restaurant");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        initializeUI();
        loadCommandes();
    }

    private void initializeUI() {
        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(245, 245, 245));

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel titleLabel = new JLabel("COMMANDES EN COURS", SwingConstants.CENTER);
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

        btnValider = createButton("Marquer comme prête", new Color(76, 175, 80));
        btnValider.addActionListener(e -> validerCommande());

        btnRefresh = createButton("Actualiser", new Color(33, 150, 243));
        btnRefresh.addActionListener(e -> loadCommandes());

        btnRetour = createButton("Retour", new Color(150, 150, 150));
        btnRetour.addActionListener(e -> {
            new Serveur(userId).setVisible(true);
            dispose();
        });

        buttonPanel.add(btnValider);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnRetour);

        // Add components to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private void loadCommandes() {
        DefaultTableModel model = (DefaultTableModel) tableCommandes.getModel();
        model.setRowCount(0); // Clear existing data
        
        try {
            CommandeDAO cdao = new CommandeDAO();
            List<Commande> commandes = cdao.getCommandesByState("en cours");
            for (Commande commande : commandes) {
                model.addRow(new Object[]{
                    commande.getIdCommande(), 
                    commande.getIdClient(), 
                    commande.getEtat(),
                    new SimpleDateFormat("dd/MM/yyyy HH:mm").format(commande.getDate())
                });
            }
        } catch (SQLException e) {
            showError("Erreur lors du chargement des commandes : " + e.getMessage());
        }
    }

    private void validerCommande() {
        int selectedRow = tableCommandes.getSelectedRow();
        if (selectedRow == -1) {
            showError("Veuillez sélectionner une commande à valider");
            return;
        }

        try {
            int idCommande = (int) tableCommandes.getValueAt(selectedRow, 0);
            CommandeDAO cdao = new CommandeDAO();
            cdao.updateCommandeState(idCommande, "prête");
            loadCommandes();
            JOptionPane.showMessageDialog(this,
                "La commande " + idCommande + " a été marquée comme prête.",
                "Succès",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                "Erreur lors de la mise à jour: " + ex.getMessage(), 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.blue);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        
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
            new CommandeEnCours().setVisible(true);
        });
    }
}
