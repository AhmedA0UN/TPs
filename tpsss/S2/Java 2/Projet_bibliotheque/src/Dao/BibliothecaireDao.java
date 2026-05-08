package Dao;

import Models.Bibliothecaire;
import Utils.DatabaseConnection;

import java.sql.*;

public class BibliothecaireDao {

    public Bibliothecaire getByCredentials(String username, String password) {
        String sql = "SELECT p.* FROM personne p "
                   + "INNER JOIN bibliothecaire b ON p.id = b.id "
                   + "WHERE p.username = ? AND p.mot_de_passe = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Bibliothecaire(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("username"),
                        rs.getString("mot_de_passe"),
                        rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}
