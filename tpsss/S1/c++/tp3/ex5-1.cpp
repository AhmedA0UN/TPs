#include <iostream>
#include <vector>
using namespace std;

struct Ville {
    string nom;
    double latitude;
    double longitude;
};

void ajouterVille(vector<Ville>& villes, const string& nom, double lat, double lon) {
    villes.push_back({nom, lat, lon});
}

bool rechercherVille(const vector<Ville>& villes, const string& nom) {
    for (const Ville& v : villes) {
        if (v.nom == nom) {
            cout << "Ville trouvée : " << v.nom << " → Latitude : " << v.latitude << ", Longitude : " << v.longitude << endl;
            return true;
        }
    }
    cout << "Ville \"" << nom << "\" non trouvée." << endl;
    return false;
}

int main() {
    vector<Ville> villes;
    ajouterVille(villes, "Tunis", 36.8065, 10.1815);
    ajouterVille(villes, "Sfax", 34.7406, 10.7603);
    ajouterVille(villes, "Gabès", 33.8815, 10.0982);

    string recherche;
    cout << "Entrez le nom de la ville à rechercher : ";
    cin >> recherche;

    rechercherVille(villes, recherche);
    return 0;
}