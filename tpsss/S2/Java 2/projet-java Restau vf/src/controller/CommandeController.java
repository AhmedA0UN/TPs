package controller;

import dao.CommandeDAO;
import model.Commande;

import java.sql.SQLException;
import java.util.List;

public class CommandeController {
    private CommandeDAO dao = new CommandeDAO();

    public void ajouterCommande(Commande commande) throws SQLException {
        dao.ajouterCommande(commande);
    }

    public List<Commande> getAllCommandes() throws SQLException {
        return dao.getAllCommandes();
    }

    public List<Commande> getCommandesByState(String state) throws SQLException {
        return dao.getCommandesByState(state);
    }

    public void updateCommandeState(int id, String newState) throws SQLException {
        dao.updateCommandeState(id, newState);
    }

    public void annulerCommande(int id) throws SQLException {
        dao.annulerCommande(id);
    }

    public int getIdClientCP(int idCommande) throws SQLException {
        return dao.getIdClientCP(idCommande);
    }

    public int existeCommande(int idCommande) throws SQLException {
        return dao.existeCommande(idCommande);
    }

    public void mettrePrix(int idCommande, double prix) throws SQLException {
        dao.mettreprix(idCommande, prix);
    }

    public double getPrix(int idCommande) throws SQLException {
        return dao.getPrix(idCommande);
    }
}
