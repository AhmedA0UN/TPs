package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SingletonConnection {
	private static Properties props = new Properties();
	private static String user;
	private static String password;
	private static String url;
	
	// Objet Connection
	private static Connection connect;
	
	// Constructeur privé
	private SingletonConnection() {
		try {
			// Charger explicitement le driver MySQL (donne un message clair si absent)
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException cnfe) {
				System.err.println("MySQL JDBC Driver introuvable. Ajoutez le driver dans le dossier lib/ et relancez.");
				cnfe.printStackTrace();
				return;
			}
			InputStream configStream = SingletonConnection.class.getClassLoader().getResourceAsStream("conf.properties");
			File configFile = new File(System.getProperty("user.dir"), "src/conf.properties");
			if (configStream == null && !configFile.exists()) {
				configFile = new File(System.getProperty("user.dir"), "conf.properties");
			}
			if (configStream != null) {
				props.load(configStream);
			} else {
				props.load(new FileInputStream(configFile));
			}
			url = props.getProperty("jdbc.url");
			user = props.getProperty("jdbc.user");
			password = props.getProperty("jdbc.password");
			connect = DriverManager.getConnection(url, user, password);
			System.out.println("connecte etablie");
		} catch (SQLException e) {
			System.err.println("Impossible d'établir la connexion JDBC: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Impossible de lire le fichier de configuration: " + e.getMessage());
			e.printStackTrace();
		}
	}
	// Méthode qui retourne l'instance et la créer si elle n'existe pas
	public static Connection getInstance() {
		if (connect == null) {
			new SingletonConnection();
		}
		if (connect == null) {
			throw new IllegalStateException("Connexion à la base de données non disponible. Voir les erreurs précédentes.");
		}
		return connect;
	}
}
