package fr.alinedubois.mymeds.pharmaciev1.domaine.modele;

import java.time.LocalDate;

public class BoiteDeMedicament {
    private final String nom;
    private final LocalDate dateDePeremption;

    public BoiteDeMedicament(String nom, LocalDate dateDePeremption) {
        this.nom = nom;
        this.dateDePeremption = dateDePeremption;
    }

    public String nom() {
        return nom;
    }

    public LocalDate dateDePeremption() {
        return dateDePeremption;
    }
}
