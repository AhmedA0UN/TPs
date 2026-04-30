class Marchandise { 
    private int poids; // Poids en kilogrammes
    public Marchandise(int poids){ 
        this.poids=poids ; 
    }
    public int getPoids(){ 
        return poids ; // Retourne le poids en kg
    }
}

abstract class Cargaison extends Marchandise{
    private int distance ;
    private int charge=0;

    public Cargaison(int charge, float distance) {
        this.charge = charge;
        this.distance = distance;
    }
    
    void ajouter(int poids){
        
    }

}