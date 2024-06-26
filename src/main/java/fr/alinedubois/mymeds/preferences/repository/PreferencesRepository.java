package fr.alinedubois.mymeds.preferences.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PreferencesRepository extends JpaRepository<Preferences, Integer> {
    @Query(
            value = "SELECT id, utilisateur_id, notification_mail, notification_heure, type_affichage_medicaments " +
                    "FROM preference " +
                    "WHERE utilisateur_id = ?",
            nativeQuery = true
    )
    Preferences recupererPreferences(String utilisateurId);

    @Modifying
    @Query(
            value = """
                    insert into preference (notification_mail, notification_heure, type_affichage_medicaments, utilisateur_id) 
                    values (?, ?, ?, ?)
                    """,
            nativeQuery = true
    )
    void modifierPreferences(Boolean notificationMail,
                             String notificationHeure,
                             String typeAffichageMedicaments,
                             String utilisateurId);

}
