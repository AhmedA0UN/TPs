#include <iostream>
#include <vector>
using namespace std;

vector<int> remplir(int taille) {
    vector<int> tab(taille);
    for (int i = 0; i < taille; ++i) {
        cout << "Entrez l'élément " << i + 1 << " : ";
        cin >> tab[i];
    }
    return tab;
}

void recherche(const vector<int>& tab, int valeur) {
    for (int i = 0; i < tab.size(); ++i) {
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

    vector<int> tab = remplir(taille);

    int v;
    cout << "Valeur à rechercher : ";
    cin >> v;

    recherche(tab, v);
    return 0;
}