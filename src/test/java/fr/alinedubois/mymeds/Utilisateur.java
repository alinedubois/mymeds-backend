package fr.alinedubois.mymeds;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.*;

/**
 * Annotation décrivant la sécurité liée à l'utilisateur
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@WithSecurityContext(factory = UtilisateurSecurityContextFactory.class)
public @interface Utilisateur {

}

