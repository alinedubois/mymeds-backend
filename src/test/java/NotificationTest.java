import fr.alinedubois.mymeds.MyMedsApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest (classes = MyMedsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ExtendWith(value = {
        PostgreSQLTestContainerExtension.class
})
public class NotificationTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("Si au moins un médicament de la pharmacie est périmé, l'utilisateur reçoit une notification par mail.")
    void envoi_de_notification_si_au_moins_un_medicament_est_perime() {
        //GIVEN ajouter des médicaments dans la pharmacie

        //WHEN utliser le batch qui vérifie s'il y a des médicaments périmés et en fonction qui envoie une notification par mail

        //THEN vérifier que le mail a bien été reçu avec les bonnes informations

    }
}
