package Librairie;

public class Livre {
private String titre,auteur;
private int nbPages;
private static int nbLivres=0;
//Constructeur
public Livre() {
	titre = "";
    auteur = "";
    nbPages=0;
    nbLivres++;
}
public Livre(String unAuteur,String unTitre) {
	titre = unTitre;
	auteur = unAuteur;
	nbLivres++;
}
public Livre(String unAuteur,String unTitre,int unNbPages) {
	titre = unTitre;
    auteur = unAuteur;
    nbPages=unNbPages;
    nbLivres++;
}
public String getTitre() {
	return titre;
}

public void setTitre(String titre) {
	this.titre = titre;
}

public int getNbPages() {
	return nbPages;
}
public void setNbPages(int nbPages) {
	if(nbPages>0)
	this.nbPages = nbPages;
	else
		System.out.println("le nombre de pages doit etre positive");
		
}
public void setAuteur(String auteur) {
	this.auteur = auteur;
}

public String getAuteur() {
	return auteur;
}

public void afficher()

{
	//System.out.println("le livre : "+titre+" de "+auteur+" "+nbPages+" pages");
	System.out.println(toString());
}
public static int getNbLivres()
{
	return nbLivres;
	}
@Override
public String toString()
{
	return "le livre : "+titre+" de "+auteur+" "+nbPages+" pages";
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (this.getClass() != obj.getClass())
		return false;
	
	Livre liv = (Livre) obj;
	
return auteur.equals(liv.auteur)& titre.equals(liv.titre)& nbPages==liv.nbPages;
}

}
