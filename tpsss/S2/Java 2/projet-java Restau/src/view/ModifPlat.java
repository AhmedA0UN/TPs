package view;

import dao.PlatDAO;
import model.Plat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class ModifPlat extends JFrame {
    private JTextField txtNomPlat;
    private JComboBox<String> comboTypeMenu;
    private JComboBox<String> comboTypePlat;
    private JTextField txtPrix;
    private JTextArea txtDescription;
    private JButton btnModifier;
    private JButton btnAnnuler;
    private int idPlat;

    public ModifPlat(int idPlat) {
        this.idPlat = idPlat;
        initializeUI();
        loadPlatData();
    }

    private void initializeUI() {
        setTitle("Modification de Plat - Gestion Restaurant");
        setSize(500, 500); // Augmenté la hauteur pour les nouveaux champs
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(245, 245, 245));

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel titleLabel = new JLabel("MODIFICATION DU PLAT", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Form panel with GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nom du Plat
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblNomPlat = new JLabel("Nom du Plat:");
        lblNomPlat.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(lblNomPlat, gbc);

        gbc.gridx = 1;
        txtNomPlat = new JTextField(20);
        txtNomPlat.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(txtNomPlat, gbc);

        // Type Menu (Salé/Sucré)
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblTypeMenu = new JLabel("Type Menu:");
        lblTypeMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(lblTypeMenu, gbc);

        gbc.gridx = 1;
        comboTypeMenu = new JComboBox<>(new String[]{"Menu Salé", "Menu Sucré"});
        comboTypeMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(comboTypeMenu, gbc);

        // Type Plat (Entrée/Plat principal/etc)
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblTypePlat = new JLabel("Type Plat:");
        lblTypePlat.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(lblTypePlat, gbc);

        gbc.gridx = 1;
        comboTypePlat = new JComboBox<>(new String[]{"Entrée", "Plat secondaire", "Plat principal", "Dessert", "Boisson"});
        comboTypePlat.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(comboTypePlat, gbc);

        // Prix
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lblPrix = new JLabel("Prix (€):");
        lblPrix.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(lblPrix, gbc);

        gbc.gridx = 1;
        txtPrix = new JTextField(10);
        txtPrix.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(txtPrix, gbc);

        // Description
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(lblDescription, gbc);

        gbc.gridx = 1;
        gbc.gridheight = 2;
        txtDescription = new JTextArea(3, 20);
        txtDescription.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txtDescription);
        formPanel.add(scrollPane, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        buttonPanel.setBackground(new Color(245, 245, 245));

        btnAnnuler = createButton("Annuler", new Color(220, 80, 80));
        btnAnnuler.addActionListener(e -> dispose());

        btnModifier = createButton("Modifier", new Color(70, 130, 180));
        btnModifier.addActionListener(this::modifierPlat);

        buttonPanel.add(btnAnnuler);
        buttonPanel.add(btnModifier);

        // Add components to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void loadPlatData() {
        try {
            PlatDAO pdo = new PlatDAO();
            Plat plat = pdo.getPlatById(idPlat);
            
            if (plat != null) {
                txtNomPlat.setText(plat.getNomPlat());
                txtPrix.setText(String.valueOf(plat.getPrix()));
                comboTypeMenu.setSelectedItem(plat.getTypeMenu());
                comboTypePlat.setSelectedItem(plat.getTypePlat());
                txtDescription.setText(plat.getDescription());
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Plat introuvable avec l'ID: " + idPlat, 
                    "Erreur", 
                    JOptionPane.ERROR_MESSAGE);
                dispose();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                "Erreur lors du chargement du plat: " + ex.getMessage(), 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            dispose();
        }
    }

    private void modifierPlat(ActionEvent e) {
        try {
            String nom = txtNomPlat.getText().trim();
            String typeMenu = (String) comboTypeMenu.getSelectedItem();
            String typePlat = (String) comboTypePlat.getSelectedItem();
            double prix = Double.parseDouble(txtPrix.getText().trim());
            String description = txtDescription.getText().trim();

            if (nom.isEmpty()) {
                showError("Veuillez saisir un nom pour le plat");
                return;
            }

            Plat plat = new Plat(idPlat, nom, prix, typeMenu, typePlat, description);

            PlatDAO pdo = new PlatDAO();
            pdo.modifierPlat(plat);

            JOptionPane.showMessageDialog(this,
                "Plat modifié avec succès!",
                "Succès",
                JOptionPane.INFORMATION_MESSAGE);

            dispose();
            new Chef().setVisible(true);
        } catch (NumberFormatException ex) {
            showError("Veuillez entrer un prix valide");
        } catch (SQLException ex) {
            showError("Erreur lors de la modification: " + ex.getMessage());
            ex.printStackTrace();
        }
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
            new ModifPlat(1).setVisible(true); // ID de test
        });
    }
}
