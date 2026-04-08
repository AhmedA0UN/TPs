// ==================== Classe CriterePrix ====================
public class CriterePrix implements Critere {
    private double prixMax;

    public CriterePrix(double prixMax) {
        this.prixMax = prixMax;
    }

    @Override
    public boolean estSatisfaitPar(Voiture v) {
        return v.getPrix() <= prixMax;
    }
}