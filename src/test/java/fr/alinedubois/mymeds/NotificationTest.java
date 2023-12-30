package fr.alinedubois.mymeds;

import fr.alinedubois.mymeds.notifications.BatchNotifications;
import fr.alinedubois.mymeds.pharmacie.service.PharmacieService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static fr.alinedubois.mymeds.Durees.*;
import static fr.alinedubois.mymeds.EmailAsserter.assertThatAnEmailTo;
import static fr.alinedubois.mymeds.Medicaments.*;
import static fr.alinedubois.mymeds.builder.AjoutDeMedicamentBuilder.ajoutDeMedicament;

@IntegrationTest
class NotificationTest {

    @Autowired
    private PharmacieService pharmacieService;

    @Autowired
    private BatchNotifications batchNotifications;

    @Utilisateur
    @Test
    @DisplayName("Si la pharmacie est vide, l'utilisateur ne doit pas recevoir de notification par mail.")
    @Sql("classpath:nettoyer.sql")
    void pas_d_envoi_de_notification_si_la_pharmacie_est_vide() {
        //GIVEN

        //WHEN
        batchNotifications.envoiDeNotificationsEnCasDeMedicamentsPerimes();

        //THEN
        assertThatAnEmailTo("juillet.aline@gmail.com")
                .wasNotSent();
    }

    @Utilisateur
    @Test
    @DisplayName("Si au moins un médicament de la pharmacie est périmé, l'utilisateur reçoit une notification par mail.")
    @Sql("classpath:nettoyer.sql")
    void envoi_de_notification_si_au_moins_un_medicament_est_perime() {
        //GIVEN ajouter des médicaments dans la pharmacie
        ajoutDeMedicament(DAFALGAN_1000_COMPRIMES).expireDepuis(_5_JOURS).ajouter(pharmacieService);
        ajoutDeMedicament(SOLUPRED_ORODISPERSIBLE).expireDans(_25_JOURS).ajouter(pharmacieService);
        ajoutDeMedicament(DOLIPRANE_1000_GELULES).expireDans(_6_MOIS).ajouter(pharmacieService);

        //WHEN utiliser le batch qui vérifie s'il y a des médicaments périmés et en fonction qui envoie une notification par mail
        batchNotifications.envoiDeNotificationsEnCasDeMedicamentsPerimes();

        //THEN vérifier que le mail a bien été reçu avec les bonnes informations
        assertThatAnEmailTo("juillet.aline@gmail.com")
                .wasSent();
    }

    @Utilisateur
    @Test
    @DisplayName("Si la pharmacie ne contient que des médicaments en cours de validité, l'utilisateur ne doit pas recevoir de notification par mail.")
    @Sql("classpath:nettoyer.sql")
    void pas_d_envoi_de_notification_si_la_pharmacie_ne_contient_que_des_medicaments_en_cours_de_validite() {
        //GIVEN
        ajoutDeMedicament(DOLIPRANE_1000_GELULES)
                .expireDans(_6_MOIS)
                .ajouter(pharmacieService);
        ajoutDeMedicament(DAFALGAN_1000_COMPRIMES)
                .expireDans(_6_MOIS)
                .ajouter(pharmacieService);

        //WHEN
        batchNotifications.envoiDeNotificationsEnCasDeMedicamentsPerimes();

        //THEN
        assertThatAnEmailTo("juillet.aline@gmail.com")
                .wasNotSent();
    }
}
