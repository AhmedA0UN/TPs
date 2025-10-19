#include <iostream>
using namespace std;

int u(int n) {
    if (n == 0) return 3;
    return 3 * u(n - 1) + 4;
}

int main() {
    int N;
    cout << "Entrez N : ";
    cin >> N;
    if (N >= 0)
        cout << "u(" << N << ") = " << u(N) << endl;
    else
        cout << "N doit être positif\n";
    return 0;
}