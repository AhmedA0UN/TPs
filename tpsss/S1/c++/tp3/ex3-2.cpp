#include <iostream>
#include <array>
using namespace std;

const int TAILLE = 3;

int produitScalaire(const array<int, TAILLE>& U, const array<int, TAILLE>& V) {
    int produit = 0;
    for (int i = 0; i < TAILLE; ++i) {
        produit += U[i] * V[i];
    }
    return produit;
}

int main() {
    array<int, TAILLE> U, V;

    cout << "Remplissage du vecteur U :\n";
    for (int& val : U) cin >> val;

    cout << "Remplissage du vecteur V :\n";
    for (int& val : V) cin >> val;

    int resultat = produitScalaire(U, V);
    cout << "Produit scalaire U·V = " << resultat << endl;

    return 0;
}