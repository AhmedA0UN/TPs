public class Multiplication {
    public static void main (String args[]) {
        int m = Integer.parseInt(args[0]); // Convert first command-line argument to integer
        for (int i = 0; i < 10; i++) {     // Loop from 0 to 9
            System.out.println(m + "x" + i + "=" + (m * i)); // Print multiplication result
        }
    }
}
