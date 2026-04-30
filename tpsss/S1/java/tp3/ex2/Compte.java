public class Compte {
    private int numero;
    private double solde;
    private static int nbComptes = 0;
    public static final int NOMBRE_MAXIMAL_COMPTE = 100;

    
    public Compte() {
        nbComptes++;
    }

    public Compte(int numero, double solde) {
        this.numero = numero;
        setSolde(solde);
        nbComptes++;
    }

    
    public void depot(double valeur) {
        if (valeur > 0) {
            solde += valeur;
        }
    }

    public void retrait(double valeur) {
        if (valeur > 0 && valeur <= solde) {
            solde -= valeur;
        } else {
            System.out.println("Retrait impossible !");
        }
    }

    public void virer(float valeur, Compte destinataire) {
        if (valeur > 0 && valeur <= solde) {
            this.retrait(valeur);
            destinataire.depot(valeur);
        } else {
            System.out.println("Virement impossible !");
        }
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double s) {
        if (s >= 0) {
            this.solde = s;
        } else {
            System.out.println("Erreur : le solde doit être positif !");
        }
    }

    public void afficherSolde() {
        System.out.println("Solde du compte " + numero + " : " + solde + " €");
    }

    public String toString() {
        return "Compte n°" + numero + " - Solde : " + solde + " €";
    }

    public boolean equals(Object o) {
        if (o instanceof Compte) {
            Compte autre = (Compte) o;
            return this.numero == autre.numero && this.solde == autre.solde;
        }
        return false;
    }

    public static Compte plusSolde(Compte c1, Compte c2) {
        return (c1.solde >= c2.solde) ? c1 : c2;
    }

    public static int getNbComptes() {
        return nbComptes;
    }
}
