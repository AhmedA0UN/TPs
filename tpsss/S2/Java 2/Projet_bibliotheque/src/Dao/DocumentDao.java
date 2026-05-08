package Dao;

import Models.Document;
import Utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentDao {

    // ── READ ────────────────────────────────────────────────────────────────

    public List<Document> getAll() {
        List<Document> list = new ArrayList<>();
        String sql = "SELECT * FROM document";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement  stmt = conn.createStatement();
             ResultSet  rs   = stmt.executeQuery(sql)) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public Document getById(int id) {
        String sql = "SELECT * FROM document WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public List<Document> search(String keyword) {
        List<Document> list = new ArrayList<>();
        String sql = "SELECT * FROM document WHERE titre LIKE ? OR auteur LIKE ? OR type LIKE ?";
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

    public List<Document> getAvailable() {
        List<Document> list = new ArrayList<>();
        String sql = "SELECT * FROM document WHERE dis = TRUE";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement  stmt = conn.createStatement();
             ResultSet  rs   = stmt.executeQuery(sql)) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // ── WRITE ───────────────────────────────────────────────────────────────

    public boolean add(Document doc) {
        // `desc` is a reserved word in MySQL → backticks required
        String sql = "INSERT INTO document (titre, auteur, `desc`, dis, type) VALUES (?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, doc.getTitre());
            ps.setString(2, doc.getAuteur());
            ps.setString(3, doc.getDesc());
            ps.setBoolean(4, doc.getDis());
            ps.setString(5, doc.getType());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean update(Document doc) {
        String sql = "UPDATE document SET titre=?, auteur=?, `desc`=?, dis=?, type=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, doc.getTitre());
            ps.setString(2, doc.getAuteur());
            ps.setString(3, doc.getDesc());
            ps.setBoolean(4, doc.getDis());
            ps.setString(5, doc.getType());
            ps.setInt(6, doc.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM document WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    // ── MAPPING ─────────────────────────────────────────────────────────────

    private Document map(ResultSet rs) throws SQLException {
        return new Document(
            rs.getInt("id"),
            rs.getString("titre"),
            rs.getString("auteur"),
            rs.getString("desc"),
            rs.getBoolean("dis"),
            rs.getString("type")
        );
    }
}
