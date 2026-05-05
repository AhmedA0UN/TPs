package modele;



public class Produit {
private String reference;
private String nom;
private double prix;
private int qte;
private Categorie cat;

public Categorie getCat() {
	return cat;
}
public void setCat(Categorie cat) {
	this.cat = cat;
}
public String getReference() {
	return reference;
}
public void setReference(String reference) {
	this.reference = reference;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public double getPrix() {
	return prix;
}
public void setPrix(double prix) {
	this.prix = prix;
}
public int getQte() {
	return qte;
}
public void setQte(int qte) {
	this.qte = qte;
}
public Produit(String reference, String nom, double prix, int qte,Categorie cat) {
	super();
	this.reference = reference;
	this.nom = nom;
	this.prix = prix;
	this.qte = qte;
	this.cat=cat;
}
public Produit() {
	super();

}

}
