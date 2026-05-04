package view;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import dao.CommandeDAO;
import dao.PlatDAO;
import model.Commande;
import model.Plat;

public class Chef extends JFrame {
    private JTabbedPane tabbedPane;
    private JTable tablePlats;
    private JTable tableCommandes;

    public Chef() {
        setTitle("Interface Chef - Gestion Restaurant");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        

     // Set modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Main panel with gradient background
        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(53, 92, 125);  // Dark blue
                Color color2 = new Color(108, 91, 123);  // Purple
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel titleLabel = new JLabel("INTERFACE CHEF", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // Menu tab
        JPanel menuPanel = createMenuPanel();
        tabbedPane.addTab("Gestion des Plats", menuPanel);

        // Commandes tab
        JPanel commandesPanel = createCommandesPanel();
        tabbedPane.addTab("Gestion des Commandes", commandesPanel);

        // Add components to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(245, 245, 245));

        // Table model for plats
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        model.addColumn("ID");
        model.addColumn("Nom");
        model.addColumn("Type Menu");
        model.addColumn("Type Plat");
        model.addColumn("Prix (DT)");
        model.addColumn("Description");

        // Load data
        try {
            PlatDAO pdao = new PlatDAO();
            List<Plat> plats = pdao.getAllPlat();
            for (Plat plat : plats) {
                model.addRow(new Object[]{
                    plat.getIdPlat(), 
                    plat.getNomPlat(), 
                    plat.getTypeMenu(),
                    plat.getTypePlat(),
                    plat.getPrix(),
                    plat.getDescription()
                });
            }
        } catch (SQLException e) {
            showError("Erreur lors de la récupération des plats : " + e.getMessage());
        }

        // Create table
        tablePlats = new JTable(model);
        styleTable(tablePlats);

        // Scroll pane
        JScrollPane scrollPane = new JScrollPane(tablePlats);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        buttonPanel.setBackground(new Color(245, 245, 245));

        // Buttons
        JButton btnAjouter = createButton("Ajouter Plat", new Color(76, 175, 80));
        JButton btnModifier = createButton("Modifier Plat", new Color(255, 193, 7));
        JButton btnSupprimer = createButton("Supprimer Plat", new Color(244, 67, 54));
        JButton btnLogout = createButton("Déconnexion", new Color(158, 158, 158));

        // Button actions
        btnAjouter.addActionListener(e -> {
            new AjoutPlat().setVisible(true);
            dispose();
        });

        btnModifier.addActionListener(e -> {
            int selectedRow = tablePlats.getSelectedRow();
            if (selectedRow == -1) {
                showError("Veuillez sélectionner un plat à modifier");
                return;
            }
            int idPlat = (int) tablePlats.getValueAt(selectedRow, 0);
            new ModifPlat(idPlat).setVisible(true);
            dispose();
        });

        btnSupprimer.addActionListener(e -> {
            int selectedRow = tablePlats.getSelectedRow();
            if (selectedRow == -1) {
                showError("Veuillez sélectionner un plat à supprimer");
                return;
            }
            
            int idPlat = (int) tablePlats.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(
                this, 
                "Êtes-vous sûr de vouloir supprimer ce plat?", 
                "Confirmation", 
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    PlatDAO pdao = new PlatDAO();
                    pdao.supprimerPlat(idPlat);
                    new Chef().setVisible(true);
                    dispose();
                } catch (SQLException ex) {
                    showError("Erreur lors de la suppression : " + ex.getMessage());
                }
            }
        });

        btnLogout.addActionListener(e -> {
            new Choisir().setVisible(true);
            dispose();
        });

        // Add buttons
        buttonPanel.add(btnAjouter);
        buttonPanel.add(btnModifier);
        buttonPanel.add(btnSupprimer);
        buttonPanel.add(btnLogout);

        // Add components to panel
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createCommandesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(245, 245, 245));

