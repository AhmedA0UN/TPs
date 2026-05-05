package model;

public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String username;
    private String password;
    private String role;

    public Utilisateur() {
    }

    public Utilisateur(int idU, String nomU, String mdpU, String role) {
        this.id = idU;
        this.nom = nomU;
        this.password = mdpU;
        this.role = role;
    }

    public Utilisateur(int id, String nom, String prenom, String username, String password, String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters
    public int getIdU() {
        return id;
    }

    public int getId() {
        return id;
    }

    public String getNomU() {
        return nom;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getUsername() {
        return username;
    }

    public String getMdpU() {
        return password;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean login(String username, String password) {
        return this.username != null && this.password != null
            && this.username.equals(username)
            && this.password.equals(password);
    }

    public String getNomComplet() {
        if (prenom == null || prenom.isBlank()) {
            return nom == null ? "" : nom;
        }
        if (nom == null || nom.isBlank()) {
            return prenom;
        }
        return prenom + " " + nom;
    }

    public String getInfos() {
        return "Utilisateur{id=" + id + ", nom='" + nom + "', prenom='" + prenom
            + "', username='" + username + "', role='" + role + "'}";
    }
    
    
    @Override
    public String toString() {
        return "Utilisateur [idU=" + id + ", nomU=" + nom + ", role=" + role + "]";
    }
}
