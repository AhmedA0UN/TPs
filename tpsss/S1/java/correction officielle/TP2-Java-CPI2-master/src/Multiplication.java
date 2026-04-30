import java.util.Scanner;

public class Multiplication {
	


	  public static void main(String[] args) {
	  
	   //Scanner sc = new Scanner(System.in);
	    int n=Integer.parseInt(args[0]);
	    //System.out.print("Entrer un nombre: ");
	   // int n = sc.nextInt();
	     //sc.close();
	    for (int i=0; i<10; i++){
	         System.out.println(n + " x " + (i+1) + " = " + (n * (i+1)));
	    }
	  }
}

