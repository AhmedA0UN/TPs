// ==================== Classe Agence ====================
import java.util.*;


public class Agence {
    private String nom;
    private ListVoitures parking;
    private Map<Client, ListVoitures> clientVoitureLoue;

    public Agence(String nom) {
        this.nom = nom;
        this.parking = new ListVoitures();
        this.clientVoitureLoue = new HashMap<>();
    }

    public void ajoutVoitureParking(Voiture v) throws VoitureException {
        parking.ajoutVoiture(v);
    }

    public void supprimeVoitureParking(Voiture v) throws VoitureException {
        parking.supprimeVoiture(v);
    }

    public void louerVoiture(Client c, Voiture v) throws VoitureException {
        // Vérifier que la voiture est disponible dans le parking
        if (!parking.getVoitures().contains(v)) {
            throw new VoitureException("Voiture non disponible dans le parking");
        }

        // Récupérer la liste des voitures louées par le client
        ListVoitures louees = clientVoitureLoue.get(c);
        if (louees == null) {
            louees = new ListVoitures();
            clientVoitureLoue.put(c, louees);
        }

        // Ajouter la voiture aux voitures louées et la retirer du parking
        louees.ajoutVoiture(v);
        parking.supprimeVoiture(v);
    }

    public void retournerVoiture(Client c, Voiture v) throws VoitureException {
        ListVoitures louees = clientVoitureLoue.get(c);
        if (louees == null || !louees.getVoitures().contains(v)) {
            throw new VoitureException("Ce client n'a pas loué cette voiture");
        }

        louees.supprimeVoiture(v);
        parking.ajoutVoiture(v);
    }

    public List<Voiture> selectVoitureSelonCritere(Critere c) {
        List<Voiture> result = new ArrayList<>();
        for (Voiture v : parking.getVoitures()) {
            if (c.estSatisfaitPar(v)) {
                result.add(v);
            }
        }
        return result;
    }

    public Set<Client> ensembleClientsLoueurs() {
        return clientVoitureLoue.keySet();
    }

    public List<Voiture> voituresEnLocation() {
        List<Voiture> result = new ArrayList<>();
        for (ListVoitures lv : clientVoitureLoue.values()) {
            result.addAll(lv.getVoitures());
        }
        return result;
    }

    public void afficheClientEtVoituresLouees() {
        for (Map.Entry<Client, ListVoitures> entry : clientVoitureLoue.entrySet()) {
            System.out.println(entry.getKey() + " a loué : ");
            entry.getValue().affiche();
        }
    }

    public void trierClientParCode() {
        List<Client> clients = new ArrayList<>(clientVoitureLoue.keySet());
        clients.sort(Comparator.comparingInt(Client::getCode));
        for (Client c : clients) {
            System.out.println(c);
        }
    }

    public void trierClientParNom() {
        List<Client> clients = new ArrayList<>(clientVoitureLoue.keySet());
        clients.sort(Comparator.comparing(Client::getNom));
        for (Client c : clients) {
            System.out.println(c);
        }
    }
}