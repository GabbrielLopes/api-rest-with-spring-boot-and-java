package br.com.gabriel.integrationtests.swagger;

import br.com.gabriel.integrationtests.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@TestPropertySource(locations="classpath:application-test.yml")
public class SwaggerIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void shouldDisplaySwaggerUiPage() {
        var content =
                given()
                        .basePath("/swagger-ui/index.html")
                        .port(8888)
                        .when()
                        .get()
                        .then()
                        .statusCode(200)
                        .extract()
                        .body().asString();
        assertTrue(content.contains("Swagger UI"));
    }

    @Test
    public void shouldDisplayActuatorUp() {
        Map content =
                given()
                        .basePath("/actuator/health")
                        .port(8888)
                        .when()
                        .get()
                        .then()
                        .statusCode(200)
                        .extract()
                        .body().as(Map.class);

        Map<String, String> statusUp = Map.of("status", "UP");
        assertEquals(statusUp, content);
    }

}
