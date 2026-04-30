#include <iostream>
#include <string>
using namespace std;

class Produit {
private:
    static int cmp;   
    int codeProd;
    string nom;
    float prix;
    int quantite;

public:
    
    Produit(string nom = "", float prix = 0, int k = 0) {
        codeProd = cmp++;
     
        this->nom = nom;
        this->prix = prix;
        quantite = k;
    }

    void saisir() {
        cout << "Nom: ";
        cin >> nom;
        cout << "Prix: ";
        cin >> prix;
        cout << "Quantite: ";
        cin >> quantite;
        codeProd = cmp++;
    }

    void afficher() const {
        cout << "Code: " << codeProd << " | Nom: " << nom << " | Prix: " << prix << " | Quantite: " << quantite << endl;
    }

    float valeurStock() const {
        return prix * quantite;
    }


    bool estPlusCher(const Produit& p) const {
        return prix > p.prix;
    }

    float getPrix() const { return prix; }
};


int Produit::cmp = 0;

int main() {
    int n;
    cout << "Donner le nombre de produits: ";
    cin >> n;

    Produit* tab = new Produit[n];

    cout << "\nSaisir les informations de chaque produit:\n";
    for (int i = 0; i < n; i++) {
        cout << "Produit " << i + 1 << ":\n";
        tab[i].saisir();
    }

    cout << "\nLes informations de chaque produit:\n";
    for (int i = 0; i < n; i++) {
        tab[i].afficher();
    }


    int idxMax = 0;
    for (int i = 1; i < n; i++) {
        if (tab[i].estPlusCher(tab[idxMax])) {
            idxMax = i;
        }
    }
    cout << "\nProduit le plus cher:\n";
    tab[idxMax].afficher();

    float total = 0;
    for (int i = 0; i < n; i++) {
        total += tab[i].valeurStock();
    }
    cout << "\nValeur totale du stock: " << total << endl;


    delete[] tab;

    return 0;
}