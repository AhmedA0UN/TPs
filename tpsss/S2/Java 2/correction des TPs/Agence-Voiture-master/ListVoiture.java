import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListVoiture{
	
		private List<Voiture> voitures;
		
		public ListVoiture(List<Voiture> voitures) 
		{
			if(voitures==null)
				this.voitures=new ArrayList<Voiture>(voitures);
			this.voitures=voitures;
		}
		
		public ListVoiture() {
			
				this.voitures=new ArrayList<Voiture>();

			
		}
		public List<Voiture> getVoitures() {
			return this.voitures;
		}
		public void setVoitures(List<Voiture> voitures) {
			if(voitures==null)
				this.voitures=new ArrayList<Voiture>(voitures);
			this.voitures=voitures;
		}
		public void ajoutVoiture(Voiture v) throws VoitureException
		{
			if(voitures.contains(v))
				{
				throw new VoitureException("la voiture existe deja dans la liste");
				}
				this.voitures.add(v);
				
		}
		public void supprimeVoiture(Voiture v)throws VoitureException{
			
			if(voitures.contains(v))
			{
				this.voitures.remove(v);
			
			}
			else
			{throw new VoitureException("la voiture n'existe pas dans la liste");}
				  
		}
		public Iterator<Voiture> iterateur()
		{
		   return voitures.iterator();
		}
		public int size(){
		return voitures.size();
		}
		public void affiche(){
			Iterator<Voiture> itr=this.voitures.iterator();
		    while(itr.hasNext())
		    	System.out.println(itr.next());
		    
		    /*for(int i=0;i<size();i++)
		    	System.out.println(voitures.get(i));
		    
		    for(Voiture v:this.voitures)
		    	System.out.println(v);*/
		}}	
