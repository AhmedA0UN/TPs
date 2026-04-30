package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Choisir extends JFrame {
    private JComboBox<String> comboBox;

    public Choisir() {
        setTitle("Gestion de Restaurant - Sélection de Rôle");
        setSize(600, 400);
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

        // Header with restaurant title
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        JLabel titleLabel = new JLabel("GESTION DE RESTAURANT", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Center panel with role selection
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Icon
        JLabel iconLabel = new JLabel(new ImageIcon("restaurant-icon.png")); // Replace with your icon path
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(iconLabel, gbc);

        // Label
        JLabel label = new JLabel("Sélectionnez votre rôle :");
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        centerPanel.add(label, gbc);

        // ComboBox with modern styling
        String[] roles = {"Client", "Serveur", "Chef de cuisine"};
        comboBox = new JComboBox<>(roles);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBox.setSelectedIndex(0);
        comboBox.setBackground(Color.WHITE);
        comboBox.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                return this;
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        centerPanel.add(comboBox, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        buttonPanel.setOpaque(false);

        JButton btnChoisir = new JButton("Valider");
        styleButton(btnChoisir, new Color(76, 175, 80)); // Fond vert
        btnChoisir.setForeground(Color.BLACK); // Texte en noir
        btnChoisir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedRole = (String) comboBox.getSelectedItem();
                switch (selectedRole) {
                    case "Client":
                        new LoginC().setVisible(true);
                        break;
                    case "Serveur":
                        new LoginS().setVisible(true);
                        break;
                    case "Chef de cuisine":
                        new LoginCH().setVisible(true);
                        break;
                }
                dispose();
            }
        });

        // Bouton Quitter
        JButton btnQuitter = new JButton("Quitter");
        styleButton(btnQuitter, new Color(244, 67, 54)); // Fond rouge
        btnQuitter.setForeground(Color.BLACK); // Texte en noir
        btnQuitter.addActionListener(e -> System.exit(0));

        buttonPanel.add(btnChoisir);
        buttonPanel.add(btnQuitter);

        // Ajout des composants au panel principal
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
    private void styleButton(JButton button, Color bgColor) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(bgColor.darker(), 2),
            BorderFactory.createEmptyBorder(10, 30, 10, 30)
        ));
        
        // Effet de survol plus prononcé
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
            Choisir choixRole = new Choisir();
            choixRole.setVisible(true);
        });
    }
}
