package config.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

public class PrescriptionSwaggerDocs {
    @APIResponses({
        @APIResponse(description = "Prescipcion creada con exito", responseCode = "201"),
        @APIResponse(description = "Bad request", responseCode = "400"),
        @APIResponse(description = "Error interno del servidor", responseCode = "500")
    })
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface SavePresciption {
    }

    @APIResponses({
        @APIResponse(description = "Prescipcion", responseCode = "200"),
        @APIResponse(description = "Prescription no encontrada", responseCode = "404"),
        @APIResponse(description = "Error interno del servidor", responseCode = "500")
    })
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface GetPrescription {
    }
}
