package view;

import dao.PlatDAO;
import model.Plat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class AfficherDescriptionPlat extends JFrame {
    private JComboBox<String> platComboBox;
    private JTextArea descriptionArea;
    private JLabel typeMenuLabel;
    private JLabel typePlatLabel;
    private JLabel prixLabel;
    private int userId;

    public AfficherDescriptionPlat(int userId) {
        this.userId = userId;
        initializeUI();
        loadPlats();
    }

    private void initializeUI() {
        setTitle("Description des Plats - Gestion Restaurant");
        setSize(600, 450); // Augmenté pour afficher plus d'informations
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
        JLabel titleLabel = new JLabel("DESCRIPTION DES PLATS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Center panel with form
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Sélection du plat
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel platLabel = new JLabel("Sélectionnez un plat:");
        platLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        centerPanel.add(platLabel, gbc);

        gbc.gridx = 1;
        platComboBox = new JComboBox<>();
        platComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        platComboBox.setPreferredSize(new Dimension(250, 30));
        platComboBox.addActionListener(this::updateDescription);
        centerPanel.add(platComboBox, gbc);

        // Type Menu
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel typeMenuTitleLabel = new JLabel("Type Menu:");
        typeMenuTitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        centerPanel.add(typeMenuTitleLabel, gbc);

        gbc.gridx = 1;
        typeMenuLabel = new JLabel("");
        typeMenuLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        centerPanel.add(typeMenuLabel, gbc);

        // Type Plat
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel typePlatTitleLabel = new JLabel("Type Plat:");
        typePlatTitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        centerPanel.add(typePlatTitleLabel, gbc);

        gbc.gridx = 1;
        typePlatLabel = new JLabel("");
        typePlatLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        centerPanel.add(typePlatLabel, gbc);

        // Prix
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel prixTitleLabel = new JLabel("Prix:");
        prixTitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        centerPanel.add(prixTitleLabel, gbc);

        gbc.gridx = 1;
        prixLabel = new JLabel("");
        prixLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        centerPanel.add(prixLabel, gbc);

        // Description
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel descLabel = new JLabel("Description:");
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        centerPanel.add(descLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        descriptionArea = new JTextArea(5, 20);
        descriptionArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        scrollPane.setPreferredSize(new Dimension(350, 150));
        centerPanel.add(scrollPane, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(new Color(245, 245, 245));

        JButton retourButton = createButton("Retour au menu", new Color(70, 130, 180));
        retourButton.addActionListener(e -> {
            new Menu(userId).setVisible(true);
            dispose();
        });

        buttonPanel.add(retourButton);

        // Add components to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void loadPlats() {
        try {
            PlatDAO platDAO = new PlatDAO();
            for (Plat plat : platDAO.getAllPlat()) {
                platComboBox.addItem(plat.getNomPlat());
            }
            
            if (platComboBox.getItemCount() > 0) {
                updateDescription(null); // Afficher les infos du premier plat
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                "Erreur lors du chargement des plats: " + ex.getMessage(),
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateDescription(ActionEvent e) {
        String platName = (String) platComboBox.getSelectedItem();
        if (platName == null) return;

        try {
            PlatDAO platDAO = new PlatDAO();
            Plat plat = platDAO.getPlatByName(platName);
            
            if (plat != null) {
                typeMenuLabel.setText(plat.getTypeMenu());
                typePlatLabel.setText(plat.getTypePlat());
                prixLabel.setText(String.format("%.2f DT", plat.getPrix()));
                descriptionArea.setText(plat.getDescription() != null ? 
                    plat.getDescription() : "Aucune description disponible");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                "Erreur lors de la récupération des informations du plat: " + ex.getMessage(),
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.black);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new AfficherDescriptionPlat(1).setVisible(true);
        });
    }
}
