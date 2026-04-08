package Exercice3;
public class Entreprises {
	public static void tousLesEntreprises(Entreprise[] e){
		for (int i = 0; i < e.length; i++){
		try{
			
		System.out.println("Mission" + (i+1) + " = " + e[i].mission());
		}
        catch(SecretMissionException a){	
        	
		}
		try
		{
			System.out.println("Capital" + (i+1) + " = " + e[i].capital());
		}
		catch(NonProfitException b)
		{
		}
		}
		}
	public static void main(String[] args){
		Entreprise [] t = new Entreprise[6];
		Entreprise Oored = new Entreprise("Ooredoo", "Telcom", 1000, 1900);
		Entreprise Poul = new Entreprise("Poulina", "Aviculture", 2000, 1500);
		Entreprise Meub = new Entreprise("Meublatex", "Meuble", 100, 170);
		Entreprise Mono = new Entreprise("Monoprix", "Supermarché", 400, 950);

		Entreprise CIA = new EntrepriseSecrete("CIA", "Spy Bill Gates", 23000, 35500);
		Entreprise CR = new EntrepriseSansProfit("CroixRouge", "look after yours health", 20, 500);

		t[0] = Oored;
		t[1] = Poul;
		t[2] = Meub;
		t[3] = Mono;
		t[4] = CIA;
		t[5] = CR;
		tousLesEntreprises(t);
		}
		} 


