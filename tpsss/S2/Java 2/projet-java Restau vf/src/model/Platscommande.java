package model;

public class Platscommande {
    private int idPlat;
    private String nomPlat;
    private int quantite;
    private int idU;
    private double prixUnitaire;
    public Platscommande() {
    }

    public Platscommande(int idPlat, String nomPlat, int quantite, int idU) {
        this.idPlat = idPlat;
        this.nomPlat = nomPlat;
        this.quantite = quantite;
        this.idU = idU;
    }

    public Platscommande(int idPlat, String nomPlat, int quantite, int idU, double prixUnitaire) {
        this(idPlat, nomPlat, quantite, idU);
        this.prixUnitaire = prixUnitaire;
    }

    public int getIdPlat() {
        return idPlat;
    }

    public void setIdPlat(int idPlat) {
        this.idPlat = idPlat;
    }

    public String getNomPlat() {
        return nomPlat;
    }

    public void setNomPlat(String nomPlat) {
        this.nomPlat = nomPlat;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
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
        return "Platscommande[idPlat=" + idPlat + ", nomPlat=" + nomPlat + ", quantite=" + quantite
            + ", idU=" + idU + ", prixUnitaire=" + prixUnitaire + "]";
    }
}


