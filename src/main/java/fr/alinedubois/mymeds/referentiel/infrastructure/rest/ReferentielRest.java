package fr.alinedubois.mymeds.referentiel.infrastructure.rest;

import fr.alinedubois.mymeds.referentiel.application.RechercheDeMedicamentsParNom;
import fr.alinedubois.mymeds.referentiel.domaine.modele.Medicament;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class ReferentielRest {
    private RechercheDeMedicamentsParNom rechercheDeMedicamentsParNom;

    public ReferentielRest(RechercheDeMedicamentsParNom rechercheDeMedicamentsParNom) {
        this.rechercheDeMedicamentsParNom = rechercheDeMedicamentsParNom;
    }

    @GetMapping("/api/referentiel/medicaments")
    public ResponseEntity<List<Medicament>> medicamentsParLeNom (@RequestParam String nom) {
        if (nom == null || nom.trim().equals("") || nom.trim().length()<3) {
            return ResponseEntity.badRequest().build();
        }
        List<Medicament> medicaments=rechercheDeMedicamentsParNom.rechercher(nom);
        return ResponseEntity.ok(medicaments);
    }
}
