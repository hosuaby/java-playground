package io.hosuaby;

import io.hosuaby.server.mock.Application;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class HttpClientTest {

    @LocalServerPort
    protected int randomServerPort;

    @Test
    void testFetchMixins() throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest
                .newBuilder(URI.create("http://localhost:%d/icecream/mixins".formatted(randomServerPort)))
                .header("Accept", "application/json")
                .GET()
                .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

        assertThat(response)
                .isNotNull();
        assertThat(response.statusCode())
                .isEqualTo(200);
        assertThat(response.body())
                .isEqualTo("[\"COOKIES\",\"MNMS\",\"CHOCOLATE_SIROP\",\"STRAWBERRY_SIROP\",\"NUTS\",\"RAINBOW\"]");
    }
}
