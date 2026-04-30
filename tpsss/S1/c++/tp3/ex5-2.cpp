#include <iostream>
#include <unordered_map>
using namespace std;

struct Position {
    double latitude;
    double longitude;
};

void ajouterVille(unordered_map<string, Position>& villes, const string& nom, double lat, double lon) {
    villes[nom] = {lat, lon};
}

bool rechercherVille(const unordered_map<string, Position>& villes, const string& nom) {
    auto it = villes.find(nom);
    if (it != villes.end()) {
        cout << "Ville trouvée : " << nom << " → Latitude : " << it->second.latitude << ", Longitude : " << it->second.longitude << endl;
        return true;
    }
    cout << "Ville \"" << nom << "\" non trouvée." << endl;
    return false;
}

int main() {
    unordered_map<string, Position> villes;
    ajouterVille(villes, "Tunis", 36.8065, 10.1815);
    ajouterVille(villes, "Sfax", 34.7406, 10.7603);
    ajouterVille(villes, "Gabès", 33.8815, 10.0982);

    string recherche;
    cout << "Entrez le nom de la ville à rechercher : ";
    cin >> recherche;

    rechercherVille(villes, recherche);
    return 0;
}