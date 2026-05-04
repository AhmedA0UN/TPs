package controller;

import dao.PlatDAO;
import model.Plat;

import java.sql.SQLException;
import java.util.List;

public class PlatController {
    private PlatDAO dao = new PlatDAO();

    public Plat getPlatById(int idPlat) throws SQLException {
        return dao.getPlatById(idPlat);
    }

    public List<Plat> getAllPlats() throws SQLException {
        return dao.getAllPlat();
    }

    public List<Plat> getPlatsByTypeMenu(String typeMenu) throws SQLException {
        return dao.getPlatByTypeMenu(typeMenu);
    }

    public List<Plat> getPlatsByTypePlat(String typePlat) throws SQLException {
        return dao.getPlatByTypePlat(typePlat);
    }

    public Plat getPlatByName(String name) throws SQLException {
        return dao.getPlatByName(name);
    }

    public void ajouterPlat(Plat plat) throws SQLException {
        dao.ajouterPlat(plat);
    }

    public void modifierPlat(Plat plat) throws SQLException {
        dao.modifierPlat(plat);
    }

    public void supprimerPlat(int id) throws SQLException {
        dao.supprimerPlat(id);
    }

    public boolean existe(int id) throws SQLException {
        return dao.existe(id);
    }

    public static boolean isDouble(String input) {
        return PlatDAO.isDouble(input);
    }
}
