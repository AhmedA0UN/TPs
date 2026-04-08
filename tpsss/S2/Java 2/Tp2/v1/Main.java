// ==================== Exemple d'utilisation ====================
public class Main {
    public static void main(String[] args) {
        try {
            Agence agence = new Agence("Agence Centrale");

            Voiture v1 = new Voiture("1234TUN", "Renault", 50);
            Voiture v2 = new Voiture("5678TUN", "Peugeot", 70);
            Voiture v3 = new Voiture("9999TUN", "Renault", 40);

            agence.ajoutVoitureParking(v1);
            agence.ajoutVoitureParking(v2);
            agence.ajoutVoitureParking(v3);

            Client c1 = new Client(1, "Dupont", "Jean");
            Client c2 = new Client(2, "Martin", "Sophie");

            agence.louerVoiture(c1, v1);
            agence.louerVoiture(c1, v2);
            agence.louerVoiture(c2, v3);

            System.out.println("=== Voitures en location ===");
            for (Voiture v : agence.voituresEnLocation()) {
                System.out.println(v);
            }

            System.out.println("\n=== Clients et leurs voitures louées ===");
            agence.afficheClientEtVoituresLouees();

            System.out.println("\n=== Voitures Renault disponibles ===");
            List<Voiture> renaults = agence.selectVoitureSelonCritere(new CritereMarque("Renault"));
            for (Voiture v : renaults) {
                System.out.println(v);
            }

        } catch (VoitureException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }
}