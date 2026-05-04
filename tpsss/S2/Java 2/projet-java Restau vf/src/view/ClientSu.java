package view;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import dao.PlatDAO;
import dao.PlatscommandeDAO;
import model.Plat;
import model.Platscommande;

public class ClientSu extends JFrame {
    private int id;
    private JTable tablePlats;
    private DefaultTableModel model;
    private JComboBox<String> typePlatFilter;

    public ClientSu(int id) {
        this.id = id;
        initializeUI();
        loadPlatsData();
        setupTable();
        setupButtons();
    }

    private void initializeUI() {
        setTitle("Plats Sucrés - Client");
        setSize(800, 600); // Augmenté la taille pour accommoder plus de colonnes
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 240, 240));
    }

    private JPanel createFilterPanel() {
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        filterPanel.setBackground(new Color(240, 240, 240));

        JLabel filterLabel = new JLabel("Filtrer par type :");
        filterLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        typePlatFilter = new JComboBox<>(new String[]{
            "Tous",
            "Dessert",
            "Boisson"
        });
        typePlatFilter.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JButton btnFiltrer = createButton("Afficher", new Color(33, 150, 243));
        btnFiltrer.addActionListener(e -> loadPlatsData());

        filterPanel.add(filterLabel);
        filterPanel.add(typePlatFilter);
        filterPanel.add(btnFiltrer);
        return filterPanel;
    }

    private void loadPlatsData() {
        model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Boolean.class : super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0 || column == 6; // Seules les colonnes Sélection et Quantité sont éditables
            }
        };

        model.addColumn("Sélection");
        model.addColumn("ID");
        model.addColumn("Nom du Plat");
        model.addColumn("Prix (DH)");
        model.addColumn("Type Plat"); // Nouvelle colonne
        model.addColumn("Description"); // Nouvelle colonne
        model.addColumn("Quantité");

        try {
            PlatDAO pdao = new PlatDAO();
            List<Plat> platsSucres = pdao.getPlatByTypeMenu("Menu Sucré");
            String selectedType = typePlatFilter == null ? "Tous" : (String) typePlatFilter.getSelectedItem();

            for (Plat plat : platsSucres) {
                if (!"Tous".equals(selectedType) && !selectedType.equalsIgnoreCase(plat.getTypePlat())) {
                    continue;
                }

                model.addRow(new Object[]{
                    false, 
                    plat.getIdPlat(), 
                    plat.getNomPlat(), 
                    plat.getPrix(),
                    plat.getTypePlat(), // Ajout du type de plat
                    plat.getDescription(), // Ajout de la description
                    1 // Quantité initiale
                });
            }
        } catch (SQLException e) {
            showError("Erreur lors de la récupération des plats : " + e.getMessage());
        }
    }

    private void setupTable() {
        tablePlats = new JTable(model);
        tablePlats.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablePlats.setRowHeight(30);
        tablePlats.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablePlats.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablePlats.setShowGrid(true);
        tablePlats.setGridColor(Color.BLUE);
        tablePlats.setIntercellSpacing(new Dimension(0, 1));

        // Centrer le contenu des colonnes (sauf description)
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 1; i < tablePlats.getColumnCount(); i++) {
            if (i != 5) { // Ne pas centrer la colonne Description (index 5)
                tablePlats.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }

        // Configurer la colonne de sélection
        TableColumn selectionColumn = tablePlats.getColumnModel().getColumn(0);
        selectionColumn.setPreferredWidth(80);
        selectionColumn.setCellRenderer(new CheckBoxRenderer());
        selectionColumn.setCellEditor(new CheckBoxEditor());

        // Configurer la colonne de quantité (maintenant à l'index 6)
        TableColumn quantityColumn = tablePlats.getColumnModel().getColumn(6);
        quantityColumn.setCellEditor(new QuantityEditor());
        quantityColumn.setPreferredWidth(80);

        // Ajuster la largeur de la colonne Description
        tablePlats.getColumnModel().getColumn(5).setPreferredWidth(200);

        JScrollPane scrollPane = new JScrollPane(tablePlats);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(createFilterPanel(), BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        setContentPane(contentPane);
    }

    private void setupButtons() {
        JButton btnAjouter = createButton("Ajouter au panier", new Color(76, 175, 80));
        JButton btnRetour = createButton("Retour", new Color(244, 67, 54));

        btnRetour.addActionListener(e -> {
            new Menu(id).setVisible(true);
            dispose();
        });

        btnAjouter.addActionListener(e -> ajouterPlatsAuPanier());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        buttonPanel.add(btnAjouter);
        buttonPanel.add(btnRetour);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
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
                    String quantiteStr = tablePlats.getValueAt(row, 6).toString(); // Changé à l'index 6 pour la quantité

                    if (!quantiteStr.matches("\\d+")) {
                        showError("La quantité doit être un nombre entier positif");
                        tablePlats.setRowSelectionInterval(row, row);
                        return;
                    }

                    int quantite = Integer.parseInt(quantiteStr);
                    if (quantite <= 0) {
                        showError("La quantité doit être supérieure à 0");
                        tablePlats.setRowSelectionInterval(row, row);
                        return;
                    }

                    Platscommande plat = new Platscommande(idPlat, nom, quantite, id);
                    pdao.ajouterPlatsCommande(plat);
                }
            }

            if (!atLeastOneSelected) {
                showError("Veuillez sélectionner au moins un plat");
                return;
            }

            JOptionPane.showMessageDialog(this, 
                "Plats ajoutés au panier avec succès", 
                "Succès", 
                JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException ex) {
            showError("Erreur lors de l'ajout des plats : " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(bgColor.darker()),
            BorderFactory.createEmptyBorder(8, 20, 8, 20)
        ));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
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

    // Classes internes pour le rendu et l'édition des cellules
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
            JCheckBox checkBox = (JCheckBox) getComponent();
            checkBox.setHorizontalAlignment(JLabel.CENTER);
            checkBox.setOpaque(true);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            JCheckBox checkBox = (JCheckBox) super.getTableCellEditorComponent(table, value, isSelected, row, column);
            checkBox.setSelected(value != null && (Boolean) value);
            checkBox.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            return checkBox;
        }
    }

    private static class QuantityEditor extends DefaultCellEditor {
        public QuantityEditor() {
            super(new JTextField());
            JTextField textField = (JTextField) getComponent();
            textField.setHorizontalAlignment(JTextField.CENTER);
            textField.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                new ClientSu(0).setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
