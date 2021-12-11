package fr.alinedubois.mymeds.pharmacie.service;

import java.time.LocalDate;
import java.util.Date;

public class BoiteDeMedicament {
    private final String id;
    private final String nom;
    private final DateDePeremption dateDePeremption;

    public BoiteDeMedicament(String id, String nom, DateDePeremption dateDePeremption) {
        this.id = id;
        this.nom = nom;
        this.dateDePeremption = dateDePeremption;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public DateDePeremption getDateDePeremption() {
        return dateDePeremption;
    }

    public boolean estDejaPerime() {
        return this.dateDePeremption.estDepassee();
    }
}
