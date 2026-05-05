package model;

import java.util.ArrayList;
import java.util.List;

public class Client extends Utilisateur {
    public Client() {
        super();
        setRole("client");
    }

    public Client(int id, String nom, String prenom, String username, String password) {
        super(id, nom, prenom, username, password, "client");
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
}
