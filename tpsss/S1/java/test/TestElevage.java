// Classe principale Volaille
class Volaille {
    private int id;
    private double poids;
    private static double prixKiloPoulet = 1.0;  // DT/kg
    private static double prixKiloCanard = 1.2;  // DT/kg
    private static double poidsAbattagePoulet = 1.3; // KG
    private static double poidsAbattageCanard = 1.5; // KG
    
    // Constructeur
    public Volaille(int id, double poids) {
        this.id = id;
        this.poids = poids;
    }
    
    // Getters et Setters
    public int getId() {
        return id;
    }
    
    public double getPoids() {
        return poids;
    }
    
    public void changerPoids(double nouveauPoids) {
        this.poids = nouveauPoids;
    }
    
    // Cette méthode sera redéfinie dans les sous-classes
    public double calculerPrix() {
        return 0;
    }
    
    // Cette méthode sera redéfinie dans les sous-classes
    public boolean assezGrosse() {
        return false;
    }
    
    // Méthode pour obtenir le prix par kilo selon le type
    public double getPrixParKilo() {
        return 0;
    }
    
    // Méthode pour obtenir le poids d'abattage selon le type
    public double getPoidsAbattage() {
        return 0;
    }
    
    @Override
    public String toString() {
        return "Volaille ID: " + id + ", Poids: " + poids + " kg";
    }
    
    // Méthodes statiques pour modifier les prix et poids d'abattage
    public static void setPrixKiloPoulet(double prix) {
        prixKiloPoulet = prix;
    }
    
    public static void setPrixKiloCanard(double prix) {
        prixKiloCanard = prix;
    }
    
    public static void setPoidsAbattagePoulet(double poids) {
        poidsAbattagePoulet = poids;
    }
    
    public static void setPoidsAbattageCanard(double poids) {
        poidsAbattageCanard = poids;
    }
    
    public static double getPrixKiloPoulet() {
        return prixKiloPoulet;
    }
    
    public static double getPrixKiloCanard() {
        return prixKiloCanard;
    }
    
    public static double getPoidsAbattagePoulet() {
        return poidsAbattagePoulet;
    }
    
    public static double getPoidsAbattageCanard() {
        return poidsAbattageCanard;
    }
}

// Sous-classe Poulet
class Poulet extends Volaille {
    
    public Poulet(int id, double poids) {
        super(id, poids);
    }
    
    @Override
    public double calculerPrix() {
        return getPoids() * getPrixParKilo();
    }
    
    @Override
    public boolean assezGrosse() {
        return getPoids() >= getPoidsAbattage();
    }
    
    @Override
    public double getPrixParKilo() {
        return getPrixKiloPoulet();
    }
    
    @Override
    public double getPoidsAbattage() {
        return getPoidsAbattagePoulet();
    }
    
    @Override
    public String toString() {
        return "Poulet " + super.toString() + ", Prix: " + calculerPrix() + " DT, " +
               (assezGrosse() ? "Prêt à abattre" : "Pas encore prêt");
    }
}

// Sous-classe Canard
class Canard extends Volaille {
    
    public Canard(int id, double poids) {
        super(id, poids);
    }
    
    @Override
    public double calculerPrix() {
        return getPoids() * getPrixParKilo();
    }
    
    @Override
    public boolean assezGrosse() {
        return getPoids() >= getPoidsAbattage();
    }
    
    @Override
    public double getPrixParKilo() {
        return getPrixKiloCanard();
    }
    
    @Override
    public double getPoidsAbattage() {
        return getPoidsAbattageCanard();
    }
    
    @Override
    public String toString() {
        return "Canard " + super.toString() + ", Prix: " + calculerPrix() + " DT, " +
               (assezGrosse() ? "Prêt à abattre" : "Pas encore prêt");
    }
}

// Classe Elevage
class Elevage {
    private Volaille[] volailles;
    private int nombreVolailles;
    private int capacite;
    
    // Constructeur avec capacité initiale
    public Elevage(int capaciteInitiale) {
        this.capacite = capaciteInitiale;
        this.volailles = new Volaille[capaciteInitiale];
        this.nombreVolailles = 0;
    }
    
    // Constructeur par défaut
    public Elevage() {
        this(10); // Capacité par défaut de 10
    }
    
    // Méthode pour ajouter une volaille
    public void ajouter(Volaille v) {
        // Vérifier si le tableau est plein
        if (nombreVolailles >= capacite) {
            // Agrandir le tableau
            agrandirTableau();
        }
        volailles[nombreVolailles] = v;
        nombreVolailles++;
        System.out.println("Volaille ajoutée: " + v);
    }
    
    // Méthode privée pour agrandir le tableau
    private void agrandirTableau() {
        capacite *= 2;
        Volaille[] nouveauTableau = new Volaille[capacite];
        for (int i = 0; i < nombreVolailles; i++) {
            nouveauTableau[i] = volailles[i];
        }
        volailles = nouveauTableau;
        System.out.println("Capacité de l'élevage augmentée à " + capacite);
    }
    
    // Méthode pour rechercher une volaille par ID
    public Volaille rechercher(int id) {
        for (int i = 0; i < nombreVolailles; i++) {
            if (volailles[i].getId() == id) {
                return volailles[i];
            }
        }
        return null; // Non trouvé
    }
    
