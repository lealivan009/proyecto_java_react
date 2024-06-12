package exceptions.handler;

import exceptions.EmailAlredyExistException;
import exceptions.EntityNotFoundException;

import java.time.LocalDateTime;

import exceptions.ErrorDtoResponse;
import exceptions.IncorrectUsernameOrPasswordExpection;
import exceptions.PasswordNotCoincidentException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
//Hace el catch de todas las Exceptions y prepara la respuesta correspondiente
@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public  Response toResponse(Exception e) { 
        if(e instanceof EntityNotFoundException) return entityNotFoundException(e);
        if(e instanceof EmailAlredyExistException) return emailAlredyExistException(e);
        if(e instanceof IncorrectUsernameOrPasswordExpection) return incorrectUsernameOrPasswordException(e);
        if(e instanceof PasswordNotCoincidentException) return passwordNotCoincidentException(e);
        //Exception personalizada

        return errorServerException(e);
    }

    private Response entityNotFoundException(Exception e){
        return createRespone(Response.Status.NOT_FOUND, e.getMessage());
    }

    private Response emailAlredyExistException(Exception e){
        return createRespone(Response.Status.CONFLICT, e.getMessage());
    }
    private Response incorrectUsernameOrPasswordException(Exception e){
        return createRespone(Response.Status.NOT_FOUND, e.getMessage());
    }
    private Response passwordNotCoincidentException(Exception e){
        return createRespone(Response.Status.NOT_FOUND, e.getMessage());
    }

    private Response errorServerException(Exception e){
        return createRespone(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
    }
    
    private Response createRespone(Status status, String message){
        return Response
            .status(status)
            .entity(new ErrorDtoResponse(status, status.getStatusCode(), message, LocalDateTime.now()))
            .build();
    }
}
