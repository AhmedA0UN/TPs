package Controller;

import Dao.DocumentDao;
import Models.Document;

import java.util.List;

public class DocumentController {

    private final DocumentDao dao = new DocumentDao();

    public List<Document> getAll()              { return dao.getAll(); }
    public Document       getById(int id)       { return dao.getById(id); }
    public List<Document> search(String kw)     { return dao.search(kw); }
    public List<Document> getAvailable()        { return dao.getAvailable(); }

    public boolean add(String titre, String auteur, String desc, Boolean dis, String type) {
        return dao.add(new Document(0, titre, auteur, desc, dis, type));
    }

    public boolean update(int id, String titre, String auteur, String desc, Boolean dis, String type) {
        return dao.update(new Document(id, titre, auteur, desc, dis, type));
    }

    public boolean delete(int id) { return dao.delete(id); }
}
