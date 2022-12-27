import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.concurrent.atomic.AtomicBoolean;

public class PostgreSQLTestContainerExtension implements BeforeAllCallback {

    private static final AtomicBoolean started = new AtomicBoolean(false);

    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:alpine")
            .withUsername("postgres")
            .withPassword("password")
            .withDatabaseName("postgres")
            .withExposedPorts(5432);

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        if (!started.get()) {
            container.start();
            started.set(true);
        }

    }
}
