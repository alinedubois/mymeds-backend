package fr.alinedubois.mymeds.referentiel.infrastructure.fichier;

import fr.alinedubois.mymeds.referentiel.domaine.modele.Medicament;
import fr.alinedubois.mymeds.referentiel.domaine.modele.Referentiel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class ReferentielFichier implements Referentiel {

    private Resource fichierDesMedicaments;

    private List<Medicament> medicaments = new ArrayList<>();

    public ReferentielFichier(@Value("classpath:referentiel/medicaments.txt") Resource fichierDesMedicaments) {
        this.fichierDesMedicaments = fichierDesMedicaments;
    }

    @Override
    public List<Medicament> medicamentsDontLeNomContient(String nom) {
        return this.medicaments().stream()
                .filter(medicament -> medicament.nom().toLowerCase().contains(nom.toLowerCase()))
                .collect(Collectors.toList());
    }

    private List<Medicament> medicaments() {
        if (this.medicaments.isEmpty()) {
            try (
                    InputStream fichierDesMedicamentsInputStream = fichierDesMedicaments.getInputStream();
                    BufferedReader lecteurDuFichier = new BufferedReader(
                            new InputStreamReader(fichierDesMedicamentsInputStream, StandardCharsets.ISO_8859_1));
            ) {
                Stream<String> lignesDuFichier = lecteurDuFichier.lines();
                List<Medicament> medicaments = lignesDuFichier
                        .map(line -> {
                            String[] colonnes = line.split("\t");
                            return new Medicament(colonnes[0], colonnes[1], colonnes[2], colonnes[3], colonnes[11]);
                        })
                        .collect(Collectors.toList());
                this.medicaments = medicaments;
            } catch (IOException exception) {
                return Collections.emptyList();
            }
        }
        return this.medicaments;
    }

    @Override
    public Medicament parIdentifiant(String identifiant) {
        return this.medicaments().stream()
                .filter(medicament -> medicament.identifiant().equals(identifiant))
                .findFirst()
                .orElseThrow(() -> new MedicamentNonExistant());
    }
}
