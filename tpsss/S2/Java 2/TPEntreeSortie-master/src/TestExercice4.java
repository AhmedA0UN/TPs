import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.InputStream;
	import java.util.ArrayList;
	import java.util.List;
	import static java.lang.Math.*;
public final class TestExercice4 {
	


	
	    public static void main(String[] args) throws IOException {
	            for (int f = 1; f <= 3; f++) {
	            String fileName = String.format("file%d.bin", f);
	            createFile(fileName, f);
	            System.out.printf("%n%s:%n", fileName);
	            int[] freq = byteFrequencies(fileName);
	            System.out.printf("moyenne: %.2f%n", average(freq));
	            System.out.printf("entropie: %.2f bits/octet%n", entropy(freq));
	            System.out.println("diagramme tige-feuille:");
	            List<String> stemPlot = stemPlot(freq);
	            for (String s : stemPlot)
	                System.out.println(s);
	        }
	    }

	    private static void createFile(String fileName, int n) throws IOException {
	    	
	    	 try (FileOutputStream stream = new FileOutputStream(fileName))
			 { 
	    		 if(n==1)
	    		 { for(int i=0; i<=255;i++)
			     	  stream.write(i); 
	    		 }
	    		 else
	    		 {
	    			 for(int i=n; i<=200+3*n+3;i++)
	    				  stream.write(i); 
	    			 stream.write("Bonjour CP2I".getBytes());
	    				 
	    			 
	    		 }
				  
			 } 
	    	 
	    }
	    
	    private static int[] byteFrequencies(String fileName) throws IOException {
	        try (InputStream stream = new FileInputStream(fileName)) {
	            int[] freq = new int[256];
	            int b;
	            while ((b = stream.read()) != -1)
	                freq[b] += 1;
	            return freq;
	        }
	    }

	    private static double average(int[] freq) {
	        int sum = 0, length = 0;
	        for (int i = 0; i < freq.length; i++) {
	            sum += i * freq[i];
	            length += freq[i];
	        }
	        return (double) sum / (double) length;
	    }

	    private static double entropy(int[] freq) {
	        int length = 0;
	        for (int f : freq)
	            length += f;

	        double hNeg = 0, log2 = log(2);
	        for (int f : freq) {
	            if (f != 0) {
	                double p = (double) f / (double) length;
	                hNeg += p * log(p) / log2;
	            }
	        }
	        return -hNeg;
	    }

	    private static List<String> stemPlot(int[] freq) {

	        List<String> lines = new ArrayList<>();

	        for (int stem = 0; stem <= freq.length / 10; stem++) {
	        	StringBuilder lineBuilder = new StringBuilder(String.format("%2d|", stem));
	            for (int leaf = 0; leaf <= 9;leaf++) {
	                int i = 10 * stem + leaf;
	                if (i >= freq.length)
	                    break;
	                for (int k = 0; k < freq[i]; k++)
	                    lineBuilder.append(leaf);
	            }
	            lines.add(lineBuilder.toString());
	        }
	        return lines;
	       
	    }
	}
