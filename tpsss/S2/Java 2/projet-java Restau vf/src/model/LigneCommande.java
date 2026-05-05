package model;

public class LigneCommande {
    private int idLigne;
    private int idCommande;
    private int idPlat;
    private int quantite;
    private double prixUnitaire;

    public LigneCommande() {
    }

    public LigneCommande(int idLigne, int idCommande, int idPlat, int quantite, double prixUnitaire) {
        this.idLigne = idLigne;
        this.idCommande = idCommande;
        this.idPlat = idPlat;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
    }

    public int getIdLigne() {
        return idLigne;
    }

    public void setIdLigne(int idLigne) {
        this.idLigne = idLigne;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getIdPlat() {
        return idPlat;
    }

    public void setIdPlat(int idPlat) {
        this.idPlat = idPlat;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public double getSousTotal() {
        return quantite * prixUnitaire;
    }

    @Override
    public String toString() {
        return "LigneCommande[idLigne=" + idLigne + ", idCommande=" + idCommande
            + ", idPlat=" + idPlat + ", quantite=" + quantite + ", prixUnitaire=" + prixUnitaire + "]";
    }
}
