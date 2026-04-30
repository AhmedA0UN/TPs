#include <iostream>
using namespace std;

// Fonction pour incrémenter une variable par référence
void incrementer(int& val) {
    val++;
}

// Fonction pour permuter deux variables par référence
void permuter(int& a, int& b) {
    int temp = a;
    a = b;
    b = temp;
}

int main() {
    int x = 5, y = 10;

    cout << "Avant incrémentation : x = " << x << endl;
    incrementer(x);
    cout << "Après incrémentation : x = " << x << endl;

    cout << "Avant permutation : x = " << x << ", y = " << y << endl;
    permuter(x, y);
    cout << "Après permutation : x = " << x << ", y = " << y << endl;

    return 0;
}
