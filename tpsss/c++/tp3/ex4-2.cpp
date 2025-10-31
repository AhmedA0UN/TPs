#include <iostream>
#include <vector>
#include <tuple> // pour std::tuple
using namespace std;

vector<int> remplir(int taille) {
    vector<int> tab(taille);
    for (int i = 0; i < taille; ++i) {
        cout << "Entrez l'élément " << i + 1 << " : ";
        cin >> tab[i];
    }
    return tab;
}

tuple<int, int> mini_maxi(const vector<int>& tab) {
    int min = tab[0], max = tab[0];
    for (int val : tab) {
        if (val < min) min = val;
        if (val > max) max = val;
    }
    return make_tuple(min, max);
}

int main() {
    int taille;
    cout << "Taille du tableau : ";
    cin >> taille;

    vector<int> tableau = remplir(taille);
    auto [minVal, maxVal] = mini_maxi(tableau); // décomposition structurée

    cout << "Valeur minimale : " << minVal << endl;
    cout << "Valeur maximale : " << maxVal << endl;

    return 0;
}