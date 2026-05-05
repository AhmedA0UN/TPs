package view;

import dao.CommandeDAO;
import dao.PlatscommandeDAO;
import model.Platscommande;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class AfficherFacturequi extends JFrame {
    private final int idUtilisateur;
    private final int idCommande;
    private JTable tablePlatsCommandes;
    private JLabel lblPrixTotal;

    public AfficherFacturequi(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
        this.idCommande = resolveCommandeId(idUtilisateur);

        if (this.idCommande == -1) {
            throw new IllegalStateException("Aucune commande trouvée pour ce client.");
        }

        initializeUI();
        loadFactureData();
    }

    private int resolveCommandeId(int idUtilisateur) {
        try {
            CommandeDAO cdao = new CommandeDAO();
            return cdao.getDerniereCommandeIdByUser(idUtilisateur);
        } catch (SQLException e) {
            throw new IllegalStateException("Erreur lors de la recherche de la commande du client : " + e.getMessage(), e);
        }
    }

    private void initializeUI() {
        setTitle("Afficher Facture - Gestion Restaurant");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(245, 245, 245));

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel titleLabel = new JLabel("FACTURE - COMMANDE #" + idCommande, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model.addColumn("ID Plat");
        model.addColumn("Nom du Plat");
        model.addColumn("Prix Unitaire (DT)");
        model.addColumn("Quantité");
        model.addColumn("Total (DT)");

        tablePlatsCommandes = new JTable(model);
        styleTable(tablePlatsCommandes);

        JScrollPane scrollPane = new JScrollPane(tablePlatsCommandes);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        bottomPanel.setBackground(new Color(245, 245, 245));

        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        totalPanel.setBackground(new Color(245, 245, 245));

        JLabel lblTotal = new JLabel("Total à payer : ");
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 16));

        lblPrixTotal = new JLabel("0.00 DT");
        lblPrixTotal.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblPrixTotal.setForeground(new Color(220, 0, 0));

        totalPanel.add(lblTotal);
        totalPanel.add(lblPrixTotal);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 20));
        buttonPanel.setBackground(new Color(245, 245, 245));

        JButton btnFermer = createButton("Fermer", new Color(150, 150, 150));
        btnFermer.addActionListener(e -> dispose());
        buttonPanel.add(btnFermer);

        bottomPanel.add(totalPanel, BorderLayout.WEST);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private void loadFactureData() {
        DefaultTableModel model = (DefaultTableModel) tablePlatsCommandes.getModel();
        model.setRowCount(0);

        try {
            CommandeDAO cdao = new CommandeDAO();
            int idClient = cdao.getIdClientCP(idCommande);
            PlatscommandeDAO pdao = new PlatscommandeDAO();

            double total = 0;
            List<Platscommande> platsCommandes = pdao.getPlatCommande(idClient);

            for (Platscommande pc : platsCommandes) {
                int idPlat = pc.getIdPlat();
                double prixUnitaire = pdao.getPrixPlat(idPlat);
                int quantite = pc.getQuantite();
                double totalLigne = prixUnitaire * quantite;

                model.addRow(new Object[] {
                    idPlat,
                    pc.getNomPlat(),
                    String.format("%.2f", prixUnitaire),
                    quantite,
                    String.format("%.2f", totalLigne)
                });

                total += totalLigne;
            }

            cdao.mettreprix(idCommande, total);
            lblPrixTotal.setText(String.format("%.2f DT", total));
        } catch (SQLException e) {
            showError("Erreur lors du chargement de la facture : " + e.getMessage());
        }
    }

    private void styleTable(JTable table) {
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setShowGrid(true);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);

        for (int i = 0; i < table.getColumnCount(); i++) {
            if (i == 1) {
                table.getColumnModel().getColumn(i).setCellRenderer(leftRenderer);
            } else {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(color);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.brighter());
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
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
                new AfficherFacturequi(3).setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}