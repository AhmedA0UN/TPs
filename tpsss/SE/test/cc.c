#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define N 3  // Nombre de lignes
#define M 3  // Nombre de colonnes

int A[N][M] = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};

int B[N][M] = {
    {1, 1, 1},
    {2, 2, 2},
    {3, 3, 3}
};

int Somme[N][M];

void* addition_ligne(void* arg) {
    int i = *(int*)arg;
    for (int j = 0; j < M; j++) {
        Somme[i][j] = A[i][j] + B[i][j];
    }
    printf("Ligne %d calculée par le thread %lu\n", i, pthread_self());
    free(arg);
    return NULL;
}

void afficher_matrice(int matrice[N][M], const char* nom) {
    printf("Matrice %s :\n", nom);
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            printf("%d ", matrice[i][j]);
        }
        printf("\n");
    }
}

int main() {
    pthread_t threads[N];

    for (int i = 0; i < N; i++) {
        int* arg = malloc(sizeof(int));
        *arg = i;
        pthread_create(&threads[i], NULL, addition_ligne, arg);
    }

    for (int i = 0; i < N; i++) {
        pthread_join(threads[i], NULL);
    }

    afficher_matrice(A, "A");
    afficher_matrice(B, "B");
    afficher_matrice(Somme, "Somme");

    return 0;
}