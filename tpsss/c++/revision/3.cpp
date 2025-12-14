#include <bits/stdc++.h>

using namespace std;

variant<double, string> f(double val) {
    if (val >= 0)
        return val * 2.5;  
    else
        return "Erreur : valeur négative"; 
}

int main() {
    double val;
    cout << "Donner une valeur : ";
    cin >> val;

    auto resultat = f(val);

    if (resultat.index() == 0)  // Si le type actif est double
        cout << "f(" << val << ") = " << get<0>(resultat) << endl;
    else                        // Sinon, c'est une string
        cout << get<1>(resultat) << endl;

    return 0;
}