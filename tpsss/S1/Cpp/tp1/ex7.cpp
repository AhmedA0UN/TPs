#include <iostream>
using namespace std;

int factorielle(int N) {
    int result = 1;
    for (int i = 2; i <= N; ++i)
        result *= i;
    return result;
}

int main() {
    int n;
    cout << "Entrez un entier N : ";
    cin >> n;
    cout << "Factorielle de " << n << " est " << factorielle(n) << endl;
    return 0;
}
