package config.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

public class SpecialistSwaggerDocs {
    @APIResponses({
        @APIResponse(description = "Lista de especialista con horarios", responseCode = "200"),
        @APIResponse(description = "Error interno del servidor", responseCode = "500")
    })
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface GetAllSpecialists {
    }

}
