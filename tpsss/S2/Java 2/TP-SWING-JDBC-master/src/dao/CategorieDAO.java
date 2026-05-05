package dao;
import modele.Categorie;
import util.SingletonConnection;

import java.sql.*;
	import java.util.ArrayList;
	import java.util.List;
public class CategorieDAO implements Idao<Categorie>{

	    private Connection conn = SingletonConnection.getInstance();
	    		

	    @Override
	    public boolean create(Categorie c) {
	        String sql = "INSERT INTO categorie(nom) VALUES(?)";
	        try {
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setString(1, c.getNom());
	            return ps.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	    @Override
	    public boolean update(Categorie c) {
	        String sql = "UPDATE categorie SET nom=? WHERE code=?";
	        try {
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setString(1, c.getNom());
	            ps.setInt(2, c.getCode());
	            return ps.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	    @Override
	    public boolean delete(Categorie c) {
	        String sql = "DELETE FROM categorie WHERE code=?";
	        try {
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setInt(1, c.getCode());
	            return ps.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	    @Override
	    public Categorie getById(int id) {
	        String sql = "SELECT * FROM categorie WHERE code=?";
	        try {
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setInt(1, id);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                return new Categorie(
	                        rs.getInt("code"),
	                        rs.getString("nom")
	                );
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    @Override
	    public List<Categorie> getAll() {
	        List<Categorie> list = new ArrayList<>();
	        String sql = "SELECT * FROM categorie";

	        try {
	            Statement st = conn.createStatement();
	            ResultSet rs = st.executeQuery(sql);

	            while (rs.next()) {
	                list.add(new Categorie(
	                        rs.getInt("code"),
	                        rs.getString("nom")
	                ));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
	}

