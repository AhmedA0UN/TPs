package metier;

public class Plat {
    private int idPlat;
    private String nomPlat;
    private double prix;
    private String typeMenu;  // Remplace 'type' (ex: "Entrée", "Plat principal", "Dessert")
    private String typePlat;  // Nouvel attribut (ex: "Végétarien", "Vegan", "Sans gluten")
    private String description;
    
    // Constructeur par défaut
    public Plat() {
        super();
    }
    
    // Constructeur de base
    public Plat(int id, String nom, double p, String typeMenu, String typePlat) {
        this.idPlat = id;
        this.nomPlat = nom;
        this.prix = p;
        this.typeMenu = typeMenu;
        this.typePlat = typePlat;
    }
    
    // Constructeur complet avec description
    public Plat(int id, String nom, double p, String typeMenu, String typePlat, String description) {
        this(id, nom, p, typeMenu, typePlat);
        this.description = description;
    }
    
    // Getters et setters
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