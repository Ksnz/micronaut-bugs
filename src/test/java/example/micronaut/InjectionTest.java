package example.micronaut;

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

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest // <1>
public class InjectionTest {

    @Inject
    List<SomeProperty> properties;


    @Test
    public void testHello() {
        assertNotNull(properties);
        properties.stream()
            .map(SomeProperty::getMapValue)
            .forEach(map -> assertFalse(map.isEmpty()));
    }
}
