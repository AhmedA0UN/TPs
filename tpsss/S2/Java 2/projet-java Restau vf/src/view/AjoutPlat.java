package view;

import javax.swing.*;
import dao.PlatDAO;
import model.Plat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class AjoutPlat extends JFrame {
    private JTextField txtNomPlat;
    private JComboBox<String> comboTypeMenu;
    private JComboBox<String> comboTypePlat;
    private JTextField txtPrix;
    private JTextArea txtDescription;
    private JButton btnAjouter;
    private JButton btnAnnuler;

    public AjoutPlat() {
        setTitle("Ajouter Plat - Gestion Restaurant");
        setSize(500, 500); // Augmenté la taille pour les nouveaux champs
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(245, 245, 245));

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel titleLabel = new JLabel("AJOUTER UN NOUVEAU PLAT", SwingConstants.CENTER);
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
        JLabel lblPrix = new JLabel("Prix (DH):");
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
        btnAnnuler.addActionListener(e -> {
            new Chef().setVisible(true);
            dispose();
        });

        btnAjouter = createButton("Ajouter", new Color(70, 130, 180));
        btnAjouter.addActionListener(this::ajouterPlat);

        buttonPanel.add(btnAnnuler);
        buttonPanel.add(btnAjouter);

        // Add components to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        
        // Donner le focus au premier champ
        SwingUtilities.invokeLater(() -> txtNomPlat.requestFocusInWindow());
    }

    private void ajouterPlat(ActionEvent e) {
        try {
            PlatDAO pdo = new PlatDAO();
            
            // Validation des champs
            String nom = txtNomPlat.getText().trim();
            if (nom.isEmpty()) {
                showError("Le nom du plat ne peut pas être vide");
                txtNomPlat.requestFocus();
                return;
            }
            
            String typeMenu = (String) comboTypeMenu.getSelectedItem();
            String typePlat = (String) comboTypePlat.getSelectedItem();
            String description = txtDescription.getText().trim();
            
            String prixText = txtPrix.getText().trim();
            if (!PlatDAO.isDouble(prixText)) {
                showError("Le prix doit être un nombre valide");
                txtPrix.requestFocus();
                return;
            }
            
            double prix = Double.parseDouble(prixText);
            if (prix <= 0) {
                showError("Le prix doit être supérieur à 0");
                txtPrix.requestFocus();
                return;
            }
            
            Plat plat = new Plat(0, nom, prix, typeMenu, typePlat, description);
            pdo.ajouterPlat(plat);
            
            // Message de confirmation
            String message = String.format(
                "<html><body style='width: 250px;'>" +
                "<h3>Plat ajouté avec succès</h3>" +
                "<p><b>Nom:</b> %s</p>" +
                "<p><b>Type Menu:</b> %s</p>" +
                "<p><b>Type Plat:</b> %s</p>" +
                "<p><b>Prix:</b> %.2f DH</p>" +
                "</body></html>",
                nom, typeMenu, typePlat, prix
            );

            JOptionPane.showMessageDialog(
                this,
                message,
                "Confirmation d'ajout",
                JOptionPane.INFORMATION_MESSAGE
            );
            
            new Chef().setVisible(true);
            dispose();
        } catch (SQLException ex) {
            showError("Erreur lors de l'ajout du plat: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.DARK_GRAY);
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
            } catch (Exception e) {
                e.printStackTrace();
            }
            new AjoutPlat().setVisible(true);
        });
    }
}
