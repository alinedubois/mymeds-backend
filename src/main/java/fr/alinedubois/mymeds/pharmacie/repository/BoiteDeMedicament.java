package fr.alinedubois.mymeds.pharmacie.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table (name="pharmacie")
public class BoiteDeMedicament {

    private String id;
    private String medicamentId;
    private LocalDate dateDePeremption;
    private String utilisateurId;

    @Id
    @Column(name="id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "medicament_id")
    public String getMedicamentId() {
        return medicamentId;
    }

    public void setMedicamentId(String medicamentId) {
        this.medicamentId = medicamentId;
    }

    @Column (name = "date_de_peremption")
    public LocalDate getDateDePeremption() {
        return dateDePeremption;
    }

    public void setDateDePeremption(LocalDate dateDePeremption) {
        this.dateDePeremption = dateDePeremption;
    }

    @Column (name = "utilisateur_id")
    public String getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(String utilisateurId) {
        this.utilisateurId = utilisateurId;
    }
}
