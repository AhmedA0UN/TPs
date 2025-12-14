
public class Etudiant extends Personne {
	protected int age;
	public Etudiant(String nom,int age) {
		super(nom);
	this.age=age;
		}
	@Override
	public void affiche() {
		System.out.println("Je suis " + nom + " l’étudiant");
		}
}
