package config.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

public class AppointmentSwaggerDocs {
    @APIResponses(value = {
        @APIResponse(description = "Turno creado con éxito", responseCode = "201"),
        @APIResponse(description = "Datos del turno no válidos", responseCode = "400"),
        @APIResponse(description = "Error interno del servidor", responseCode = "500")
    })
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface CreateAppointment {
    }

    @APIResponses(value = {
        @APIResponse(description = "Turno eliminado con exito", responseCode = "201"),
        @APIResponse(description = "El turno no se pudo eliminar", responseCode = "400"),
        @APIResponse(description = "Error interno del servidor", responseCode = "500")
    })
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface DeleteAppointment {
    }

    @APIResponses(value = {
        @APIResponse(description = "Turnos encontrados", responseCode = "201"),
        @APIResponse(description = "Turnos no encontrados", responseCode = "400"),
        @APIResponse(description = "Error interno del servidor", responseCode = "500")
    })
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface GetAllAppointment {
    }

    @APIResponses(value = {
        @APIResponse(description = "Turno actualizado con éxito", responseCode = "201"),
        @APIResponse(description = "El turno no se ha podido actualizar", responseCode = "400"),
        @APIResponse(description = "Error interno del servidor", responseCode = "500")
    })
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface UpdateAppointment {
    }

    @APIResponses(value = {
        @APIResponse(description = "Turno encontrado", responseCode = "200"),
        @APIResponse(description = "Turno no encontrado", responseCode = "400"),
        @APIResponse(description = "Error interno del servidor", responseCode = "500")
    })
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface GetAppointmentById {
    }
}
