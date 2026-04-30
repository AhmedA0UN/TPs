interface Reservable {
    double calculerCout(double dureeHeures);
    String infoReservation(double dureeHeures);
    String getId();
    String getMarque();
    double getTarifHoraireBase();
}

abstract class MoyenTransport implements Reservable {
    private final String id;
    private final String marque;
    private final double tarifHoraireBase;

    protected MoyenTransport(String id, String marque, double tarifHoraireBase) {
        this.id = id;
        this.marque = marque;
        this.tarifHoraireBase = tarifHoraireBase;
    }

    @Override public String getId() { return id; }
    @Override public String getMarque() { return marque; }
    @Override public double getTarifHoraireBase() { return tarifHoraireBase; }

    protected String baseInfo() {
        return "ID: " + id + " | Marque: " + marque + " | Tarif horaire: " + tarifHoraireBase;
    }
}

class Voiture extends MoyenTransport {
    private final String carburant;

    public Voiture(String carburant, String id, double tarifHoraireBase, String marque) {
        super(id, marque, tarifHoraireBase);
        this.carburant = carburant;
    }

    

    @Override
    public double calculerCout(double dureeHeures) {
        double cout = getTarifHoraireBase() * dureeHeures;
        if (dureeHeures > 5) cout *= 0.9; 
        return cout;
    }

    @Override
    public String infoReservation(double dureeHeures) {
        return "Voiture | " + baseInfo() + " | Carburant: " + carburant +" | Durée: " + dureeHeures + "h | Prix final: " + calculerCout(dureeHeures);
    }
}

class Velo extends MoyenTransport {
    private final boolean avecVitesses;

    public Velo(String id, String marque, double tarifHoraireBase, boolean avecVitesses) {
        super(id, marque, tarifHoraireBase);
        this.avecVitesses = avecVitesses;
    }

    @Override
    public double calculerCout(double dureeHeures) {
        return getTarifHoraireBase() * dureeHeures;
    }

    @Override
    public String infoReservation(double dureeHeures) {
        return "Vélo | " + baseInfo() + " | Vitesses: " + (avecVitesses ? "oui" : "non") +" | Durée: " + dureeHeures + "h | Prix final: " + calculerCout(dureeHeures);
    }
}

class TrottinetteElectrique extends MoyenTransport {
    private double batterie;
    private final double coutRecharge;

    public TrottinetteElectrique(String id,String marque,double tarifHoraireBase,double batterie,double coutRecharge) {
        super(id, marque, tarifHoraireBase);
        this.batterie = batterie;
        this.coutRecharge = coutRecharge;
    }

    @Override
    public double calculerCout(double dureeHeures) {
        double cout = getTarifHoraireBase() * dureeHeures;
        if (batterie < 20) {
            cout += coutRecharge;
            batterie = 20;
        }
        return cout;
    }

    @Override
    public String infoReservation(double dureeHeures) {
        return "Trottinette | " + baseInfo() + " | Batterie: " + batterie + "%" +" | Durée: " + dureeHeures + "h | Prix final: " + calculerCout(dureeHeures);
    }
}

class Reservation {
    private final Reservable reservable;
    private final double duree;

    public Reservation(Reservable reservable, double duree) {
        this.reservable = reservable;
        this.duree = duree;
    }

    public void afficher() {
        System.out.println(reservable.infoReservation(duree));
    }
}

public class TestMoyenTransport {
    public static void main(String[] args) {
        
        Voiture voiture = new Voiture("cjhfffdn", "Toyota", 15.0, "Essence");
        Velo velo = new Velo("dzbzdhvu", "BMV", 5.0, true);
        TrottinetteElectrique trottinette = new TrottinetteElectrique("hdhdh", "Xiaomi", 8.0, 15.0, 3.5);
        
        System.out.println("");
        System.out.println("Test 1: Voiture 6h");
        new Reservation(voiture, 6).afficher();

        System.out.println("");
        System.out.println("Test 2: Vélo 3h");
        new Reservation(velo, 3).afficher();

        System.out.println("");
        System.out.println("Test 3: Trottinette batterie 15%");
        new Reservation(trottinette, 2).afficher();
    }
}


