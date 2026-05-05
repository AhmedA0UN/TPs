import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Encryptor {
	private int clef;

	public Encryptor(int clef) {
		this.clef = clef;
	}

	public void encrypt(String inFile, String outFile) throws IOException {
		
		String line = null;
		try(BufferedReader in = new BufferedReader(new FileReader(inFile));       
                  PrintWriter out = new PrintWriter(new FileWriter(outFile))){
		while ((line = in.readLine()) != null) {
			out.println(encrypted(line));
		}
		}
	}
	private String encrypted(String line) {
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < line.length(); i++)
			buf.append(encrypted(line.charAt(i)));
		return buf.toString();
	}

	private char encrypted(char ch) {
		return (char) (ch + clef);
	}

	public static void main(String[] args) {
		String inFile = "testEcry.txt";
		String outFile1 = "sortie1.txt";
		String outFile2 = "sortie2.txt";

				
		try (Scanner sc = new java.util.Scanner(System.in)) 
		{
			System.out.println("Entrez la clef de cryptage (chiffre de Cesar) : "); 
			int clef = sc.nextInt();
	System.out.println("Cryptage " + inFile + " dans " + outFile1 + " avec une clef de " + clef);
			Encryptor encryptor = new Encryptor(clef);
			encryptor.encrypt(inFile, outFile1);
			
    System.out.println("Decryptage " + outFile1 + " dans " + outFile2 + " avec une clef de " + -clef);
			Encryptor decryptor = new Encryptor(-clef);
			decryptor.encrypt(outFile1, outFile2);

			
		} catch (IOException exception) {
			System.out.println("exception: " + exception);
		}


	}

}
