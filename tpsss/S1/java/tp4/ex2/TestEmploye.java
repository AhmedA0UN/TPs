import java.time.LocalDate;

// Classe abstraite Employe
abstract class Employe {
    protected String nom;
    protected String prenom;
    protected int age;
    protected LocalDate dateEntree;

    public Employe(String nom, String prenom, int age, LocalDate dateEntree) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.dateEntree = dateEntree;
    }

    public abstract double calculerSalaire();

    public String getNom() {
        return "L'employe " + prenom + " " + nom;
    }
}

// Classe intermédiaire pour les commerciaux
abstract class Commercial extends Employe {
    protected double chiffreAffaire;

    public Commercial(double chiffreAffaire, String nom, String prenom, int age, LocalDate dateEntree) {
        super(nom, prenom, age, dateEntree);
        this.chiffreAffaire = chiffreAffaire;
    }

}

// Vente 
class Vente extends Commercial {

    public Vente(double chiffreAffaire, String nom, String prenom, int age, LocalDate dateEntree) {
        super(chiffreAffaire, nom, prenom, age, dateEntree);
    }
    

    @Override
    public double calculerSalaire() {
        return 0.2 * chiffreAffaire + 400;
    }
    


    @Override
    public String getNom() {
        return "Le vendeur " + prenom + " " + nom;
    }
}

// Représentation
class Representation extends Commercial {

    public Representation(double chiffreAffaire, String nom, String prenom, int age, LocalDate dateEntree) {
        super(chiffreAffaire, nom, prenom, age, dateEntree);
    }

    
    @Override
    public double calculerSalaire() {
        return 0.2 * chiffreAffaire + 800;
    }


    @Override
    public String getNom() {
        return "Le representant " + prenom + " " + nom;
    }
}

// Production
class Production extends Employe {
    protected int nbUnites;

    public Production(String nom, String prenom, int age, LocalDate dateEntree, int nbUnites) {
        super(nom, prenom, age, dateEntree);
        this.nbUnites = nbUnites;
    }

    @Override
    public double calculerSalaire() {
        return nbUnites * 5;
    }

    @Override
    public String getNom() {
        return "L'ouvrier de production " + prenom + " " + nom;
    }
}

// Manutention
class Manutention extends Employe {
    protected int heures;

    public Manutention(String nom, String prenom, int age, LocalDate dateEntree, int heures) {
        super(nom, prenom, age, dateEntree);
        this.heures = heures;
    }

    @Override
    public double calculerSalaire() {
        return heures * 65;
    }

    @Override
    public String getNom() {
        return "L'agent de manutention " + prenom + " " + nom;
    }
}

// Production à risque
class ProductionARisque extends Production {
    private static final double PRIME_RISQUE = 200;

    public ProductionARisque(String nom, String prenom, int age, LocalDate dateEntree, int nbUnites) {
        super(nom, prenom, age, dateEntree, nbUnites);
    }

    @Override
    public double calculerSalaire() {
        return super.calculerSalaire() + PRIME_RISQUE;
    }

    @Override
    public String getNom() {
        return "L'ouvrier de production a risque " + prenom + " " + nom;
    }
}

// Manutention à risque
class ManutentionARisque extends Manutention {
    private static final double PRIME_RISQUE = 200;

    public ManutentionARisque(String nom, String prenom, int age, LocalDate dateEntree, int heures) {
        super(nom, prenom, age, dateEntree, heures);
    }

    @Override
    public double calculerSalaire() {
        return super.calculerSalaire() + PRIME_RISQUE;
    }

    @Override
    public String getNom() {
        return "L'agent de manutention a risque " + prenom + " " + nom;
    }
}

// Classe de test
public class TestEmploye {
    public static void main(String[] args) {
        Employe[] staff = {
            new Vente("Ben Ali", "Ahmed", 30, LocalDate.of(2020, 1, 10), 20000),
            new Representation("Kacem", "Sami", 40, LocalDate.of(2018, 5, 20), 15000),
            new Production("Trabelsi", "Nadia", 28, LocalDate.of(2021, 3, 15), 1000),
            new Manutention("Haddad", "Omar", 35, LocalDate.of(2019, 7, 1), 160),
            new ProductionARisque("Mansour", "Leila", 32, LocalDate.of(2022, 2, 5), 1200),
            new ManutentionARisque("Saidi", "Karim", 29, LocalDate.of(2023, 9, 12), 170)
        };

        for (Employe e : staff) {
            System.out.println(e.getNom() + " gagne " + e.calculerSalaire() + " Dinars.");
        }
    }
}