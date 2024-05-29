package fr.alinedubois.mymeds.preferences.service;

import fr.alinedubois.mymeds.preferences.repository.Preferences;
import fr.alinedubois.mymeds.preferences.repository.PreferencesRepository;
import org.springframework.stereotype.Service;

@Service
public class PreferencesServiceImpl implements PreferencesService {
    private PreferencesRepository preferencesRepository;

    public PreferencesServiceImpl(PreferencesRepository preferencesRepository) {
        this.preferencesRepository = preferencesRepository;
    }

    @Override
    public PreferenceDTO recupererPreferences(String utilisateurId) {
        Preferences preferences = preferencesRepository.recupererPreferences(utilisateurId);
        if (preferences == null) {
            PreferenceDTO preferenceParDefaut = new PreferenceDTO(
                    true,
                     9,
                    "ORDRE_ALPHABETIQUE"
            );
            return preferenceParDefaut;
        }

        PreferenceDTO preferenceDTO = new PreferenceDTO(
                preferences.getNotificationMail(),
                preferences.getNotificationHeure(),
                preferences.getTypeAffichageMedicaments());

        return preferenceDTO;
    }

    @Override
    public void modifierPreferences(String utilisateurId, PreferenceDTO preferenceDTO) {
        preferencesRepository.modifierPreferences(utilisateurId,
                preferenceDTO.getNotificationMail(),
                preferenceDTO.getNotificationHeure(),
                preferenceDTO.getTypeAffichageMedicaments());
    }
}
