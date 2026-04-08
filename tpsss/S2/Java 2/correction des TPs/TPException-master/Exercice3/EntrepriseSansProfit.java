package Exercice3;

public class EntrepriseSansProfit extends Entreprise {
	EntrepriseSansProfit(String nom, String mission, int nombre_employes, int capital){
		super(nom, mission, nombre_employes, capital);
		}
	@Override
		public int capital() throws NonProfitException{
		throw new NonProfitException();
		}

}
