package example.micronaut;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.StreamingFileUpload;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.views.View;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Controller
@Secured(SecurityRule.IS_ANONYMOUS)
public class StreamingFileUploadController
{
    @View("home")
    @Get
    Map<String, Object> index()
    {
        return new HashMap<>();
    }

    @Consumes(MediaType.MULTIPART_FORM_DATA) // <1>
    @Post("/upload") // <2>
    @Secured(SecurityRule.DENY_ALL)
    public Mono<HttpResponse<?>> upload(StreamingFileUpload file) throws IOException
    {
        Path tempFile = Files.createTempFile(null, null);
        return Mono.from(file.transferTo(tempFile.toFile()))
                   .thenReturn(HttpResponse.ok());
    }
}
