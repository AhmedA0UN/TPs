#include <stdio.h>

// Définition récursive de la fonction u(n)
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
        printf("Veuillez entrer un entier positif.\n");
    } else {
        printf("u(%d) = %d\n", N, u(N));
    }

    return 0;
}