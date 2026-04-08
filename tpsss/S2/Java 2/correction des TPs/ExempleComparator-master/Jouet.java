import java.util.Objects;

public class Jouet implements Comparable<Jouet>{
private String nom;
private int prix;
private String desc;
private int promo;
public Jouet(String nom, int prix, String desc, int promo) {
	super();
	this.nom = nom;
	this.prix = prix;
	this.desc = desc;
	this.promo = promo;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public int getPrix() {
	return prix;
}
public void setPrix(int prix) {
	this.prix = prix;
}
public String getDesc() {
	return desc;
}
public void setDesc(String desc) {
	this.desc = desc;
}
public int getPromo() {
	return promo;
}
public void setPromo(int promo) {
	this.promo = promo;
}
@Override
public int compareTo(Jouet jouet2) {

return this.promo-jouet2.promo;

			 
}



}
