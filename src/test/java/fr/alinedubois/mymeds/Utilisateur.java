package fr.alinedubois.mymeds;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation décrivant la sécurité liée à l'utilisateur
 */
@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = UtilisateurSecurityContextFactory.class)
public @interface Utilisateur {
}

