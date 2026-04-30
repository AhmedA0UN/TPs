#include <iostream>
#include <vector>
using namespace std;

int produitScalaire(const vector<int>& U, const vector<int>& V) {
    int produit = 0;
    for (size_t i = 0; i < U.size(); ++i) {
        produit += U[i] * V[i];
    }
    return produit;
}

int main() {
    int taille;
    cout << "Entrez la taille des vecteurs : ";
    cin >> taille;

    vector<int> U(taille), V(taille);

    cout << "Remplissage du vecteur U :\n";
    for (int& val : U) cin >> val;

    cout << "Remplissage du vecteur V :\n";
    for (int& val : V) cin >> val;

    int resultat = produitScalaire(U, V);
    cout << "Produit scalaire U·V = " << resultat << endl;

    return 0;
}