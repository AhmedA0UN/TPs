#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// Génère un entier aléatoire entre min et max inclus
int entierAleatoire(int min, int max) {
    return rand() % (max - min + 1) + min;
}

// Génère un réel aléatoire entre min et max
float reelAleatoire(float min, float max) {
    float scale = rand() / (float) RAND_MAX; // entre 0 et 1
    return min + scale * (max - min);
}

int main() {
    srand(time(NULL)); // Initialisation du générateur aléatoire

    int i;
    printf("=== Entiers aléatoires entre 10 et 50 ===\n");
    for (i = 0; i < 5; i++) {
        int val = entierAleatoire(10, 50);
        printf("Entier %d : %d\n", i + 1, val);
    }

    printf("\n=== Réels aléatoires entre 1.0 et 5.0 ===\n");
    for (i = 0; i < 5; i++) {
        float val = reelAleatoire(1.0, 5.0);
        printf("Réel %d : %.2f\n", i + 1, val);
    }

    return 0;
}