package model;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private int id;
    private String nom;
    private String description;
    private final List<Plat> plats;

    public Menu() {
        this.plats = new ArrayList<>();
    }

    public Menu(int id, String nom, String description) {
        this();
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Plat> getPlats() {
        return plats;
    }

    public void addPlat(Plat plat) {
        if (plat != null) {
            plats.add(plat);
        }
    }

    public void removePlat(int idPlat) {
        plats.removeIf(plat -> plat.getIdPlat() == idPlat);
    }

    @Override
    public String toString() {
        return "Menu[id=" + id + ", nom=" + nom + ", description=" + description + ", plats=" + plats.size() + "]";
    }
}
