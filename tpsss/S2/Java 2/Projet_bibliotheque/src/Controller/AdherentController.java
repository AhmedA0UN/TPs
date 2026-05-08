package Controller;

import Dao.AdherentDao;
import Models.Adherent;

import java.util.List;

public class AdherentController {

    private final AdherentDao dao = new AdherentDao();

    public List<Adherent> getAll()          { return dao.getAll(); }
    public Adherent       getById(int id)   { return dao.getById(id); }
    public List<Adherent> search(String kw) { return dao.search(kw); }

    public boolean add(String nom, String prenom, String username, String password, String email) {
        return dao.add(new Adherent(0, nom, prenom, username, password, email));
    }

    public boolean update(int id, String nom, String prenom, String username, String password, String email) {
        return dao.update(new Adherent(id, nom, prenom, username, password, email));
    }

    public boolean delete(int id) { return dao.delete(id); }
}
