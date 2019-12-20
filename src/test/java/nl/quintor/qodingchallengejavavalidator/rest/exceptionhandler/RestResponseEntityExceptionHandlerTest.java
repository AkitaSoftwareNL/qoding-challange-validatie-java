package nl.quintor.qodingchallengejavavalidator.rest.exceptionhandler;

import nl.quintor.qodingchallengejavavalidator.service.exception.CanNotCompileException;
import nl.quintor.qodingchallengejavavalidator.service.exception.ExecutionTimeoutException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class RestResponseEntityExceptionHandlerTest {

    private RestResponseEntityExceptionHandler sut;

    @BeforeEach
    void setUp() {
        sut = new RestResponseEntityExceptionHandler();
    }

    @Test
    void CanNotCompileExceptionGivesCorrectStatus() {
        var result = sut.handleCustomExceptionExpectationFailed(new CanNotCompileException());
        var expected = HttpStatus.EXPECTATION_FAILED;
        Assertions.assertEquals(expected, result.getStatusCode());
    }

    @Test
    void TimeoutExceptionGivesCorrectStatus() {
        var result = sut.handleCustomExceptionTimeOut(new ExecutionTimeoutException());
        var expected = HttpStatus.REQUEST_TIMEOUT;
        Assertions.assertEquals(expected, result.getStatusCode());
    }
}