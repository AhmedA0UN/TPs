package model;

public class Cuisinier extends Utilisateur {
    private String specialite;

    public Cuisinier() {
        super();
        setRole("chef");
    }

    public Cuisinier(int id, String nom, String prenom, String username, String password, String specialite) {
        super(id, nom, prenom, username, password, "chef");
        this.specialite = specialite;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void ajouterPlat(Plat plat) {
    }

    public void modifierPlat(Plat plat) {
    }

    public void supprimerPlat(int idPlat) {
    }

    public void traiterCommande(int idCommande) {
    }

    public void annulerCommande(int idCommande) {
    }

    public void notifierServeuse(Commande commande) {
    }
}
