#include <iostream>
using namespace std;

int produitScalaire(int* U, int* V, int taille) {
    int produit = 0;
    for (int i = 0; i < taille; ++i) {
        produit += U[i] * V[i];
    }
    return produit;
}

int main() {
    int taille;
    cout << "Entrez la taille des vecteurs : ";
    cin >> taille;

    int* U = new int[taille];
    int* V = new int[taille];

    cout << "Remplissage du vecteur U :\n";
    for (int i = 0; i < taille; ++i) {
        cin >> U[i];
    }

    cout << "Remplissage du vecteur V :\n";
    for (int i = 0; i < taille; ++i) {
        cin >> V[i];
    }

    int resultat = produitScalaire(U, V, taille);
    cout << "Produit scalaire U·V = " << resultat << endl;

    delete[] U;
    delete[] V;
    return 0;
}