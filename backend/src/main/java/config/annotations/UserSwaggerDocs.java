package config.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

public class UserSwaggerDocs {

    @APIResponses({
        @APIResponse(description = "Usuario logueado con exito", responseCode = "200"),
        @APIResponse(description = "No se pudo loguear el usuario", responseCode = "404"),
        @APIResponse(description = "Error interno del servidor", responseCode = "500")
    })
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface LoginUser {
    }

    @APIResponses({
        @APIResponse(description = "Usuario registrado con exito", responseCode = "201"),
        @APIResponse(description = "No se pudo registrar el usuario", responseCode = "400"),
        @APIResponse(description = "Error interno del servidor", responseCode = "500")
    })
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface RegisterUser {
    }

    @APIResponses({
        @APIResponse(description = "No content", responseCode = "204"),
        @APIResponse(description = "Recurso no encontrado", responseCode = "404"),
        @APIResponse(description = "Error interno del servidor", responseCode = "500")
    })
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface UpdateUser {
    }

    @APIResponses({
        @APIResponse(description = "Usuario encontrado", responseCode = "200"),
        @APIResponse(description = "Recurso no encontrado", responseCode = "404"),
        @APIResponse(description = "Error interno del servidor", responseCode = "500")
    })
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface GetUserById {
    }

    @APIResponses({
        @APIResponse(description = "No content", responseCode = "204"),
        @APIResponse(description = "Recurso no encontrado", responseCode = "404"),
        @APIResponse(description = "Error interno del servidor", responseCode = "500")
    })
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface DeleteUserById {
    }

    @APIResponses({
        @APIResponse(description = "Lista de usuarios", responseCode = "200"),
        @APIResponse(description = "Error interno del servidor", responseCode = "500")
    })
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface GetAllUser {
    }
}
