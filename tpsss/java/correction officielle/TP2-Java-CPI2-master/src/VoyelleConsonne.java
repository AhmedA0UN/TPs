import java.util.Scanner;
public class VoyelleConsonne {
	
	    public static void main(String[] variables) {
	        Scanner sc = new Scanner(System.in);
	 
	        char ch;
	 
	        // Fournir les données d'entrée
	        System.out.print("Saisir une lettre : ");
	        ch = sc.next().charAt(0);
	 
	        switch (ch) {
	        case 'a','A','E','e','u','U','O','o','I','i':
	            System.out.println("Voyelle");
	            break;
	       
	        default:
	            System.out.println("Consonne");
	        }
	
	        // fermer les ressources
	        sc.close();
	    }
}
