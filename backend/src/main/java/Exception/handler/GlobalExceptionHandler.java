package Exception.handler;

import Exception.ErrorResponseDto;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
//Hace el catch de todas las Exceptions y prepara la respuesta correspondiente
@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        ErrorResponseDto errorResponse = createRespone(Response.Status.BAD_REQUEST, e.getMessage());

        return Response.status(Response.Status.BAD_REQUEST)
            .entity(errorResponse).build();
    }

    private ErrorResponseDto createRespone(Status status, String message){
        return new ErrorResponseDto(status, status.getStatusCode(), message);
    }
}
