package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Plat;
import dao.PlatDAO;

public class DataInitializer {
    
    public static void initializeTestData() {
        try {
            Connection connection = SingletonConnection.getInstance();
            
            // Vérifier si la table plat est vide
            if (isPlatTableEmpty(connection)) {
                insertTestPlats();
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'initialisation des données : " + e.getMessage());
        }
    }
    
    private static boolean isPlatTableEmpty(Connection connection) throws SQLException {
        String sql = "SELECT COUNT(*) as count FROM plat";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            if (resultSet.next()) {
                return resultSet.getInt("count") == 0;
            }
        }
        return true;
    }
    
    private static void insertTestPlats() {
        try {
            PlatDAO platDAO = new PlatDAO();
            
            // Plats salés
            Plat[] platsSales = {
                new Plat(0, "Salade César", 12.50, "Menu Salé", "Entrée", 
                    "Salade fraîche avec poulet grillé et sauce César"),
                new Plat(0, "Bruschetta tomates", 10.00, "Menu Salé", "Entrée", 
                    "Pain grillé avec tomates fraîches et basilic"),
                new Plat(0, "Soupe à l'oignon", 8.00, "Menu Salé", "Entrée", 
                    "Soupe gratinée au fromage"),
                new Plat(0, "Poulet rôti", 28.00, "Menu Salé", "Plat principal", 
                    "Poulet fermier rôti aux herbes"),
                new Plat(0, "Tagine agneau", 35.00, "Menu Salé", "Plat principal", 
                    "Tagine traditionnel aux olives et citron confit"),
                new Plat(0, "Pâtes Bolognaise", 18.50, "Menu Salé", "Plat principal", 
                    "Pâtes al dente avec sauce Bolognaise maison")
            };
            
            // Plats sucrés
            Plat[] platsSucres = {
                new Plat(0, "Tarte aux pommes", 9.00, "Menu Sucré", "Dessert", 
                    "Tarte maison aux pommes caramélisées"),
                new Plat(0, "Fondant au chocolat", 11.50, "Menu Sucré", "Dessert", 
                    "Moelleux au chocolat noir avec sauce chaude"),
                new Plat(0, "Crème caramel", 8.50, "Menu Sucré", "Dessert", 
                    "Dessert onctueux au caramel"),
                new Plat(0, "Mousse aux fraises", 10.00, "Menu Sucré", "Dessert", 
                    "Mousse légère aux fraises fraîches"),
                new Plat(0, "Tiramisu", 10.50, "Menu Sucré", "Dessert", 
                    "Tiramisu traditionnel au mascarpone"),
                new Plat(0, "Panna cotta", 9.50, "Menu Sucré", "Dessert", 
                    "Panna cotta vanille avec coulis de fruits rouges")
            };
            
            // Ajouter les plats salés
            for (Plat plat : platsSales) {
                platDAO.ajouterPlat(plat);
            }
            
            // Ajouter les plats sucrés
            for (Plat plat : platsSucres) {
                platDAO.ajouterPlat(plat);
            }
            
            System.out.println("Données de test initialisées avec succès : " + 
                (platsSales.length + platsSucres.length) + " plats ajoutés");
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'insertion des plats de test : " + e.getMessage());
        }
    }
}
