#ifndef TABLEAUX_H
#define TABLEAUX_H

#include <iostream>
#include <vector>
#include <algorithm>
#include <tuple>
#include <utility>

using namespace std;

// Remplit un tableau de taille donnée
vector<int> remplir(int taille) {
    vector<int> tab(taille);
    for (int i = 0; i < taille; ++i) {
        cout << "Entrez l'élément " << i + 1 << " : ";
        cin >> tab[i];
    }
    return tab;
}

// Affiche les éléments du tableau
void afficher(const vector<int>& tab) {
    cout << "[ ";
    for (int val : tab) {
        cout << val << " ";
    }
    cout << "]" << endl;
}

// Trie le tableau en ordre croissant
void trier(vector<int>& tab) {
    sort(tab.begin(), tab.end());
}

// Recherche une valeur dans le tableau
bool rechercher(const vector<int>& tab, int valeur, int& position) {
    for (int i = 0; i < tab.size(); ++i) {
        if (tab[i] == valeur) {
            position = i;
            return true;
        }
    }
    return false;
}

// Calcule le produit scalaire de deux vecteurs
int produitScalaire(const vector<int>& U, const vector<int>& V) {
    int produit = 0;
    for (size_t i = 0; i < U.size(); ++i) {
        produit += U[i] * V[i];
    }
    return produit;
}

// Retourne le min et le max sous forme de pair
pair<int, int> mini_maxi_pair(const vector<int>& tab) {
    int min = tab[0], max = tab[0];
    for (int val : tab) {
        if (val < min) min = val;
        if (val > max) max = val;
    }
    return make_pair(min, max);
}

// Retourne le min et le max sous forme de tuple
tuple<int, int> mini_maxi_tuple(const vector<int>& tab) {
    int min = tab[0], max = tab[0];
    for (int val : tab) {
        if (val < min) min = val;
        if (val > max) max = val;
    }
    return make_tuple(min, max);
}

#endif // TABLEAUX_H