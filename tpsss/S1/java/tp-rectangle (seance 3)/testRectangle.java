 class Rectangle {
    private double largeur ,longeur;
    public Rectangle (double longeur , double largeur){
        this.largeur = largeur ;
        this.longeur = longeur ;
    }

    public double getLongeur(){
        return longeur ;
    }
    
    public double getLargeur(){
        return largeur ;
    }

    public void setLargeur(double largeur){
        this.largeur = largeur;
    }

    public void setLongeur(double largeur){
        this.longeur = longeur;
    }

    public double calculerPerimetre(){
        return 2*(largeur + longeur);
    }

    public double claculerSurface(){
        return largeur * longeur;
    }
 }
    public class testRectangle{
        public static void main(String[] args){
            Rectangle r1 = new Rectangle(10.5 , 4.2);
            Rectangle r2 = new Rectangle(8.4 , 2.2);
            System.out.println("la largeur du r1 : "+r1.getLargeur()+"la largeur du r1 : "+r1.getLongeur()+"la surface du r1 : "+r1.claculerSurface()+"le perimetre du r1 : "+r1.calculerPerimetre());

        }
    }
    
