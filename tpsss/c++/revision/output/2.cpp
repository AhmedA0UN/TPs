#include <iostream>
using namespace std;

const int MAX = 100; // taille maximal du tableau

bool estpremier(int n) {
    if (n < 2) return false;
    for (int i = 2; i <= n / 2; i++) {
        if (n % i == 0) return false;
    }
    return true;
}

int remplir(int tab[]) {
    int n;
    cout << "donner la taille max (<= " << MAX << ") : ";
    cin >> n;
    if (n > MAX) n = MAX;
    for (int i = 0; i < n; i++) {
        tab[i] = i;
    }
    return n;
}

void afficher(const int tab[], int taille) {
    for (int i = 0; i < taille; i++) {
        cout << "[" << tab[i] << "]";
    }
    cout << endl;
}

int main() {
    int t[MAX], vp[MAX], vnp[MAX];
    int taille = remplir(t);

    int ip = 0, inp = 0;
    for (int i = 0; i < taille; i++) {
        if (estpremier(t[i]))
            vp[ip++] = t[i];
        else
            vnp[inp++] = t[i];
    }

    cout << "les nbres premiers : " << endl;
    afficher(vp, ip);

    cout << "les nbres non premiers : " << endl;
    afficher(vnp, inp);

    return 0;
}