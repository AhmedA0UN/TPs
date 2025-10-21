#include <iostream>
using namespace std;

// Incrémenter par adresse
void incrementerParAdresse(int* a) {
    (*a)++;
}

// Permuter par adresse
void permuterParAdresse(int* a, int* b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

// Incrémenter par référence
void incrementerParReference(int& a) {
    a++;
}

// Permuter par référence
void permuterParReference(int& a, int& b) {
    int temp = a;
    a = b;
    b = temp;
}

int main() {
    int x = 5, y = 10;

    // 🔸 Test des fonctions par adresse
    cout << "Avant incrementerParAdresse: x = " << x << endl;
    incrementerParAdresse(&x);
    cout << "Après incrementerParAdresse: x = " << x << endl;

    cout << "Avant permuterParAdresse: x = " << x << ", y = " << y << endl;
    permuterParAdresse(&x, &y);
    cout << "Après permuterParAdresse: x = " << x << ", y = " << y << endl;

    // 🔸 Test des fonctions par référence
    cout << "Avant incrementerParReference: y = " << y << endl;
    incrementerParReference(y);
    cout << "Après incrementerParReference: y = " << y << endl;

    cout << "Avant permuterParReference: x = " << x << ", y = " << y << endl;
    permuterParReference(x, y);
    cout << "Après permuterParReference: x = " << x << ", y = " << y << endl;

    return 0;
}