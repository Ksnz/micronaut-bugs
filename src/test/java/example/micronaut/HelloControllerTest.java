package example.micronaut;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import java.util.List;

@MicronautTest // <1>
public class HelloControllerTest {

    @Inject
    @Client("/")  // <2>
    HttpClient client;

    @Inject
    ObjectMapper objectMapper;

    @Test
    public void testHello() throws JsonProcessingException {
        HttpRequest<List<SomeProperty>> request = HttpRequest.GET("/hello");  // <3>
        List<SomeProperty> body = objectMapper.readValue(client.toBlocking().retrieve(request), new TypeReference<List<SomeProperty>>() {
        });
        assertNotNull(body);
        body.stream()
            .map(SomeProperty::getMapValue)
            .forEach(Assertions::assertNotNull);
    }
}
