package fr.alinedubois.mymeds.pharmacie.resource;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import fr.alinedubois.mymeds.pharmacie.service.DateDePeremption;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class DateDePeremptionJsonSerializer extends JsonSerializer<DateDePeremption> {
    @Override
    public void serialize(DateDePeremption dateDePeremption, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("date", dateDePeremption.date());
        jsonGenerator.writeBooleanField("estAuDelaDeTroisMois", dateDePeremption.estAuDelaDeTroisMois());
        jsonGenerator.writeBooleanField("estDansLesTroisMois", dateDePeremption.estDansLesTroisMois());
        jsonGenerator.writeBooleanField("estDepassee", dateDePeremption.estDepassee());
        jsonGenerator.writeNumberField("nombreDeJoursRestants", dateDePeremption.nombreDeJoursRestants());
        jsonGenerator.writeEndObject();
    }
}

