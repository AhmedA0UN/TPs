// On met la classe dans un package nommé librairie
package librairie;

public class Livre {
    private String titre;
    private String auteur;
    private int nbPages;

    private static int nbLivres = 0;

        public Livre() {
        nbLivres++;
    }

    public Livre(String unAuteur, String unTitre) {
        this.auteur = unAuteur;
        this.titre = unTitre;
        nbLivres++;
    }

    public Livre(String unAuteur, String unTitre, int nbPages) {
        this.auteur = unAuteur;
        this.titre = unTitre;
        setNbPages(nbPages); 
        nbLivres++;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getTitre() {
        return titre;
    }

    public int getNbPages() {
        return nbPages;
    }

    
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setNbPages(int nbPages) {
        if (nbPages > 0) {
            this.nbPages = nbPages;
        } else {
            System.out.println("Erreur : le nombre de pages doit être positif !");
        }
    }

    
    public void afficher() {
        System.out.println(this.toString());
    }

    
    public String toString() {
        return "Livre : \"" + titre + "\" de " + auteur + " (" + nbPages + " pages)";
    }

    
    public boolean equals(Object o) {
        if (o instanceof Livre) {
            Livre autre = (Livre) o;
            return this.titre.equals(autre.titre)
                    && this.auteur.equals(autre.auteur)
                    && this.nbPages == autre.nbPages;
        }
        return false;
    }


    public static int getNbLivres() {
        return nbLivres;
    }
}
