package model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Commande {
    private int idCommande;
    private int idClient;
    private String etat;
    private Date date;  // Ajout du champ date
    private double prixC;
    private List<LigneCommande> lignesCommande;


    public Commande(int idCommande, int idClient, String etat, Date date) {
        this.idCommande = idCommande;
        this.idClient = idClient;
        this.etat = etat;
        this.date = date;
    }
    public Commande() {
	    this.lignesCommande = new ArrayList<>();
    }

    
    public Date getDate() {
        return date;
    }

    public double getPrixC() {
		return prixC;
	}

	public void setPrixC(double prixC) {
		this.prixC = prixC;
	}

	public void setDate(Date date) {
        this.date = date;
    }

    


    public int getIdCommande() {
        return idCommande;
    }


    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getIdClient() {
        return idClient;
    }


    public void setIdClient(int idC) {
        this.idClient = idC;
    }


    public String getEtat() {
        return etat;
    }


    public void setEtat(String enCours) {
        this.etat = enCours;
    }

    public List<LigneCommande> getLignesCommande() {
        return lignesCommande;
    }

    public void setLignesCommande(List<LigneCommande> lignesCommande) {
        this.lignesCommande = lignesCommande;
    }

    public void ajouterLigneCommande(LigneCommande ligneCommande) {
        if (lignesCommande == null) {
            lignesCommande = new ArrayList<>();
        }
        lignesCommande.add(ligneCommande);
    }

    public double calculerTotal() {
        double total = 0.0;
        if (lignesCommande != null) {
            for (LigneCommande ligneCommande : lignesCommande) {
                total += ligneCommande.getSousTotal();
            }
        }
        return total;
    }

    public void changerStatut(String nouvelEtat) {
        this.etat = nouvelEtat;
    }

    @Override
    public String toString() {
        return "Commande[idCommande=" + idCommande + ", idClient=" + idClient
            + ", etat=" + etat + ", date=" + date + ", prixC=" + prixC + "]";
    }
   

    
}

