#include <iostream>
#include <random>
using namespace std;

// Générateur global
random_device rd;
mt19937 gen(rd());

// Fonction pour générer un entier aléatoire
int genererEntierAleatoire(int min, int max) {
    uniform_int_distribution<> distr(min, max);
    return distr(gen);
}

// Fonction pour générer un réel aléatoire
double genererReelAleatoire(double min, double max) {
    uniform_real_distribution<> distr(min, max);
    return distr(gen);
}

int main() {
    cout << "Nombres entiers aléatoires entre 1 et 10 :" << endl;
    for (int i = 0; i < 5; ++i) {
        cout << genererEntierAleatoire(1, 10) << " ";
    }
    cout << endl << endl;

    cout << "Nombres réels aléatoires entre 0.0 et 1.0 :" << endl;
    for (int i = 0; i < 5; ++i) {
        cout << genererReelAleatoire(0.0, 1.0) << " ";
    }
    cout << endl;

    return 0;
}