public class Banque {
public static void main(String arg[])
{
	
	Compte c1=new Compte(120520,200.000); 
	Compte c2=new Compte(120521,800.000); 
	Compte c3=new Compte(120522,100.000); 
	Compte c4=new Compte(120523,500.000); 
	Compte c5=new Compte(120524,400.000); 
	Compte [] comptescl1=new Compte [Compte.getNbmaxcompte()];
	Compte [] comptescl2=new Compte [Compte.getNbmaxcompte()];
	//comptescl1[0]=c1;
	//comptescl1[1]=c2;
	//comptescl1[2]=c3;
	//comptescl2[0]=c4;
	//comptescl2[1]=c5;
	Client cl1=new Client("ali",comptescl1);
	Client cl2=new Client("mohamed",comptescl2);
	cl1.ajouterCompte(c1);
	cl1.ajouterCompte(c2);
	cl1.ajouterCompte(c3);
	cl2.ajouterCompte(c4);
	cl2.ajouterCompte(c5);
	System.out.println("nombre de compte du client:"+cl1.getNom()+" :"+cl1.nbrComptes);
	cl1.afficherSolde();
	cl2.afficherSolde();
	System.out.println(cl1.toString());
	

}
}
