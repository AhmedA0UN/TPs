#include <iostream>

// Fonction récursive pour calculer u(n)
int u(int n) {
    if (n == 0)
        return 3;
    else
        return 3 * u(n - 1) + 4;
}

int main() {
    int N;
    std::cout << "Entrez un entier N : ";
    std::cin >> N;

    if (N < 0) {
        std::cout << "Veuillez entrer un entier positif ou nul." << std::endl;
    }
    else {
        std::cout << "u(" << N << ") = " << u(N) << std::endl;
    }

    return 0;
}
