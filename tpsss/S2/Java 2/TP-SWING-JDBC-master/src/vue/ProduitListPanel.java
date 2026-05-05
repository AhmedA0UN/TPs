package vue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modele.*;
import dao.*;
public class ProduitListPanel extends JPanel {

    JTable table;
    DefaultTableModel model;

    JTextField txtSearch = new JTextField(10);
    JButton btnSearch = new JButton("Rechercher");

    ProduitDAO proddao = new ProduitDAO();

    public ProduitListPanel() {

        setLayout(new java.awt.BorderLayout());

        JPanel top = new JPanel();
        top.add(new JLabel("Mot clé:"));
        top.add(txtSearch);
        top.add(btnSearch);
        add(top, java.awt.BorderLayout.NORTH);

   
        model = new DefaultTableModel(
            new Object[]{"Ref","Nom","Prix","Qte","Cat"},0
        );

        table = new JTable(model);
        add(new JScrollPane(table), java.awt.BorderLayout.CENTER);

        loadData();

        btnSearch.addActionListener(e -> {
            loadSearch(txtSearch.getText());
        });
    }

    void loadData(){
        model.setRowCount(0);
        for(Produit p : proddao.getAll()){
            model.addRow(new Object[]{
                    p.getReference(),
                    p.getNom(),
                    p.getPrix(),
                    p.getQte(),
                    p.getCat().getNom()
            });
        }
    }

    void loadSearch(String mc){
        model.setRowCount(0);
        for(Produit p : proddao.findByMotCle(mc)){
            model.addRow(new Object[]{
                    p.getReference(),
                    p.getNom(),
                    p.getPrix(),
                    p.getQte(),
                    p.getCat().getNom()
            });
        }
    }
}