package fr.alinedubois.mymeds.preferences.service;

import jakarta.validation.constraints.NotNull;

public class PreferenceDTO {

    private final Long id;
    private final String utilisateurId;
    @NotNull
    private final String notificationMail;
    @NotNull
    private final Integer notificationHeure;
    @NotNull
    private final String typeAffichageMedicaments;

    public PreferenceDTO(Long id, String utilisateurId, String notificationMail, Integer notificationHeure, String typeAffichageMedicaments) {
        this.id = id;
        this.utilisateurId = utilisateurId;
        this.notificationMail = notificationMail;
        this.notificationHeure = notificationHeure;
        this.typeAffichageMedicaments = typeAffichageMedicaments;
    }

    public Long getId() {
        return id;
    }

    public String getUtilisateurId() {
        return utilisateurId;
    }

    public String getNotificationMail() {
        return notificationMail;
    }

    public Integer getNotificationHeure() {
        return notificationHeure;
    }

    public String getTypeAffichageMedicaments() {
        return typeAffichageMedicaments;
    }
}
