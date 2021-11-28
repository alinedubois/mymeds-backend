package fr.alinedubois.mymeds.pharmacie.service;

import fr.alinedubois.mymeds.pharmacie.repository.BoiteDeMedicament;
import fr.alinedubois.mymeds.pharmacie.repository.BoiteDeMedicamentRepository;
import fr.alinedubois.mymeds.referentiel.domaine.modele.Medicament;
import fr.alinedubois.mymeds.referentiel.domaine.modele.Referentiel;
import org.springframework.stereotype.Service;

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
        List<BoiteDeMedicament> boitesDeMedicaments = this.boiteDeMedicamentRepository.findByUtilisateurId(email);
        boitesDeMedicaments.forEach(boiteDeMedicament ->{
            Medicament medicament = referentiel.parIdentifiant(boiteDeMedicament.getMedicamentId());
            BoiteDeMedicamentDTO boiteDeMedicamentDTO = new BoiteDeMedicamentDTO(
                    boiteDeMedicament.getId(),
                    medicament.nom(),
                    boiteDeMedicament.getDateDePeremption());
            pharmacieDTO.ajouter(boiteDeMedicamentDTO);
        });
        return pharmacieDTO;
    }
}
