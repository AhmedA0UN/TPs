package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import model.Plat;
import util.SingletonConnection;

public class PlatDAO {
    Connection connection = SingletonConnection.getInstance();

    public void ajouterPlat(Plat plat) throws SQLException {
        String sql = "INSERT INTO plat(nomPlat, prix, typeMenu, typePlat, description) VALUES (?,?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, plat.getNomPlat());
            statement.setDouble(2, plat.getPrix());
            statement.setString(3, plat.getTypeMenu());
            statement.setString(4, plat.getTypePlat());
            statement.setString(5, plat.getDescription());
            statement.executeUpdate();
        }
    }

    public void modifierPlat(Plat plat) throws SQLException {
        String sql = "UPDATE plat SET nomPlat=?, prix=?, typeMenu=?, typePlat=?, description=? WHERE idPlat=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, plat.getNomPlat());
            statement.setDouble(2, plat.getPrix());
            statement.setString(3, plat.getTypeMenu());
            statement.setString(4, plat.getTypePlat());
            statement.setString(5, plat.getDescription());
            statement.setInt(6, plat.getIdPlat());
            statement.executeUpdate();
        }
    }

    public void supprimerPlat(int idP) throws SQLException {
        String sql = "DELETE FROM plat WHERE idPlat=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idP);
            statement.executeUpdate();
        }
    }

    public Plat getPlatById(int idPlat) throws SQLException {
        Plat plat = null;
        String sql = "SELECT * FROM plat WHERE idPlat = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idPlat);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    plat = new Plat(
                        resultSet.getInt("idPlat"),
                        resultSet.getString("nomPlat"),
                        resultSet.getDouble("prix"),
                        resultSet.getString("typeMenu"),
                        resultSet.getString("typePlat"),
                        resultSet.getString("description")
                    );
                }
            }
        }
        return plat;
    }

    public List<Plat> getAllPlat() throws SQLException {
        List<Plat> plats = new ArrayList<>();
        String sql = "SELECT * FROM plat";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Plat plat = new Plat(
                    resultSet.getInt("idPlat"),
                    resultSet.getString("nomPlat"),
                    resultSet.getDouble("prix"),
                    resultSet.getString("typeMenu"),
                    resultSet.getString("typePlat"),
                    resultSet.getString("description")
                );
                plats.add(plat);
            }
        }
        return plats;
    }

    public Plat getPlatByName(String nomPlat) throws SQLException {
        Plat plat = null;
        String sql = "SELECT * FROM plat WHERE nomPlat = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nomPlat);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    plat = new Plat(
                        resultSet.getInt("idPlat"),
                        resultSet.getString("nomPlat"),
                        resultSet.getDouble("prix"),
                        resultSet.getString("typeMenu"),
                        resultSet.getString("typePlat"),
                        resultSet.getString("description")
                    );
                }
            }
        }
        return plat;
    }

    public List<Plat> getPlatByTypeMenu(String typeMenu) throws SQLException {
        List<Plat> plats = new ArrayList<>();
        String normalizedTypeMenu = normalizeText(typeMenu);
        String sql = "SELECT * FROM plat";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String dbTypeMenu = resultSet.getString("typeMenu");
                if (!normalizeText(dbTypeMenu).equals(normalizedTypeMenu)) {
                    continue;
                }

                Plat plat = new Plat(
                    resultSet.getInt("idPlat"),
                    resultSet.getString("nomPlat"),
                    resultSet.getDouble("prix"),
                    resultSet.getString("typeMenu"),
                    resultSet.getString("typePlat"),
                    resultSet.getString("description")
                );
                plats.add(plat);
            }
        }
        return plats;
    }

    private String normalizeText(String value) {
        if (value == null) {
            return "";
        }

        String normalized = Normalizer.normalize(value, Normalizer.Form.NFD)
            .replaceAll("\\p{M}+", "");

        return normalized.trim().toLowerCase();
    }

    public List<Plat> getPlatByTypePlat(String typePlat) throws SQLException {
        List<Plat> plats = new ArrayList<>();
        String sql = "SELECT * FROM plat WHERE typePlat = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, typePlat);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Plat plat = new Plat(
                        resultSet.getInt("idPlat"),
                        resultSet.getString("nomPlat"),
                        resultSet.getDouble("prix"),
                        resultSet.getString("typeMenu"),
                        resultSet.getString("typePlat"),
                        resultSet.getString("description")
                    );
                    plats.add(plat);
                }
            }
        }
        return plats;
    }

    public boolean existe(int idPlat) throws SQLException {
        String sql = "SELECT * FROM plat WHERE idPlat=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idPlat);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    public static boolean isDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
