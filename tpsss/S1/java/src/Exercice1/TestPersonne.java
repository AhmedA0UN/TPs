
public class TestPersonne {
	public static void main(String[] args) {
		Personne[] personnes = new Personne[4];
		personnes[0] = new Etudiant("Ali",4);
		personnes[1] = new Enseignant("Sofiane");
		personnes[2] = new Agent("Hichem");
		personnes[3] = new Etudiant("Hichem",2);
		for (Personne p : personnes)
		{
		
			p.affiche();
		    }
		}
		
		}

