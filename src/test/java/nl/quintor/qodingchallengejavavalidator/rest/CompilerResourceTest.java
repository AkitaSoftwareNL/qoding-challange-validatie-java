package nl.quintor.qodingchallengejavavalidator.rest;

import nl.quintor.qodingchallengejavavalidator.dto.CodingQuestionDTO;
import nl.quintor.qodingchallengejavavalidator.dto.TestResultDTO;
import nl.quintor.qodingchallengejavavalidator.service.CompilerService;
import nl.quintor.qodingchallengejavavalidator.service.exception.CanNotCompileException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import static org.mockito.ArgumentMatchers.any;

class CompilerResourceTest {

    private CompilerResource sut;
    private CompilerService mockedCompilerService;

    @BeforeEach
    void setUp() {
        sut = new CompilerResource();
        mockedCompilerService = Mockito.mock(CompilerService.class);
        sut.setCompilerService(mockedCompilerService);
    }

    @Test
    void getTestResultReturnsOKStatus() throws CanNotCompileException {
        Mockito.when(mockedCompilerService.runTests(any())).thenReturn(new TestResultDTO());

        var expected = HttpStatus.OK;
        var actualResult = sut.getTestResult(new CodingQuestionDTO());

        Assertions.assertEquals(expected, actualResult.getStatusCode());
    }

    @Test
    void getTestResultThrowsCanNotCompileException() throws CanNotCompileException {
        Mockito.when(mockedCompilerService.runTests(any())).thenThrow(CanNotCompileException.class);

        Assertions.assertThrows(CanNotCompileException.class, () -> sut.getTestResult(new CodingQuestionDTO()));
    }

    @Test
    void getTestResultCallsCorrectMethod() throws CanNotCompileException {
        Mockito.when(mockedCompilerService.runTests(any())).thenReturn(new TestResultDTO());
        sut.getTestResult(new CodingQuestionDTO());
        Mockito.verify(mockedCompilerService).runTests(new CodingQuestionDTO());
    }

    @Test
    void canCompileCodeReturnsOKStatus() throws CanNotCompileException {
        Mockito.when(mockedCompilerService.canCompile(any())).thenReturn(true);

        var expected = HttpStatus.OK;
        var actualResult = sut.canCompileCode(new CodingQuestionDTO());

        Assertions.assertEquals(expected, actualResult.getStatusCode());

    }

    @Test
    void canCompileCodeThrowsCanNotCompileException() throws CanNotCompileException {
        Mockito.when(mockedCompilerService.canCompile(any())).thenThrow(CanNotCompileException.class);

        Assertions.assertThrows(CanNotCompileException.class, () -> sut.canCompileCode(new CodingQuestionDTO()));
    }

    @Test
    void canCompileCodeCallsCorrectMethod() throws CanNotCompileException {
        Mockito.when(mockedCompilerService.canCompile(any())).thenReturn(true);
        sut.canCompileCode(new CodingQuestionDTO());
        Mockito.verify(mockedCompilerService).canCompile(new CodingQuestionDTO());
    }


}