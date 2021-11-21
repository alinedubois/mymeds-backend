package fr.alinedubois.mymeds.referentiel.infrastructure.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import fr.alinedubois.mymeds.referentiel.domaine.modele.Medicament;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class MedicamentJsonSerializer extends JsonSerializer<Medicament> {
    @Override
    public void serialize(Medicament medicament, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", medicament.identifiant());
        jsonGenerator.writeStringField("nom", medicament.nom());
        jsonGenerator.writeStringField("formePharmaceutique", medicament.formePharmaceutique());
        jsonGenerator.writeStringField("voieAdministration", medicament.voieAdministration());
        jsonGenerator.writeBooleanField("surveillanceRenforcee", medicament.surveillanceRenforcee().equals("Oui"));
        jsonGenerator.writeEndObject();
    }
}
