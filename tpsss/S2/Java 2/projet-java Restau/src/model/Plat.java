package model;

public class Plat {
    private int idPlat;
    private String nomPlat;
    private double prix;
    private String typeMenu;  
    private String typePlat;  
    private String description;
    
    
    public Plat() {
        super();
    }
    
    
    public Plat(int id, String nom, double p, String typeMenu, String typePlat) {
        this.idPlat = id;
        this.nomPlat = nom;
        this.prix = p;
        this.typeMenu = typeMenu;
        this.typePlat = typePlat;
    }
    
    
    public Plat(int id, String nom, double p, String typeMenu, String typePlat, String description) {
        this(id, nom, p, typeMenu, typePlat);
        this.description = description;
    }
    
    
    public int getIdPlat() {
        return idPlat;
    }
    
    public void setIdPlat(int id) {
        this.idPlat = id;
    }
    
    public String getNomPlat() {
        return nomPlat;
    }
    
    public void setNomPlat(String nom) {
        this.nomPlat = nom;
    }
    
    public double getPrix() {
        return prix;
    }
    
    public void setPrix(double prix) {
        this.prix = prix;
    }
    
    public String getTypeMenu() {
        return typeMenu;
    }
    
    public void setTypeMenu(String typeMenu) {
        this.typeMenu = typeMenu;
    }
    
    public String getTypePlat() {
        return typePlat;
    }
    
    public void setTypePlat(String typePlat) {
        this.typePlat = typePlat;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return "Plat [idPlat=" + idPlat + ", nomPlat=" + nomPlat + ", prix=" + prix 
                + ", typeMenu=" + typeMenu + ", typePlat=" + typePlat 
                + ", description=" + description + "]";
    }
}
