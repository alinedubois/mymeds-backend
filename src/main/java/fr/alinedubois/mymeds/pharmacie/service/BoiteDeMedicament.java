package fr.alinedubois.mymeds.pharmacie.service;

public class BoiteDeMedicament {
    private final Long id;
    private final String nom;
    private final DateDePeremption dateDePeremption;

    public BoiteDeMedicament(Long id, String nom, DateDePeremption dateDePeremption) {
        this.id = id;
        this.nom = nom;
        this.dateDePeremption = dateDePeremption;
    }

    public Long getId() {
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

    public boolean perimeDansLesTroisMois() {
        return this.dateDePeremption.estDansLesTroisMois();
    }

    public boolean perimeDAuDelaDeTroisMois() {
        return this.dateDePeremption.estAuDelaDeTroisMois();
    }
}