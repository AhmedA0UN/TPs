package modele;

public class Categorie {
private int code;
private String nom;
public int getCode() {
	return code;
}
public void setCode(int code) {
	this.code = code;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public Categorie(int code, String nom) {

	this.code = code;
	this.nom = nom;
}
public Categorie(String nom) {


	this.nom = nom;
}
public Categorie() {
	super();
}
@Override
public String toString() {
	return "Categorie [code=" + code + ", nom=" + nom + "]";
}
}