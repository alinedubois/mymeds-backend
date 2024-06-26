package fr.alinedubois.mymeds.preferences.service;

import fr.alinedubois.mymeds.preferences.repository.Preferences;
import fr.alinedubois.mymeds.preferences.repository.PreferencesRepository;
import jakarta.transaction.Transactional;
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
                     "09:00",
                    "PAR_ORDRE_ALPHABETIQUE"
            );
            return preferenceParDefaut;
        }

        PreferenceDTO preferenceDTO = new PreferenceDTO(
                preferences.getNotificationMail(),
                preferences.getNotificationHeure(),
                preferences.getTypeAffichageMedicaments());

        return preferenceDTO;
    }

    @Transactional
    @Override
    public void modifierPreferences(String utilisateurId, PreferenceDTO preferenceDTO) {
        preferencesRepository.modifierPreferences(preferenceDTO.getNotificationMail(),
                preferenceDTO.getNotificationHeure(),
                        preferenceDTO.getTypeAffichageMedicaments(),
                        utilisateurId);
    }
}
