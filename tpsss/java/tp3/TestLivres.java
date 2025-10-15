import librairie.Livre;

public class TestLivres {
    public static void main(String[] args) {

        
        Livre livre1 = new Livre("Bruce Eckel", "Thinking in Java");
        Livre livre2 = new Livre("Claude Delannoy", "Programmer en Java");

        
        livre1.setNbPages(800);
        livre2.setNbPages(650);

        
        System.out.println("Auteur du livre 1 : " + livre1.getAuteur());
        System.out.println("Auteur du livre 2 : " + livre2.getAuteur());

        
        livre1.afficher();
        livre2.afficher();

        
        int totalPages = livre1.getNbPages() + livre2.getNbPages();
        System.out.println("Nombre total de pages : " + totalPages);

        
        System.out.println(livre1);

        
        Livre livre3 = new Livre();
        livre3.setAuteur("J.K. Rowling");
        livre3.setTitre("Harry Potter");
        livre3.setNbPages(400);
        livre3.afficher();

        Livre livre4 = new Livre("George Orwell", "1984", 300);
        livre4.afficher();

        Livre livre5 = new Livre("George Orwell", "1984", 300);
        System.out.println("livre4 == livre5 ? " + (livre4 == livre5)); 
        System.out.println("livre4.equals(livre5) ? " + livre4.equals(livre5)); 
        
        System.out.println("Nombre total de livres créés : " + Livre.getNbLivres());
    }
}
