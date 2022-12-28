package fr.alinedubois.mymeds;

import com.dumbster.smtp.SimpleSmtpServer;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.concurrent.atomic.AtomicBoolean;

public class ServeurMailExtension implements BeforeAllCallback, AfterAllCallback {

    private static final AtomicBoolean started = new AtomicBoolean(false);

    public static SimpleSmtpServer serveurDeMails;

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        if (!started.get()) {
            serveurDeMails = SimpleSmtpServer.start(SimpleSmtpServer.AUTO_SMTP_PORT);
            started.set(true);
        }

    }

    @Override
    public void afterAll(ExtensionContext extensionContext) {
        if (started.get()) {
            serveurDeMails.stop();
            started.set(false);
        }

    }
}
