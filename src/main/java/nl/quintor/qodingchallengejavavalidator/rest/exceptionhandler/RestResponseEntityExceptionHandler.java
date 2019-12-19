package nl.quintor.qodingchallengejavavalidator.rest.exceptionhandler;

import nl.quintor.qodingchallengejavavalidator.rest.customexception.CustomException;
import nl.quintor.qodingchallengejavavalidator.rest.customexception.JSONCustomExceptionSchema;
import nl.quintor.qodingchallengejavavalidator.service.exception.CanNotCompileException;
import nl.quintor.qodingchallengejavavalidator.service.exception.ExecutionTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    @ExceptionHandler({
            CanNotCompileException.class
    })
    public final ResponseEntity<Object> handleCustomExceptionExpectationFailed(CustomException ex) {
        JSONCustomExceptionSchema exceptionResponse =
                new JSONCustomExceptionSchema(
                        ex.getMessage(), ex.getDetails(), ex.getNextActions(), ex.getSupport()
                );
        LOGGER.error(ex.getMessage(), ex.getDetails(), ex.fillInStackTrace().toString());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler({
            ExecutionTimeoutException.class
    })
    public final ResponseEntity<Object> handleCustomExceptionTimeOut(CustomException ex) {
        JSONCustomExceptionSchema exceptionResponse =
                new JSONCustomExceptionSchema(
                        ex.getMessage(), ex.getDetails(), ex.getNextActions(), ex.getSupport()
                );
        LOGGER.error(ex.getMessage(), ex.getDetails(), ex.fillInStackTrace().toString());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.REQUEST_TIMEOUT);
    }
}
