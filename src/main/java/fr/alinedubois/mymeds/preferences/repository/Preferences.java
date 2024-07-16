package fr.alinedubois.mymeds.preferences.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "preference")
public class Preferences {
    private String utilisateurId;
    private Boolean notificationMail;
    private String notificationHeure;
    private String typeAffichageMedicaments;


    @Id
    @Column(name = "utilisateur_id")
    public String getUtilisateurId() {
        return utilisateurId;
    }
    public void setUtilisateurId(String utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    @Column(name = "notification_mail")
    public Boolean getNotificationMail() {
        return notificationMail;
    }
    public void setNotificationMail(Boolean notificationMail) {
        this.notificationMail = notificationMail;
    }

    @Column(name = "notification_heure")
    public String getNotificationHeure() {
        return notificationHeure;
    }
    public void setNotificationHeure(String notificationHeure) {
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
