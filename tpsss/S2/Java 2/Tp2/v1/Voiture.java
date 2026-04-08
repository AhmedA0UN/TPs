// ==================== Classe Voiture ====================
import java.util.Objects;

public class Voiture {
    private String immatriculation;
    private String marque;
    private double prix;

    public Voiture(String immatriculation, String marque, double prix) {
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.prix = prix;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public String getMarque() {
        return marque;
    }

    public double getPrix() {
        return prix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voiture voiture = (Voiture) o;
        return immatriculation.equals(voiture.immatriculation) && marque.equals(voiture.marque);
    }

    @Override
    public int hashCode() {
        return Objects.hash(immatriculation, marque);
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "immatriculation='" + immatriculation + '\'' +
                ", marque='" + marque + '\'' +
                ", prix=" + prix +
                '}';
    }
}