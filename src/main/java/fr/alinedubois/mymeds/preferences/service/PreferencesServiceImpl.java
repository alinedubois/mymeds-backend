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
        PreferenceDTO preferenceDTO = new PreferenceDTO(preferences.getId(),
                preferences.getUtilisateurId(),
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
