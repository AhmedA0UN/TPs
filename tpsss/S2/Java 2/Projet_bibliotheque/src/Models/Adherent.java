package Models;
import java.util.ArrayList;
import java.util.List;
public class Adherent extends Personne {
    private List<Document> docs_emp;
    public Adherent() {
        super(0,"" ,"" ,"" , "" ,"");
        this.docs_emp = new ArrayList<>();
    }
    
    public Adherent(int id, String nom, String prenom, String username, String mot_de_passe, String email) {
        super(id, nom, prenom, username, mot_de_passe, email);
        this.docs_emp = new ArrayList<>();
    }
    
    public List<Document> getDocs_emp() {
    	return docs_emp; }
    
    public void setDocs_emp(List<Document> docs_emp) {
    	this.docs_emp = docs_emp; }
    
    public List<Document> consulter_liste_doc() {
    	return docs_emp; }
    
    public boolean recherche_doc(String name) {
    	return false; }
    
    public void emprunte_doc(int id) {
    	
    }
}