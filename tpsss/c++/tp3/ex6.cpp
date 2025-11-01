#include "ex6.h"

int main() {
    int taille;
    cout << "Taille du tableau : ";
    cin >> taille;

    vector<int> tab = remplir(taille);
    afficher(tab);

    auto [minVal, maxVal] = mini_maxi_tuple(tab);
    cout << "Min : " << minVal << ", Max : " << maxVal << endl;

    trier(tab);
    cout << "Tableau trié : ";
    afficher(tab);

    int v, pos;
    cout << "Valeur à rechercher : ";
    cin >> v;
    if (rechercher(tab, v, pos)) {
        cout << v << " trouvé à la position " << pos << endl;
    } else {
        cout << v << " non trouvé." << endl;
    }

    return 0;
}