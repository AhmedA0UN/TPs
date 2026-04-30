#include <iostream>
using namespace std;

// 🔹 Version 1 : Passage par valeur
bool estEgalValeur(int a, int b) {
    return a == b;
}

// 🔹 Version 2 : Passage par référence
bool estEgalReference(const int& a, const int& b) {
    return a == b;
}

// 🔹 Version 3 : Passage par adresse
bool estEgalAdresse(const int* a, const int* b) {
    return (*a == *b);
}

int main() {
    int x = 10, y = 10, z = 20;

    // Test passage par valeur
    cout << "Valeur (x, y): " << estEgalValeur(x, y) << endl;
    cout << "Valeur (x, z): " << estEgalValeur(x, z) << endl;

    // Test passage par référence
    cout << "Référence (x, y): " << estEgalReference(x, y) << endl;
    cout << "Référence (x, z): " << estEgalReference(x, z) << endl;

    // Test passage par adresse
    cout << "Adresse (&x, &y): " << estEgalAdresse(&x, &y) << endl;
    cout << "Adresse (&x, &z): " << estEgalAdresse(&x, &z) << endl;

    return 0;
}