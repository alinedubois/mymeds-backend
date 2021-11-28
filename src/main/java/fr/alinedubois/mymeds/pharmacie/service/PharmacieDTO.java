package fr.alinedubois.mymeds.pharmacie.service;

import java.util.ArrayList;
import java.util.List;

public class PharmacieDTO {
    private final List<BoiteDeMedicamentDTO> boitesDeMedicaments = new ArrayList<>();

    public void ajouter(BoiteDeMedicamentDTO boiteDeMedicamentDTO) {
        this.boitesDeMedicaments.add(boiteDeMedicamentDTO);
    }

    public List<BoiteDeMedicamentDTO> getBoitesDeMedicaments() {
        return boitesDeMedicaments;
    }
}