        // Table model for commandes
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
        };
        
        model.addColumn("Sélection");
        model.addColumn("ID Commande");
        model.addColumn("État");
        model.addColumn("ID Client");
        model.addColumn("Prix (DH)");

        // Load data
        try {
            CommandeDAO cdao = new CommandeDAO();
            List<Commande> commandes = cdao.getAllCommandes();
            for (Commande commande : commandes) {
                model.addRow(new Object[]{
                    false,
                    commande.getIdCommande(),
                    commande.getEtat(),
                    commande.getIdClient(),
                    cdao.getPrix(commande.getIdCommande())
                });
            }
        } catch (SQLException e) {
            showError("Erreur lors de la récupération des commandes : " + e.getMessage());
        }

        // Create table
        tableCommandes = new JTable(model);
        styleTable(tableCommandes);

        // Set checkbox renderer and editor for first column
        tableCommandes.getColumnModel().getColumn(0).setCellRenderer(new CheckBoxRenderer());
        tableCommandes.getColumnModel().getColumn(0).setCellEditor(new CheckBoxEditor());

        // Scroll pane
        JScrollPane scrollPane = new JScrollPane(tableCommandes);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        buttonPanel.setBackground(new Color(245, 245, 245));

        // Buttons
        JButton btnCommencer = createButton("Commencer", new Color(76, 175, 80));
        JButton btnPrete = createButton("Marquer Prête", new Color(33, 150, 243));
        JButton btnSupprimer = createButton("Supprimer", new Color(244, 67, 54));
        JButton btnAfficher = createButton("Afficher Plats", new Color(156, 39, 176));
        JButton btnRefresh = createButton("Actualiser", new Color(158, 158, 158));

        // Button actions
        btnCommencer.addActionListener(e -> updateSelectedCommandes("en cours"));
        btnPrete.addActionListener(e -> updateSelectedCommandes("prête"));
        btnSupprimer.addActionListener(e -> deleteSelectedCommandes());
        btnAfficher.addActionListener(e -> showSelectedPlats());
        btnRefresh.addActionListener(e -> refreshCommandes());

        // Add buttons
        buttonPanel.add(btnCommencer);
        buttonPanel.add(btnPrete);
        buttonPanel.add(btnSupprimer);
        buttonPanel.add(btnAfficher);
        buttonPanel.add(btnRefresh);

        // Add components to panel
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void styleTable(JTable table) {
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setShowGrid(true);
      
        // Center align all columns except name and description
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (i == 1 || (table == tablePlats && i == 5)) { // Name and Description columns
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

    private void updateSelectedCommandes(String newState) {
        boolean atLeastOneSelected = false;
        for (int row = 0; row < tableCommandes.getRowCount(); row++) {
            boolean selected = (Boolean) tableCommandes.getValueAt(row, 0);
            if (selected) {
                atLeastOneSelected = true;
                int idCommande = (Integer) tableCommandes.getValueAt(row, 1);
                try {
                    CommandeDAO cdao = new CommandeDAO();
                    cdao.updateCommandeState(idCommande, newState);
                } catch (SQLException ex) {
                    showError("Erreur lors de la mise à jour : " + ex.getMessage());
                }
            }
        }
        
        if (!atLeastOneSelected) {
            showError("Veuillez sélectionner au moins une commande");
            return;
        }
        
        refreshCommandes();
        JOptionPane.showMessageDialog(this, 
            "État des commandes mis à jour avec succès", 
            "Succès", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void deleteSelectedCommandes() {
        boolean atLeastOneSelected = false;
        for (int row = 0; row < tableCommandes.getRowCount(); row++) {
            boolean selected = (Boolean) tableCommandes.getValueAt(row, 0);
            if (selected) {
                atLeastOneSelected = true;
                int idCommande = (Integer) tableCommandes.getValueAt(row, 1);
                int confirm = JOptionPane.showConfirmDialog(
                    this, 
                    "Êtes-vous sûr de vouloir supprimer la commande #" + idCommande + "?", 
                    "Confirmation", 
                    JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        CommandeDAO cdao = new CommandeDAO();
                        cdao.annulerCommande(idCommande);
                    } catch (SQLException ex) {
                        showError("Erreur lors de la suppression : " + ex.getMessage());
                    }
                }
            }
        }
        
        if (!atLeastOneSelected) {
            showError("Veuillez sélectionner au moins une commande");
            return;
        }
        
        refreshCommandes();
    }

    private void showSelectedPlats() {
        boolean atLeastOneSelected = false;
        for (int row = 0; row < tableCommandes.getRowCount(); row++) {
            boolean selected = (Boolean) tableCommandes.getValueAt(row, 0);
            if (selected) {
                atLeastOneSelected = true;
                String etat = (String) tableCommandes.getValueAt(row, 2);
                if (!"en cours".equals(etat)) {
                    showError("Seules les commandes 'en cours' peuvent être affichées");
                    return;
                }
                int idClient = (Integer) tableCommandes.getValueAt(row, 3);
                new ViewP(idClient).setVisible(true);
                break; // Only show first selected
            }
        }
        
        if (!atLeastOneSelected) {
            showError("Veuillez sélectionner au moins une commande");
        }
    }

    private void refreshCommandes() {
        DefaultTableModel model = (DefaultTableModel) tableCommandes.getModel();
        model.setRowCount(0); // Clear existing data
        
        try {
            CommandeDAO cdao = new CommandeDAO();
            List<Commande> commandes = cdao.getAllCommandes();
            for (Commande commande : commandes) {
                model.addRow(new Object[]{
                    false,
                    commande.getIdCommande(),
                    commande.getEtat(),
                    commande.getIdClient(),
                    cdao.getPrix(commande.getIdCommande())
                });
            }
        } catch (SQLException e) {
            showError("Erreur lors de l'actualisation : " + e.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
            this,
            "<html><b>" + message + "</b></html>",
            "Erreur",
            JOptionPane.ERROR_MESSAGE
        );
    }

    // Checkbox renderer and editor for table
    private static class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {
        public CheckBoxRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setSelected(value != null && (Boolean) value);
            setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            return this;
        }
    }

    private static class CheckBoxEditor extends DefaultCellEditor {
        public CheckBoxEditor() {
            super(new JCheckBox());
            ((JCheckBox) getComponent()).setHorizontalAlignment(JLabel.CENTER);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                new Chef().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
