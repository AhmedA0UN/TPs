package model;

import java.util.ArrayList;
import java.util.List;

public class Serveuse extends Utilisateur {
    public Serveuse() {
        super();
        setRole("serveuse");
    }

    public Serveuse(int id, String nom, String prenom, String username, String password) {
        super(id, nom, prenom, username, password, "serveuse");
    }

    public List<Plat> parcourirMenu() {
        return new ArrayList<>();
    }

    public Commande commander(List<Plat> plats) {
        Commande commande = new Commande();
        commande.setIdClient(getIdU());
        commande.setEtat("en cours");
        return commande;
    }

    public List<Commande> voirCmdsEnCours() {
        return new ArrayList<>();
    }

    public List<Commande> voirCmdsRecues() {
        return new ArrayList<>();
    }

    public Facture genererFacture(Commande commande) {
        Facture facture = new Facture();
        if (commande != null) {
            facture.setIdCommande(commande.getIdCommande());
            facture.setMontantTotal(commande.getPrixC());
        }
        return facture;
    }
}
