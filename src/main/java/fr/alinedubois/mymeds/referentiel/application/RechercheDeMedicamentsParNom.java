package fr.alinedubois.mymeds.referentiel.application;

import fr.alinedubois.mymeds.referentiel.domaine.modele.Medicament;
import fr.alinedubois.mymeds.referentiel.domaine.modele.Referentiel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RechercheDeMedicamentsParNom {
    private Referentiel referentiel;

    public RechercheDeMedicamentsParNom(Referentiel referentiel) {
        this.referentiel = referentiel;
    }

    public List<Medicament> rechercher(String nom) {
        return referentiel.medicamentsDontLeNomContient(nom);
    }
}
