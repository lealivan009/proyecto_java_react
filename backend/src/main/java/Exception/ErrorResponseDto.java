package Exception;

import jakarta.ws.rs.core.Response.Status;
//genera un objeto respuesta en el cuerpo del Response
public record ErrorResponseDto(Status httpStatus, int codeError, String message) {
}
