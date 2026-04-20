package metier;

public class Utilisateur {
    private int idU;
    private String nomU;
    private String mdpU;
    private String role;

    public Utilisateur(int idU, String nomU, String mdpU, String role) {
        this.idU = idU;
        this.nomU = nomU;
        this.mdpU = mdpU;
        this.role = role;
    }

    // Getters
    public int getIdU() {
        return idU;
    }

    public String getNomU() {
        return nomU;
    }

    public String getMdpU() {
        return mdpU;
    }

    public String getRole() {
        return role;
    }
    
    // Pour faciliter l'affichage
    @Override
    public String toString() {
        return "Utilisateur [idU=" + idU + ", nomU=" + nomU + ", role=" + role + "]";
    }
}