

class Temps {

    private int heures;
    private int minutes;
    private int secondes;

    Temps(int h, int m, int s) throws TempsException
    {
    	if(h>0&&h<24)
    		heures = h;
    	else
    		throw new TempsException("heure non valide");
    	if(m>0&&m<60)
    			minutes = m;
        	else
        		throw new TempsException("minute non valide");
    		
    	if(s>0&&s<60)
			secondes = s;
    	else
    		throw new TempsException("seconde non valide");
    }

    public static void main(String[] args)throws TempsException {
       try
       {
    	Temps t = new Temps(4, 12, 67);
       } 
       catch(TempsException e)
       {
    	   System.out.println("Temps invalide"+e.getMessage());
       }
    }
}

