package fr.alinedubois.mymeds.preferences.controller;

import fr.alinedubois.mymeds.preferences.service.PreferenceDTO;
import fr.alinedubois.mymeds.preferences.service.PreferencesService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PreferencesController {
    private final PreferencesService preferencesService;


    public PreferencesController(PreferencesService preferencesService) {
        this.preferencesService = preferencesService;
    }

    @GetMapping("/{email}/preferences")
    public ResponseEntity<PreferenceDTO> getPreferences(
            @PathVariable String email,
            @AuthenticationPrincipal Jwt jetonAuthentification) {
        String emailAuthentifie = (String) jetonAuthentification.getClaims().get("email");
        if (email.equals(emailAuthentifie)) {
            return ResponseEntity.ok(this.preferencesService.recupererPreferences(email));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{email}/preferences")
    public ResponseEntity<Void> updatePreferences(
            @PathVariable String email,
            @AuthenticationPrincipal Jwt jetonAuthentification,
            @RequestBody @Valid PreferenceDTO preferenceDTO) {
        String emailAuthentifie = (String) jetonAuthentification.getClaims().get("email");
        if (email.equals(emailAuthentifie)) {
            this.preferencesService.modifierPreferences(email, preferenceDTO);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
