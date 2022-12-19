package fr.alinedubois.mymeds.pharmacie.resource;

import fr.alinedubois.mymeds.pharmacie.service.AjoutMedicamentDTO;
import fr.alinedubois.mymeds.pharmacie.service.PharmacieDTO;
import fr.alinedubois.mymeds.pharmacie.service.PharmacieService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<PharmacieDTO> recupererLaPharmacieDeLUtilisateur(
            @PathVariable String email,
            @AuthenticationPrincipal Jwt jetonAuthentification)
    {
        String emailAuthentifie = (String) jetonAuthentification.getClaims().get("email");
        if (email.equals(emailAuthentifie)) {
            return ResponseEntity.ok(this.pharmacieService.recupererLaPharmacieDeLUtilisateur(emailAuthentifie));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{email}/boites-de-medicaments")
    public ResponseEntity ajouterUneBoiteDeMedicament(@RequestBody @Valid AjoutMedicamentDTO ajoutMedicamentDTO,
                                                      @PathVariable String email,
                                                      @AuthenticationPrincipal Jwt jetonAuthentification) throws URISyntaxException
    {
        String emailAuthentifie = (String) jetonAuthentification.getClaims().get("email");
        if (email.equals(emailAuthentifie)) {
            pharmacieService.ajouterBoiteDeMedicament(ajoutMedicamentDTO, emailAuthentifie);
            return ResponseEntity.created(new URI("")).build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{email}/boites-de-medicaments/{id}")
    public ResponseEntity<Void> supprimerUneBoiteDeMedicament(
            @PathVariable Long id,
            @PathVariable String email,
            @AuthenticationPrincipal Jwt jetonAuthentification) {
        String emailAuthentifie = (String) jetonAuthentification.getClaims().get("email");
        if (email.equals(emailAuthentifie)) {
            pharmacieService.supprimerUneBoiteDeMedicament(id, emailAuthentifie);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
