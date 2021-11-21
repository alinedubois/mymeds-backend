package fr.alinedubois.mymeds.pharmacie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoiteDeMedicamentRepository extends JpaRepository<BoiteDeMedicament, String> {
    List<BoiteDeMedicament> findByUtilisateurId(String utilisateurId);
}
