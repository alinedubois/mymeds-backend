package fr.alinedubois.mymeds.referentiel.domaine.modele;

public class Medicament {
    private String identifiant;
    private String nom;
    private String formePharmaceutique;
    private String voieAdministration;
    private String surveillanceRenforcee;

    public Medicament(String identifiant, String nom, String formePharmaceutique, String voieAdministration, String surveillanceRenforcee) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.formePharmaceutique = formePharmaceutique;
        this.voieAdministration = voieAdministration;
        this.surveillanceRenforcee = surveillanceRenforcee;
    }

    public String identifiant() {
        return identifiant;
    }

    public String nom() {
        return nom;
    }

    public String formePharmaceutique() {
        return formePharmaceutique;
    }

    public String voieAdministration() {
        return voieAdministration;
    }

    public String surveillanceRenforcee() {
        return surveillanceRenforcee;
    }
}
