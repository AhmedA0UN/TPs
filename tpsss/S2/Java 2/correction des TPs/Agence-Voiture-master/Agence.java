import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BiConsumer;
public class Agence {

	private String nom;
	private ListVoiture vs;
	private Map<Client, ListVoiture> clientVoitureLoue;

	public Agence(String nom) {
		this.nom = nom;
		vs = new ListVoiture();
		clientVoitureLoue = new HashMap<>();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void ajoutVoiture(Voiture v) throws VoitureException {
		
		vs.ajoutVoiture(v);
		
	
	}

	public void suppVoiture(Voiture v) throws VoitureException {
		vs.supprimeVoiture(v);
	}

	public void loueClientVoiture(Client cl, Voiture v) throws VoitureException {
		
	suppVoiture(v);
		if (!clientVoitureLoue.containsKey(cl)) {
			         
			clientVoitureLoue.put(cl,new ListVoiture());
		   }
		       
		  clientVoitureLoue.get(cl).ajoutVoiture(v);
		
	}

	public void retourClientVoiture(Client cl, Voiture v) throws VoitureException {
		ajoutVoiture(v);
		
		clientVoitureLoue.get(cl).supprimeVoiture(v);
	}

	public List<Voiture> selectVoitureSelonCritere(Critere c) {
		
		List<Voiture> voitures = new ArrayList<>();
		
		for (Voiture v : vs.getVoitures()) {
			if (c.estSatisfaitPar(v)) {
				voitures.add(v);
			}
		}
	
		
		for (Map.Entry<Client, ListVoiture> entry : clientVoitureLoue.entrySet())
		{
			
			for (Voiture v : entry.getValue().getVoitures()) {
				if (c.estSatisfaitPar(v)) {
					voitures.add(v);
				}
			}
		}
		/*	
		while(clientVoitureLoue.values().iterator().hasNext())
		{
			
			for(Voiture v:clientVoitureLoue.values().iterator().next().getVoitures())
			{	if (c.estSatisfaitPar(v)) {
					voitures.add(v);
				}
			}
		}*/
		return voitures;
	}
///////////////////////////////////////////////////////////////
	public Set<Client> ensembleClientsLoueurs() {
		return clientVoitureLoue.keySet();
	}

	public Collection<ListVoiture> collectionVoituresLouees() {
		return clientVoitureLoue.values();
	}

	public void afficheLesClientsEtLeursListesVoitures() {
		
		clientVoitureLoue.forEach( 
				(k,v) -> 
		{
			System.out.println(k);
			v.affiche();
		});
	}

	public Map<Client, ListVoiture> triCodeCroissant() {
		Comparator<Client> c=(c1,c2)->{return c1.getCode()-c2.getCode();};
		Map<Client, ListVoiture> map = new TreeMap<>(c);
			map.putAll(clientVoitureLoue);
		return map;
	}

	public Map<Client, ListVoiture> triNomCroissant() {
		Comparator<Client> c=(c1, c2)-> c1.getNom().compareTo(c2.getNom());
		Map<Client, ListVoiture> map = new TreeMap<>(c);
		map.putAll(clientVoitureLoue);
		return map;
	}
}