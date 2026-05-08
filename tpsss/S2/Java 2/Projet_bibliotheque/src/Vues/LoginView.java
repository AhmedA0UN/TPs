package Vues;

import Controller.AuthController;
import Models.Adherent;
import Models.Bibliothecaire;
import Models.Personne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginView extends JFrame {

    private final JTextField    usernameField = AppTheme.textField("");
    private final JPasswordField passwordField = AppTheme.passwordField();
    private final JLabel        statusLabel   = new JLabel(" ");
    private final AuthController auth         = new AuthController();

    public LoginView() {
        super("BiblioGest — Connexion");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 550);
        setLocationRelativeTo(null);
        setResizable(false);
        buildUI();
    }

    // ── UI ───────────────────────────────────────────────────────────────────

    private void buildUI() {
        JPanel root = new JPanel(new GridLayout(1, 2));
        root.add(buildLeft());
        root.add(buildRight());
        setContentPane(root);
    }

    /** Gradient brand panel (left half). */
    private JPanel buildLeft() {
        JPanel p = new JPanel(new GridBagLayout()) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setPaint(new GradientPaint(0, 0, AppTheme.PRIMARY, 0, getHeight(), AppTheme.PRIMARY_DARK));
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
            }
        };

        JPanel brand = new JPanel();
        brand.setOpaque(false);
        brand.setLayout(new BoxLayout(brand, BoxLayout.Y_AXIS));

        JLabel icon = centred(new JLabel("📚"));
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 72));

        JLabel title = centred(new JLabel("BiblioGest"));
        title.setFont(new Font("Segoe UI", Font.BOLD, 34));
        title.setForeground(Color.WHITE);

        JLabel sub = centred(new JLabel("Système de Gestion de Bibliothèque"));
        sub.setFont(AppTheme.F_BODY);
        sub.setForeground(new Color(180, 200, 255));

        JSeparator sep = new JSeparator();
        sep.setMaximumSize(new Dimension(220, 1));
        sep.setForeground(new Color(100, 130, 200));

        JLabel desc = new JLabel(
            "<html><center>Gérez vos documents,<br>adhérents et emprunts<br>en toute simplicité.</center></html>",
            SwingConstants.CENTER);
        desc.setFont(AppTheme.F_SMALL);
        desc.setForeground(new Color(160, 185, 240));
        desc.setAlignmentX(Component.CENTER_ALIGNMENT);

        brand.add(icon);
        brand.add(Box.createVerticalStrut(12));
        brand.add(title);
        brand.add(Box.createVerticalStrut(6));
        brand.add(sub);
        brand.add(Box.createVerticalStrut(22));
        brand.add(sep);
        brand.add(Box.createVerticalStrut(22));
        brand.add(desc);

        p.add(brand);
        return p;
    }

    /** Login form panel (right half). */
    private JPanel buildRight() {
        JPanel outer = new JPanel(new GridBagLayout());
        outer.setBackground(AppTheme.BG);

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(AppTheme.BORDER),
            BorderFactory.createEmptyBorder(40, 40, 40, 40)));
        card.setPreferredSize(new Dimension(330, 400));

        JLabel h1 = left(new JLabel("Connexion"));
        h1.setFont(new Font("Segoe UI", Font.BOLD, 22));
        h1.setForeground(AppTheme.PRIMARY);

        JLabel h2 = left(new JLabel("Bienvenue ! Veuillez vous identifier."));
        h2.setFont(AppTheme.F_SMALL);
        h2.setForeground(AppTheme.TEXT_MUTED);

        JLabel userLbl = formLabel("Nom d'utilisateur");
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        usernameField.setAlignmentX(LEFT_ALIGNMENT);

        JLabel passLbl = formLabel("Mot de passe");
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        passwordField.setAlignmentX(LEFT_ALIGNMENT);

        statusLabel.setFont(AppTheme.F_SMALL);
        statusLabel.setForeground(AppTheme.ERROR);
        statusLabel.setAlignmentX(LEFT_ALIGNMENT);

        JButton loginBtn = AppTheme.btnPrimary("Se connecter");
        loginBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        loginBtn.setAlignmentX(LEFT_ALIGNMENT);
        loginBtn.addActionListener(e -> doLogin());

        passwordField.addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) doLogin();
            }
        });

        card.add(h1);
        card.add(Box.createVerticalStrut(4));
        card.add(h2);
        card.add(Box.createVerticalStrut(28));
        card.add(userLbl);
        card.add(Box.createVerticalStrut(6));
        card.add(usernameField);
        card.add(Box.createVerticalStrut(14));
        card.add(passLbl);
        card.add(Box.createVerticalStrut(6));
        card.add(passwordField);
        card.add(Box.createVerticalStrut(6));
        card.add(statusLabel);
        card.add(Box.createVerticalStrut(14));
        card.add(loginBtn);

        outer.add(card);
        return outer;
    }

    // ── Logic ────────────────────────────────────────────────────────────────

    private void doLogin() {
        String user = usernameField.getText().trim();
        String pass = new String(passwordField.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            statusLabel.setText("Veuillez remplir tous les champs.");
            return;
        }

        Personne p = auth.login(user, pass);
        if (p == null) {
            statusLabel.setText("Identifiants incorrects. Réessayez.");
            passwordField.setText("");
            return;
        }

        dispose();
        if (p instanceof Bibliothecaire bib) {
            new BibliothecaireDashboard(bib).setVisible(true);
        } else if (p instanceof Adherent ad) {
            new AdherentDashboard(ad).setVisible(true);
        }
    }

    // ── Helpers ──────────────────────────────────────────────────────────────

    private static JLabel centred(JLabel l) {
        l.setAlignmentX(Component.CENTER_ALIGNMENT);
        return l;
    }
    private static JLabel left(JLabel l) {
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }
    private static JLabel formLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(AppTheme.F_BOLD_SM);
        l.setForeground(AppTheme.TEXT);
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }

    // ── Entry point ──────────────────────────────────────────────────────────

    public static void main(String[] args) {
        AppTheme.applyGlobalLook();
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}
