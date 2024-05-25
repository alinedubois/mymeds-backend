package fr.alinedubois.mymeds.pharmacie.controller;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class CaracteristiquesMedicamentDTO {
    @NotNull
    private final Long idMedicament;
    @NotNull
    private final LocalDate dateDePeremption;

    public CaracteristiquesMedicamentDTO(Long idMedicament, LocalDate dateDePeremption) {
        this.idMedicament = idMedicament;
        this.dateDePeremption = dateDePeremption;
    }

    public Long idMedicament() {
        return idMedicament;
    }

    public LocalDate dateDePeremption() {
        return dateDePeremption;
    }
}
