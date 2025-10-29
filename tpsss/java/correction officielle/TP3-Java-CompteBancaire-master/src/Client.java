public class Client {
private String nom;
private Compte compte;
public Compte [] comptes=new Compte [100];
public  int nbrComptes;
public void setNom(String n)
{
	nom=n;
}
public Client()
{
	nom="";
	//compte=new Compte();
	//comptes[0]=new Compte();
	nbrComptes=0;
	
}

//
public Client(String nom,Compte[] c)
{
	this.nom=nom;
	this.comptes=c;
	nbrComptes=0;
}
public String getNom()
{
	return nom;
}
/*public double getSolde()
{
	return compte.getSolde();
}*/
public double getSoldeTotal()
{    double totale=0;
      
for(int i=0;i<nbrComptes;i++)
	
	totale=totale+comptes[i].getSolde();
		
	
	return totale;
}
public void ajouterCompte(Compte c)
{   
	if(nbrComptes<Compte.getNbmaxcompte())
	{comptes[nbrComptes]=c;
    nbrComptes++;
    }
	else
		System.out.println("Vous avez dépassez le nombre maximal");
   
}
	

public void afficherSolde()
{
	System.out.println("le solde est: "+getSoldeTotal());
}
/*public String toString()
{
	String s="le nom du client est: "+nom+" le compte du client est: "+compte.toString();
	return s;
}*/

public String toString()
{
	String s="le nom du client est: "+nom+" les comptes du client sont:\n";
	for (int i=0;i<nbrComptes;i++)
		
	s=s+comptes[i].toString()+"\n";
	    
		
	return s;
}
public boolean equals(Object cl)
{
	if(this==cl)
		return true;
	if (cl==null)
		return false;
	if(this.getClass()!=cl.getClass())
		return false;
	Client c=(Client)cl;
return c.nom.equals(this.nom);
		
}
}

