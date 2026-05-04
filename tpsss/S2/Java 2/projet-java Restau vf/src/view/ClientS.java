package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import dao.PlatDAO;
import dao.PlatscommandeDAO;
import model.Plat;
import model.Platscommande;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ClientS extends JFrame {
    private int id;
    private JTable tablePlats;
    private DefaultTableModel model;
    private JComboBox<String> typePlatFilter;

    public ClientS(int id) {
        this.id = id;
        setTitle("Menu des Plats Salés - Client");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(245, 245, 245));

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(60, 90, 120));
        JLabel titleLabel = new JLabel("PLATS SALÉS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        filterPanel.setBackground(new Color(245, 245, 245));
        JLabel filterLabel = new JLabel("Filtrer par type :");
        filterLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        typePlatFilter = new JComboBox<>(new String[]{
            "Tous",
            "Entrée",
            "Plat secondaire",
            "Plat principal",
            "Dessert",
            "Boisson"
        });
        typePlatFilter.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JButton btnFiltrer = new JButton("Afficher");
        styleButton(btnFiltrer, new Color(33, 150, 243));
        btnFiltrer.addActionListener(e -> loadPlatsData());
        filterPanel.add(filterLabel);
        filterPanel.add(typePlatFilter);
        filterPanel.add(btnFiltrer);

        // Table model
        model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : super.getColumnClass(columnIndex);
            }
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0 || column == 6; // Colonnes Sélection (0) et Quantité (6) éditables
            }
        };
        
        model.addColumn("Sélection");
        model.addColumn("ID");
        model.addColumn("Nom");
        model.addColumn("Prix (DH)");
        model.addColumn("Type Plat");
        model.addColumn("Description");
        model.addColumn("Quantité");

        // Create table with model
        tablePlats = new JTable(model);
        tablePlats.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablePlats.setRowHeight(25);
        tablePlats.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablePlats.setFillsViewportHeight(true);

        loadPlatsData();

        // Configurer l'éditeur pour la colonne Quantité
        tablePlats.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(new JTextField()));

        // Center align all columns except selection and description
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 1; i < tablePlats.getColumnCount(); i++) {
            if (i != 5) { // Ne pas centrer la colonne Description (index 5)
                tablePlats.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }

        // Set checkbox renderer and editor for first column
        tablePlats.getColumnModel().getColumn(0).setCellRenderer(new CheckBoxRenderer());
        tablePlats.getColumnModel().getColumn(0).setCellEditor(new CheckBoxEditor());
        tablePlats.getColumnModel().getColumn(0).setPreferredWidth(80);

        // Ajuster la largeur des colonnes
        tablePlats.getColumnModel().getColumn(5).setPreferredWidth(200); // Description plus large

        // Scroll pane for table
        JScrollPane scrollPane = new JScrollPane(tablePlats);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Buttons
        JButton btnAjouter = new JButton("Ajouter au panier");
        styleButton(btnAjouter, new Color(76, 175, 80)); // Green
        
        JButton btnRetour = new JButton("Retour au menu");
        styleButton(btnRetour, new Color(150, 150, 150)); // Gray

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.add(btnAjouter);
        buttonPanel.add(btnRetour);

        // Add action listeners
        btnRetour.addActionListener(e -> {
            new Menu(id).setVisible(true);
            dispose();
        });

        btnAjouter.addActionListener(e -> ajouterPlatsAuPanier());

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setOpaque(false);
        northPanel.add(headerPanel, BorderLayout.NORTH);
        northPanel.add(filterPanel, BorderLayout.SOUTH);

        // Add components to main panel
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private void loadPlatsData() {
        model.setRowCount(0);

        try {
            PlatDAO pdao = new PlatDAO();
            List<Plat> platsSale = pdao.getPlatByTypeMenu("Menu Salé");
            String selectedType = (String) typePlatFilter.getSelectedItem();

            for (Plat plat : platsSale) {
                if (!"Tous".equals(selectedType) && !selectedType.equalsIgnoreCase(plat.getTypePlat())) {
                    continue;
                }

                model.addRow(new Object[]{
                    false,
                    plat.getIdPlat(),
                    plat.getNomPlat(),
                    plat.getPrix(),
                    plat.getTypePlat(),
                    plat.getDescription(),
                    1
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                "Erreur lors de la récupération des plats : " + e.getMessage(),
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ajouterPlatsAuPanier() {
        try {
            PlatscommandeDAO pdao = new PlatscommandeDAO();
            boolean atLeastOneSelected = false;

            for (int row = 0; row < tablePlats.getRowCount(); row++) {
                boolean isSelected = (boolean) tablePlats.getValueAt(row, 0);

                if (isSelected) {
                    atLeastOneSelected = true;
                    int idPlat = (int) tablePlats.getValueAt(row, 1);
                    String nom = (String) tablePlats.getValueAt(row, 2);
                    String quantiteS = tablePlats.getValueAt(row, 6).toString(); // Index 6 pour la quantité

                    if (!quantiteS.matches("\\d+")) {
                        JOptionPane.showMessageDialog(this, "La quantité doit être un entier positif", 
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int quantite = Integer.parseInt(quantiteS);
                    if (quantite <= 0) {
                        JOptionPane.showMessageDialog(this, "Entrer la Quantité ,SVP!!", 
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Platscommande plat = new Platscommande(idPlat, nom, quantite, id);
                    pdao.ajouterPlatsCommande(plat);
                }
            }

            if (!atLeastOneSelected) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner au moins un plat", 
                    "Information", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this, "Plats ajoutés au panier avec succès", 
                "Succès", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout des plats : " + ex.getMessage(), 
                "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(bgColor.darker(), 1),
            BorderFactory.createEmptyBorder(8, 20, 8, 20)
        ));
        
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

    // CheckBox Renderer
    class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {
        public CheckBoxRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
            setBackground(Color.WHITE);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, 
                boolean isSelected, boolean hasFocus, int row, int column) {
            setSelected((value != null && (boolean) value));
            return this;
        }
    }

    // CheckBox Editor
    class CheckBoxEditor extends DefaultCellEditor {
        public CheckBoxEditor() {
            super(new JCheckBox());
            JCheckBox checkBox = (JCheckBox) getComponent();
            checkBox.setHorizontalAlignment(JLabel.CENTER);
            checkBox.setBackground(Color.WHITE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClientS clientS = new ClientS(0); // 0 pour test
            clientS.setVisible(true);
        });
    }
}
