import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
public class Count {
	
	public static void main(String[] args) {
		String inFile = "test.txt";
		String outFile = "testUpperCase.txt";
		try (
			    FileReader fr =new FileReader(inFile);
				BufferedReader in = new BufferedReader(fr);
				FileWriter fw=new FileWriter(outFile);
		        PrintWriter out = new PrintWriter(fw)
		        		)
			{
			
			System.out.println("Lecture de :\t" + inFile);
			
			String line=null ;
			int numLines = 0, numWords = 0, numChars = 0;
			
			while ((line = in.readLine()) != null) {
			
				out.println(line.toUpperCase());
				++numLines;
				numChars += line.length();
				StringTokenizer tok = new StringTokenizer(line);
				while (tok.hasMoreTokens()) {
					++numWords;
					tok.nextToken();
				}
			}

			System.out.println("Lignes :\t" + numLines);
			System.out.println("Mots :\t" + numWords);
			System.out.println("Caractères :\t" + numChars);
		} catch (IOException exception) {
			System.out.println("exception : " + exception);
		}
	}

}
