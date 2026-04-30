package Exercice2;

public class Personnel {
	 private Employe[] staff;
	 private int nbreEmploye;
	 private final static int MAXEMPLOYE = 200;
	 
	    public Personnel() {
	        staff = new Employe[MAXEMPLOYE];
	        nbreEmploye = 0;
	    }
	 
	    public void ajouterEmploye(Employe e) {
	        if (nbreEmploye < MAXEMPLOYE) {
	            staff[nbreEmploye] = e;
	            nbreEmploye++;
	        } 
	        else {
	            System.out.println("Pas plus de " + MAXEMPLOYE + " employés");
	        }
	    }
	 
	    public double salaireMoyen() {
	        double somme = 0.0;
	        for (int i = 0; i < nbreEmploye; i++) {
	            somme += staff[i].calculerSalaire();
	        
	        }
	        return somme / nbreEmploye;
	    }
	 
	    public void afficherSalaires() {
	        for (int i = 0; i < nbreEmploye; i++) {
	            System.out.println(staff[i].getNom() + " gagne "
	                    + staff[i].calculerSalaire() + " dinars.");
	        }
	    }

	    public static void main(String[] args) {
	        Personnel p = new Personnel();
	        p.ajouterEmploye(new Vendeur("Ali", "Sassi", 45, "1995", 30000));
	        p.ajouterEmploye(new Representant("Sami", "Soussi", 25, "2001", 20000));
	        p.ajouterEmploye(new Technicien("Rami", "Bouaziz", 28, "1998", 1000));
	        p.ajouterEmploye(new Manutentionnaire("Jacem", "Zrig", 32, "1998", 45));
	        p.ajouterEmploye(new TechnARisque("Jalel", "Fetwi", 28, "2000", 1000));
	        p.ajouterEmploye(new ManutARisque("Amir", "Ammar", 30, "2001", 45));
	 
	        p.afficherSalaires();
	        System.out.println("Le salaire moyen dans l'entreprise est de "
	                + p.salaireMoyen() + " dinars.");
	 
	    }
}
