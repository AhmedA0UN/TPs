class MaDate{
    private int j; //jour
    private int m; //mois
    private int a; //an
    public void initialise(int jour, int mois, int an){ j=jour;m=mois;a=an; }
    public MaDate(){this(1,1,1900);}
    public MaDate(int j, int m, int a)
    {this.j=j;this.m=m;this.a=a;}
    public String toString(){  return j+"/"+m+"/"+a;  }
 }

class Personne{
    private String nom;
    private String prenom;
    private MaDate naissance;
    public Personne(String nom,String prenom, int j, int m, int a){  
        this.nom=nom;
        this.prenom=prenom;
        naissance=new MaDate(j,m,a);
    }
    public void affiche(){
        System.out.println("Identite Personne:");
        System.out.println(nom+""+prenom);
        System.out.println(naissance);
    }
}

public class TestPersonne{
    static{
        System.out.println("Bloc statique");
    }
    public static void main(String args[]){
        Personne p=new Personne("X", "Y", 1,2,1999);
        p.affiche();
    }
}