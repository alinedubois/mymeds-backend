package fr.alinedubois.mymeds.pharmacie.service;

import java.util.ArrayList;
import java.util.List;

public class PharmacieDTO {
    private final List<BoiteDeMedicament> boitesDeMedicaments = new ArrayList<>();

    public void ajouter(BoiteDeMedicament boiteDeMedicament) {
        this.boitesDeMedicaments.add(boiteDeMedicament);
    }

    public void supprimer(BoiteDeMedicament boiteDeMedicament) {this.boitesDeMedicaments.remove(boiteDeMedicament);}

    public List<BoiteDeMedicament> getBoitesDeMedicaments() {
        return boitesDeMedicaments;
    }
}
