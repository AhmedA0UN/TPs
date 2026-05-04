package controller;

import dao.PlatscommandeDAO;
import model.Plat;
import model.Platscommande;

import java.sql.SQLException;
import java.util.List;

public class PlatscommandeController {
    private PlatscommandeDAO dao = new PlatscommandeDAO();

    public List<Plat> getPlatCommandeById(int idU) throws SQLException {
        return dao.getPlatCommandeById(idU);
    }

    public double getPrixPlat(int idPlat) throws SQLException {
        return dao.getPrixPlat(idPlat);
    }

    public int existeClient(int idU) throws SQLException {
        return dao.existeClient(idU);
    }

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
