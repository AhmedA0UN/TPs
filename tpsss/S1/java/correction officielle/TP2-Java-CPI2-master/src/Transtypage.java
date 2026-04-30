
public class Transtypage {
	 public static void main(String[] args) {
	        int n1 = 2;
	        double r1 = 2.7;

	       // int n2 = 2.7;
	        double r2 = 5;
            System.out.println(r2);
	        int n3 = (int) 5.6;
	        double r3 = 5.6;

	        
	        
	        int n4 = 9;
	        double r4 = (double) n4;
	        double r5 = (int) r4;

	        int n5 = 19 / 4;
	        double r6 = 5.0;
	       //int n6 = n5 / r5;
	       
	        int n7 = (int) (n5 / r5);
	        double r7 = 19.0 / r6;
	        double r8 = 15.0 / n3;

	        double r9 = 19 / 4;
	        double r10 = (double) (19 / 4);
	        double r11 = (double) 19 / (double) 4;
	 
	 }

	/* n1 vaut 2
	 r1 vaut 2.7

	 n2 vaut (affectation impossible)
	 r2 vaut 5.0

	 n3 vaut 5
	 r3 vaut 5.6

	 n4 vaut 9
	 r4 vaut 9.0
	 r5 vaut 9.0

	 n5 vaut 4
	 n6 vaut (affectation impossible)
	 r6 vaut 5.0
	 n7 vaut 0
	 r7 vaut 3.8

	 r8 vaut 3.0
	 r9 vaut 4.0
	 r10 vaut 4.0
	 r11 vaut 4.75*/
}
