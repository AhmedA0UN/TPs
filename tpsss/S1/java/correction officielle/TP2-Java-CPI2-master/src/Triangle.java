import java.util.Scanner;
public class Triangle {
	
		public static void main(String[] args) {
			int n, i, j;
			System.out.print("Combien de lignes ? ");
			Scanner x = new Scanner(System.in);
			n = x.nextInt();
			
			for (i = 0; i < n; i++) {
				for (j = 0; j < n - i - 1; j++)
					System.out.print(" ");
				for (j = 0; j <= i * 2; j++)
					System.out.print("*");
				System.out.println();
			}
		}
	}


