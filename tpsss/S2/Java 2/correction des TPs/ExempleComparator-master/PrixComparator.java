import java.util.Comparator;

public class PrixComparator implements Comparator<Jouet>{

	@Override

	public int compare(Jouet jouet1, Jouet jouet2) {
		
		
		
		if(jouet1.getPrix()==jouet2.getPrix())
			
			return jouet1.getNom().compareTo(jouet2.getNom());
					
		return jouet1.getPrix()-jouet2.getPrix();
			
	}


}
