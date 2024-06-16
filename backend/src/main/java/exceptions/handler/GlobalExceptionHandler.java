package exceptions.handler;

import exceptions.AppointmentCancellationException;
import exceptions.ConsultationScheduleException;
import exceptions.EntityAlredyExistException;
import exceptions.EntityNotFoundException;

import java.time.LocalDateTime;

import exceptions.IncorrectUsernameOrPasswordExpection;
import exceptions.InvalidFieldException;
import exceptions.InvalidStartEndTimeException;
import exceptions.PasswordNotCoincidentException;
import exceptions.SpecialityNotExistException;
import exceptions.UserWithoutAppointmentException;
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
        if(e instanceof EntityAlredyExistException) return entityAlredyExistException(e);
        if(e instanceof IncorrectUsernameOrPasswordExpection) return incorrectUsernameOrPasswordException(e);
        if(e instanceof PasswordNotCoincidentException) return passwordNotCoincidentException(e);
        if(e instanceof InvalidFieldException) return invalidFieldException(e);
        if(e instanceof ConsultationScheduleException) return consultationScheduleException(e);
        if(e instanceof UserWithoutAppointmentException) return userWithAppointmentException(e);
        if(e instanceof AppointmentCancellationException) return appointmentCancelationException(e);
        if(e instanceof SpecialityNotExistException) return specialityNotExistException(e);
        if(e instanceof InvalidStartEndTimeException) return invalidStartEndTimeException(e);
        //Exception personalizada

        return errorServerException(e);
    }

    private Response entityNotFoundException(Exception e){
        return createRespone(Response.Status.NOT_FOUND, e.getMessage());
    }

    private Response entityAlredyExistException(Exception e){
        return createRespone(Response.Status.CONFLICT, e.getMessage());
    }
    private Response incorrectUsernameOrPasswordException(Exception e){
        return createRespone(Response.Status.UNAUTHORIZED, e.getMessage());
    }
    private Response passwordNotCoincidentException(Exception e){
        return createRespone(Response.Status.UNAUTHORIZED, e.getMessage());
    }
    private Response invalidFieldException(Exception e){
        return createRespone(Response.Status.BAD_REQUEST, e.getMessage());
    }
    private Response consultationScheduleException(Exception e){
        return createRespone(Response.Status.BAD_REQUEST, e.getMessage());
    }
    private Response userWithAppointmentException(Exception e){
        return createRespone(Response.Status.BAD_REQUEST, e.getMessage());
    }
    private Response appointmentCancelationException(Exception e){
        return createRespone(Response.Status.BAD_REQUEST, e.getMessage());
    }
    private Response specialityNotExistException(Exception e){
        return createRespone(Response.Status.BAD_REQUEST, e.getMessage());
    }
    private Response invalidStartEndTimeException(Exception e){
        return createRespone(Response.Status.BAD_REQUEST, e.getMessage());
    }
    private Response errorServerException(Exception e){
        return createRespone(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
    }
    
    /**
     * Prepara un cuerpo de respuesta con <strong>codigo de estado</strong>, <strong>mensaje</strong> y <strong>tiempo en que ocurrio el error</strong>.
     * No necesita modificar este metodo, solo uselo para crear su respuesta de error. 
     * @param status Status code (BAD_REQUEST, NOT_FOUND, INTERNAL_SERVER_ERROR...etc)
     * @param message String con mensaje personalizado del error, normalmente ya lo indico cuando creo la Exception. Utilize getMessage()
     * @return
     */
    private Response createRespone(Status status, String message){
        return Response
            .status(status)
            .entity(new ErrorDtoResponse(status, status.getStatusCode(), message, LocalDateTime.now()))
            .build();
    }
}
