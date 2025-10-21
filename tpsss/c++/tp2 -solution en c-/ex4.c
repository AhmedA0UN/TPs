#include <stdio.h>
#include <stdlib.h>

// Fonctions avec passage par adresse
void addition(int* a, int* b, int* resultat) {
    *resultat = *a + *b;
}

void soustraction(int* a, int* b, int* resultat) {
    *resultat = *a - *b;
}

void multiplication(int* a, int* b, int* resultat) {
    *resultat = *a * *b;
}

void division(int* a, int* b, float* resultat) {
    if (*b != 0)
        *resultat = (float)(*a) / (*b);
    else
        printf("Erreur : division par zéro !\n");
}

// Affichage du menu
void afficherMenu() {
    printf("=== CALCULATRICE ===\n");
    printf("1. Addition\n");
    printf("2. Multiplication\n");
    printf("3. Soustraction\n");
    printf("4. Division\n");
    printf("5. Quitter\n");
    printf("Operation ? ");
}

int main() {
    int choix;
    int x, y, res;
    float resDiv;

    do {
        system("cls"); // Efface la console (Windows)
        afficherMenu();
        scanf("%d", &choix);

        if (choix >= 1 && choix <= 4) {
            printf("Entrez le premier entier : ");
            scanf("%d", &x);
            printf("Entrez le deuxième entier : ");
            scanf("%d", &y);
        }

        switch (choix) {
            case 1:
                addition(&x, &y, &res);
                printf("Résultat : %d\n", res);
                break;
            case 2:
                multiplication(&x, &y, &res);
                printf("Résultat : %d\n", res);
                break;
            case 3:
                soustraction(&x, &y, &res);
                printf("Résultat : %d\n", res);
                break;
            case 4:
                division(&x, &y, &resDiv);
                if (y != 0)
                    printf("Résultat : %.2f\n", resDiv);
                break;
            case 5:
                printf("Au revoir !\n");
                break;
            default:
                printf("Choix invalide.\n");
        }

        if (choix != 5) {
            printf("\nAppuyez sur Entrée pour continuer...");
            getchar(); // Consomme le '\n' restant
            getchar(); // Attend une touche
        }

    } while (choix != 5);

    return 0;
}