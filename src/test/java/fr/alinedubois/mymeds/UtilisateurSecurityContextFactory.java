package fr.alinedubois.mymeds;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.time.Instant;
import java.util.HashMap;

public class UtilisateurSecurityContextFactory implements WithSecurityContextFactory<Utilisateur> {
    @Override
    public SecurityContext createSecurityContext(Utilisateur annotation) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        var authentication = new JwtAuthenticationToken(jwtMock());
        authentication.setAuthenticated(true);
        context.setAuthentication(authentication);
        return context;
    }

    public static Jwt jwtMock() {
        var headers = new HashMap<String, Object>();
        headers.put("type", "JWT");
        headers.put("alg", "RS256");
        headers.put("kid", "clws34i4100002305vtgfu6ip");

        var claims = new HashMap<String, Object>();
        claims.put("sub", "juillet.aline@gmail.com");
        claims.put("email", "juillet.aline@gmail.com");
        claims.put("aud", "XXO7CW1nSrf08sIBQSGW5CMJqlS40tuw");

        return new Jwt(
                "token",
                Instant.now(),
                Instant.now().plusSeconds(300),
                headers,
                claims
        );
    }
}
