package fr.alinedubois.mymeds.pharmaciev1.domaine.modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pharmacie {
    private final List<BoiteDeMedicament> boitesDeMedicaments;

    public Pharmacie() {
        this.boitesDeMedicaments = new ArrayList<>();
    }

    public List<BoiteDeMedicament> boitesDeMedicaments() {
        return Collections.unmodifiableList(this.boitesDeMedicaments);
    }

    public void ajouter(BoiteDeMedicament boiteDeMedicament) {
        this.boitesDeMedicaments.add(boiteDeMedicament);
    }
}
