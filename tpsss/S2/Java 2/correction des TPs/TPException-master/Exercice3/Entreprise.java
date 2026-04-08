package Exercice3;

public class Entreprise {
	private String nom, mission;
	private int nombre_employes, capital;
	Entreprise(String nom, String mission, int nombre_employes, int capital){
	this.nom = nom;
	this.mission = mission;
	this.nombre_employes = nombre_employes;
	this.capital = capital;
	}
	public String mission() throws SecretMissionException
	{ return mission; }
	
	public int capital() throws NonProfitException
	{ return capital; }
	
}


