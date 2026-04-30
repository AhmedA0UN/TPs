#include<iostream>
using namespace std;

bool estEgalValeur(int a, int b) {
	return a == b;
}

bool estEgalAdresse(int* a, int* b) {
	return *a == *b;
}

bool estEgalReference(int& a, int& b) {
	return a == b;
}



bool estEgalValeur(int a, int b);
bool estEgalAdresse(int* a, int* b);
bool estEgalReference(int& a, int& b);

int main() {
    int x = 5, y = 5, z = 10;

    // Test passage par valeur
    cout << "Valeur (x, y): " << estEgalValeur(x, y) << endl;
    cout << "Valeur (x, z): " << estEgalValeur(x, z) << endl;

    // Test passage par adresse
    cout << "Adresse (x, y): " << estEgalAdresse(&x, &y) << endl;
    cout << "Adresse (x, z): " << estEgalAdresse(&x, &z) << endl;

    // Test passage par référence
    cout << "Reference (x, y): " << estEgalReference(x, y) << endl;
    cout << "Reference (x, z): " << estEgalReference(x, z) << endl;

    return 0;
}
