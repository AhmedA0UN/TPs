package view;

import javax.swing.*;
import dao.UtilisateurDAO;
import java.awt.*;
import java.sql.SQLException;

public class Menu extends JFrame {
    private int id;
    private UtilisateurDAO utilisateurDAO;

    public Menu(int id) {
        this.id = id;
        this.utilisateurDAO = new UtilisateurDAO();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Menu Principal - Gestion de Restaurant");
        setSize(500, 430);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 245, 245));

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(60, 90, 120));
        
        try {
            String nomUtilisateur = utilisateurDAO.getNom(id);
            JLabel welcomeLabel = new JLabel("Bienvenue, " + nomUtilisateur + " !", SwingConstants.CENTER);
            welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
            welcomeLabel.setForeground(Color.WHITE);
            headerPanel.add(welcomeLabel);
        } catch (SQLException ex) {
            JLabel welcomeLabel = new JLabel("Bienvenue !", SwingConstants.CENTER);
            welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
            welcomeLabel.setForeground(Color.WHITE);
            headerPanel.add(welcomeLabel);
        }

        // Center panel with menu selection
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Bouton pour le menu salé
        JButton menuSaleButton = new JButton("Menu Salé");
        styleButton(menuSaleButton, new Color(76, 175, 80)); // Green
        menuSaleButton.addActionListener(e -> {
            new ClientS(id).setVisible(true);
            dispose();
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(menuSaleButton, gbc);

        // Bouton pour le menu sucré
        JButton menuSucreButton = new JButton("Menu Sucré");
        styleButton(menuSucreButton, new Color(255, 193, 7)); // Yellow
        menuSucreButton.addActionListener(e -> {
            new ClientSu(id).setVisible(true);
            dispose();
        });
        gbc.gridy = 1;
        centerPanel.add(menuSucreButton, gbc);

        // Bouton pour afficher la description des plats
        JButton descriptionButton = new JButton("Afficher description des plats");
        styleButton(descriptionButton, new Color(33, 150, 243)); // Blue
        descriptionButton.addActionListener(e -> {
            new AfficherDescriptionPlat(id).setVisible(true);
            dispose();
        });
        gbc.gridy = 2;
        centerPanel.add(descriptionButton, gbc);

        // Bouton pour afficher la facture
        JButton factureButton = new JButton("Afficher facture");
        styleButton(factureButton, new Color(156, 39, 176)); // Purple
        factureButton.addActionListener(e -> {
            try {
                new AfficherFacturequi(id).setVisible(true);
                dispose();
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        gbc.gridy = 3;
        centerPanel.add(factureButton, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(new Color(245, 245, 245));

        JButton logoutButton = new JButton("Déconnexion");
        styleButton(logoutButton, new Color(244, 67, 54)); // Red
        logoutButton.addActionListener(e -> logout());

        buttonPanel.add(logoutButton);

        // Add components to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void logout() {
        try {
            String nom = utilisateurDAO.getNom(id);
            JOptionPane.showMessageDialog(this, 
                "Au revoir " + nom + " ! Bon appétit !", 
                "Déconnexion", 
                JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                "Erreur lors de la déconnexion : " + ex.getMessage(), 
                "Erreur", 
                JOptionPane.ERROR_MESSAGE);
        }
        
        new Choisir().setVisible(true);
        dispose();
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button.setPreferredSize(new Dimension(200, 40));
        
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Menu(1).setVisible(true); // ID de test
        });
    }
}
