package sixgarlic.potenday.global.error;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sixgarlic.potenday.travelroom.exception.UserAlreadyExistException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleAccessDeniedException(AccessDeniedException e) {
        return ErrorResponse.error(HttpStatus.FORBIDDEN, e.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNoSuchElementException(NoSuchElementException e) {
        return ErrorResponse.error(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ErrorResponse handleUserAlreadyExistException(UserAlreadyExistException e) {
        return ErrorResponse.error(HttpStatus.NO_CONTENT, e.getMessage());
    }
}
