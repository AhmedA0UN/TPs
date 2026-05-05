import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TestExercice3 {

	static List<Country> getData(String PATH, String SOURCE) {
		List<Country> list = new ArrayList<>();
		String line = null;
		try (BufferedReader in = new BufferedReader(new FileReader(PATH + SOURCE))) {

			while ((line = in.readLine()) != null) {
				StringTokenizer tok = new StringTokenizer(line, "\t");
				Country country = new Country(tok.nextToken());
				country.setName(tok.nextToken());
				country.setCapital(tok.nextToken());
				country.setArea(Integer.parseInt(tok.nextToken()));
				country.setPopulation(Integer.parseInt(tok.nextToken()));
				list.add(country);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return (list);
	}

	public static void main(String[] args) {

		String PATH = "D:\\";
		String SOURCE = "Countries.txt";
		String DATA = "CountriesSerial.bin";

		List<Country> c = getData(PATH, SOURCE);
		int n = c.size();
		System.out.println("Lecture de " + n + " lignes du fichier texte : " + PATH + SOURCE);
		ObjectOutputStream oos;
		ObjectInputStream ois;

		try {
			oos = new ObjectOutputStream(new FileOutputStream(PATH + DATA));
			System.out.println("Serialisation :");
			for (int i = 0; i < n; i++)
				oos.writeObject(c.get(i));
           System.out.println("Ecriture de " + n + " objets dans le fichier binaire : " + PATH + DATA);

			oos.close();
			//oos = new ObjectOutputStream(new FileOutputStream(PATH + DATA));
			ois = new ObjectInputStream(new FileInputStream(PATH + DATA));

			try {

				System.out.println("Deserialisation :");
				for (int i = 0; i < n; i++)
		                 System.out.println(((Country)ois.readObject()));

			
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			ois.close();
		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}
}
