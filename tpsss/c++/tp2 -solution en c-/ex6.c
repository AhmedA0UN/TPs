#include <stdio.h>

// Fonction récursive
int u(int n) {
    if (n == 0)
        return 3;
    else
        return 3 * u(n - 1) + 4;
}

int main() {
    int N;

    printf("Entrez un entier N : ");
    scanf("%d", &N);

    if (N < 0) {
        printf("Erreur : N doit être un entier positif.\n");
    } else {
        int resultat = u(N);
        printf("u(%d) = %d\n", N, resultat);
    }

    return 0;
}