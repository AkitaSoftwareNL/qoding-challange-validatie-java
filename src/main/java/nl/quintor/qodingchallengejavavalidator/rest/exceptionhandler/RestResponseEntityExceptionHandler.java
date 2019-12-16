package nl.quintor.qodingchallengejavavalidator.rest.exceptionhandler;

import nl.quintor.qodingchallengejavavalidator.rest.customexception.CustomException;
import nl.quintor.qodingchallengejavavalidator.rest.customexception.JSONCustomExceptionSchema;
import nl.quintor.qodingchallengejavavalidator.service.exception.CanNotCompileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    @ExceptionHandler({SQLException.class})
    public ResponseEntity<Object> handleNotFoundStatus(Exception e, WebRequest request) {
        JSONCustomExceptionSchema exceptionResponse =
                new JSONCustomExceptionSchema(
                        e.getMessage()
                );
        logger.error(e.fillInStackTrace().toString());
        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({

    })
    public final ResponseEntity<Object> handleCustomExceptionInternalServerError(CustomException ex, WebRequest webRequest) {
        JSONCustomExceptionSchema exceptionResponse =
                new JSONCustomExceptionSchema(
                        ex.getMessage(), ex.getDetails(), ex.getNextActions(), ex.getSupport()
                );
        logger.error(ex.getMessage(), ex.getDetails(), ex.fillInStackTrace().toString());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({
            CanNotCompileException.class
    })
    public final ResponseEntity<Object> handleCustomExceptionNotFound(CustomException ex, WebRequest webRequest) {
        JSONCustomExceptionSchema exceptionResponse =
                new JSONCustomExceptionSchema(
                        ex.getMessage(), ex.getDetails(), ex.getNextActions(), ex.getSupport()
                );
        logger.error(ex.getMessage(), ex.getDetails(), ex.fillInStackTrace().toString());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
