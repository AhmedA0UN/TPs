
public class Enseignant extends Personne{
	public Enseignant(String nom) {
		super(nom);
		}
		public void affiche() {
		System.out.println( "Je suis " + super.nom + " l’enseignant");
		}
		
}
