package Controller;

import Dao.AdherentDao;
import Dao.BibliothecaireDao;
import Models.Adherent;
import Models.Bibliothecaire;
import Models.Personne;

public class AuthController {

    private final AdherentDao     adherentDao     = new AdherentDao();
    private final BibliothecaireDao bibliothecaireDao = new BibliothecaireDao();

    /**
     * Tries the bibliothecaire table first, then the adherent table.
     * Returns the matching Personne subtype, or {@code null} on failure.
     */
    public Personne login(String username, String password) {
        Bibliothecaire bib = bibliothecaireDao.getByCredentials(username, password);
        if (bib != null) return bib;

        Adherent ad = adherentDao.getByCredentials(username, password);
        return ad; // null if not found
    }
}
