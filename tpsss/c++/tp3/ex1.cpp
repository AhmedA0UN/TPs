#include <iostream>
#include <vector>

using namespace std;

// Fonction pour demander la taille du tableau
int saisirTailleTableau() {
    int taille;
    cout << "Entrez la taille du tableau : ";
    cin >> taille;
    return taille;
}

// Fonction pour remplir le tableau et compter les positifs en une seule passe
int remplirEtCompterPositifs(vector<int>& tableau) {
    int compteur = 0;
    for (int i = 0; i < tableau.size(); ++i) {
        cout << "Entrez l'élément " << i + 1 << " : ";
        cin >> tableau[i];
        if (tableau[i] > 0) {
            ++compteur;
        }
    }
    return compteur;
}

// Fonction principale
int main() {
    int taille = saisirTailleTableau();

    // Vérification de la validité de la taille
    if (taille <= 0) {
        cout << "Taille invalide. Le programme s'arrête." << endl;
        return 1;
    }

    vector<int> tableau(taille);
    int nbPositifs = remplirEtCompterPositifs(tableau);

    cout << "Nombre de valeurs positives : " << nbPositifs << endl;

    return 0;
}