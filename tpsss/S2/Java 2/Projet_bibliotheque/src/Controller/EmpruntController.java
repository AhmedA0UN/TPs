package Controller;

import Dao.EmpruntDao;
import Models.Emprunt;

import java.util.List;

public class EmpruntController {

    private final EmpruntDao dao = new EmpruntDao();

    public List<Emprunt> getAll()                       { return dao.getAll(); }
    public List<Emprunt> getByAdherent(int idAdherent)  { return dao.getByAdherent(idAdherent); }
    public List<Emprunt> getPendingReturns()             { return dao.getPendingReturns(); }

    /** Creates a new loan with status "En cours". */
    public boolean createLoan(int idAdherent, int idDocument, String dateEmp, String dateRetourPrevue) {
        Emprunt e = new Emprunt(0, idAdherent, idDocument, dateEmp, dateRetourPrevue, null, "En cours");
        return dao.add(e);
    }

    /** Marks an emprunt as returned with the given real-return date. */
    public boolean markReturned(int id, String dateRetourReelle) {
        Emprunt e = dao.getById(id);
        if (e == null) return false;
        e.setStatus("Retourné");
        e.setDate_retour_r(dateRetourReelle);
        return dao.update(e);
    }

    /** Changes the planned return date. */
    public boolean updateReturnDate(int id, String newDate) {
        Emprunt e = dao.getById(id);
        if (e == null) return false;
        e.setDate_retour_p(newDate);
        return dao.update(e);
    }

    public boolean delete(int id) { return dao.delete(id); }
}
