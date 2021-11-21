package fr.alinedubois.mymeds.referentiel;

import fr.alinedubois.mymeds.referentiel.application.RechercheDeMedicamentsParNom;
import fr.alinedubois.mymeds.referentiel.domaine.modele.Medicament;
import fr.alinedubois.mymeds.referentiel.infrastructure.fichier.ReferentielFichier;
import fr.alinedubois.mymeds.referentiel.infrastructure.rest.ReferentielRest;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ReferentielTest {
    private ReferentielRest referentielRest = new ReferentielRest(
        new RechercheDeMedicamentsParNom(new ReferentielFichier(new ClassPathResource("referentiel/medicaments.txt")))
    );
    @Test
    void doit_retourner_une_erreur_400_quand_le_nom_du_medicament_n_est_pas_renseigne() {

        // GIVEN

        // WHEN
        ResponseEntity<List<Medicament>> response=referentielRest.medicamentsParLeNom(null);

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    void doit_retourner_une_erreur_400_quand_le_nom_du_medicament_est_vide() {

        // GIVEN

        // WHEN
        ResponseEntity<List<Medicament>> response=referentielRest.medicamentsParLeNom("     ");

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    void doit_retourner_une_erreur_400_quand_le_nom_du_medicament_a_moins_de_3_caracteres() {

        // GIVEN

        // WHEN
        ResponseEntity<List<Medicament>> response=referentielRest.medicamentsParLeNom("do");

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    void doit_retourner_un_code_200_quand_le_nom_du_medicament_a_au_moins_3_caracteres() {

        // GIVEN

        // WHEN
        ResponseEntity<List<Medicament>> response=referentielRest.medicamentsParLeNom("dol");

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    void doit_retourner_une_liste_de_medicaments_correspondant_au_nom_recherche() {

        // GIVEN

        // WHEN
        ResponseEntity<List<Medicament>> response=referentielRest.medicamentsParLeNom("dafalgan");

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody()).hasSize(13);
    }
}
