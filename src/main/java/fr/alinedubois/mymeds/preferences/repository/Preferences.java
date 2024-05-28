package fr.alinedubois.mymeds.preferences.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "preference")
public class Preferences {
    private Long id;
    private String utilisateurId;
    private String notificationMail;
    private Integer notificationHeure;
    private String typeAffichageMedicaments;

    @Id
    @Column(name="id")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    @Column(name = "utilisateur_id")
    public String getUtilisateurId() {
        return utilisateurId;
    }
    public void setUtilisateurId(String utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    @Column(name = "notification_mail")
    public String getNotificationMail() {
        return notificationMail;
    }
    public void setNotificationMail(String notificationMail) {
        this.notificationMail = notificationMail;
    }

    @Column(name = "notification_heure")
    public Integer getNotificationHeure() {
        return notificationHeure;
    }
    public void setNotificationHeure(Integer notificationHeure) {
        this.notificationHeure = notificationHeure;
    }

    @Column(name = "type_affichage_medicaments")
    public String getTypeAffichageMedicaments() {
        return typeAffichageMedicaments;
    }
    public void setTypeAffichageMedicaments(String typeAffichageMedicaments) {
        this.typeAffichageMedicaments = typeAffichageMedicaments;
    }
}
