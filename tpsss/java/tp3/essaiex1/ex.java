public class Livre {
    private String titre,auteur;
    private int nbPages;
    //constructeur
    public Livre(String unAuteur,String unTitre){
        titre = unTitre;
        auteur = unAuteur;
    }
    //Accesseur
    public String getAuteur(){
        return auteur;
    }

    public String getTitre(){
        return auteur;
    }

    public String getNbPages(){
        return nbPages;
    }

    //Modificateur
    public void setNbPages(int nbPages){
        if(nbPages > 0){
            this.nbPages = nbPages;
        } else {
            System.out.println("Error: le nbre de page doit etre positive");
    }
        
    }

    public void setAuteur(String auteur){
        this.auteur = auteur;
    }

    public void setTitre(String titre){
        this.titre = titre;
    }

    public void afficher() {
        System.out.println(this.toString());
    }

    
    public String toString() {
        return "Livre : \"" + titre + "\" de " + auteur + " (" + nbPages + " pages)";
    }

}


public class TestLivres{
    public static void main(String[] args){
        Livre livre1 = new Livre("Bruce Eckel","Thinking in java");
        Livre livre2 = new Livre("Claude Deelannoy","Programmer en java");
        
        System.out.println("Auteur du livre 1 : " + livre1.getAuteur());
        System.out.println("Auteur du livre 2 : " + livre2.getAuteur());

        livre1.setNbPages(10);
        livre2.setNbPages(15);

        livre1.afficher();
        livre2.afficher();

        int totalPages = livre1.getNbPages() + livre2.getNbPages();
        System.out.println("Nombre total de pages : " + totalPages);

        System.out.println(livre1);

    }
}