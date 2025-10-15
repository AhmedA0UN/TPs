public class Client {
    private String nom;
    private Compte[] comptes = new Compte[100];
    private int nbComptes = 0;

    
    public Client() {}

    public Client(String nom) {
        this.nom = nom;
    }

    
    public String getNom() {
        return nom;
    }

    public void ajouterCompte(Compte c) {
        if (nbComptes < comptes.length) {
            comptes[nbComptes] = c;
            nbComptes++;
        } else {
            System.out.println("Le client a atteint le nombre maximal de comptes !");
        }
    }

    public double getSolde() {
        double total = 0;
        for (int i = 0; i < nbComptes; i++) {
            total += comptes[i].getSolde();
        }
        return total;
    }

    public void afficherSolde() {
        System.out.println("Client : " + nom + ", Solde total : " + getSolde() + " €");
    }

    public String toString() {
        return "Client : " + nom + ", nombre de comptes : " + nbComptes;
    }

    public boolean equals(Object o) {
        if (o instanceof Client) {
            Client autre = (Client) o;
            return this.nom.equals(autre.nom) && this.getSolde() == autre.getSolde();
        }
        return false;
    }
}
