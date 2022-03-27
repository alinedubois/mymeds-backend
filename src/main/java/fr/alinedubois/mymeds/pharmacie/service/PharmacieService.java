package fr.alinedubois.mymeds.pharmacie.service;

import fr.alinedubois.mymeds.pharmacie.repository.BoiteDeMedicamentRepository;
import fr.alinedubois.mymeds.referentiel.domaine.modele.Medicament;
import fr.alinedubois.mymeds.referentiel.domaine.modele.Referentiel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Year;
import java.util.List;

@Service
public class PharmacieService {
    private BoiteDeMedicamentRepository boiteDeMedicamentRepository;
    private Referentiel referentiel;

    public PharmacieService(BoiteDeMedicamentRepository boiteDeMedicamentRepository, Referentiel referentiel) {
        this.boiteDeMedicamentRepository = boiteDeMedicamentRepository;
        this.referentiel = referentiel;
    }

    public PharmacieDTO recupererLaPharmacieDeLUtilisateur(String email) {
        PharmacieDTO pharmacieDTO = new PharmacieDTO();
        List<fr.alinedubois.mymeds.pharmacie.repository.BoiteDeMedicament> boitesDeMedicaments = this.boiteDeMedicamentRepository.findByUtilisateurId(email);
        boitesDeMedicaments.forEach(entiteBoiteDeMedicament ->{
            Medicament medicament = referentiel.parIdentifiant(entiteBoiteDeMedicament.getMedicamentId());
            BoiteDeMedicament boiteDeMedicament = new BoiteDeMedicament(
                    entiteBoiteDeMedicament.getId(),
                    entiteBoiteDeMedicament.getMedicamentId(),
                    medicament.nom(),
                    new DateDePeremption(
                            entiteBoiteDeMedicament.getDateDePeremption().getMonth(),
                            Year.of(entiteBoiteDeMedicament.getDateDePeremption().getYear())));
            pharmacieDTO.ajouter(boiteDeMedicament);
        });
        return pharmacieDTO;
    }

    @Transactional
    public void ajouterBoiteDeMedicament(AjoutMedicamentDTO ajoutMedicamentDTO, String utilisateurId) {
        boiteDeMedicamentRepository.insererBoiteDeMedicament("" + ajoutMedicamentDTO.idMedicament(), ajoutMedicamentDTO.dateDePeremption(), utilisateurId);
    }

    @Transactional
    public void supprimerUneBoiteDeMedicament(Long id, String utilisateurId) {
        boiteDeMedicamentRepository.supprimerBoiteDeMedicament(id, utilisateurId);
    }
}
