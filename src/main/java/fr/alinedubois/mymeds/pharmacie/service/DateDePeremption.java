package fr.alinedubois.mymeds.pharmacie.service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.Year;
import java.time.format.DateTimeFormatter;

public class DateDePeremption {
    private final Month mois;
    private final Year annee;

    public DateDePeremption(Month mois, Year annee) {
        this.mois = mois;
        this.annee = annee;
    }

    public String date() {
        return LocalDate.of(annee.getValue(), mois, 1).format(DateTimeFormatter.ofPattern("MM/yyyy"));
    }

    public Integer nombreDeJoursRestants() {
        return Math.abs(Period.between(
                LocalDate.of(annee.getValue(), mois, 1),
                LocalDate.now()).getDays());
    }

    public boolean estDepassee() {
        LocalDate moisCourant = LocalDate.now().withDayOfMonth(1);
        LocalDate moisDePeremption = LocalDate.of(annee.getValue(), mois, 1);
        return moisDePeremption.isBefore(moisCourant) || moisDePeremption.isEqual(moisCourant);
    }

    public boolean estDansLesTroisMois() {
        LocalDate dansTroisMois = LocalDate.now().plusMonths(3).withDayOfMonth(1);
        LocalDate moisDePeremption = LocalDate.of(annee.getValue(), mois, 1);
        Period ecartAvantPeremption = Period.between(moisDePeremption, dansTroisMois);
        return ecartAvantPeremption.toTotalMonths() >= 0 && ecartAvantPeremption.toTotalMonths() < 3;
    }

    public boolean estAuDelaDeTroisMois() {
        LocalDate dansTroisMois = LocalDate.now().plusMonths(3).withDayOfMonth(1);
        LocalDate moisDePeremption = LocalDate.of(annee.getValue(), mois, 1);
        Period ecartAvantPeremption = Period.between(moisDePeremption, dansTroisMois);
        return ecartAvantPeremption.toTotalMonths() < 0;
    }
}
