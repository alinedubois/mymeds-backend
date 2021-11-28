package fr.alinedubois.mymeds.pharmacie.service;

import java.util.Date;

public class BoiteDeMedicamentDTO {
    private final String id;
    private final String nom;
    private final Date dateDePeremption;

    public BoiteDeMedicamentDTO(String id, String nom, Date dateDePeremption) {
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

    public Date getDateDePeremption() {
        return dateDePeremption;
    }
}
