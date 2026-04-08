
public class TestAgence {

public static void main(String args[])
{
	try
	{
	Agence a =new Agence("TunAgence");
	Voiture v1=new Voiture(134,"Ford",50000);
	Voiture v2=new Voiture(135,"Renault",88000);
	Voiture v3=new Voiture(136,"Hyundai",75000);
	Voiture v4=new Voiture(137,"Nissan",41200);
	Voiture v5=new Voiture(138,"BMW",525800);
	
	Client c1 =new Client(1,"A","Ali");
	Client c2 =new Client(2,"F","Fatma");
	Client c3 =new Client(3,"D","Dalel");
	Client c4 =new Client(4,"G","Gaddour");
	

	a.ajoutVoiture(v1);
	a.ajoutVoiture(v2);
	a.ajoutVoiture(v3);
	a.ajoutVoiture(v4);
	a.ajoutVoiture(v5);

	a.loueClientVoiture(c1,v1);
	
	a.loueClientVoiture(c2, v2);
	a.loueClientVoiture(c1, v3);
	a.afficheLesClientsEtLeursListesVoitures();
	a.retourClientVoiture(c1, v1);
	a.afficheLesClientsEtLeursListesVoitures();
	}
	catch(VoitureException e)
	{
	   System.out.println(e.getMessage());
	}
	
	
}


}
