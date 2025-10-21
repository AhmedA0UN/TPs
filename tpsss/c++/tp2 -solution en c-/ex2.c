#include <iostream>
using namespace std;

// Passage par valeur
bool estEgalValeur(int a, int b) {
    return a == b;
}

// Passage par adresse
bool estEgalAdresse(int* a, int* b) {
    return *a == *b;
}

// Passage par référence
bool estEgalReference(int& a, int& b) {
    return a == b;
}

int main() {
    int x = 10, y = 10, z = 20;

    // Test passage par valeur
    cout << "Valeur: x et y sont égaux ? " << (estEgalValeur(x, y) ? "Oui" : "Non") << endl;

    // Test passage par adresse
    cout << "Adresse: x et z sont égaux ? " << (estEgalAdresse(&x, &z) ? "Oui" : "Non") << endl;

    // Test passage par référence
    cout << "Référence: y et z sont égaux ? " << (estEgalReference(y, z) ? "Oui" : "Non") << endl;

    return 0;
}