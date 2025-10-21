#include <iostream>
#include <cstdlib> // pour system("cls")
using namespace std;

// Fonctions avec passage par référence
void addition(double a, double b, double &result) {
    result = a + b;
}

void soustraction(double a, double b, double &result) {
    result = a - b;
}

void multiplication(double a, double b, double &result) {
    result = a * b;
}

void division(double a, double b, double &result) {
    if (b != 0)
        result = a / b;
    else {
        cout << "Erreur : division par zéro !" << endl;
        result = 0;
    }
}

// Fonction pour afficher le menu
void afficherMenu() {
    cout << "******** CALCULATRICE *****" << endl;
    cout << "* 1. Addition             **" << endl;
    cout << "* 2. Multiplication       **" << endl;
    cout << "* 3. Soustraction         **" << endl;
    cout << "* 4. Division             **" << endl;
    cout << "* 5. Quitter              **" << endl;
    cout << "****************************" << endl;
    cout << "Operation? ";
}

int main() {
    int choix;
    double a, b, resultat;

    do {
        system("cls"); // Efface la console
        afficherMenu();
        cin >> choix;

        if (choix >= 1 && choix <= 4) {
            cout << "Entrez le premier nombre : ";
            cin >> a;
            cout << "Entrez le deuxième nombre : ";
            cin >> b;

            switch (choix) {
                case 1:
                    addition(a, b, resultat);
                    break;
                case 2:
                    multiplication(a, b, resultat);
                    break;
                case 3:
                    soustraction(a, b, resultat);
                    break;
                case 4:
                    division(a, b, resultat);
                    break;
            }

            cout << "Résultat = " << resultat << endl;
            //system("pause"); // Attend que l'utilisateur appuie sur une touche
        }

    } while (choix != 5);

    cout << "Au revoir !" << endl;
    return 0;
}