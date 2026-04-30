#include <iostream>
#include <cstdlib> // pour system("cls")
using namespace std;

// Fonctions avec passage par référence
void addition(float a, float b, float& res) {
    res = a + b;
}

void multiplication(float a, float b, float& res) {
    res = a * b;
}

void soustraction(float a, float b, float& res) {
    res = a - b;
}

void division(float a, float b, float& res) {
    if (b != 0)
        res = a / b;
    else {
        cout << "Erreur : division par zéro !" << endl;
        res = 0;
    }
}

int main() {
    int choix;
    float x, y, resultat;

    do {
        system("cls"); // Efface la console (Windows uniquement)

        // Affichage du menu
        cout << "******** CALCULATRICE *****" << endl;
        cout << "* 1. Addition              *" << endl;
        cout << "* 2. Multiplication        *" << endl;
        cout << "* 3. Soustraction          *" << endl;
        cout << "* 4. Division              *" << endl;
        cout << "* 5. Quitter               *" << endl;
        cout << "****************************" << endl;
        cout << "Operation? ";
        cin >> choix;

        if (choix >= 1 && choix <= 4) {
            cout << "Entrez le premier opérande : ";
            cin >> x;
            cout << "Entrez le second opérande : ";
            cin >> y;

            switch (choix) {
            case 1:
                addition(x, y, resultat);
                break;
            case 2:
                multiplication(x, y, resultat);
                break;
            case 3:
                soustraction(x, y, resultat);
                break;
            case 4:
                division(x, y, resultat);
                break;
            }

            cout << "Résultat : " << resultat << endl;
        }
        else if (choix != 5) {
            cout << "Choix invalide. Veuillez réessayer." << endl;
        }

        if (choix != 5) {
            cout << "\nAppuyez sur Entrée pour continuer...";
            cin.ignore(); // vide le buffer
            cin.get();    // attend une touche
        }

    } while (choix != 5);

    cout << "Merci d'avoir utilisé la calculatrice. À bientôt !" << endl;
    return 0;
}
