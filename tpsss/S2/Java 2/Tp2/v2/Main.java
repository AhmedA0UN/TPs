import java.util.*;

// ==================== CLASSE VOITURE ====================
class Voiture {
    private String immatriculation;
    private String marque;
    private double prix;

    public Voiture(String immatriculation, String marque, double prix) {
        this.immatriculation = immatriculation;
        this.marque = marque;
        this.prix = prix;
    }

    public String getImmatriculation() { return immatriculation; }
    public void setImmatriculation(String immatriculation) { this.immatriculation = immatriculation; }
    public String getMarque() { return marque; }
    public void setMarque(String marque) { this.marque = marque; }
    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Voiture voiture = (Voiture) obj;
        return Objects.equals(immatriculation, voiture.immatriculation) && 
               Objects.equals(marque, voiture.marque);
    }

    @Override
    public int hashCode() {
        return Objects.hash(immatriculation, marque);
    }

    @Override
    public String toString() {
        return "Voiture [immatriculation=" + immatriculation + ", marque=" + marque + ", prix=" + prix + "]";
    }
}

// ==================== CLASSE CLIENT ====================
class Client {
    private int code;
    private String nom;
    private String prenom;

    public Client(int code, String nom, String prenom) {
        this.code = code;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Client client = (Client) obj;
        return code == client.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "Client [code=" + code + ", nom=" + nom + ", prenom=" + prenom + "]";
    }
}

// ==================== INTERFACE CRITERE ====================
interface Critere {
    boolean estSatisfaitPar(Voiture v);
}

// ==================== CLASSE CRITERE MARQUE ====================
class CritereMarque implements Critere {
    private String marque;

    public CritereMarque(String marque) {
        this.marque = marque;
    }

    @Override
    public boolean estSatisfaitPar(Voiture v) {
        return v.getMarque().equalsIgnoreCase(marque);
    }
}

// ==================== CLASSE CRITERE PRIX ====================
class CriterePrix implements Critere {
    private double prixMax;

    public CriterePrix(double prixMax) {
        this.prixMax = prixMax;
    }

    @Override
    public boolean estSatisfaitPar(Voiture v) {
        return v.getPrix() <= prixMax;
    }
}

// ==================== CLASSE EXCEPTION ====================
class VoitureException extends Exception {
    public VoitureException(String message) {
        super(message);
    }
}

// ==================== CLASSE LISTE VOITURES ====================
class ListVoitures {
    private List<Voiture> voitures;

    public ListVoitures(List<Voiture> voitures) {
        this.voitures = voitures;
    }

    public ListVoitures() {
        this.voitures = new ArrayList<>();
    }

    public List<Voiture> getVoitures() { return voitures; }
    public void setVoitures(List<Voiture> voitures) { this.voitures = voitures; }

    public void ajoutVoiture(Voiture v) throws VoitureException {
        if (voitures.contains(v)) {
            throw new VoitureException("Cette voiture existe déjà dans la liste !");
        }
        voitures.add(v);
        System.out.println("Voiture ajoutée avec succès : " + v);
    }

    public void supprimeVoiture(Voiture v) throws VoitureException {
        if (!voitures.remove(v)) {
            throw new VoitureException("Voiture introuvable dans la liste !");
        }
        System.out.println("Voiture supprimée avec succès : " + v);
    }

    public Iterator<Voiture> iterateur() { return voitures.iterator(); }
    public int size() { return voitures.size(); }

    public void affiche() {
        if (voitures.isEmpty()) {
            System.out.println("Aucune voiture dans la liste.");
        } else {
            System.out.println("Liste des voitures (" + voitures.size() + ") :");
            for (Voiture v : voitures) {
                System.out.println("  - " + v);
            }
        }
    }
}

// ==================== CLASSE AGENCE ====================
class Agence {
    private String nom;
    private ListVoitures parking;
    private Map<Client, ListVoitures> clientVoitureLoue;

    public Agence(String nom) {
        this.nom = nom;
        this.parking = new ListVoitures();
        this.clientVoitureLoue = new HashMap<>();
    }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public ListVoitures getParking() { return parking; }
    public void setParking(ListVoitures parking) { this.parking = parking; }
    public Map<Client, ListVoitures> getClientVoitureLoue() { return clientVoitureLoue; }
    public void setClientVoitureLoue(Map<Client, ListVoitures> clientVoitureLoue) { this.clientVoitureLoue = clientVoitureLoue; }

    public void ajouterVoitureParking(Voiture v) throws VoitureException {
        parking.ajoutVoiture(v);
    }

    public void supprimerVoitureParking(Voiture v) throws VoitureException {
        parking.supprimeVoiture(v);
    }

