abstract class Personne {
    protected String nom;

    public Personne(String nom) {
        this.nom = nom;
    }

    public abstract void affiche();
}


class Etudiant extends Personne {
    public Etudiant(String nom) {
        super(nom);
    }

    @Override
    public void affiche() {
        System.out.println("Je suis " + nom + " l etudiant");
    }
}


class Enseignant extends Personne {
    public Enseignant(String nom) {
        super(nom);
    }

    @Override
    public void affiche() {
        System.out.println("Je suis " + nom + " l enseignant");
    }
}



class Agent extends Personne {
    public Agent(String nom) {
        super(nom);
    }

    @Override
    public void affiche() {
        System.out.println("Je suis " + nom + " l agent administratif");
    }
}


public class TestPersonne { 
public static void main(String[] args) { 
Personne[] personnes = new Personne[4]; 
personnes[0] = new Etudiant("Ali"); 
personnes[1] = new Enseignant("Sofiane"); 
personnes[2] = new Agent("Hichem"); 
personnes[3] = new Etudiant("Hichem"); 
for (Personne p : personnes) 
p.affiche(); 
} 
}