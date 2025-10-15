public class Banque {
    public static void main(String[] args) {
        
        Client c1 = new Client("Ali");
        Client c2 = new Client("Sarra");

        
        Compte compte1 = new Compte(1, 500);
        Compte compte2 = new Compte(2, 1000);
        Compte compte3 = new Compte(3, 1500);

        
        c1.ajouterCompte(compte1);
        c1.ajouterCompte(compte2);
        c2.ajouterCompte(compte3);

        
        c1.afficherSolde();
        c2.afficherSolde();

        
        compte1.virer(200, compte3);

        System.out.println("Après virement :");
        c1.afficherSolde();
        c2.afficherSolde();

        
        Compte max = Compte.plusSolde(compte2, compte3);
        System.out.println("Le compte avec le plus grand solde est : " + max);
    }
}
