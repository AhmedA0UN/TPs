package controller;

import dao.UtilisateurDAO;
import model.Utilisateur;

import java.sql.SQLException;

public class UtilisateurController {
    private UtilisateurDAO dao = new UtilisateurDAO();

    public boolean login(int id, String mdp, String role) throws SQLException {
        return dao.existeUtilisateur(id) == 1
            && dao.verifMdpU(id, mdp)
            && dao.getRole(id, mdp, role);
    }

    public int existeUtilisateur(int id) throws SQLException {
        return dao.existeUtilisateur(id);
    }

    public boolean verifMdpU(int id, String mdp) throws SQLException {
        return dao.verifMdpU(id, mdp);
    }

    public boolean getRole(int id, String mdp, String role) throws SQLException {
        return dao.getRole(id, mdp, role);
    }

    public Utilisateur getUtilisateurById(int id) throws SQLException {
        return dao.getUtilisateurById(id);
    }

    public String getNom(int id) throws SQLException {
        return dao.getNom(id);
    }

    public String getNomComplet(int id) throws SQLException {
        return getNom(id);
    }

    public String getInfo(int id) throws SQLException {
        Utilisateur utilisateur = dao.getUtilisateurById(id);
        return utilisateur == null ? "" : utilisateur.toString();
    }

    public void ajouterUtilisateur(Utilisateur u) throws SQLException {
        dao.ajouterUtilisateur(u);
    }
}
