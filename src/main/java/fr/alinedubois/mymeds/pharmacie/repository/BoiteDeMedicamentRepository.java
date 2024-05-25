package fr.alinedubois.mymeds.pharmacie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BoiteDeMedicamentRepository extends JpaRepository<BoiteDeMedicament, Long> {

    @Query(
        value = "SELECT * FROM pharmacie WHERE utilisateur_id = ?",
        nativeQuery = true
    )
    List<BoiteDeMedicament> findByUtilisateurId(String utilisateurId);

    @Modifying
    @Query(
            value = "INSERT INTO pharmacie (medicament_id, date_de_peremption, utilisateur_id)" +
                    "VALUES (?, ?, ?)",
            nativeQuery = true
    )
    void insererBoiteDeMedicament (String medicamentId, LocalDate dateDePeremption, String utilisateurId);

    @Modifying
    @Query(
            value = "DELETE FROM pharmacie WHERE id = ? AND utilisateur_id = ?",
            nativeQuery = true
    )
    void supprimerBoiteDeMedicament (Long id, String utilisateurId);

    @Query(
            value = "SELECT * FROM pharmacie WHERE medicament_id = ?",
            nativeQuery = true
    )
    List<BoiteDeMedicament> findByMedicamentId(String medicamentId);

    @Modifying
    @Query(
            value = """
                    update pharmacie
                    set date_de_peremption = ?2,
                        medicament_id = ?3
                    where id = ?1 and utilisateur_id = ?4
                    """,
            nativeQuery = true
    )
    void modifierBoiteDeMedicament (Long id,
                                    LocalDate dateDePeremption,
                                    Long medicamentId,
                                    String utilisateurId);


}