    // Méthode pour obtenir la liste des animaux à abattre
    public Volaille[] envoyerALAbattoir() {
        // Compter combien de volailles sont prêtes
        int count = 0;
        for (int i = 0; i < nombreVolailles; i++) {
            if (volailles[i].assezGrosse()) {
                count++;
            }
        }
        
        // Créer le tableau de résultat
        Volaille[] aAbattre = new Volaille[count];
        int index = 0;
        for (int i = 0; i < nombreVolailles; i++) {
            if (volailles[i].assezGrosse()) {
                aAbattre[index] = volailles[i];
                index++;
            }
        }
        
        return aAbattre;
    }
    
    // Méthode pour afficher toutes les volailles
    public void afficher() {
        System.out.println("\n=== LISTE DES VOLAILLES DANS L'ÉLEVAGE ===");
        if (nombreVolailles == 0) {
            System.out.println("L'élevage est vide.");
            return;
        }
        
        for (int i = 0; i < nombreVolailles; i++) {
            System.out.println((i+1) + ". " + volailles[i]);
        }
        System.out.println("Total: " + nombreVolailles + " volailles");
        
        // Afficher les statistiques
        afficherStatistiques();
    }
    
    // Méthode privée pour afficher les statistiques
    private void afficherStatistiques() {
        int poulets = 0;
        int canards = 0;
        int pretsAbattre = 0;
        
        for (int i = 0; i < nombreVolailles; i++) {
            if (volailles[i] instanceof Poulet) {
                poulets++;
            } else if (volailles[i] instanceof Canard) {
                canards++;
            }
            
            if (volailles[i].assezGrosse()) {
                pretsAbattre++;
            }
        }
        
        System.out.println("\n=== STATISTIQUES ===");
        System.out.println("Poulets: " + poulets);
        System.out.println("Canards: " + canards);
        System.out.println("Prêts à abattre: " + pretsAbattre);
    }
    
    // Getter pour le nombre de volailles
    public int getNombreVolailles() {
        return nombreVolailles;
    }
}

// Classe principale de test
public class TestElevage {
    public static void main(String[] args) {
        System.out.println("=== TEST DU SYSTÈME DE GESTION D'ÉLEVAGE DE VOLAILLES ===\n");
        
        // 1. Création d'un élevage
        Elevage elevage = new Elevage(5);
        
        // 2. Création de quelques volailles
        Poulet poulet1 = new Poulet(1, 1.0);
        Poulet poulet2 = new Poulet(2, 1.5);
        Canard canard1 = new Canard(3, 1.2);
        Canard canard2 = new Canard(4, 1.8);
        Poulet poulet3 = new Poulet(5, 1.4);
        
        // 3. Ajout des volailles à l'élevage
        System.out.println("=== AJOUT DES VOLAILLES ===");
        elevage.ajouter(poulet1);
        elevage.ajouter(poulet2);
        elevage.ajouter(canard1);
        elevage.ajouter(canard2);
        elevage.ajouter(poulet3);
        
        // 4. Affichage de toutes les volailles
        elevage.afficher();
        
        // 5. Test de recherche
        System.out.println("\n=== TEST DE RECHERCHE ===");
        Volaille trouvee = elevage.rechercher(3);
        if (trouvee != null) {
            System.out.println("Volaille trouvée: " + trouvee);
        } else {
            System.out.println("Volaille non trouvée.");
        }
        
        // 6. Test de changement de poids
        System.out.println("\n=== TEST CHANGEMENT DE POIDS ===");
        System.out.println("Avant changement - Poulet ID 1: " + poulet1);
        poulet1.changerPoids(1.4);
        System.out.println("Après changement - Poulet ID 1: " + poulet1);
        
        // 7. Test d'envoi à l'abattoir
        System.out.println("\n=== VOLAILLES PRÊTES POUR L'ABATTOIR ===");
        Volaille[] aAbattre = elevage.envoyerALAbattoir();
        if (aAbattre.length == 0) {
            System.out.println("Aucune volaille n'est prête pour l'abattoir.");
        } else {
            for (int i = 0; i < aAbattre.length; i++) {
                System.out.println((i+1) + ". " + aAbattre[i]);
            }
            System.out.println("Total à abattre: " + aAbattre.length + " volailles");
        }
        
        // 8. Test de modification des prix et poids d'abattage
        System.out.println("\n=== MODIFICATION DES PRIX ET POIDS ===");
        System.out.println("Ancien prix poulet: " + Volaille.getPrixKiloPoulet() + " DT/kg");
        System.out.println("Ancien prix canard: " + Volaille.getPrixKiloCanard() + " DT/kg");
        
        Volaille.setPrixKiloPoulet(1.1); // Augmentation du prix des poulets
        Volaille.setPrixKiloCanard(1.3); // Augmentation du prix des canards
        Volaille.setPoidsAbattagePoulet(1.4); // Augmentation du poids d'abattage des poulets
        
        System.out.println("Nouveau prix poulet: " + Volaille.getPrixKiloPoulet() + " DT/kg");
        System.out.println("Nouveau prix canard: " + Volaille.getPrixKiloCanard() + " DT/kg");
        System.out.println("Nouveau poids abattage poulet: " + Volaille.getPoidsAbattagePoulet() + " kg");
        
        // 9. Affichage final après modifications
        System.out.println("\n=== SITUATION FINALE DE L'ÉLEVAGE ===");
        elevage.afficher();
        
        // 10. Test avec un élevage vide
        System.out.println("\n=== TEST AVEC UN ÉLEVAGE VIDE ===");
        Elevage elevageVide = new Elevage();
        elevageVide.afficher();
        
        System.out.println("\n=== FIN DU TEST ===");
    }
}