package fr.alinedubois.mymeds.preferences;

import fr.alinedubois.mymeds.IntegrationTest;
import fr.alinedubois.mymeds.Utilisateur;
import fr.alinedubois.mymeds.preferences.service.TypeAffichageMedicaments;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
                            "notificationHeure" : "09:00",
                            "typeAffichageMedicaments" : "%s"
                          }
                """.formatted(TypeAffichageMedicaments.PAR_ORDRE_ALPHABETIQUE));
    }

    @Test
    @Utilisateur
    void doit_retourner_les_preferences_lorsque_utilisateur_les_a_deja_enregistrees() {

        //Given
        webTestClient
                .put()
                .uri("/api/juillet.aline@gmail.com/preferences")
                .bodyValue("""
                        {
                            "notificationMail" : false,
                            "notificationHeure" : "10:00",
                            "typeAffichageMedicaments" : "%s"
                          }
                        """.formatted(TypeAffichageMedicaments.PAR_DATE_DE_PEREMPTION))
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

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
                            "notificationMail" : false,
                            "notificationHeure" : "10:00",
                            "typeAffichageMedicaments" : "%s"
                          }
                """.formatted(TypeAffichageMedicaments.PAR_DATE_DE_PEREMPTION));


    }

}
