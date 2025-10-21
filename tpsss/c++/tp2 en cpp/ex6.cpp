#include <iostream>
using namespace std;

// Fonction récursive pour calculer u(n)
int u(int n) {
    if (n == 0)
        return 3;
    else
        return 3 * u(n - 1) + 4;
}

int main() {
    int N;
    cout << "Entrez un entier N : ";
    cin >> N;

    if (N < 0) {
        cout << "Veuillez entrer un entier positif." << endl;
    } else {
        cout << "u(" << N << ") = " << u(N) << endl;
    }

    return 0;
}