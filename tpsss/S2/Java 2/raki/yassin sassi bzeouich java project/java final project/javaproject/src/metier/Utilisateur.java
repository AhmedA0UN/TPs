package metier;

public class Utilisateur {
    private int idU;
    private String nomU;
    private String mdpU;
    private String role;

    public Utilisateur() {
    }

    public Utilisateur(int idU, String nomU, String mdpU, String role) {
        this.idU = idU;
        this.nomU = nomU;
        this.mdpU = mdpU;
        this.role = role;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public String getNomU() {
        return nomU;
    }

    public void setNomU(String nomU) {
        this.nomU = nomU;
    }

    public String getMdpU() {
        return mdpU;
    }

    public void setMdpU(String mdpU) {
        this.mdpU = mdpU;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

