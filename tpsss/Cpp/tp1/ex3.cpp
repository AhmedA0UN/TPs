#include <iostream>
#include <string>
using namespace std;

int main() {
    int N;
    cout << "Combien de mots voulez-vous saisir ? ";
    cin >> N;

    string mot, mot_le_plus_long;
    int longueur_max = 0;

    for (int i = 0; i < N; ++i) {
        cout << "Mot " << i + 1 << " : ";
        cin >> mot;

        if (mot.length() > longueur_max) {
            longueur_max = mot.length();
            mot_le_plus_long = mot;
        }
    }

    cout << "\nLe mot le plus long est : " << mot_le_plus_long
         << " (" << longueur_max << " caractères)" << endl;

    return 0;
}
