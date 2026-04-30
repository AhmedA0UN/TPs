
public class Agent extends Personne{
	public Agent(String nom) {
		super(nom);
		}
		public void affiche() {
		System.out.println( "Je suis " + super.nom + " l’agent administratif");
		}
}