    public void louerVoiture(Client client, Voiture voiture) throws VoitureException {
        if (!parking.getVoitures().contains(voiture)) {
            throw new VoitureException("Cette voiture n'est pas disponible dans le parking !");
        }

        ListVoitures voituresLouees = clientVoitureLoue.get(client);
        if (voituresLouees == null) {
            voituresLouees = new ListVoitures();
            clientVoitureLoue.put(client, voituresLouees);
        }

        voituresLouees.ajoutVoiture(voiture);
        parking.supprimeVoiture(voiture);
        System.out.println("Voiture louée avec succès au client " + client.getNom() + " " + client.getPrenom());
    }

    public void retournerVoiture(Client client, Voiture voiture) throws VoitureException {
        ListVoitures voituresLouees = clientVoitureLoue.get(client);
        
        if (voituresLouees == null || !voituresLouees.getVoitures().contains(voiture)) {
            throw new VoitureException("Ce client n'a pas loué cette voiture !");
        }

        voituresLouees.supprimeVoiture(voiture);
        parking.ajoutVoiture(voiture);
        System.out.println("Voiture retournée avec succès par le client " + client.getNom() + " " + client.getPrenom());
        
        if (voituresLouees.size() == 0) {
            clientVoitureLoue.remove(client);
        }
    }

    public List<Voiture> selectVoitureSelonCritere(Critere critere) {
        List<Voiture> resultat = new ArrayList<>();
        for (Voiture v : parking.getVoitures()) {
            if (critere.estSatisfaitPar(v)) {
                resultat.add(v);
            }
        }
        return resultat;
    }

    public Set<Client> ensembleClientsLoueurs() {
        return clientVoitureLoue.keySet();
    }

    public List<Voiture> voituresEnLocation() {
        List<Voiture> resultat = new ArrayList<>();
        for (ListVoitures lv : clientVoitureLoue.values()) {
            resultat.addAll(lv.getVoitures());
        }
        return resultat;
    }

    public void afficherClientsEtVoituresLouees() {
        if (clientVoitureLoue.isEmpty()) {
            System.out.println("Aucune voiture en location pour le moment.");
        } else {
            System.out.println("=== État des locations (" + clientVoitureLoue.size() + " clients) ===");
            for (Map.Entry<Client, ListVoitures> entry : clientVoitureLoue.entrySet()) {
                System.out.println(entry.getKey());
                entry.getValue().affiche();
                System.out.println("---");
            }
        }
    }

    public void trierClientsParCode() {
        List<Client> clients = new ArrayList<>(clientVoitureLoue.keySet());
        Collections.sort(clients, new Comparator<Client>() {
            @Override
            public int compare(Client c1, Client c2) {
                return Integer.compare(c1.getCode(), c2.getCode());
            }
        });
        
        System.out.println("=== Clients triés par code (" + clients.size() + " clients) ===");
        for (Client c : clients) {
            System.out.println("  - " + c);
        }
    }

    public void trierClientsParNom() {
        List<Client> clients = new ArrayList<>(clientVoitureLoue.keySet());
        Collections.sort(clients, new Comparator<Client>() {
            @Override
            public int compare(Client c1, Client c2) {
                return c1.getNom().compareToIgnoreCase(c2.getNom());
            }
        });
        
        System.out.println("=== Clients triés par nom (" + clients.size() + " clients) ===");
        for (Client c : clients) {
            System.out.println("  - " + c);
        }
    }

    public void afficherParking() {
        System.out.println("=== Parking de l'agence " + nom + " ===");
        parking.affiche();
    }
}

