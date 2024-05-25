package fr.alinedubois.mymeds.scan;

import fr.alinedubois.mymeds.IntegrationTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

@IntegrationTest
class ScanDeMedicamentsTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private JdbcTemplate jdbcClient;

    @Disabled
    @Test
    void doit_renvoyer_une_erreur_404_lorsque_le_medicament_est_inconnu() {
        etantDonneQueLeMedicamentEstInconnu();

        var reponse = scanner(codeBarreDuDoliprane());

        reponse.expectStatus().isNotFound();
    }

    @Disabled
    @Test
    void doit_renvoyer_les_caracteristiques_du_medicament_lorsque_le_medicament_est_inconnu() {
        etantDonneLeCodeBarre(codeBarreDuDoliprane(), doliprane());

        var reponse = scanner(codeBarreDuDoliprane());

        reponse.expectBody().json("{\n" +
                "  \"identifiant\": \"63368332\",\n" +
                "  \"nom\": \"DOLIPRANE 500 mg, comprimé\",\n" +
                "  \"formePharmaceutique\": \"comprimé\",\n" +
                "  \"voieAdministration\": \"orale\",\n" +
                "  \"surveillanceRenforcee\": false\n" +
                "}");
    }

    private WebTestClient.ResponseSpec scanner(String codeBarreDuMedicament) {
        return webTestClient.get()
                .uri("/api/v1/medicaments?codeBarre=%s".formatted(codeBarreDuMedicament))
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
    }

    private String doliprane() {
        return "63368332";
    }

    private String codeBarreDuDoliprane() {
        return "03400934507786";
    }

    private void etantDonneQueLeMedicamentEstInconnu() {
        jdbcClient.update("delete from medicaments_codes_barres");
    }

    private void etantDonneLeCodeBarre(String codeBarre, String identifiantDuMedicament) {
        jdbcClient.update(
                "insert into medicaments_codes_barres (id, medicament_id, code_barre) values (? ::uuid, ?, ?)",
                UUID.randomUUID().toString(),
                identifiantDuMedicament,
                codeBarre
        );
    }
}
