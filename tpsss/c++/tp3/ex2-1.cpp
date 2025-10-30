#include <iostream>
using namespace std;

void remplir(int* tab, int taille) {
    for (int i = 0; i < taille; ++i) {
        cout << "Entrez l'élément " << i + 1 << " : ";
        cin >> tab[i];
    }
}

void recherche(int* tab, int taille, int valeur) {
    for (int i = 0; i < taille; ++i) {
        if (tab[i] == valeur) {
            cout << valeur << " se trouve dans le tableau à la position " << i << endl;
            return;
        }
    }
    cout << valeur << " ne se trouve pas dans le tableau." << endl;
}

int main() {
    int taille;
    cout << "Taille du tableau : ";
    cin >> taille;

    int* tab = new int[taille];
    remplir(tab, taille);

    int v;
    cout << "Valeur à rechercher : ";
    cin >> v;

    recherche(tab, taille, v);

    delete[] tab;
    return 0;
}