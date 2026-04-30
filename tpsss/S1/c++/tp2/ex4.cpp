#include <iostream>
using namespace std;

void addition(int& a, int& b, int& res) { res = a + b; }
void soustraction(int& a, int& b, int& res) { res = a - b; }
void multiplication(int& a, int& b, int& res) { res = a * b; }
void division(int& a, int& b, float& res) {
    if (b != 0) res = static_cast<float>(a) / b;
    else cout << "Erreur : division par zéro\n";
}

int main() {
    int choix, x, y, res;
    float resDiv;
    do {
        system("cls");
        cout << "=== CALCULATRICE ===\n1. Addition\n2. Multiplication\n3. Soustraction\n4. Division\n5. Quitter\nOperation ? ";
        cin >> choix;
        if (choix >= 1 && choix <= 4) {
            cout << "Entrez deux entiers : ";
            cin >> x >> y;
        }
        switch (choix) {
            case 1: addition(x, y, res); cout << "Résultat : " << res << endl; break;
            case 2: multiplication(x, y, res); cout << "Résultat : " << res << endl; break;
            case 3: soustraction(x, y, res); cout << "Résultat : " << res << endl; break;
            case 4: division(x, y, resDiv); if (y != 0) cout << "Résultat : " << resDiv << endl; break;
            case 5: cout << "Au revoir !\n"; break;
            default: cout << "Choix invalide\n";
        }
        if (choix != 5) { cout << "Appuyez sur Entrée...\n"; cin.ignore(); cin.get(); }
    } while (choix != 5);
    return 0;
}