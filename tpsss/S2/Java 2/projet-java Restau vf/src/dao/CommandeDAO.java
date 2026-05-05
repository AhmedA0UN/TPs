package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Commande;
import util.SingletonConnection;

public class CommandeDAO {
    Connection connection=SingletonConnection.getInstance();

 

    public void ajouterCommande(Commande commande) throws SQLException {
        String sql = "INSERT INTO commande (idC, etat, idU, date_commande) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, commande.getIdCommande());
            statement.setString(2, commande.getEtat());
            statement.setInt(3, commande.getIdClient());
            statement.setTimestamp(4, new java.sql.Timestamp(commande.getDate().getTime()));
            statement.executeUpdate();
        }
    }
    
    
    public int getIdClientCP(int idCom) throws SQLException {
    	String sql = "SELECT idU FROM commande WHERE idC = ? and etat = 'prete'";
    	try (PreparedStatement statement = connection.prepareStatement(sql)){
    		statement.setInt(1,idCom);
    		try(ResultSet resultSet = statement.executeQuery()){
    			if (resultSet.next()) {
    				return resultSet.getInt("idU");
    			} else {
    				throw new SQLException("Aucune commande trouvée avec l'ID " + idCom);
    			}
    		}
    	}
    			
    			
    	
    }

    public int getDerniereCommandeIdByUser(int idU) throws SQLException {
        String sql = "SELECT idC FROM commande WHERE idU = ? AND etat IN ('prete','servie') ORDER BY date_commande DESC, idC DESC LIMIT 1";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idU);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("idC");
                }
            }
        }
        return -1;
    }
    public void updateCommandeState(int idCommande, String nouvelEtat) throws SQLException {
        String sql = "UPDATE commande SET etat = ? WHERE idC = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nouvelEtat);
            statement.setInt(2, idCommande);
            int rowsUpdated = statement.executeUpdate();
            
            if (rowsUpdated == 0) {
                throw new SQLException("La commande avec l'ID " + idCommande + " n'existe pas");
            }
        }
    }
    
    public List<Commande> getAllCommandes() throws SQLException {
    	List<Commande> commandes = new ArrayList<>();
        String sql = "SELECT * FROM commande ";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Commande commande = new Commande();
                commande.setIdCommande(resultSet.getInt("idC"));
                commande.setIdClient(resultSet.getInt("idU"));
                commande.setEtat(resultSet.getString("etat"));
                commandes.add(commande);
            }
        }
        return commandes;
    }
    	
    }

    public List<Commande> getCommandesByState(String etat) throws SQLException {
        List<Commande> commandes = new ArrayList<>();
        String sql = "SELECT * FROM commande WHERE etat = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, etat);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Commande commande = new Commande(
                        resultSet.getInt("idC"),
                        resultSet.getInt("idU"),
                        resultSet.getString("etat"),
                        resultSet.getTimestamp("date_commande") // ou getDate() selon votre type en base
                    );
                    commande.setPrixC(resultSet.getDouble("prixC"));
                    commandes.add(commande);
                }
            }
        }
        return commandes;
    }
    public int existeCommande(int idC) throws SQLException{
    	String sql="select idC from commande where idC=?";
    	try(PreparedStatement statement =connection.prepareStatement(sql)){
    		statement.setInt(1, idC);
    		try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next())
                	return 1;
                else return 0;
    		
    	}
    	
    }
    	}
    public void annulerCommande(int idC) throws SQLException{
    	String sql = "delete from commande where idC =?";
    	try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1,idC);
            statement.executeUpdate();
        }
    	
    }
    public void mettreprix(int idC,double p) throws SQLException {
    	String sql = "UPDATE commande SET prixC = ? WHERE idC = ?";
    	try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1,p);
            statement.setInt(2, idC);
            statement.executeUpdate();
        }
    }
    public double getPrix(int idC) throws SQLException {
    	String sql = "select prixC from commande where idC = ?";
    	try(PreparedStatement statement =connection.prepareStatement(sql)){
    		statement.setInt(1, idC);
    		try (ResultSet resultSet = statement.executeQuery()) {
    			resultSet.next();
        		return (resultSet.getDouble("prixC"));
    }

}}}

