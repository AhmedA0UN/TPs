package controller;

import dao.PlatscommandeDAO;
import model.Platscommande;

import java.sql.SQLException;
import java.util.List;

public class PlatscommandeController {
    private PlatscommandeDAO dao = new PlatscommandeDAO();

    public void ajouterPlatsCommande(Platscommande pc) throws SQLException {
        dao.ajouterPlatsCommande(pc);
    }

    public List<Platscommande> getPlatCommande() throws SQLException {
        return dao.getPlatCommande();
    }

    public List<Platscommande> getPlatCommande(int idU) throws SQLException {
        return dao.getPlatCommande(idU);
    }

    public void supprimerPlatsCommande(int idU) throws SQLException {
        dao.supprimerPlatsCommande(idU);
    }
}
