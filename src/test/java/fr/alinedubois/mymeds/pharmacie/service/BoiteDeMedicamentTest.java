package fr.alinedubois.mymeds.pharmacie.service;

import fr.alinedubois.mymeds.referentiel.domaine.modele.Medicament;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BoiteDeMedicamentTest {

    @Nested
    class EstDejaPerime {
        @Test
        void doit_retourner_true_lorsque_le_medicament_depasse_le_mois_de_peremption() {
            BoiteDeMedicament boiteDeMedicament = new BoiteDeMedicament(
                    "0123",
                    "doliprane",
                    new DateDePeremption(Month.DECEMBER, Year.of(2020)));

            boolean estDejaPerime = boiteDeMedicament.estDejaPerime();

            assertThat(estDejaPerime).isTrue();
        }

        @Test
        void doit_retourner_true_lorsque_le_medicament_atteint_le_mois_de_peremption() {
            LocalDate moisCourant = LocalDate.now();
            BoiteDeMedicament boiteDeMedicament = new BoiteDeMedicament(
                    "0123",
                    "doliprane",
                    new DateDePeremption(moisCourant.getMonth(), Year.of(moisCourant.getYear())));

            boolean estDejaPerime = boiteDeMedicament.estDejaPerime();

            assertThat(estDejaPerime).isTrue();
        }

        @Test
        void doit_retourner_false_lorsque_le_medicament_n_est_pas_perime() {
            BoiteDeMedicament boiteDeMedicament = new BoiteDeMedicament(
                    "0123",
                    "doliprane",
                    new DateDePeremption(Month.DECEMBER, Year.of(2030)));

            boolean estDejaPerime = boiteDeMedicament.estDejaPerime();

            assertThat(estDejaPerime).isFalse();
        }
    }
}