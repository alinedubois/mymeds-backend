package fr.alinedubois.mymeds;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.time.Instant;
import java.util.Map;

public class UtilisateurSecurityContextFactory implements WithSecurityContextFactory<Utilisateur> {
    @Override
    public SecurityContext createSecurityContext(Utilisateur annotation) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Jwt token = new Jwt(
                "token",
                Instant.now(),
                Instant.now().plusSeconds(30),
                Map.of("alg", "none"),
                Map.of("sub", "juillet.aline@gmail.com"));
        context.setAuthentication(new JwtAuthenticationToken(token));
        return context;
    }
}
