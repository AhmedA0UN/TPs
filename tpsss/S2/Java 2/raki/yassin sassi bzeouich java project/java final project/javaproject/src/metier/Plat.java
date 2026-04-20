package metier;

public class Plat {
	
	private int idPlat;
	private String nomPlat;
	private double prix;
	private String type;
	
	public Plat() {
		super();
	}
public Plat(int id,String nom,double p,String t) {
	super();
	idPlat=id;
	nomPlat=nom;
	type=t;
	prix=p;
	
}
public String getType() {
	return type;
}
public void setType(String t) {
	type=t;
}
public int getIdPlat() {
	return idPlat;
}
public  String getNomPlat() {
	return nomPlat;
}
public void setIdPlat(int id) {
	idPlat=id;
}
public void setNomPlat(String nom) {
	nomPlat=nom;
}
public double getPrix() {
	return prix;
}
public void setPrix(double d) {
	prix=d;
}




}

