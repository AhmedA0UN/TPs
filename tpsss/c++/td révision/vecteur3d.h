#ifndef VECTEUR3D_H
#define VECTEUR3D_H
// #pragma once

#include <iostream>

class vecteur3d {
private:
    float x, y, z;

public:
    // --- Constructeur avec valeurs par défaut ---
    vecteur3d(float a = 0, float b = 0, float c = 0);

    // --- Affichage ---
    void afficher() const;

    // --- Somme de deux vecteurs ---
    vecteur3d somme(const vecteur3d &v) const;

    // --- Produit scalaire ---
    float produit_scalaire(const vecteur3d &v) const;

    // --- Coïncidence ---
    bool coincide(const vecteur3d &v) const;

    // --- Norme ---
    float norme() const;

    // --- Normax : renvoie par valeur ---
    vecteur3d normax_valeur(const vecteur3d &v) const;

    // --- Normax : renvoie par adresse ---
    vecteur3d* normax_adresse(vecteur3d *v);

    // --- Normax : renvoie par référence ---
    vecteur3d& normax_reference(vecteur3d &v);
};

#endif
