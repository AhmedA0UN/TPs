package Vues;

import Controller.*;
import Models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class BibliothecaireDashboard extends JFrame {

    private final Bibliothecaire        user;
    private final DocumentController    docCtrl = new DocumentController();
    private final AdherentController    adhCtrl = new AdherentController();
    private final EmpruntController     empCtrl = new EmpruntController();

    // Document tab
    private DefaultTableModel docModel;
    private JTable            docTable;
    private JTextField        docSearch;

    // Adherent tab
    private DefaultTableModel adhModel;
    private JTable            adhTable;
    private JTextField        adhSearch;

    // Emprunt tab
    private DefaultTableModel empModel;
    private JTable            empTable;

    // ── Constructor ──────────────────────────────────────────────────────────

    public BibliothecaireDashboard(Bibliothecaire user) {
        super("BiblioGest — Bibliothécaire : " + user.getPrenom() + " " + user.getNom());
        this.user = user;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1150, 720);
        setLocationRelativeTo(null);
        buildUI();
    }

    // ── Shell ────────────────────────────────────────────────────────────────

    private void buildUI() {
        JButton logoutBtn = AppTheme.btnDanger("Déconnexion");
        logoutBtn.setPreferredSize(new Dimension(120, 32));
        logoutBtn.addActionListener(e -> logout());

        JPanel nav = AppTheme.navBar(user.getPrenom() + " " + user.getNom() + "  |  Bibliothécaire", logoutBtn);

        JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
        tabs.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabs.addTab("  📄  Documents  ",  buildDocumentTab());
        tabs.addTab("  👥  Adhérents  ",  buildAdherentTab());
        tabs.addTab("  📋  Emprunts  ",   buildEmpruntTab());

        JLabel status = new JLabel("  Connecté en tant que Bibliothécaire");
        status.setFont(AppTheme.F_SMALL);
        status.setForeground(AppTheme.TEXT_MUTED);
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 4));
        statusBar.setBackground(new Color(245, 245, 245));
        statusBar.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, AppTheme.BORDER));
        statusBar.add(status);

        setLayout(new BorderLayout());
        add(nav,       BorderLayout.NORTH);
        add(tabs,      BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);
    }

    // ════════════════════════════════════════════════════════════════════════
    // TAB 1 — Documents
    // ════════════════════════════════════════════════════════════════════════

    private JPanel buildDocumentTab() {
        JPanel panel = tabPanel();

        // ── toolbar ──
        docSearch = searchField("Titre, auteur ou type…");
        JButton btnSearch  = AppTheme.btnPrimary("🔍 Rechercher");
        JButton btnRefresh = AppTheme.btnWarning("↺ Tout");
        JButton btnAdd     = AppTheme.btnSuccess("＋ Ajouter");
        JButton btnEdit    = AppTheme.btnPrimary("✎  Modifier");
        JButton btnDel     = AppTheme.btnDanger("✖  Supprimer");

        JPanel toolbar = toolbar(
            row(new JLabel("Recherche :"), docSearch, btnSearch, btnRefresh),
            row(btnAdd, btnEdit, btnDel));
        panel.add(toolbar, BorderLayout.NORTH);

        // ── table ──
        String[] cols = {"ID", "Titre", "Auteur", "Description", "Disponible", "Type"};
        docModel = model(cols);
        docTable = new JTable(docModel);
        AppTheme.styleTable(docTable);
        widths(docTable, 50, 200, 130, 250, 100, 100);
        panel.add(AppTheme.scrollPane(docTable), BorderLayout.CENTER);

        loadDocs();

        // ── listeners ──
        btnSearch .addActionListener(e -> { String k = docSearch.getText().trim(); if (!k.isEmpty()) searchDocs(k); else loadDocs(); });
        btnRefresh.addActionListener(e -> { docSearch.setText(""); loadDocs(); });
        btnAdd    .addActionListener(e -> docDialog(null));
        btnEdit   .addActionListener(e -> {
            int row = docTable.getSelectedRow();
            if (noSel(row)) return;
            docDialog(docCtrl.getById((int) docModel.getValueAt(row, 0)));
        });
        btnDel.addActionListener(e -> {
            int row = docTable.getSelectedRow();
            if (noSel(row)) return;
            if (confirm("Supprimer ce document ?")) {
                ok(docCtrl.delete((int) docModel.getValueAt(row, 0)), "Document supprimé.");
                loadDocs();
            }
        });

        return panel;
    }

    private void loadDocs()              { fill(docModel, docCtrl.getAll()); }
    private void searchDocs(String kw)   { fill(docModel, docCtrl.search(kw)); }

    private void fill(DefaultTableModel m, List<Document> docs) {
        m.setRowCount(0);
        for (Document d : docs)
            m.addRow(new Object[]{d.getId(), d.getTitre(), d.getAuteur(), d.getDesc(), d.getDis(), d.getType()});
    }

    private void docDialog(Document existing) {
        boolean edit = existing != null;
        JDialog dlg = dialog(edit ? "Modifier le document" : "Ajouter un document", 500, 430);

        JTextField titreF  = dlgField(edit ? existing.getTitre()  : "");
        JTextField auteurF = dlgField(edit ? existing.getAuteur() : "");
        JTextField descF   = dlgField(edit ? existing.getDesc()   : "");
        JCheckBox  disF    = new JCheckBox("Disponible", !edit || existing.getDis());
        disF.setFont(AppTheme.F_BODY); disF.setBackground(Color.WHITE);
        JComboBox<String> typeF = new JComboBox<>(new String[]{"Livre","Revue","Thèse","Rapport","Autre"});
        typeF.setFont(AppTheme.F_BODY);
        if (edit) typeF.setSelectedItem(existing.getType());

        JPanel form = form();
        addRow(form, 0, "Titre *",      titreF);
        addRow(form, 1, "Auteur *",     auteurF);
        addRow(form, 2, "Description",  descF);
        addRow(form, 3, "Type *",       typeF);
        addRow(form, 4, "",             disF);

        JButton save = AppTheme.btnSuccess("Enregistrer");
        save.addActionListener(e -> {
            String t = titreF.getText().trim(), a = auteurF.getText().trim();
            if (t.isEmpty() || a.isEmpty()) { warn("Titre et Auteur sont obligatoires."); return; }
            boolean res = edit
                ? docCtrl.update(existing.getId(), t, a, descF.getText().trim(), disF.isSelected(), (String) typeF.getSelectedItem())
                : docCtrl.add(t, a, descF.getText().trim(), disF.isSelected(), (String) typeF.getSelectedItem());
            ok(res, "Document enregistré !");
            if (res) { dlg.dispose(); loadDocs(); }
        });

        attachDialog(dlg, edit ? "✎  Modifier le document" : "➕  Nouveau document", form, save);
    }

    // ════════════════════════════════════════════════════════════════════════
    // TAB 2 — Adhérents
    // ════════════════════════════════════════════════════════════════════════

    private JPanel buildAdherentTab() {
        JPanel panel = tabPanel();

        adhSearch = searchField("Nom, prénom ou username…");
        JButton btnSearch  = AppTheme.btnPrimary("🔍 Rechercher");
        JButton btnRefresh = AppTheme.btnWarning("↺ Tout");
        JButton btnAdd     = AppTheme.btnSuccess("＋ Ajouter");
        JButton btnEdit    = AppTheme.btnPrimary("✎  Modifier");
        JButton btnDel     = AppTheme.btnDanger("✖  Supprimer");

        panel.add(toolbar(
            row(new JLabel("Recherche :"), adhSearch, btnSearch, btnRefresh),
            row(btnAdd, btnEdit, btnDel)), BorderLayout.NORTH);

        String[] cols = {"ID", "Nom", "Prénom", "Username", "Email"};
        adhModel = model(cols);
        adhTable = new JTable(adhModel);
        AppTheme.styleTable(adhTable);
        widths(adhTable, 50, 150, 150, 150, 220);
        panel.add(AppTheme.scrollPane(adhTable), BorderLayout.CENTER);

        loadAdh();

        btnSearch .addActionListener(e -> { String k = adhSearch.getText().trim(); if (!k.isEmpty()) searchAdh(k); else loadAdh(); });
        btnRefresh.addActionListener(e -> { adhSearch.setText(""); loadAdh(); });
        btnAdd    .addActionListener(e -> adhDialog(null));
        btnEdit   .addActionListener(e -> {
            int row = adhTable.getSelectedRow();
            if (noSel(row)) return;
            adhDialog(adhCtrl.getById((int) adhModel.getValueAt(row, 0)));
        });
        btnDel.addActionListener(e -> {
            int row = adhTable.getSelectedRow();
            if (noSel(row)) return;
            if (confirm("Supprimer cet adhérent ?")) {
                ok(adhCtrl.delete((int) adhModel.getValueAt(row, 0)), "Adhérent supprimé.");
                loadAdh();
            }
        });

        return panel;
    }

    private void loadAdh()             { fillAdh(adhCtrl.getAll()); }
    private void searchAdh(String kw)  { fillAdh(adhCtrl.search(kw)); }

    private void fillAdh(List<Adherent> list) {
        adhModel.setRowCount(0);
        for (Adherent a : list)
            adhModel.addRow(new Object[]{a.getId(), a.getNom(), a.getPrenom(), a.getUsername(), a.getEmail()});
    }

    private void adhDialog(Adherent ex) {
        boolean edit = ex != null;
        JDialog dlg = dialog(edit ? "Modifier l'adhérent" : "Ajouter un adhérent", 480, 450);

        JTextField   nomF      = dlgField(edit ? ex.getNom()       : "");
        JTextField   prenomF   = dlgField(edit ? ex.getPrenom()    : "");
        JTextField   usernameF = dlgField(edit ? ex.getUsername()  : "");
        JTextField   emailF    = dlgField(edit ? ex.getEmail()     : "");
        JPasswordField passF   = new JPasswordField(edit ? ex.getMot_de_passe() : "");
        passF.setFont(AppTheme.F_BODY);
        passF.setPreferredSize(new Dimension(220, 38));
        passF.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(AppTheme.BORDER),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)));

        JPanel form = form();
        addRow(form, 0, "Nom *",          nomF);
        addRow(form, 1, "Prénom *",       prenomF);
        addRow(form, 2, "Username *",     usernameF);
        addRow(form, 3, "Email *",        emailF);
        addRow(form, 4, "Mot de passe *", passF);

        JButton save = AppTheme.btnSuccess("Enregistrer");
        save.addActionListener(e -> {
            String n = nomF.getText().trim(), p = prenomF.getText().trim(),
                   u = usernameF.getText().trim(), em = emailF.getText().trim(),
                   pw = new String(passF.getPassword());
            if (n.isEmpty() || p.isEmpty() || u.isEmpty() || em.isEmpty() || pw.isEmpty()) {
                warn("Tous les champs sont obligatoires."); return;
            }
            boolean res = edit
                ? adhCtrl.update(ex.getId(), n, p, u, pw, em)
                : adhCtrl.add(n, p, u, pw, em);
            ok(res, "Adhérent enregistré !");
            if (res) { dlg.dispose(); loadAdh(); }
        });

        attachDialog(dlg, edit ? "✎  Modifier l'adhérent" : "➕  Nouvel adhérent", form, save);
    }

    // ════════════════════════════════════════════════════════════════════════
    // TAB 3 — Emprunts
    // ════════════════════════════════════════════════════════════════════════

    private JPanel buildEmpruntTab() {
        JPanel panel = tabPanel();

        JButton btnNew    = AppTheme.btnSuccess("＋ Créer un prêt");
        JButton btnReturn = AppTheme.btnPrimary("✔  Marquer retour");
        JButton btnDate   = AppTheme.btnWarning("📅 Modifier date");
        JButton btnDel    = AppTheme.btnDanger("✖  Supprimer");
        JButton btnRef    = AppTheme.btnPrimary("↺ Actualiser");

        btnNew.setPreferredSize(new Dimension(150, 36));
        btnReturn.setPreferredSize(new Dimension(155, 36));

        panel.add(toolbar(new JPanel(), row(btnNew, btnReturn, btnDate, btnDel, btnRef)), BorderLayout.NORTH);

        String[] cols = {"ID", "ID Adhérent", "ID Document", "Date Emprunt", "Retour Prévu", "Retour Réel", "Statut"};
        empModel = model(cols);
        empTable = new JTable(empModel);
        AppTheme.styleTable(empTable);
        empTable.getColumnModel().getColumn(6).setCellRenderer(AppTheme.statusRenderer());
        widths(empTable, 50, 100, 110, 120, 120, 120, 110);
        panel.add(AppTheme.scrollPane(empTable), BorderLayout.CENTER);

        loadEmp();

        btnRef   .addActionListener(e -> loadEmp());
        btnNew   .addActionListener(e -> loanDialog());
        btnReturn.addActionListener(e -> {
            int row = empTable.getSelectedRow();
            if (noSel(row)) return;
            ok(empCtrl.markReturned((int) empModel.getValueAt(row, 0), LocalDate.now().toString()), "Retour enregistré !");
            loadEmp();
        });
        btnDate.addActionListener(e -> {
            int row = empTable.getSelectedRow();
            if (noSel(row)) return;
            String d = JOptionPane.showInputDialog(this, "Nouvelle date de retour prévue (AAAA-MM-JJ) :", "Modifier date", JOptionPane.QUESTION_MESSAGE);
            if (d != null && !d.isBlank()) {
                ok(empCtrl.updateReturnDate((int) empModel.getValueAt(row, 0), d.trim()), "Date mise à jour !");
                loadEmp();
            }
        });
        btnDel.addActionListener(e -> {
            int row = empTable.getSelectedRow();
            if (noSel(row)) return;
            if (confirm("Supprimer cet emprunt ?")) {
                ok(empCtrl.delete((int) empModel.getValueAt(row, 0)), "Emprunt supprimé.");
                loadEmp();
            }
        });

        return panel;
    }

    private void loadEmp() {
        empModel.setRowCount(0);
        for (Emprunt e : empCtrl.getAll())
            empModel.addRow(new Object[]{
                e.getId(), e.getId_adherent(), e.getId_document(),
                e.getDate_emp(), e.getDate_retour_p(), e.getDate_retour_r(), e.getStatus()});
    }

    private void loanDialog() {
        JDialog dlg = dialog("Créer un prêt", 460, 360);

        JTextField adhIdF  = dlgField("");
        JTextField docIdF  = dlgField("");
        JTextField dateEmp = dlgField(LocalDate.now().toString());
        JTextField dateRet = dlgField(LocalDate.now().plusDays(14).toString());

        JPanel form = form();
        addRow(form, 0, "ID Adhérent *",       adhIdF);
        addRow(form, 1, "ID Document *",        docIdF);
        addRow(form, 2, "Date emprunt *",       dateEmp);
        addRow(form, 3, "Date retour prévue *", dateRet);

        JButton save = AppTheme.btnSuccess("Créer le prêt");
        save.addActionListener(e -> {
            try {
                int idA = Integer.parseInt(adhIdF.getText().trim());
                int idD = Integer.parseInt(docIdF.getText().trim());
                String de = dateEmp.getText().trim(), dr = dateRet.getText().trim();
                if (de.isEmpty() || dr.isEmpty()) { warn("Tous les champs sont obligatoires."); return; }
                boolean res = empCtrl.createLoan(idA, idD, de, dr);
                ok(res, "Prêt créé !");
                if (res) { dlg.dispose(); loadEmp(); }
            } catch (NumberFormatException ex) {
                warn("Les IDs doivent être des nombres.");
            }
        });

        attachDialog(dlg, "📋  Nouveau prêt", form, save);
    }

    // ════════════════════════════════════════════════════════════════════════
    // Shared helpers
    // ════════════════════════════════════════════════════════════════════════

    private void logout() {
        if (confirm("Voulez-vous vous déconnecter ?")) { dispose(); new LoginView().setVisible(true); }
    }

    private JPanel tabPanel() {
        JPanel p = new JPanel(new BorderLayout(10, 10));
        p.setBackground(AppTheme.BG);
        p.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));
        return p;
    }

    private JPanel toolbar(JPanel left, JPanel right) {
        JPanel t = new JPanel(new BorderLayout(8, 0));
        t.setOpaque(false);
        t.add(left, BorderLayout.WEST);
        t.add(right, BorderLayout.EAST);
        return t;
    }

    private JPanel row(Component... cs) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        p.setOpaque(false);
        for (Component c : cs) p.add(c);
        return p;
    }

    private JTextField searchField(String tip) {
        JTextField f = AppTheme.textField("");
        f.setToolTipText(tip);
        f.setPreferredSize(new Dimension(260, 36));
        return f;
    }

    private DefaultTableModel model(String[] cols) {
        return new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
            @Override public Class<?> getColumnClass(int c) {
                return c < getColumnCount() && getValueAt(0, c) instanceof Boolean ? Boolean.class : Object.class;
            }
        };
    }

    private void widths(JTable t, int... ws) {
        for (int i = 0; i < ws.length; i++) t.getColumnModel().getColumn(i).setPreferredWidth(ws[i]);
    }

    private JDialog dialog(String title, int w, int h) {
        JDialog d = new JDialog(this, title, true);
        d.setSize(w, h);
        d.setLocationRelativeTo(this);
        d.setLayout(new BorderLayout());
        return d;
    }

    private void attachDialog(JDialog dlg, String header, JPanel form, JButton save) {
        JPanel hdr = new JPanel(new BorderLayout());
        hdr.setBackground(AppTheme.PRIMARY);
        hdr.setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));
        JLabel lbl = new JLabel(header);
        lbl.setFont(AppTheme.F_HEADING);
        lbl.setForeground(Color.WHITE);
        hdr.add(lbl);

        JButton cancel = AppTheme.btnDanger("Annuler");
        cancel.addActionListener(e -> dlg.dispose());

        JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        btns.setBackground(new Color(248, 248, 248));
        btns.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, AppTheme.BORDER));
        btns.add(cancel);
        btns.add(save);

        dlg.add(hdr,  BorderLayout.NORTH);
        dlg.add(form, BorderLayout.CENTER);
        dlg.add(btns, BorderLayout.SOUTH);
        dlg.setVisible(true);
    }

    private JPanel form() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));
        return p;
    }

    private void addRow(JPanel form, int row, String lbl, JComponent field) {
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 4, 6, 4);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 0; g.gridy = row; g.weightx = 0.3;
        JLabel l = new JLabel(lbl);
        l.setFont(AppTheme.F_BOLD_SM);
        form.add(l, g);
        g.gridx = 1; g.weightx = 0.7;
        form.add(field, g);
    }

    private JTextField dlgField(String val) {
        JTextField f = AppTheme.textField(val);
        f.setPreferredSize(new Dimension(230, 38));
        return f;
    }

    private boolean noSel(int row) {
        if (row == -1) { JOptionPane.showMessageDialog(this, "Sélectionnez une ligne.", "Info", JOptionPane.INFORMATION_MESSAGE); return true; }
        return false;
    }

    private boolean confirm(String msg) {
        return JOptionPane.showConfirmDialog(this, msg, "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION;
    }

    private void ok(boolean success, String msg) {
        if (success) JOptionPane.showMessageDialog(this, msg, "Succès", JOptionPane.INFORMATION_MESSAGE);
        else         JOptionPane.showMessageDialog(this, "Opération échouée. Vérifiez les données.", "Erreur", JOptionPane.ERROR_MESSAGE);
    }

    private void warn(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Validation", JOptionPane.WARNING_MESSAGE);
    }
}
