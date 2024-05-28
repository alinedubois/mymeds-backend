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
                    update preference
                    set notification_heure = ?3,
                        notification_mail = ?2,
                        type_affichage_medicaments = ?4
                    where utilisateur_id = ?1
                    """,
            nativeQuery = true
    )
    void modifierPreferences(String utilisateurId,
                             String notificationMail,
                             Integer notificationHeure,
                             String typeAffichageMedicaments);

}
