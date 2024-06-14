package config.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

public class MedicalSwaggerDocs {

    @APIResponses(value = {
        @APIResponse(description = "Este metodo se utiliza para obtener un medico por el id", responseCode = "201"),
        @APIResponse(description = "No se pudo conseguir el medico por el id", responseCode = "404")
    })
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface GetMedicalById {
    }

    @APIResponses(value = {
        @APIResponse(description = "Este metodo se utiliza para obtener todos los medicos", responseCode = "201"),
        @APIResponse(description = "No se pudo obtener los medicos", responseCode = "404")
    })
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface GetAllMedical {
    }

    @APIResponses(value = {
        @APIResponse(description = "Este metodo se utiliza para el registro de medicos", responseCode = "201"),
        @APIResponse(description = "No se pudo cargar el medico", responseCode = "404")
    })
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface RegisterMedical {
    }

    @APIResponses(value = {
        @APIResponse(description = "Este metodo se utiliza para actualizar los horarios de un medico", responseCode = "201"),
        @APIResponse(description = "No se pudo actualizar los horarios del medico", responseCode = "404")
    })
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface UpdateSchedules {
    }
}
