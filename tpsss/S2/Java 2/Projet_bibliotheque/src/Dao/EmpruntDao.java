package Dao;

import Models.Emprunt;
import Utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpruntDao {

    // ── READ ────────────────────────────────────────────────────────────────

    public List<Emprunt> getAll() {
        List<Emprunt> list = new ArrayList<>();
        String sql = "SELECT * FROM emprunt ORDER BY id DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement  stmt = conn.createStatement();
             ResultSet  rs   = stmt.executeQuery(sql)) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public Emprunt getById(int id) {
        String sql = "SELECT * FROM emprunt WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public List<Emprunt> getByAdherent(int idAdherent) {
        List<Emprunt> list = new ArrayList<>();
        String sql = "SELECT * FROM emprunt WHERE id_adherent = ? ORDER BY id DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idAdherent);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public List<Emprunt> getPendingReturns() {
        List<Emprunt> list = new ArrayList<>();
        String sql = "SELECT * FROM emprunt WHERE status = 'En cours' ORDER BY date_retour_p";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement  stmt = conn.createStatement();
             ResultSet  rs   = stmt.executeQuery(sql)) {
            while (rs.next()) list.add(map(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // ── WRITE ───────────────────────────────────────────────────────────────

    public boolean add(Emprunt e) {
        String sql = "INSERT INTO emprunt "
                   + "(id_adherent, id_document, date_emp, date_retour_p, date_retour_r, status) "
                   + "VALUES (?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, e.getId_adherent());
            ps.setInt(2, e.getId_document());
            ps.setString(3, e.getDate_emp());
            ps.setString(4, e.getDate_retour_p());
            ps.setString(5, e.getDate_retour_r());
            ps.setString(6, e.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) { ex.printStackTrace(); }
        return false;
    }

    public boolean update(Emprunt e) {
        String sql = "UPDATE emprunt SET "
                   + "id_adherent=?, id_document=?, date_emp=?, date_retour_p=?, date_retour_r=?, status=? "
                   + "WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, e.getId_adherent());
            ps.setInt(2, e.getId_document());
            ps.setString(3, e.getDate_emp());
            ps.setString(4, e.getDate_retour_p());
            ps.setString(5, e.getDate_retour_r());
            ps.setString(6, e.getStatus());
            ps.setInt(7, e.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) { ex.printStackTrace(); }
        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM emprunt WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    // ── MAPPING ─────────────────────────────────────────────────────────────

    private Emprunt map(ResultSet rs) throws SQLException {
        return new Emprunt(
            rs.getInt("id"),
            rs.getInt("id_adherent"),
            rs.getInt("id_document"),
            rs.getString("date_emp"),
            rs.getString("date_retour_p"),
            rs.getString("date_retour_r"),
            rs.getString("status")
        );
    }
}