// ==================== CLASSE PRINCIPALE (MAIN) ====================
public class Main {
    public static void main(String[] args) {
        try {
            // Création de l'agence
            Agence agence = new Agence("Agence Centrale");
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║     CRÉATION DE L'AGENCE : " + agence.getNom() + "      ║");
            System.out.println("╚════════════════════════════════════════════════╝\n");

            // Création des voitures
            Voiture v1 = new Voiture("123-TUN-456", "Renault", 50.0);
            Voiture v2 = new Voiture("789-TUN-012", "Peugeot", 75.0);
            Voiture v3 = new Voiture("345-TUN-678", "Citroën", 60.0);
            Voiture v4 = new Voiture("901-TUN-234", "Renault", 45.0);
            Voiture v5 = new Voiture("567-TUN-890", "Peugeot", 80.0);
            Voiture v6 = new Voiture("111-TUN-111", "Renault", 55.0);

            // Ajout des voitures au parking
            System.out.println("📦 AJOUT DES VOITURES AU PARKING");
            System.out.println("──────────────────────────────────────");
            agence.ajouterVoitureParking(v1);
            agence.ajouterVoitureParking(v2);
            agence.ajouterVoitureParking(v3);
            agence.ajouterVoitureParking(v4);
            agence.ajouterVoitureParking(v5);
            System.out.println();

            // Création des clients
            Client c1 = new Client(103, "Dupont", "Jean");
            Client c2 = new Client(101, "Martin", "Sophie");
            Client c3 = new Client(102, "Bernard", "Pierre");
            Client c4 = new Client(104, "Petit", "Marie");

            // Test de location
            System.out.println("🚗 TEST DE LOCATION");
            System.out.println("──────────────────────────────────────");
            agence.louerVoiture(c1, v1);  // Jean loue Renault
            agence.louerVoiture(c1, v2);  // Jean loue Peugeot
            agence.louerVoiture(c2, v3);  // Sophie loue Citroën
            agence.louerVoiture(c3, v4);  // Pierre loue Renault
            System.out.println();

            // Affichage de l'état actuel
            agence.afficherParking();
            System.out.println();
            
            agence.afficherClientsEtVoituresLouees();
            System.out.println();

            // Test des critères de sélection
            System.out.println("🔍 TEST DES CRITÈRES DE SÉLECTION");
            System.out.println("──────────────────────────────────────");
            System.out.println("Voitures de marque RENAULT disponibles :");
            CritereMarque critereRenault = new CritereMarque("Renault");
            List<Voiture> renaults = agence.selectVoitureSelonCritere(critereRenault);
            if (renaults.isEmpty()) {
                System.out.println("  Aucune voiture Renault disponible");
            } else {
                for (Voiture v : renaults) {
                    System.out.println("  - " + v);
                }
            }

            System.out.println("\nVoitures à prix ≤ 70 disponibles :");
            CriterePrix criterePrix = new CriterePrix(70);
            List<Voiture> pasCheres = agence.selectVoitureSelonCritere(criterePrix);
            if (pasCheres.isEmpty()) {
                System.out.println("  Aucune voiture à ce prix disponible");
            } else {
                for (Voiture v : pasCheres) {
                    System.out.println("  - " + v);
                }
            }
            System.out.println();

            // Test de retour de voiture
            System.out.println("🔄 TEST DE RETOUR DE VOITURE");
            System.out.println("──────────────────────────────────────");
            agence.retournerVoiture(c1, v2);  // Jean retourne la Peugeot
            System.out.println();

            // Affichage après retour
            agence.afficherParking();
            System.out.println();
            agence.afficherClientsEtVoituresLouees();
            System.out.println();

            // Test des tris
            System.out.println("📊 TEST DES TRIS");
            System.out.println("──────────────────────────────────────");
            agence.trierClientsParCode();
            System.out.println();
            agence.trierClientsParNom();
            System.out.println();

            // Test de l'ensemble des clients loueurs
            System.out.println("👥 CLIENTS AYANT DES VOITURES EN LOCATION");
            System.out.println("──────────────────────────────────────");
            Set<Client> loueurs = agence.ensembleClientsLoueurs();
            if (loueurs.isEmpty()) {
                System.out.println("  Aucun client n'a de voiture en location");
            } else {
                for (Client c : loueurs) {
                    System.out.println("  - " + c);
                }
            }
            System.out.println();

            // Test de la liste des voitures en location
            System.out.println("🚙 VOITURES ACTUELLEMENT EN LOCATION");
            System.out.println("──────────────────────────────────────");
            List<Voiture> enLocation = agence.voituresEnLocation();
            if (enLocation.isEmpty()) {
                System.out.println("  Aucune voiture en location");
            } else {
                for (Voiture v : enLocation) {
                    System.out.println("  - " + v);
                }
            }
            System.out.println();

            // Test de gestion d'erreurs
            System.out.println("⚠️ TEST DE GESTION D'ERREURS");
            System.out.println("──────────────────────────────────────");
            try {
                System.out.println("Tentative de location d'une voiture déjà louée...");
                agence.louerVoiture(c2, v3);  // v3 déjà louée à Sophie
            } catch (VoitureException e) {
                System.out.println("✅ Erreur attendue : " + e.getMessage());
            }

            try {
                System.out.println("\nTentative de retour d'une voiture non louée par le client...");
                agence.retournerVoiture(c2, v1);  // v1 louée par Jean, pas Sophie
            } catch (VoitureException e) {
                System.out.println("✅ Erreur attendue : " + e.getMessage());
            }

            try {
                System.out.println("\nTentative d'ajout d'une voiture déjà existante...");
                agence.ajouterVoitureParking(v1);  // v1 déjà dans le parking (retournée)
            } catch (VoitureException e) {
                System.out.println("✅ Erreur attendue : " + e.getMessage());
            }
            
            // Test d'égalité
            System.out.println("\n🔧 TEST D'ÉGALITÉ");
            System.out.println("──────────────────────────────────────");
            Voiture v1Duplicate = new Voiture("123-TUN-456", "Renault", 50.0);
            System.out.println("v1 = " + v1);
            System.out.println("v1Duplicate = " + v1Duplicate);
            System.out.println("v1 equals v1Duplicate ? " + v1.equals(v1Duplicate));
            
            Client c1Duplicate = new Client(103, "Dupont", "Jean");
            System.out.println("\nc1 = " + c1);
            System.out.println("c1Duplicate = " + c1Duplicate);
            System.out.println("c1 equals c1Duplicate ? " + c1.equals(c1Duplicate));
            
        } catch (VoitureException e) {
            System.err.println("\n❌ Erreur : " + e.getMessage());
        }
    }
}

