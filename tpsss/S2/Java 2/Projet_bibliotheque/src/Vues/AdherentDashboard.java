package Vues;

import Controller.DocumentController;
import Controller.EmpruntController;
import Models.Adherent;
import Models.Document;
import Models.Emprunt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdherentDashboard extends JFrame {

    private final Adherent           user;
    private final DocumentController docCtrl = new DocumentController();
    private final EmpruntController  empCtrl = new EmpruntController();

    // Catalog tab
    private DefaultTableModel catModel;
    private JTextField        catSearch;

    // My loans tab
    private DefaultTableModel loanModel;

    // ── Constructor ──────────────────────────────────────────────────────────

    public AdherentDashboard(Adherent user) {
        super("BiblioGest — " + user.getPrenom() + " " + user.getNom());
        this.user = user;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1050, 670);
        setLocationRelativeTo(null);
        buildUI();
    }

    // ── Shell ────────────────────────────────────────────────────────────────

    private void buildUI() {
        JButton logoutBtn = AppTheme.btnDanger("Déconnexion");
        logoutBtn.setPreferredSize(new Dimension(120, 32));
        logoutBtn.addActionListener(e -> logout());

        JPanel nav = AppTheme.navBar(
            user.getPrenom() + " " + user.getNom() + "  |  Adhérent", logoutBtn);

        JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
        tabs.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabs.addTab("  📚  Catalogue  ",  buildCatalogTab());
        tabs.addTab("  📋  Mes Emprunts  ", buildLoansTab());

        setLayout(new BorderLayout());
        add(nav,  BorderLayout.NORTH);
        add(tabs, BorderLayout.CENTER);
    }

    // ════════════════════════════════════════════════════════════════════════
    // TAB 1 — Catalogue
    // ════════════════════════════════════════════════════════════════════════

    private JPanel buildCatalogTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(AppTheme.BG);
        panel.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));

        // welcome + search bar
        JPanel top = new JPanel(new BorderLayout(8, 0));
        top.setOpaque(false);

        JLabel welcome = new JLabel("Bienvenue, " + user.getPrenom() + " !  Parcourez notre catalogue.");
        welcome.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        welcome.setForeground(AppTheme.TEXT_MUTED);

        catSearch = new JTextField();
        catSearch.setFont(AppTheme.F_BODY);
        catSearch.setPreferredSize(new Dimension(260, 36));
        catSearch.setToolTipText("Rechercher par titre, auteur ou type…");
        catSearch.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(AppTheme.BORDER),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)));

        JButton btnSearch = AppTheme.btnPrimary("🔍 Rechercher");
        JButton btnAll    = AppTheme.btnWarning("Tout afficher");
        btnSearch.setPreferredSize(new Dimension(130, 36));
        btnAll   .setPreferredSize(new Dimension(130, 36));

        JPanel searchBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        searchBar.setOpaque(false);
        searchBar.add(catSearch);
        searchBar.add(btnSearch);
        searchBar.add(btnAll);

        top.add(welcome,   BorderLayout.WEST);
        top.add(searchBar, BorderLayout.EAST);

        // table
        String[] cols = {"ID", "Titre", "Auteur", "Description", "Disponible", "Type"};
        catModel = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
            @Override public Class<?> getColumnClass(int c) {
                return c == 4 ? Boolean.class : Object.class;
            }
        };
        JTable catTable = new JTable(catModel);
        AppTheme.styleTable(catTable);
        catTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        catTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        catTable.getColumnModel().getColumn(2).setPreferredWidth(130);
        catTable.getColumnModel().getColumn(3).setPreferredWidth(250);
        catTable.getColumnModel().getColumn(4).setPreferredWidth(110);
        catTable.getColumnModel().getColumn(5).setPreferredWidth(100);

        panel.add(top, BorderLayout.NORTH);
        panel.add(AppTheme.scrollPane(catTable), BorderLayout.CENTER);

        loadCatalog();

        btnSearch.addActionListener(e -> {
            String k = catSearch.getText().trim();
            if (!k.isEmpty()) fillCatalog(docCtrl.search(k)); else loadCatalog();
        });
        btnAll.addActionListener(e -> { catSearch.setText(""); loadCatalog(); });

        return panel;
    }

    private void loadCatalog()    { fillCatalog(docCtrl.getAll()); }

    private void fillCatalog(java.util.List<Document> docs) {
        catModel.setRowCount(0);
        for (Document d : docs)
            catModel.addRow(new Object[]{d.getId(), d.getTitre(), d.getAuteur(), d.getDesc(), d.getDis(), d.getType()});
    }

    // ════════════════════════════════════════════════════════════════════════
    // TAB 2 — Mes Emprunts
    // ════════════════════════════════════════════════════════════════════════

    private JPanel buildLoansTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(AppTheme.BG);
        panel.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));

        // stats banner
        JPanel banner = buildStatsBanner();
        panel.add(banner, BorderLayout.NORTH);

        // table
        String[] cols = {"ID Emprunt", "ID Document", "Date Emprunt", "Retour Prévu", "Retour Réel", "Statut"};
        loanModel = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable loanTable = new JTable(loanModel);
        AppTheme.styleTable(loanTable);
        loanTable.getColumnModel().getColumn(5).setCellRenderer(AppTheme.statusRenderer());

        JButton refresh = AppTheme.btnPrimary("↺ Actualiser");
        refresh.setPreferredSize(new Dimension(120, 36));
        refresh.addActionListener(e -> { loadLoans(); refreshBanner(banner); });

        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        toolbar.setOpaque(false);
        toolbar.add(refresh);

        // wrap table + toolbar in a sub-panel so BorderLayout works
        JPanel center = new JPanel(new BorderLayout(6, 6));
        center.setOpaque(false);
        center.add(toolbar, BorderLayout.NORTH);
        center.add(AppTheme.scrollPane(loanTable), BorderLayout.CENTER);

        panel.add(center, BorderLayout.CENTER);

        loadLoans();
        return panel;
    }

    private JPanel buildStatsBanner() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 6));
        p.setOpaque(false);
        return p;
    }

    private void refreshBanner(JPanel banner) {
        banner.removeAll();
        java.util.List<Emprunt> all = empCtrl.getByAdherent(user.getId());
        long total    = all.size();
        long encours  = all.stream().filter(e -> "En cours".equals(e.getStatus())).count();
        long retournes = all.stream().filter(e -> "Retourné".equals(e.getStatus())).count();

        banner.add(statCard("📋 Total",     String.valueOf(total),     AppTheme.PRIMARY));
        banner.add(statCard("⏳ En cours",  String.valueOf(encours),   AppTheme.WARNING));
        banner.add(statCard("✅ Retournés", String.valueOf(retournes), AppTheme.SUCCESS));
        banner.revalidate(); banner.repaint();
    }

    private JPanel statCard(String label, String value, Color color) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 2),
            BorderFactory.createEmptyBorder(8, 20, 8, 20)));

        JLabel val = new JLabel(value, SwingConstants.CENTER);
        val.setFont(new Font("Segoe UI", Font.BOLD, 26));
        val.setForeground(color);
        val.setAlignmentX(CENTER_ALIGNMENT);

        JLabel lbl = new JLabel(label, SwingConstants.CENTER);
        lbl.setFont(AppTheme.F_SMALL);
        lbl.setForeground(AppTheme.TEXT_MUTED);
        lbl.setAlignmentX(CENTER_ALIGNMENT);

        card.add(val);
        card.add(lbl);
        return card;
    }

    private void loadLoans() {
        loanModel.setRowCount(0);
        for (Emprunt e : empCtrl.getByAdherent(user.getId()))
            loanModel.addRow(new Object[]{
                e.getId(), e.getId_document(),
                e.getDate_emp(), e.getDate_retour_p(), e.getDate_retour_r(), e.getStatus()});
    }

    // ── Helpers ──────────────────────────────────────────────────────────────

    private void logout() {
        if (JOptionPane.showConfirmDialog(this, "Voulez-vous vous déconnecter ?",
                "Déconnexion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            dispose();
            new LoginView().setVisible(true);
        }
    }
}
