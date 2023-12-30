package fr.alinedubois.mymeds.notifications;

import fr.alinedubois.mymeds.pharmacie.repository.UtilisateurRepository;
import fr.alinedubois.mymeds.pharmacie.service.BoiteDeMedicament;
import fr.alinedubois.mymeds.pharmacie.service.PharmacieDTO;
import fr.alinedubois.mymeds.pharmacie.service.PharmacieService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatchNotifications {
    private final PharmacieService pharmacieService;
    private final UtilisateurRepository utilisateurRepository;
    private final MedicamentPerimeNotification medicamentPerimeNotification;

    public BatchNotifications(PharmacieService pharmacieService, UtilisateurRepository utilisateurRepository, MedicamentPerimeNotification medicamentPerimeNotification) {
        this.pharmacieService = pharmacieService;
        this.utilisateurRepository = utilisateurRepository;
        this.medicamentPerimeNotification = medicamentPerimeNotification;
    }

    @Scheduled(cron = "0 0 0 * * MON")
    public void envoiDeNotificationsEnCasDeMedicamentsPerimes() {

        List<String> utilisateursActifs = utilisateurRepository.utilisateursActifs();
        for (String utilisateurActif : utilisateursActifs) {
            PharmacieDTO pharmacie = pharmacieService.recupererLaPharmacieDeLUtilisateur(utilisateurActif);
            List<BoiteDeMedicament> boitesDeMedicaments = pharmacie.getBoitesDeMedicaments();
            List<BoiteDeMedicament> boitesDeMedicamentsPerimes = boitesDeMedicaments.stream()
                    .filter(boiteDeMedicament -> boiteDeMedicament.estDejaPerime() || boiteDeMedicament.perimeDansLesTroisMois())
                    .collect(Collectors.toList());
            if (!boitesDeMedicamentsPerimes.isEmpty()) {
                medicamentPerimeNotification.envoiDeNotification(utilisateurActif, boitesDeMedicamentsPerimes);
            }
        }
    }
}
