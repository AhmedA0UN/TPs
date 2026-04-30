package controller;

import dao.CommandeDAO;
import model.Commande;

import java.sql.SQLException;
import java.util.List;

public class CommandeController {
    private CommandeDAO dao = new CommandeDAO();

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

    public double getPrix(int idCommande) throws SQLException {
        return dao.getPrix(idCommande);
    }
}
