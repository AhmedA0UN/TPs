package view;

import javax.swing.*;
import dao.CommandeDAO;
import dao.PlatscommandeDAO;
import model.Commande;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Commander extends JFrame {
    private JTextField txtIdCommande;
    private JTextField txtIdClient;

    public Commander() {
        setTitle("Nouvelle Commande - Gestion Restaurant");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 245, 245));

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel titleLabel = new JLabel("NOUVELLE COMMANDE", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Form panel with GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // ID Commande
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblIdCommande = new JLabel("ID Commande:");
        lblIdCommande.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(lblIdCommande, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        txtIdCommande = new JTextField(15);
        txtIdCommande.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(txtIdCommande, gbc);

        // ID Client
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblIdClient = new JLabel("ID Client:");
        lblIdClient.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(lblIdClient, gbc);

        gbc.gridx = 1;
        txtIdClient = new JTextField(15);
        txtIdClient.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        formPanel.add(txtIdClient, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(245, 245, 245));

        JButton btnAjouter = createButton("Ajouter Commande", new Color(76, 175, 80));
        btnAjouter.addActionListener(this::ajouterCommande);

        buttonPanel.add(btnAjouter);

        // Add components to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void ajouterCommande(ActionEvent e) {
        try {
            // Validation des champs
            if (!txtIdCommande.getText().matches("\\d+")) {
                showError("L'ID commande doit être un nombre entier");
                return;
            }
            
            if (!txtIdClient.getText().matches("\\d+")) {
                showError("L'ID client doit être un nombre entier");
                return;
            }

            int idCommande = Integer.parseInt(txtIdCommande.getText());
            int idClient = Integer.parseInt(txtIdClient.getText());

            // Vérifications
            CommandeDAO cdao = new CommandeDAO();
            PlatscommandeDAO pdao = new PlatscommandeDAO();

            if (cdao.existeCommande(idCommande) == 1) {
                showError("Cet ID commande est déjà utilisé");
                return;
            }

            if (pdao.existeClient(idClient) == 0) {
                showError("Ce client n'a pas de plats commandés");
                return;
            }

            // Création et ajout de la commande avec état et date
            Date dateCommande = new Date();
            Commande commande = new Commande(idCommande, idClient, "en cours", dateCommande);
            cdao.ajouterCommande(commande);

            // Message de confirmation détaillé
            String message = String.format(
                "<html><body style='width: 200px;'>" +
                "<h3>Commande créée avec succès</h3>" +
                "<p><b>N° Commande:</b> %d</p>" +
                "<p><b>Client:</b> %d</p>" +
                "<p><b>État:</b> en cours</p>" +
                "<p><b>Date:</b> %s</p>" +
                "</body></html>",
                idCommande,
                idClient,
                new SimpleDateFormat("dd/MM/yyyy à HH:mm").format(dateCommande)
            );

            JOptionPane.showMessageDialog(
                this,
                message,
                "Confirmation de commande",
                JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon("src/images/success_icon.png") // Optionnel: ajouter une icône
            );

            dispose();
        } catch (SQLException ex) {
            showError("Erreur lors de l'ajout de la commande: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.BLUE);
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
            new Commander().setVisible(true);
        });
    }
}
