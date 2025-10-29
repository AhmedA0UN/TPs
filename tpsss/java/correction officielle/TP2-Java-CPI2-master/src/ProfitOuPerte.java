import java.util.Scanner;
public class ProfitOuPerte {
	
	    public static void main(String[] variables) {
	        Scanner sc = new Scanner(System.in);
	 
	        double pf, pv, montant;
	 
	  // Fournir les données d'entrée
    System.out.print("Saisir le prix de fabrication : ");
    pf = sc.nextDouble();

    System.out.print("Saisir le prix de vente : ");
    pv = sc.nextDouble();

    if (pv > pf) {
        // calculer le profit */
        montant = pv - pf;
        System.out.print("Profit = " + montant);
    } else if (pf > pv) {
        // Calculer la perte */
        montant = pf - pv;
        System.out.print("Perte = " + montant);
    } else {
        // Ni profit ni perte */
        System.out.print("Ni profit ni perte.");
    }

    // fermer les ressources
    sc.close();
}

}
