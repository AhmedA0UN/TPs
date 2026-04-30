public class Compte {
private int numero;
private double solde;
private final static int nbMaxCompte=100;
public static int nbCompteTotale=0;



public Compte()
{
numero=0;
solde=0;
nbCompteTotale++;
}
public static int getNbmaxcompte() {
	return nbMaxCompte;
}
public Compte(int numero,double solde)
{
this.numero=numero;
this.solde=solde;
nbCompteTotale++;
}

/* pour faire un dépôt sur le compte. */
void depot(double valeur)
{
	this.solde=this.solde+valeur;
}
/* pour faire un retrait sur le compte. */
void retrait(double valeur)
{
	this.solde=this.solde-valeur;
}
/* pour obtenir la valeur du solde */
public double getSolde()
{
	return solde;
}
/* pour afficher le solde */
void afficherSolde()
{
	System.out.println("le solde est: "+solde);
}

public void setSolde(double s) 
{
	if(s>0)
		solde=s;
	else
		System.out.println("solde doit etre positif");
}

public String toString()
{
	return "le numero du compte est: "+numero+" le solde est: "+solde;
}
public void virer(float valeur, Compte destinataire)
{
	//destinataire.solde=destinataire.solde+valeur;
	destinataire.depot(valeur);
	this.retrait(valeur);
	
}
public static Compte plusSolde(Compte c1,Compte c2)
{
if (c1.solde>c2.solde)
	return c1;
else if(c2.solde>c1.solde)
	return c2;
else
	return null;
}

public boolean equals(Object obj) 
{
	if(this==obj)
		return true;
	if (obj==null)
		return false;
	if(this.getClass()!=obj.getClass())
		return false;
	Compte c=(Compte)obj;
	return c.numero==numero;
	
}
}
