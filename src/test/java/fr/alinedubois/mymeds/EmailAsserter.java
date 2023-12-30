package fr.alinedubois.mymeds;

import com.dumbster.smtp.SmtpMessage;
import fr.alinedubois.mymeds.notifications.MailSender;
import org.assertj.core.api.AbstractAssert;

class EmailAsserter extends AbstractAssert<EmailAsserter, String> {
    private SmtpMessage messageRecu;

    private EmailAsserter(String destinataire) {
        super(destinataire, EmailAsserter.class);
    }

    static EmailAsserter assertThatAnEmailTo(String destinataire) {
        return new EmailAsserter(destinataire);
    }

    EmailAsserter wasNotSent() {
        var contexteSpring = ContexteSpring.recuperer();
        var mailSender = contexteSpring.getBean(MailSender.class);
        if (mailSender.hasSentEmailTo(actual)) {
            failWithMessage("Un email a été reçu par " + actual);
        }
        return this;
    }

    EmailAsserter wasSent() {
        var contexteSpring = ContexteSpring.recuperer();
        var mailSender = contexteSpring.getBean(MailSender.class);
        if (!mailSender.hasSentEmailTo(actual)) {
            failWithMessage("Aucun email n'a été reçu par " + actual);
        }
        return this;
    }

    EmailAsserter withSubject(String objetDuMail) {
        if (this.messageRecu != null) {
        }
        return this;
    }
}
