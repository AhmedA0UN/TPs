package vue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import dao.*;
import modele.*;
import java.awt.*;
import java.awt.event.*;

public class CategoriePanel extends JPanel {

    JTextField txtNom = new JTextField(15);
    JButton btnAdd = new JButton("Ajouter");
    JButton btnUpdate = new JButton("Modifier");
    JButton btnDelete = new JButton("Supprimer");

    JTable table;
    DefaultTableModel model;

    CategorieDAO catdao = new CategorieDAO();

    int selectedId = -1;

    public CategoriePanel() {
        setLayout(new BorderLayout());

       
        JPanel form = new JPanel();
        form.add(new JLabel("Nom:"));
        form.add(txtNom);
        form.add(btnAdd);
        form.add(btnUpdate);
        form.add(btnDelete);

        add(form, BorderLayout.NORTH);


        model = new DefaultTableModel(new Object[]{"Code","Nom"},0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadData();


        btnAdd.addActionListener(e -> {
            Categorie c = new Categorie();
            c.setNom(txtNom.getText());

            if(catdao.create(c)){
                clear();
                loadData();
            }
        });

    
        btnUpdate.addActionListener(e -> {
            Categorie c = new Categorie(selectedId, txtNom.getText());

            if(catdao.update(c)){
                clear();
                loadData();
            }
        });


        btnDelete.addActionListener(e -> {
        	Categorie c = new Categorie();
        	c.setCode(selectedId);
            if(catdao.delete(c)){
                clear();
                loadData();
            }
        });

      
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                selectedId = Integer.parseInt(model.getValueAt(row,0).toString());
                txtNom.setText(model.getValueAt(row,1).toString());
            }
        });
    }

    void loadData(){
        model.setRowCount(0);
        for(Categorie c : catdao.getAll()){
            model.addRow(new Object[]{
                c.getCode(),
                c.getNom()
            });
        }
    }

    void clear(){
        txtNom.setText("");
        selectedId = -1;
    }
}