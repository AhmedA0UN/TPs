#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int main() {
    char t;
    float r, d;

    cout << "Pr�cisez le type de transfert :\n";
    cout << "Si vous voulez convertir des kilom�tres vers miles, entrez 'M'.\n";
    cout << "Sinon, pour convertir des miles vers kilom�tres, entrez 'K'.\n";
    cin >> t;

    cout << "Entrez la distance : ";
    cin >> d;

    if (t == 'K') {
        r = d * 1.609;
        cout << "La conversion vers le kilom�tre est : " << r << endl;
    }
    else if (t == 'M') {
        r = d / 1.609;
        cout << "La conversion vers le mile est : " << r << endl;
    }
    else {
        cout << "Type de transfert invalide. Veuillez entrer 'K' ou 'M'." << endl;
    }

    return 0;
}
