package fr.alinedubois.mymeds.pharmacie.service;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

class BoiteDeMedicamentTest {

    @Nested
    class EstDejaPerime {
        @Test
        void doit_retourner_true_lorsque_le_medicament_depasse_le_mois_de_peremption() {
            BoiteDeMedicament boiteDeMedicament = new BoiteDeMedicament(
                    123L,
                    "doliprane",
                    "doliprane",
                    new DateDePeremption(Month.DECEMBER, Year.of(2020)));

            boolean estDejaPerime = boiteDeMedicament.estDejaPerime();

            assertThat(estDejaPerime).isTrue();
        }

        @Test
        void doit_retourner_true_lorsque_le_medicament_atteint_le_mois_de_peremption() {
            LocalDate moisCourant = LocalDate.now();
            BoiteDeMedicament boiteDeMedicament = new BoiteDeMedicament(
                    123L,
                    "doliprane",
                    "doliprane",
                    new DateDePeremption(moisCourant.getMonth(), Year.of(moisCourant.getYear())));

            boolean estDejaPerime = boiteDeMedicament.estDejaPerime();

            assertThat(estDejaPerime).isTrue();
        }

        @Test
        void doit_retourner_false_lorsque_le_medicament_n_est_pas_perime() {
            BoiteDeMedicament boiteDeMedicament = new BoiteDeMedicament(
                    123L,
                    "doliprane",
                    "doliprane",
                    new DateDePeremption(Month.DECEMBER, Year.of(2030)));

            boolean estDejaPerime = boiteDeMedicament.estDejaPerime();

            assertThat(estDejaPerime).isFalse();
        }
    }

    @Nested
    class PerimeDansLesTroisMois {
        @Test
        void doit_retourner_true_lorsque_le_medicament_perime_dans_trois_mois() {
            LocalDate dansTroisMois = LocalDate.now().plusMonths(3);
            BoiteDeMedicament boiteDeMedicament = new BoiteDeMedicament(
                    123L,
                    "doliprane",
                    "doliprane",
                    new DateDePeremption(dansTroisMois.getMonth(), Year.of(dansTroisMois.getYear())));

            boolean perimeDansLesTroisMois = boiteDeMedicament.perimeDansLesTroisMois();

            assertThat(perimeDansLesTroisMois).isTrue();
        }

        @Test
        void doit_retourner_true_lorsque_le_medicament_perime_dans_deux_mois() {
            LocalDate dansDeuxMois = LocalDate.now().plusMonths(2);
            BoiteDeMedicament boiteDeMedicament = new BoiteDeMedicament(
                    123L,
                    "doliprane",
                    "doliprane",
                    new DateDePeremption(dansDeuxMois.getMonth(), Year.of(dansDeuxMois.getYear())));

            boolean perimeDansLesTroisMois = boiteDeMedicament.perimeDansLesTroisMois();

            assertThat(perimeDansLesTroisMois).isTrue();
        }

        @Test
        void doit_retourner_true_lorsque_le_medicament_perime_dans_un_mois() {
            LocalDate dansUnMois = LocalDate.now().plusMonths(1);
            BoiteDeMedicament boiteDeMedicament = new BoiteDeMedicament(
                    123L,
                    "doliprane",
                    "doliprane",
                    new DateDePeremption(dansUnMois.getMonth(), Year.of(dansUnMois.getYear())));

            boolean perimeDansLesTroisMois = boiteDeMedicament.perimeDansLesTroisMois();

            assertThat(perimeDansLesTroisMois).isTrue();
        }

        @Test
        void doit_retourner_false_lorsque_le_medicament_perime_dans_le_mois_courant() {
            LocalDate moisCourant = LocalDate.now();
            BoiteDeMedicament boiteDeMedicament = new BoiteDeMedicament(
                    123L,
                    "doliprane",
                    "doliprane",
                    new DateDePeremption(moisCourant.getMonth(), Year.of(moisCourant.getYear())));

            boolean perimeDansLesTroisMois = boiteDeMedicament.perimeDansLesTroisMois();

            assertThat(perimeDansLesTroisMois).isFalse();
        }

