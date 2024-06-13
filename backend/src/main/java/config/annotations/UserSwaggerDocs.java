package config.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

public class UserSwaggerDocs {
    @APIResponses(value = {
            @APIResponse(description = "Usuario logueado con exito", responseCode = "201"),
            @APIResponse(description = "No se pudo loguear el usuario", responseCode = "404"),
            @APIResponse(description = "Error interno del servidor", responseCode = "500")
    })
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface LoginUser {
    }

    @APIResponses(value = {
            @APIResponse(description = "Esto es para registrar un usuario", responseCode = "201"),
            @APIResponse(description = "", responseCode = "404")
    })
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface RegisterUser {
    }
}
