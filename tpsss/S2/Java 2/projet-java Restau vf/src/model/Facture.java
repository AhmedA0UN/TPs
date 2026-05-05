package model;

import java.util.Date;

public class Facture {
    private int idFacture;
    private Date dateFacture;
    private double montantTotal;
    private int idCommande;

    public Facture() {
    }

    public Facture(int idFacture, Date dateFacture, double montantTotal, int idCommande) {
        this.idFacture = idFacture;
        this.dateFacture = dateFacture;
        this.montantTotal = montantTotal;
        this.idCommande = idCommande;
    }

    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public Date getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(Date dateFacture) {
        this.dateFacture = dateFacture;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public String generer() {
        return "Facture[idFacture=" + idFacture + ", idCommande=" + idCommande
            + ", dateFacture=" + dateFacture + ", montantTotal=" + montantTotal + "]";
    }

    public void afficher() {
        System.out.println(generer());
    }

    @Override
    public String toString() {
        return generer();
    }
}
