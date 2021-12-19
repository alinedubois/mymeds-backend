package fr.alinedubois.mymeds.pharmacie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoiteDeMedicamentRepository extends JpaRepository<BoiteDeMedicament, Long> {

    @Query(
        value = "SELECT * FROM pharmacie WHERE utilisateur_id = ?",
        nativeQuery = true
    )
    List<BoiteDeMedicament> findByUtilisateurId(String utilisateurId);
}