        @Test
        void doit_retourner_false_lorsque_le_medicament_perime_dans_dix_mois() {
            LocalDate dansDixMois = LocalDate.now().plusMonths(10);
            BoiteDeMedicament boiteDeMedicament = new BoiteDeMedicament(
                    123L,
                    "doliprane",
                    "doliprane",
                    new DateDePeremption(dansDixMois.getMonth(), Year.of(dansDixMois.getYear())));

            boolean perimeDansLesTroisMois = boiteDeMedicament.perimeDansLesTroisMois();

            assertThat(perimeDansLesTroisMois).isFalse();
        }

        @Test
        void doit_retourner_false_lorsque_le_medicament_est_deja_perime() {
            BoiteDeMedicament boiteDeMedicament = new BoiteDeMedicament(
                    123L,
                    "doliprane",
                    "doliprane",
                    new DateDePeremption(Month.DECEMBER, Year.of(2020)));

            boolean perimeDansLesTroisMois = boiteDeMedicament.perimeDansLesTroisMois();

            assertThat(perimeDansLesTroisMois).isFalse();
        }
    }

    @Nested
    class PerimeAuDelaDeTroisMois {
        @Test
        void doit_retourner_true_lorsque_le_medicament_perime_au_dela_de_trois_mois() {
            LocalDate dansPlusDeTroisMois = LocalDate.now().plusMonths(4);
            BoiteDeMedicament boiteDeMedicament = new BoiteDeMedicament(
                    123L,
                    "doliprane",
                    "doliprane",
                    new DateDePeremption(dansPlusDeTroisMois.getMonth(), Year.of(dansPlusDeTroisMois.getYear())));

            boolean perimeAuDelaDeTroisMois = boiteDeMedicament.perimeDAuDelaDeTroisMois();

            assertThat(perimeAuDelaDeTroisMois).isTrue();
        }

        @Test
        void doit_retourner_false_lorsque_le_medicament_est_perime() {
            BoiteDeMedicament boiteDeMedicament = new BoiteDeMedicament(
                    123L,
                    "doliprane",
                    "doliprane",
                    new DateDePeremption(Month.DECEMBER, Year.of(2020)));

            boolean perimeAuDelaDeTroisMois = boiteDeMedicament.perimeDAuDelaDeTroisMois();

            assertThat(perimeAuDelaDeTroisMois).isFalse();
        }

        @Test
        void doit_retourner_false_lorsque_le_medicament_perime_dans_un_mois() {
            LocalDate dansUnMois = LocalDate.now().plusMonths(1);
            BoiteDeMedicament boiteDeMedicament = new BoiteDeMedicament(
                    123L,
                    "doliprane",
                    "doliprane",
                    new DateDePeremption(dansUnMois.getMonth(), Year.of(dansUnMois.getYear())));

            boolean perimeAuDelaDeTroisMois = boiteDeMedicament.perimeDAuDelaDeTroisMois();

            assertThat(perimeAuDelaDeTroisMois).isFalse();
        }
    }

    @Nested
    class DateDePeremptionTest {

        @Nested
        class NombreDeJoursRestants {

            @Test
            void doit_retourner_le_nombre_de_jours_restants_quand_la_date_de_peremption_est_dans_les_trois_mois() {
                LocalDate dansUnMois = LocalDate.now().plusMonths(1);
                BoiteDeMedicament boiteDeMedicament = new BoiteDeMedicament(
                        123L,
                        "doliprane",
                        "doliprane",
                        new DateDePeremption(dansUnMois.getMonth(), Year.of(dansUnMois.getYear())));

                Integer nombreDeJoursRestants = boiteDeMedicament.getDateDePeremption().nombreDeJoursRestants();

                assertThat(nombreDeJoursRestants).isGreaterThan(1).isLessThan(29*3);
            }
        }
    }

}