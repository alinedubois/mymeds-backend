package fr.alinedubois.mymeds.pharmaciev1.application;

import fr.alinedubois.mymeds.pharmaciev1.domaine.modele.Pharmacie;
import fr.alinedubois.mymeds.pharmaciev1.domaine.modele.Pharmacies;
import org.springframework.stereotype.Component;

@Component
public class RecuperationDeLaPharmacie {
    private Pharmacies pharmacies;

    public RecuperationDeLaPharmacie(Pharmacies pharmacies) {
        this.pharmacies = pharmacies;
    }

    public Pharmacie recupererPourUtilisateur(String emailUtilisateur) {
        return pharmacies.rechercherParEmail(emailUtilisateur);
    }
}
