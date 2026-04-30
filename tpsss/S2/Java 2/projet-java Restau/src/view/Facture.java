package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Facture extends JFrame {
    private JTextField txtIdCommande;
    private int idCommande;
    
    public Facture() {
        setTitle("Génération de Facture - Gestion Restaurant");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 245, 245));

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel titleLabel = new JLabel("GÉNÉRATION DE FACTURE", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // ID Commande
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblIdCommande = new JLabel("Numéro de commande :");
        lblIdCommande.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(lblIdCommande, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtIdCommande = new JTextField();
        txtIdCommande.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtIdCommande.setPreferredSize(new Dimension(150, 30));
        formPanel.add(txtIdCommande, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(245, 245, 245));

        JButton btnCalculer = createButton("Générer Facture", new Color(76, 175, 80));
        btnCalculer.addActionListener(this::genererFacture);

        buttonPanel.add(btnCalculer);

        // Add components to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        this.idCommande = -1;
        
        // Donner le focus au champ de texte
        SwingUtilities.invokeLater(() -> txtIdCommande.requestFocusInWindow());
    }
    
    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
        if (idCommande != -1) {
            txtIdCommande.setText(String.valueOf(idCommande));
            txtIdCommande.setEditable(false);
            txtIdCommande.setBackground(new Color(240, 240, 240)); // Gris clair pour indiquer qu'il est désactivé
        } else {
            txtIdCommande.setEditable(true);
            txtIdCommande.setBackground(Color.WHITE);
            txtIdCommande.setText("");
            txtIdCommande.requestFocus();
        }
    }

    private void genererFacture(ActionEvent e) {
        try {
            if (idCommande != -1) {  // Si un ID a été défini
                Facturefinal f = new Facturefinal(idCommande);
                f.setVisible(true);
                dispose();
            } else {
                // Vérification de la saisie
                String text = txtIdCommande.getText().trim();
                if (text.isEmpty()) {
                    showError("Veuillez entrer un numéro de commande");
                    txtIdCommande.requestFocus();
                    return;
                }
                
                if (!text.matches("\\d+")) {
                    showError("Le numéro de commande doit être un nombre entier");
                    txtIdCommande.requestFocus();
                    return;
                }
                
                int id = Integer.parseInt(text);
                Facturefinal f = new Facturefinal(id);
                f.setVisible(true);
                dispose();
            }
        } catch (Exception ex) {
            showError("Erreur lors de la génération de la facture: " + ex.getMessage());
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
            new Facture().setVisible(true);
        });
    }
}
