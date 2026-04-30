#include <iostream>
using namespace std;

int main() {
    // Pointeur vers un entier
    int* ptrInt = new int;     // Allocation dynamique
    *ptrInt = 42;              // Initialisation

    cout << "Valeur de l'entier : " << *ptrInt << endl;
    cout << "Adresse mémoire de l'entier : " << ptrInt << endl;

    // Pointeur vers un réel (double)
    double* ptrDouble = new double;  // Allocation dynamique
    *ptrDouble = 3.14159;            // Initialisation

    cout << "Valeur du réel : " << *ptrDouble << endl;
    cout << "Adresse mémoire du réel : " << ptrDouble << endl;

    // Libération de la mémoire
    delete ptrInt;
    delete ptrDouble;

    return 0;
}
