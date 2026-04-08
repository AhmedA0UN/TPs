import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Jouets {

	public static void main(String[] args) {
	
	
		
     List<String> liste=new ArrayList<>();
     liste.add("bkjsqhflk");
     liste.add("aze");
     liste.add("fgtr");
     Collections.sort(liste);
     //for(String s:liste)
    	 //System.out.println(s);
     
	
	
	
	
	
	
	
	
	
	
	
	
	List<Jouet> jouets=new ArrayList<>();
	jouets.add(new Jouet("Zebre Figurine",10,"Petite figurine de zebrou",10));
    jouets.add(new Jouet("Izibot Evo",10,"Petit robot programmable",5));
    jouets.add(new Jouet("Araignée Peluche",10,"Un super doudou",5));
    jouets.add(new Jouet("Ligo Start Wars",120,"Jeu de construction",10));
    jouets.add(new Jouet("Bakogan Battle Pack",40,"Figurine d'action",0));
   
    Collections.sort(jouets);
    
    for(Jouet jouet:jouets)
   	 System.out.println(jouet.getNom()+" - "+jouet.getPrix()+" DT "+jouet.getDesc()+" promo : "+jouet.getPromo()+"%");
    
    Comparator<Jouet> c=
    		(jouet1,jouet2)->
    {
    	if(jouet1.getPrix()==jouet2.getPrix())
    		//return jouet1.compareTo(jouet2);
			return jouet2.getNom().compareTo(jouet1.getNom());
		return jouet1.getPrix()-jouet2.getPrix();
    	};
    Collections.sort(jouets,c);
    System.out.println("***************************");
  for(Jouet jouet:jouets)
  System.out.println(jouet.getNom()+" - "+jouet.getPrix()+" DT "+jouet.getDesc()+" promo : "+jouet.getPromo()+"%");
  Collections.sort(jouets,c);  
  
  Comparator<Jouet> c2=Comparator.comparing(Jouet::getPrix)
          .thenComparingInt(Jouet::getPromo).reversed();
  jouets.sort(c2);
    System.out.println("***************************");
    for(Jouet jouet:jouets)
	System.out.println(jouet.getNom()+" - "+jouet.getPrix()+" DT "+jouet.getDesc()+" promo : "+jouet.getPromo()+"%");
  
    
    
    
    
    
    //Comparator<Jouet> c=(jouet1,jouet2)->{return jouet1.getNom().compareTo(jouet2.getNom());};
    //Collections.sort(jouets,new PrixComparator());
    


	}
	public static void test(Map<String,Integer> map)
	{
		map.put("Ali",11);
		map.put("Kamel",4);
		map.put("Badr",18);
		map.put("Fatma",5);
		map.put("Amira",20);
		for(String nom:map.keySet())
			System.out.println(nom+" :"+map.get(nom));
	}
}
