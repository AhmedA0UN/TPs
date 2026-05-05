package vue;



import javax.swing.*;

public class MainFrame extends JFrame {

    private JPanel container;

    public MainFrame() {
        setTitle("Gestion Catalogue");
        setSize(900, 500);

        container = new JPanel();
        setContentPane(container);

     
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Gestion");
        JMenu menuRecherche = new JMenu("Recherche");

        JMenuItem catItem = new JMenuItem("Catégories");
        JMenuItem prodItem = new JMenuItem("Produits");
        JMenuItem listItem = new JMenuItem("Recherche Produit");

        
        menuRecherche.add(listItem);
        menu.add(catItem);
        menu.add(prodItem);
        
        menuBar.add(menu);
        menuBar.add(menuRecherche);

        setJMenuBar(menuBar);

       
        catItem.addActionListener(e -> setPanel(new CategoriePanel()));
        prodItem.addActionListener(e -> setPanel(new ProduitPanel()));
        listItem.addActionListener(e -> setPanel(new ProduitListPanel()));
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setPanel(JPanel panel) {
        container.removeAll();
        container.add(panel);
        container.revalidate();
        container.repaint();
    }
}