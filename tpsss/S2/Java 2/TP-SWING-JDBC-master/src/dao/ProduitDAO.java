package dao;

import modele.*;
import util.SingletonConnection;

import java.sql.*;
import java.util.*;

public class ProduitDAO implements Idao<Produit> {

    Connection conn = SingletonConnection.getInstance();

    public boolean create(Produit p) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO produit VALUES(?,?,?,?,?)"
            );
            ps.setString(1, p.getReference());
            ps.setString(2, p.getNom());
            ps.setDouble(3, p.getPrix());
            ps.setInt(4, p.getQte());
            ps.setInt(5, p.getCat().getCode());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public List<Produit> getAll() {
        List<Produit> list = new ArrayList<>();
        try {
            String sql = "SELECT p.*, c.nom as catNom FROM produit p JOIN categorie c ON p.code_categorie=c.code";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {
                Categorie c = new Categorie(rs.getInt("code_categorie"), rs.getString("catNom"));
                list.add(new Produit(
                        rs.getString("reference"),
                        rs.getString("nom"),
                        rs.getDouble("prix"),
                        rs.getInt("quantite"),
                        c
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    
    public List<Produit> findByMotCle(String mc) {
        List<Produit> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM produit WHERE nom LIKE ?"
            );
            ps.setString(1, "%" + mc + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Produit(
                        rs.getString("reference"),
                        rs.getString("nom"),
                        rs.getDouble("prix"),
                        rs.getInt("quantite"),
                        new Categorie(rs.getInt("code_categorie"), "")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    
    public List<Produit> findByCategorie(int code) {
        List<Produit> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM produit WHERE code_categorie=?"
            );
            ps.setInt(1, code);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Produit(
                        rs.getString("reference"),
                        rs.getString("nom"),
                        rs.getDouble("prix"),
                        rs.getInt("quantite"),
                        new Categorie(code, "")
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public boolean update(Produit p){return false;}
    

	@Override
	public boolean delete(Produit o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Produit getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}