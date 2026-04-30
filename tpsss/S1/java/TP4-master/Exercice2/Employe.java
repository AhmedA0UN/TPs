package Exercice2;

public abstract class Employe {
	    private String nom;
	    private String prenom;
	    private int age;
	    private String date;


    public Employe(int age, String date, String nom, String prenom) {
        this.age = age;
        this.date = date;
        this.nom = nom;
        this.prenom = prenom;
    }

	 
	public abstract double calculerSalaire();
    public String getTitre()
	{
		return "L'employé ";
	}
	public String getNom() {
	    return  getTitre()+ prenom + " " + nom;
	}
}

