import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class TestExercice5 {

	public static void main(String args[])  {
		Path p = Paths.get("D:\\");
		// ou Path p = FileSystems.getDefault().getPath("D:\\");
		lister();
		filtrerfichier(p);
		copierfichier("D:\\test.txt", "D:\\testC.txt");
    gererzip("D:\\monFichier.zip", "sujets.txt", "nouveau.txt", "D:\\test.txt", "fichierDansZIP.txt");

	}

	static void lister() {
		Iterable<Path> roots = FileSystems.getDefault().getRootDirectories();

		for (Path chemin : roots) {
			System.out.println(chemin);

			try (DirectoryStream<Path> listing = Files.newDirectoryStream(chemin)) {

				int i = 0;
				for (Path nom : listing) {
			System.out.print("\t" + ((Files.isDirectory(nom)) ? nom + "/" : nom));
					i++;
					if (i % 4 == 0)
						System.out.println("\n");
				}

			}catch (IOException e) {
				e.printStackTrace();
			} 
		}

	}

	static void filtrerfichier(Path path) {
		try (DirectoryStream<Path> listing = Files.newDirectoryStream(path, "*.pdf")) {
			for (Path nom : listing)
				System.out.print("\t" + nom);
			System.out.println("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

	static void copierfichier(String chemin1, String chemin2) {
		Path source = Paths.get(chemin1);
		Path cible = Paths.get(chemin2);

		try {
			Files.copy(source, cible, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	static void gererzip(String chemin1, String fich1, String fich2, String chemin2, String fich3) {
		try (FileSystem zipFS = FileSystems.newFileSystem(Paths.get(chemin1))) {

			Files.deleteIfExists(zipFS.getPath(fich1));

			Path path = zipFS.getPath(fich2);
			String message = "Coucou CP2I";
			Files.write(path, message.getBytes());

			Files.copy(Paths.get(chemin2), zipFS.getPath(fich3));

	try (DirectoryStream<Path> stream = Files.newDirectoryStream(zipFS.getPath("/"))) {
				for (Path entry : stream) {
					System.out.println(entry);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}