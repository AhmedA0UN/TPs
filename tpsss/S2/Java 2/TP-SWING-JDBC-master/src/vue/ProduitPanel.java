package vue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import dao.*;
import modele.*;
import java.awt.*;
import java.awt.event.*;

public class ProduitPanel extends JPanel {

    JTextField txtRef = new JTextField(8);
    JTextField txtNom = new JTextField(10);
    JTextField txtPrix = new JTextField(5);
    JTextField txtQte = new JTextField(5);

    JComboBox<Categorie> combo = new JComboBox<>();

    JButton btnAdd = new JButton("Ajouter");
    JButton btnUpdate = new JButton("Modifier");
    JButton btnDelete = new JButton("Supprimer");

    JTable table;
    DefaultTableModel model;

    ProduitDAO proddao = new ProduitDAO();
    CategorieDAO catdao = new CategorieDAO();

    public ProduitPanel() {
        setLayout(new BorderLayout());

  
        JPanel form = new JPanel();
        form.add(new JLabel("Ref")); form.add(txtRef);
        form.add(new JLabel("Nom")); form.add(txtNom);
        form.add(new JLabel("Prix")); form.add(txtPrix);
        form.add(new JLabel("Qte")); form.add(txtQte);
        form.add(new JLabel("Cat")); form.add(combo);

        form.add(btnAdd);
        form.add(btnUpdate);
        form.add(btnDelete);

        add(form, BorderLayout.NORTH);

     
        model = new DefaultTableModel(
                new Object[]{"Ref","Nom","Prix","Qte","Cat"},0
        );

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadCategories();
        loadData();

      
        btnAdd.addActionListener(e -> addProduit());

 
        btnUpdate.addActionListener(e -> updateProduit());

 
        btnDelete.addActionListener(e -> deleteProduit());

     
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                fillForm();
            }
        });
    }

    void loadCategories(){
        combo.removeAllItems();
        for(Categorie c : catdao.getAll()){
            combo.addItem(c);
        }
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

    void addProduit(){
        try {
            Produit p = new Produit(
                    txtRef.getText(),
                    txtNom.getText(),
                    Double.parseDouble(txtPrix.getText()),
                    Integer.parseInt(txtQte.getText()),
                    (Categorie) combo.getSelectedItem()
            );

            if(proddao.create(p)){
                clearForm();
                loadData();
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Erreur saisie");
        }
    }

    void updateProduit(){
        try {
            Produit p = new Produit(
                    txtRef.getText(),
                    txtNom.getText(),
                    Double.parseDouble(txtPrix.getText()),
                    Integer.parseInt(txtQte.getText()),
                    (Categorie) combo.getSelectedItem()
            );

            if(proddao.update(p)){
                clearForm();
                loadData();
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Erreur");
        }
    }

    void deleteProduit(){
    	Produit p = new Produit();
    	p.setReference(txtRef.getText());
        if(proddao.delete(p)){
            clearForm();
            loadData();
        }
    }

    void fillForm(){
        int row = table.getSelectedRow();

        txtRef.setText(model.getValueAt(row,0).toString());
        txtNom.setText(model.getValueAt(row,1).toString());
        txtPrix.setText(model.getValueAt(row,2).toString());
        txtQte.setText(model.getValueAt(row,3).toString());

        String catName = model.getValueAt(row,4).toString();
        for(int i=0;i<combo.getItemCount();i++){
            if(combo.getItemAt(i).getNom().equals(catName)){
                combo.setSelectedIndex(i);
                break;
            }
        }
    }

    void clearForm(){
        txtRef.setText("");
        txtNom.setText("");
        txtPrix.setText("");
        txtQte.setText("");
    }
}