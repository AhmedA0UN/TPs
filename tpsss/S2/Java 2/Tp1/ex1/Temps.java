// Exception personnalisée
class TempsException extends Exception {
    public TempsException(String message) {
        super(message);
    }
}

// Classe Temps
class Temps {
    private int heures;
    private int minutes;
    private int secondes;

    // Constructeur avec vérification
    Temps(int h, int m, int s) throws TempsException {
        if (h < 0 || h > 23 || m < 0 || m > 59 || s < 0 || s > 59) {
            throw new TempsException("Temps invalide");
        }
        heures = h;
        minutes = m;
        secondes = s;
    }

    // Méthode d'affichage pratique
    @Override
    public String toString() {
        return String.format("%02d:%02d:%02d", heures, minutes, secondes);
    }

    // Méthode main
    public static void main(String[] args) {
        try {
            // Exemple avec secondes invalides
            Temps t = new Temps(4, 12, 67);
            System.out.println("Temps créé : " + t);
        } catch (TempsException e) {
            System.out.println("Temps invalide");
        }
    }
}