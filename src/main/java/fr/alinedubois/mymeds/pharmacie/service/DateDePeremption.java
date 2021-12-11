package fr.alinedubois.mymeds.pharmacie.service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

public class DateDePeremption {
    private final Month mois;
    private final Year annee;

    public DateDePeremption(Month mois, Year annee) {
        this.mois = mois;
        this.annee = annee;
    }

    public boolean estDepassee() {
        LocalDate moisCourant = LocalDate.now().withDayOfMonth(1);
        LocalDate moisDePeremption = LocalDate.of(annee.getValue(), mois, 1);
        return moisDePeremption.isBefore(moisCourant) || moisDePeremption.isEqual(moisCourant);
    }
}
