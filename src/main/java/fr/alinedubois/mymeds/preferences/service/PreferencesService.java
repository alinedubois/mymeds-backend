package fr.alinedubois.mymeds.preferences.service;

public interface PreferencesService {
    PreferenceDTO recupererPreferences(String utilisateurId);
    void modifierPreferences(String utilisateurId, PreferenceDTO preferenceDTO);
}


