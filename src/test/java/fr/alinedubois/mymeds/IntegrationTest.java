package fr.alinedubois.mymeds;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SpringBootTest(classes = MyMedsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(value = {
        PostgreSQLTestContainerExtension.class, ServeurMailExtension.class
})
public @interface IntegrationTest {
}
