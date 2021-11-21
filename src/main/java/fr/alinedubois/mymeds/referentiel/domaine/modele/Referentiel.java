package fr.alinedubois.mymeds.referentiel.domaine.modele;

import java.util.List;

public interface Referentiel {
    List<Medicament> medicamentsDontLeNomContient (String nom);
    Medicament parIdentifiant(String identifiant);
}
