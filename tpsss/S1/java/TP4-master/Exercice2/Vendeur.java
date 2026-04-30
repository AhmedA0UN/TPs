package Exercice2;

public class Vendeur extends Commercial {
	private final static double pourcentVendeur = 0.2;
	private final static int bonusVendeur = 400;
	 
	    public Vendeur(String prenom, String nom, int age, String date,
	            double chiffreAffaire) {
	        super(prenom, nom, age, date, chiffreAffaire);
	    }
	 
	    public double calculerSalaire() {
	        return (pourcentVendeur * getChiffreAffaire() + bonusVendeur);
	    }
	 
	    public String getTitre()
	        {
	            return "Le vendeur ";
	        }
}
