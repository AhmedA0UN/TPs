package controller;

import dao.UtilisateurDAO;
import model.Utilisateur;

import java.sql.SQLException;

public class UtilisateurController {
    private UtilisateurDAO dao = new UtilisateurDAO();

    public String getNom(int id) throws SQLException {
        return dao.getNom(id);
    }

    public void ajouterUtilisateur(Utilisateur u) throws SQLException {
        dao.ajouterUtilisateur(u);
    }
}
