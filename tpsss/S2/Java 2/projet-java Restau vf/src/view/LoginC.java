package view;

import javax.swing.*;
import dao.UtilisateurDAO;
import java.awt.*;
import java.sql.SQLException;

public class LoginC extends JFrame {
    private JTextField txtId;
    private JPasswordField txtMdp;

    public LoginC() {
        setTitle("Connexion Client - Gestion de Restaurant");
        setSize(400, 350);
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

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(60, 90, 120));
        JLabel titleLabel = new JLabel("CONNEXION CLIENT", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Center panel with form
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(245, 245, 245));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // ID field
        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        idPanel.setBackground(new Color(245, 245, 245));
        JLabel lblNom = new JLabel("ID : ");
        lblNom.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtId = new JTextField(15);
        txtId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        idPanel.add(lblNom);
        idPanel.add(txtId);

        // Password field
        JPanel pwdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pwdPanel.setBackground(new Color(245, 245, 245));
        JLabel lblMdp = new JLabel("Mot de passe : ");
        lblMdp.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtMdp = new JPasswordField(15);
        txtMdp.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        pwdPanel.add(lblMdp);
        pwdPanel.add(txtMdp);

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // Login button
        JButton btnLogin = new JButton("Se connecter");
        styleButton(btnLogin, new Color(76, 175, 80)); // Green
        btnLogin.setForeground(Color.BLACK);

        // Return button
        JButton btnRetour = new JButton("Retour");
        styleButton(btnRetour, new Color(150, 150, 150)); // Gray
        btnRetour.setForeground(Color.BLACK);

        // Register button
        JButton btnInscription = new JButton("Inscription");
        styleButton(btnInscription, new Color(33, 150, 243)); // Blue
        btnInscription.setForeground(Color.BLACK);

        // Add buttons to panel
        buttonPanel.add(btnRetour);
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnInscription);

        // Add components to center panel
        centerPanel.add(idPanel);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(pwdPanel);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(buttonPanel);

        // Add action listeners
        btnRetour.addActionListener(e -> {
            new Choisir().setVisible(true);
            dispose();
        });

        btnLogin.addActionListener(e -> {
            String idt = txtId.getText();
            if (idt.matches("-?\\d+(\\.\\d+)?")) {
                int id = Integer.parseInt(idt);
                String mdp = new String(txtMdp.getPassword());
                UtilisateurDAO u = new UtilisateurDAO();

                try {
                    if (u.existeUtilisateur(id) == 1) {
                        if (u.verifMdpU(id, mdp)) {
                            if (u.getRole(id, mdp, "client")) {
                                new Menu(id).setVisible(true);
                                dispose();
                            } else {
                                showError("Tu n'es pas un client");
                            }
                        } else {
                            showError("Mot de passe incorrect");
                        }
                    } else {
                        showError("Utilisateur non trouvé, il faut s'inscrire");
                    }
                } catch (SQLException ex) {
                    showError("Erreur lors de la connexion: " + ex.getMessage());
                }
            } else {
                showError("L'ID doit être un nombre entier");
            }
        });

        btnInscription.addActionListener(e -> {
            new Inscription().setVisible(true);
            dispose();
        });

        // Add panels to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        getContentPane().add(mainPanel);
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(bgColor.darker(), 1),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
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

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginC login = new LoginC();
            login.setVisible(true);
        });
    }
}
