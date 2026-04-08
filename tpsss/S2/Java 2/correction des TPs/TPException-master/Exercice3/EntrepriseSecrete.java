package Exercice3;

public class EntrepriseSecrete extends Entreprise
{
	EntrepriseSecrete(String nom, String mission, int nombre_employes, int capital)
	{
		super(nom, mission, nombre_employes, capital);
	}
	@Override
		public String mission() throws SecretMissionException{
		throw new SecretMissionException();
		}

}
