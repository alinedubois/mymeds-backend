package fr.alinedubois.mymeds.pharmacie.resource;

import fr.alinedubois.mymeds.pharmacie.service.PharmacieDTO;
import fr.alinedubois.mymeds.pharmacie.service.PharmacieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/pharmacies")
public class PharmacieResource {
    private final PharmacieService pharmacieService;

    public PharmacieResource(PharmacieService pharmacieService) {
        this.pharmacieService = pharmacieService;
    }

    @GetMapping("/{email}")
    public PharmacieDTO recupererLaPharmacieDeLUtilisateur(@PathVariable String email) {
        return this.pharmacieService.recupererLaPharmacieDeLUtilisateur(email);
    }
}
