#include <iostream>
using namespace std;

// Incrémenter via adresse
void incrementerAdresse(int* a) {
    (*a)++;
}

// Permuter via adresse
void permuterAdresse(int* a, int* b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

// Incrémenter via référence
void incrementerReference(int& a) {
    a++;
}

// Permuter via référence
void permuterReference(int& a, int& b) {
    int temp = a;
    a = b;
    b = temp;
}

int main() {
    int x = 5, y = 10;

    // Test passage par adresse
    cout << "Avant incrementerAdresse : x = " << x << endl;
    incrementerAdresse(&x);
    cout << "Après incrementerAdresse : x = " << x << endl;

    cout << "Avant permuterAdresse : x = " << x << ", y = " << y << endl;
    permuterAdresse(&x, &y);
    cout << "Après permuterAdresse : x = " << x << ", y = " << y << endl;

    // Test passage par référence
    cout << "Avant incrementerReference : x = " << x << endl;
    incrementerReference(x);
    cout << "Après incrementerReference : x = " << x << endl;

    cout << "Avant permuterReference : x = " << x << ", y = " << y << endl;
    permuterReference(x, y);
    cout << "Après permuterReference : x = " << x << ", y = " << y << endl;

    return 0;
}