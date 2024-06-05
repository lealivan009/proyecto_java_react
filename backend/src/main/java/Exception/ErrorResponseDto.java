package Exception;

import jakarta.ws.rs.core.Response.Status;

public record ErrorResponseDto(Status httpStatus, int codeError, String message) {
}
