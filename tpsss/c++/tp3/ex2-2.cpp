#include <iostream>
#include <array>
using namespace std;

const int TAILLE = 5; // Exemple fixe

void remplir(array<int, TAILLE>& tab) {
    for (int i = 0; i < TAILLE; ++i) {
        cout << "Entrez l'élément " << i + 1 << " : ";
        cin >> tab[i];
    }
}

void recherche(const array<int, TAILLE>& tab, int valeur) {
    for (int i = 0; i < TAILLE; ++i) {
        if (tab[i] == valeur) {
            cout << valeur << " se trouve dans le tableau à la position " << i << endl;
            return;
        }
    }
    cout << valeur << " ne se trouve pas dans le tableau." << endl;
}

int main() {
    array<int, TAILLE> tab;
    remplir(tab);

    int v;
    cout << "Valeur à rechercher : ";
    cin >> v;

    recherche(tab, v);
    return 0;
}