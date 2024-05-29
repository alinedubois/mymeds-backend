package fr.alinedubois.mymeds.preferences;

import fr.alinedubois.mymeds.IntegrationTest;
import fr.alinedubois.mymeds.Utilisateur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@IntegrationTest
public class PreferencesTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @Utilisateur
    void doit_retourner_les_preferences_par_defaut_lorsque_utilisateur_ne_les_a_pas_enregistrees() {
        //When -> appel au web service via webTestClient
        var resultat = webTestClient
                .get()
                .uri("/api/juillet.aline@gmail.com/preferences")
                .exchange();

        //Then -> verifier que le contenu de la requête est bien l'attendu
        resultat.expectStatus()
                .isOk()
                .expectBody()
                .json("""
                          {
                            "notificationMail" : true,
                            "notificationHeure" : 9,
                            "typeAffichageMedicaments" : "ORDRE_ALPHABETIQUE"
                          }
                """);
    }
}
