package fr.alinedubois.mymeds;

import fr.alinedubois.mymeds.notifications.BatchNotifications;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static fr.alinedubois.mymeds.Durees._1_MOIS;
import static fr.alinedubois.mymeds.Durees._6_MOIS;
import static fr.alinedubois.mymeds.EmailAsserter.assertThatAnEmailTo;
import static fr.alinedubois.mymeds.Medicaments.DOLIPRANE_1000_COMPRIMES;
import static fr.alinedubois.mymeds.Medicaments.DOLIPRANE_1000_GELULES;
import static fr.alinedubois.mymeds.builder.AjoutDeMedicamentBuilder.ajoutDeMedicament;

@SpringBootTest (classes = MyMedsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ExtendWith(value = {
        PostgreSQLTestContainerExtension.class, ServeurMailExtension.class
})
public class NotificationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private BatchNotifications batchNotifications;


    @Test
    @DisplayName("Si la pharmacie est vide, l'utilisateur ne doit pas recevoir de notification par mail.")
    void pas_d_envoi_de_notification_si_la_pharmacie_est_vide() {
        //GIVEN

        //WHEN
        batchNotifications.envoiDeNotificationsEnCasDeMedicamentsPerimes();

        //THEN
        assertThatAnEmailTo("user@mymeds.com")
                .wasNotSent();
    }

    @Test
    @DisplayName("Si la pharmacie ne contient que des médicaments en cours de validité, l'utilisateur ne doit pas recevoir de notification par mail.")
    @Utilisateur
    void pas_d_envoi_de_notification_si_la_pharmacie_ne_contient_que_des_medicaments_en_cours_de_validite() {
        //GIVEN
        ajoutDeMedicament(DOLIPRANE_1000_GELULES)
                .expireDans(_6_MOIS)
                .ajouter(webTestClient);
        ajoutDeMedicament(DOLIPRANE_1000_COMPRIMES)
                .expireDans(_1_MOIS)
                .ajouter(webTestClient);

        //WHEN
        batchNotifications.envoiDeNotificationsEnCasDeMedicamentsPerimes();

        //THEN
        assertThatAnEmailTo("user@mymeds.com")
                .wasNotSent();
    }

//    @Test
//    @DisplayName("Si au moins un médicament de la pharmacie est périmé, l'utilisateur reçoit une notification par mail.")
//    void envoi_de_notification_si_au_moins_un_medicament_est_perime() {
//        //GIVEN ajouter des médicaments dans la pharmacie
//        ajouter(DOLIPRANE_1000_COMPRIMES).expireDePuis(5_JOURS);
//        ajouter(SOLUPRED_ORODISPERSIBLE).expireDans(25_JOURS);
//        ajouter(DOLIPRANE_1000_GELULES).expireDans(6_MOIS);
//
//        //WHEN utliser le batch qui vérifie s'il y a des médicaments périmés et en fonction qui envoie une notification par mail
//
//
//        //THEN vérifier que le mail a bien été reçu avec les bonnes informations
//        assertThatAnEmailTo("user@mymeds.com")
//                .wasSent()
//                .withSubject("Vous avez 2 médicaments périmés");
//    }
}
