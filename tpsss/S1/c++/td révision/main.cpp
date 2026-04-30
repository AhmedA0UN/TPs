#include"vecteur3d.h"
#include<bits/stdc++.h>
using namespace std;

int main() {
    vecteur3d v1(3, 4, 5);
    vecteur3d v2(1, 2, 2);

    cout << "v1 = "; v1.afficher();
    cout << "v2 = "; v2.afficher();

    // Somme
    vecteur3d s = v1.somme(v2);
    cout << "Somme v1 + v2 = "; s.afficher();

    // Produit scalaire
    cout << "Produit scalaire = " << v1.produit_scalaire(v2) << endl;

    // Coïncidence
    cout << "v1 et v2 coincident ? " << (v1.coincide(v2) ? "Oui" : "Non") << endl;

    // Normes
    cout << "Norme de v1 = " << v1.norme() << endl;
    cout << "Norme de v2 = " << v2.norme() << endl;

    // Normax par valeur
    vecteur3d nv = v1.normax_valeur(v2);
    cout << "Normax (valeur) = "; nv.afficher();

    // Normax par adresse
    vecteur3d *na = v1.normax_adresse(&v2);
    cout << "Normax (adresse) = "; na->afficher();

    // Normax par référence
    vecteur3d &nr = v1.normax_reference(v2);
    cout << "Normax (référence) = "; nr.afficher();

    return 0;
}
