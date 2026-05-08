package Dao;

import Models.Adherent;
import Utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdherentDao {

    private static final String SELECT_BASE =
        "SELECT p.* FROM personne p INNER JOIN adherent a ON p.id = a.id";

    // ── READ ────────────────────────────────────────────────────────────────

    public List<Adherent> getAll() {
        List<Adherent> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement  stmt = conn.createStatement();
             ResultSet  rs   = stmt.executeQuery(SELECT_BASE)) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public Adherent getById(int id) {
        String sql = SELECT_BASE + " WHERE p.id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public Adherent getByCredentials(String username, String password) {
        String sql = SELECT_BASE + " WHERE p.username = ? AND p.mot_de_passe = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public List<Adherent> search(String keyword) {
        List<Adherent> list = new ArrayList<>();
        String sql = SELECT_BASE + " WHERE p.nom LIKE ? OR p.prenom LIKE ? OR p.username LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String k = "%" + keyword + "%";
            ps.setString(1, k); ps.setString(2, k); ps.setString(3, k);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // ── WRITE ───────────────────────────────────────────────────────────────

    /** Inserts into personne then adherent inside one transaction. */
    public boolean add(Adherent a) {
        String sql1 = "INSERT INTO personne (nom, prenom, username, mot_de_passe, email) VALUES (?,?,?,?,?)";
        String sql2 = "INSERT INTO adherent (id) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps1 = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS)) {
                ps1.setString(1, a.getNom());
                ps1.setString(2, a.getPrenom());
                ps1.setString(3, a.getUsername());
                ps1.setString(4, a.getMot_de_passe());
                ps1.setString(5, a.getEmail());
                ps1.executeUpdate();
                try (ResultSet keys = ps1.getGeneratedKeys()) {
                    if (keys.next()) {
                        int newId = keys.getInt(1);
                        try (PreparedStatement ps2 = conn.prepareStatement(sql2)) {
                            ps2.setInt(1, newId);
                            ps2.executeUpdate();
                        }
                    }
                }
            }
            conn.commit();
            conn.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Adherent a) {
        String sql = "UPDATE personne SET nom=?, prenom=?, username=?, mot_de_passe=?, email=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, a.getNom());
            ps.setString(2, a.getPrenom());
            ps.setString(3, a.getUsername());
            ps.setString(4, a.getMot_de_passe());
            ps.setString(5, a.getEmail());
            ps.setInt(6, a.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    /** Deletes adherent row then personne row (FK order). */
    public boolean delete(int id) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement ps1 = conn.prepareStatement("DELETE FROM adherent  WHERE id = ?");
                 PreparedStatement ps2 = conn.prepareStatement("DELETE FROM personne  WHERE id = ?")) {
                ps1.setInt(1, id); ps1.executeUpdate();
                ps2.setInt(1, id); ps2.executeUpdate();
            }
            conn.commit();
            conn.setAutoCommit(true);
            return true;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    // ── MAPPING ─────────────────────────────────────────────────────────────

    private Adherent map(ResultSet rs) throws SQLException {
        return new Adherent(
            rs.getInt("id"),
            rs.getString("nom"),
            rs.getString("prenom"),
            rs.getString("username"),
            rs.getString("mot_de_passe"),
            rs.getString("email")
        );
    }
}
