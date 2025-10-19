#include <iostream>
#include <random>
using namespace std;

int entierAleatoire(int min, int max) {
    static random_device rd;
    static mt19937 gen(rd());
    uniform_int_distribution<> dist(min, max);
    return dist(gen);
}

double reelAleatoire(double min, double max) {
    static random_device rd;
    static mt19937 gen(rd());
    uniform_real_distribution<> dist(min, max);
    return dist(gen);
}

int main() {
    for (int i = 0; i < 5; ++i)
        cout << "Entier : " << entierAleatoire(10, 50) << endl;

    for (int i = 0; i < 5; ++i)
        cout << "Réel : " << reelAleatoire(1.0, 5.0) << endl;

    return 0;
}