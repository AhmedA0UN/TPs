package metier;

import java.util.Date;

public class Commande {
    private int idCommande;
    private int idClient;
    private String etat;
    private Date date;  // Ajout du champ date
    private double prixC;

    // Constructeur
    public Commande(int idCommande, int idClient, String etat, Date date) {
        this.idCommande = idCommande;
        this.idClient = idClient;
        this.etat = etat;
        this.date = date;
    }
    public Commande() {
    	
    }

    // Getters et Setters
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

    // ... (autres getters et setters existants)


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
   

    
}

