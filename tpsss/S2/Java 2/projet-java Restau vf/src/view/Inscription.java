package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import dao.UtilisateurDAO;
import model.Utilisateur;

public class Inscription extends JFrame {

    private JTextField txtId;
    private JTextField txtNom;
    private JPasswordField txtMdp;

    public Inscription() {
        setTitle("Inscription Client");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Police moderne
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(new Color(245, 245, 245));

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        
        JLabel titleLabel = new JLabel("INSCRIPTION CLIENT");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Panel formulaire
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Champ ID
        JLabel lblId = new JLabel("ID :");
        lblId.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblId, gbc);

        txtId = new JTextField(15);
        txtId.setFont(fieldFont);
        txtId.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(txtId, gbc);

        // Champ Nom
        JLabel lblNom = new JLabel("Nom :");
        lblNom.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblNom, gbc);

        txtNom = new JTextField(15);
        txtNom.setFont(fieldFont);
        txtNom.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(txtNom, gbc);

        // Champ Mot de passe
        JLabel lblMdp = new JLabel("Mot de passe :");
        lblMdp.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lblMdp, gbc);

        txtMdp = new JPasswordField(15);
        txtMdp.setFont(fieldFont);
        txtMdp.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(txtMdp, gbc);

        // Panel boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(new Color(245, 245, 245));

        // Bouton Inscription
        JButton btnInscrire = new JButton("S'inscrire");
        btnInscrire.setFont(buttonFont);
        btnInscrire.setBackground(new Color(70, 130, 180));
        btnInscrire.setForeground(Color.GREEN);
        btnInscrire.setFocusPainted(false);
        btnInscrire.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        btnInscrire.addActionListener(this::handleInscription);

        // Bouton Retour
        JButton btnRetour = new JButton("Retour");
        btnRetour.setFont(buttonFont);
        btnRetour.setBackground(new Color(120, 120, 120));
        btnRetour.setForeground(Color.RED);
        btnRetour.setFocusPainted(false);
        btnRetour.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        btnRetour.addActionListener(e -> dispose());

        // Effets hover
        addHoverEffect(btnInscrire, new Color(90, 150, 200), new Color(70, 130, 180));
        addHoverEffect(btnRetour, new Color(150, 150, 150), new Color(120, 120, 120));

        buttonPanel.add(btnInscrire);
        buttonPanel.add(btnRetour);

        // Assemblage
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void handleInscription(ActionEvent e) {
        String idText = txtId.getText().trim();
        if (!idText.matches("\\d+")) {
            showError("L'ID doit être un entier.");
            return;
        }

        int id = Integer.parseInt(idText);
        String nom = txtNom.getText().trim();
        String mdp = new String(txtMdp.getPassword()).trim();

        if (nom.isEmpty() || mdp.isEmpty()) {
            showError("Tous les champs doivent être remplis.");
            return;
        }

        try {
            UtilisateurDAO udao = new UtilisateurDAO();
            
            if (udao.existeUtilisateur(id) == 1) {
                showError("ID déjà utilisé. Veuillez en choisir un autre.");
                return;
            }

            Utilisateur user = new Utilisateur(id, nom, mdp, "client");
            udao.ajouterUtilisateur(user);

            JOptionPane.showMessageDialog(this, 
                "Inscription réussie ! Bienvenue " + nom + ".",
                "Succès",
                JOptionPane.INFORMATION_MESSAGE);
                
            // Redirection vers le menu principal
            new Menu(id).setVisible(true);
            dispose();

        } catch (SQLException ex) {
            showError("Erreur de base de données : " + ex.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, 
            message,
            "Erreur",
            JOptionPane.ERROR_MESSAGE);
    }

    private void addHoverEffect(JButton button, Color hoverColor, Color normalColor) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(normalColor);
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
            new Inscription().setVisible(true);
        });
    }
}
