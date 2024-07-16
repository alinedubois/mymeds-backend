package fr.alinedubois.mymeds.preferences.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PreferencesRepository extends JpaRepository<Preferences, Integer> {
    @Query(
            value = "SELECT utilisateur_id, notification_mail, notification_heure, type_affichage_medicaments " +
                    "FROM preference " +
                    "WHERE utilisateur_id = ?",
            nativeQuery = true
    )
    Preferences recupererPreferences(String utilisateurId);

    @Modifying
    @Query(
            value = """
                    insert into preference (notification_mail, notification_heure, type_affichage_medicaments, utilisateur_id) 
                    values (:notificationMail, :notificationHeure, :typeAffichageMedicaments, :utilisateurId)
                    on conflict (utilisateur_id)
                    do update 
                    set notification_mail = :notificationMail, notification_heure = :notificationHeure, type_affichage_medicaments = :typeAffichageMedicaments
                    """,
            nativeQuery = true
    )
    void modifierPreferences(@Param("notificationMail") Boolean notificationMail,
                             @Param("notificationHeure") String notificationHeure,
                             @Param("typeAffichageMedicaments") String typeAffichageMedicaments,
                             @Param("utilisateurId") String utilisateurId);

}
