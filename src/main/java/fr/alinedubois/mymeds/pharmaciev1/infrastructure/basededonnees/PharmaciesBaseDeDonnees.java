package fr.alinedubois.mymeds.pharmaciev1.infrastructure.basededonnees;

import fr.alinedubois.mymeds.pharmaciev1.domaine.modele.BoiteDeMedicament;
import fr.alinedubois.mymeds.pharmaciev1.domaine.modele.Pharmacie;
import fr.alinedubois.mymeds.pharmaciev1.domaine.modele.Pharmacies;
import fr.alinedubois.mymeds.referentiel.domaine.modele.Medicament;
import fr.alinedubois.mymeds.referentiel.domaine.modele.Referentiel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PharmaciesBaseDeDonnees implements Pharmacies {
    private JdbcTemplate jdbcTemplate;
    private Referentiel referentiel;

    public PharmaciesBaseDeDonnees(JdbcTemplate jdbcTemplate, Referentiel referentiel) {
        this.jdbcTemplate = jdbcTemplate;
        this.referentiel = referentiel;
    }

    @Override
    public Pharmacie rechercherParEmail(String email) {
        List<BoiteDeMedicament> boitesDeMedicaments = jdbcTemplate.query(
                "select * from pharmacie",
                (resultSet, index) -> {
                    Medicament medicament = referentiel.parIdentifiant(resultSet.getString("medicament_id"));
                    return new BoiteDeMedicament(
                            medicament.nom(),
                            resultSet.getDate("date_de_peremption").toLocalDate()
                    );
                }
        );
        Pharmacie pharmacie = new Pharmacie();
        boitesDeMedicaments.forEach(boiteDeMedicament -> pharmacie.ajouter(boiteDeMedicament));
        return pharmacie;
    }
}

