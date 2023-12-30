package fr.alinedubois.mymeds.pharmacie.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UtilisateurRepository {
    private final JdbcTemplate jdbcClient;

    public UtilisateurRepository(JdbcTemplate jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<String> utilisateursActifs() {
        return jdbcClient.queryForList("select distinct utilisateur_id from pharmacie", String.class);
    }
}
