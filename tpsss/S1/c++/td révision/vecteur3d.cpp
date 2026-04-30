#include <iostream>
#include <cmath>
using namespace std;

class vecteur3d {
private:
    float x, y, z;

public:

    // --- Constructeur avec valeurs par défaut ---
    vecteur3d(float a = 0, float b = 0, float c = 0) {
        x = a;
        y = b;
        z = c;
    }

    // --- Fonction d'affichage ---
    void afficher() const {
        cout << "(" << x << ", " << y << ", " << z << ")" << endl;
    }

    // --- Somme de deux vecteurs ---
    vecteur3d somme(const vecteur3d &v) const {
        return vecteur3d(x + v.x, y + v.y, z + v.z);
    }

    // --- Produit scalaire ---
    float produit_scalaire(const vecteur3d &v) const {
        return x * v.x + y * v.y + z * v.z;
    }

    // --- Test de coïncidence ---
    bool coincide(const vecteur3d &v) const {
        return (x == v.x && y == v.y && z == v.z);
    }

    // --- Norme du vecteur ---
    float norme() const {
        return sqrt(x*x + y*y + z*z);
    }

    // --- Normax : renvoie par valeur ---
    vecteur3d normax_valeur(const vecteur3d &v) const {
        return (this->norme() >= v.norme()) ? *this : v;
    }

    // --- Normax : renvoie par adresse ---
    vecteur3d* normax_adresse(vecteur3d *v) {
        return (this->norme() >= v->norme()) ? this : v;
    }

    // --- Normax : renvoie par référence ---
    vecteur3d& normax_reference(vecteur3d &v) {
        return (this->norme() >= v.norme()) ? *this : v;
    }
};
