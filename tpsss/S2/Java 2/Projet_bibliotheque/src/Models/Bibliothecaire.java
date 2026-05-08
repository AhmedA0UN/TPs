package Models;
import java.util.ArrayList;
import java.util.List;
public class Bibliothecaire extends Personne {
    public Bibliothecaire() 
    { 
    	super(0, "", "", "", "", ""); 
    }
    
    public Bibliothecaire(int id, String nom, String prenom, String username, String mot_de_passe, String email) {
        super(id, nom, prenom, username, mot_de_passe, email);
    }
    public void ajouter_doc() {
    	
    }
    public void modifier_doc(Document doc) {
    	
    }
    public void del_doc(Document doc) {
    	
    }
    public void ajouter_adherent() {
    	
    }
    public void modifier_adherent(Adherent ad) {
    	
    }
    public void supprimer_adherent(Adherent ad) {
    	
    }
    public void creer_pret(Adherent ad, Document doc, String date_e, String date_r) {
    	
    }
    public List<Emprunt> consulter_pret() { 
    	return new ArrayList<>(); }
    public void modifier_date(Emprunt e) {
    	
    }
    public void supp_pret(int id) 
    {}
    public List<Emprunt> consulter_retour() { return new ArrayList<>(
    		); }
    public void modifier_status(Emprunt e) {}
    public List<Emprunt> rechercherPretsParAdherent(int id) { return new ArrayList<>(); }
}