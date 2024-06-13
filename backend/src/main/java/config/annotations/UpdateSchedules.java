package config.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@APIResponses(
    value={
    @APIResponse(description = "Este metodo se utiliza para actualizar los horarios de un medico", responseCode = "201"),
    @APIResponse(description = "No se pudo actualizar los horarios del medico", responseCode = "404")
    }
)

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UpdateSchedules {
    
}
   
