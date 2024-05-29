package fr.alinedubois.mymeds.preferences.service;

import jakarta.validation.constraints.NotNull;

public class PreferenceDTO {

    @NotNull
    private final Boolean notificationMail;
    @NotNull
    private final Integer notificationHeure;
    @NotNull
    private final String typeAffichageMedicaments;

    public PreferenceDTO(Boolean notificationMail, Integer notificationHeure, String typeAffichageMedicaments) {
        this.notificationMail = notificationMail;
        this.notificationHeure = notificationHeure;
        this.typeAffichageMedicaments = typeAffichageMedicaments;
    }

    public Boolean getNotificationMail() {
        return notificationMail;
    }

    public Integer getNotificationHeure() {
        return notificationHeure;
    }

    public String getTypeAffichageMedicaments() {
        return typeAffichageMedicaments;
    }
}
