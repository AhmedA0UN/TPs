public class ProfitePerte {	
    public static void main (String args[]) {
		int f=Integer.parseInt(args[0]);
		int v=Integer.parseInt(args[0]);
		if(f>v) {
			System.out.println("perte");
		}
		else if (f<v){
			System.out.println("profite");
		}
		else {
			System.out.println("Null");
		}
	}
}
