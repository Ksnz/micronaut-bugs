package example.micronaut;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;

import java.util.List;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/hello") // <1>
public class HelloController {
    @Inject
    private List<SomeProperty> properties;

    @Get // <2>
    public List<SomeProperty> index() {
        return properties;
    }
}
