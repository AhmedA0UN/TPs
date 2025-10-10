#include <iostream>
#include <random>

// Fonction pour générer un réel aléatoire dans un intervalle donné
double genererReel(double min, double max) {
    std::random_device rd;
    std::mt19937 gen(rd());
    std::uniform_real_distribution<> dis(min, max);
    return dis(gen);
}

// Fonction pour générer un entier aléatoire dans un intervalle donné
int genererEntier(int min, int max) {
    std::random_device rd;
    std::mt19937 gen(rd());
    std::uniform_int_distribution<> dis(min, max);
    return dis(gen);
}

int main() {
    std::cout << "Réels aléatoires entre 1.0 et 10.0 :" << std::endl;
    for (int i = 0; i < 5; ++i) {
        std::cout << genererReel(1.0, 10.0) << std::endl;
    }

    std::cout << "\nEntiers aléatoires entre 1 et 10 :" << std::endl;
    for (int i = 0; i < 5; ++i) {
        std::cout << genererEntier(1, 10) << std::endl;
    }

    return 0;
}
