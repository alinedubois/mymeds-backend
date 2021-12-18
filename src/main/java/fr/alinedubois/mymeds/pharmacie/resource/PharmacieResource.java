package fr.alinedubois.mymeds.pharmacie.resource;

import fr.alinedubois.mymeds.pharmacie.service.AjoutMedicamentDTO;
import fr.alinedubois.mymeds.pharmacie.service.PharmacieDTO;
import fr.alinedubois.mymeds.pharmacie.service.PharmacieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

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

    @PostMapping("/{email}/boites-de-medicaments")
    public ResponseEntity ajouterUneBoiteDeMedicament(@RequestBody @Valid AjoutMedicamentDTO ajoutMedicamentDTO) throws URISyntaxException {
        return ResponseEntity.created(new URI("")).build();
    }
}
