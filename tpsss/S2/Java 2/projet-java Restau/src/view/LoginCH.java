package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import dao.UtilisateurDAO;

public class LoginCH extends JFrame {
    private JTextField txtId;
    private JPasswordField txtMdp;

    public LoginCH() {
        setTitle("Connexion Chef de Cuisine - Gestion de Restaurant");
        setSize(450, 300);
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

        // Header panel with chef theme
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(120, 60, 60)); // Dark red for chef
        JLabel titleLabel = new JLabel("CONNEXION CHEF DE CUISINE", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Center panel with form
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // ID field
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblId = new JLabel("ID : ");
        lblId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        centerPanel.add(lblId, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtId = new JTextField(15);
        txtId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        centerPanel.add(txtId, gbc);

        // Password field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel lblMdp = new JLabel("Mot de passe : ");
        lblMdp.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        centerPanel.add(lblMdp, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtMdp = new JPasswordField(15);
        txtMdp.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        centerPanel.add(txtMdp, gbc);

        // Button panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(new Color(245, 245, 245));

        // Login button
        JButton btnLogin = new JButton("Se connecter");
        styleButton(btnLogin, new Color(200, 80, 80)); // Red for chef
        btnLogin.setForeground(Color.BLACK);
        btnLogin.addActionListener(this::loginAction);

        // Return button
        JButton btnRetour = new JButton("Retour");
        styleButton(btnRetour, new Color(150, 150, 150));
        btnRetour.setForeground(Color.BLACK);
        btnRetour.addActionListener(e -> {
            new Choisir().setVisible(true);
            dispose();
        });

        buttonPanel.add(btnLogin);
        buttonPanel.add(btnRetour);
        centerPanel.add(buttonPanel, gbc);

        // Add panels to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        getContentPane().add(mainPanel);
    }

    private void loginAction(ActionEvent e) {
        String idText = txtId.getText().trim();
        if (!idText.matches("\\d+")) {
            showError("L'ID doit être un entier");
            return;
        }

        int id = Integer.parseInt(idText);
        String mdp = new String(txtMdp.getPassword());
        UtilisateurDAO u = new UtilisateurDAO();

        try {
            if (u.existeUtilisateur(id) == 1) {
                if (u.verifMdpU(id, mdp)) {
                    if (u.getRole(id, mdp, "chef")) {
                        new Chef().setVisible(true);
                        dispose();
                    } else {
                        showError("Tu n'es pas un chef de cuisine !");
                    }
                } else {
                    showError("Mot de passe incorrect");
                }
            } else {
                showError("Utilisateur non trouvé");
            }
        } catch (SQLException ex) {
            showError("Erreur lors de la connexion: " + ex.getMessage());
        }
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(bgColor.darker(), 1),
            BorderFactory.createEmptyBorder(8, 20, 8, 20)
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
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new LoginCH().setVisible(true);
        });
    }
}
