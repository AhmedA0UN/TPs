class Voiture {
    private int code;
    private String nom;
    private String prenom;

    public Voiture(int code, String nom, String prenom) {
        this.code = code;
        this.nom = nom;
        this.prenom = prenom;
    }


    public int getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    
    @Override
    public String toString() {
        return "Voiture [code=" + code + ", nom=" + nom + ", prenom=" + prenom + "]";
    }
}