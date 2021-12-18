package fr.alinedubois.mymeds.pharmacie.service;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AjoutMedicamentDTO {
    @NotNull
    private Long idMedicament;
    @NotNull
    private LocalDate dateDePeremption;

    public Long idMedicament() {
        return idMedicament;
    }

    public void setIdMedicament(Long idMedicament) {
        this.idMedicament = idMedicament;
    }

    public LocalDate dateDePeremption() {
        return dateDePeremption;
    }

    public void setDateDePeremption(LocalDate dateDePeremption) {
        this.dateDePeremption = dateDePeremption;
    }
}
