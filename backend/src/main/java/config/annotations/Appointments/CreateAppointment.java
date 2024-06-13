package config.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@APIResponses(
    value={
        @APIResponse(description = "Turno creado con éxito", responseCode = "201"),
        @APIResponse(description = "Datos del turno no válidos", responseCode = "400"),
        @APIResponse(description = "Error interno del servidor", responseCode = "500")
    }
)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CreateAppointment {
    String apiValue() default "";
}
