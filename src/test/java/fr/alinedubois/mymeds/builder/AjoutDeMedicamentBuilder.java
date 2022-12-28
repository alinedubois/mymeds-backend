package fr.alinedubois.mymeds.builder;

import net.minidev.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.util.HashMap;

public class AjoutDeMedicamentBuilder {

    private final long identifiantDuMedicament;

    private LocalDate dateDePeremption;

    private AjoutDeMedicamentBuilder(long identifiantDuMedicament) {
        this.identifiantDuMedicament = identifiantDuMedicament;
    }

    public static AjoutDeMedicamentBuilder ajoutDeMedicament (long identifiantDuMedicament) {
        return new AjoutDeMedicamentBuilder(identifiantDuMedicament);
    }

    public AjoutDeMedicamentBuilder expireDans(int nombreDeMois) {
        this.dateDePeremption = LocalDate.now().plusMonths(nombreDeMois);
        return this;
    }

    public WebTestClient.ResponseSpec ajouter(WebTestClient webTestClient) {
        var builder = this;
        return webTestClient
                .post()
                .uri("/api/v2/pharmacies/juillet.aline@gmail.com/boites-de-medicaments")
                .header(HttpHeaders.ACCEPT, "application/json")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new JSONObject(new HashMap<>(){{
                    put("idMedicament", builder.identifiantDuMedicament);
                    put("dateDePeremption", builder.dateDePeremption.toString());
                }}).toString())
                .exchange();
    }
}
