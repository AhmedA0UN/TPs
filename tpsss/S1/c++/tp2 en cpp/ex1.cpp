#include <iostream>
using namespace std;

int main() {
    // Pointeur vers un entier
    int* ptrInt = new int;     // Allocation dynamique
    *ptrInt = 42;              // Initialisation

    // Affichage
    cout << "Entier: " << *ptrInt << endl;
    cout << "Adresse de l'entier: " << ptrInt << endl;

    // Pointeur vers un réel (float)
    float* ptrFloat = new float;  // Allocation dynamique
    *ptrFloat = 3.14f;            // Initialisation

    // Affichage
    cout << "Réel: " << *ptrFloat << endl;
    cout << "Adresse du réel: " << ptrFloat << endl;

    // Libération de la mémoire
    delete ptrInt;
    delete ptrFloat;

    return 0;
}