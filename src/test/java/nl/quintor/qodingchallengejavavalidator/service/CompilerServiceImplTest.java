package nl.quintor.qodingchallengejavavalidator.service;

import nl.quintor.qodingchallengejavavalidator.dto.CodingQuestionDTO;
import nl.quintor.qodingchallengejavavalidator.dto.TestResultDTO;
import nl.quintor.qodingchallengejavavalidator.service.compiler.Compiler;
import nl.quintor.qodingchallengejavavalidator.service.compiler.RuntimeCompiler;
import nl.quintor.qodingchallengejavavalidator.service.exception.CanNotCompileException;
import nl.quintor.qodingchallengejavavalidator.service.exception.ExecutionTimeoutException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeoutException;

class CompilerServiceImplTest {

    private Compiler mockedCompiler;
    private CompilerService sut;

    @BeforeEach
    void setUp() {
        mockedCompiler = Mockito.mock(Compiler.class);
        sut = new CompilerServiceImpl();
        sut.setCompiler(mockedCompiler);
    }

    @Test
    void canRunTestsWithTestsCode() throws CanNotCompileException {
        sut.setCompiler(new RuntimeCompiler());
        for (int i = 1; i <= 3; i++) {
            CodingQuestionDTO codingQuestionDTO = getTestData(i);
            var expected = new TestResultDTO(2, 2, 0);
            var actual = sut.runTests(codingQuestionDTO);
            Assertions.assertEquals(expected, actual);
        }
    }

    @Test
    void runTestsThrowsCanNotCompileException() {
        Mockito.when(mockedCompiler.compile()).thenThrow(RuntimeException.class);
        Assertions.assertThrows(CanNotCompileException.class, () -> sut.runTests(new CodingQuestionDTO()));
    }

    @Test
    void runTestsThrowsExecutionTimeoutException() {
        Mockito.when(mockedCompiler.compile()).thenAnswer(invocationOnMock -> {
            throw new TimeoutException();
        });

        Assertions.assertThrows(ExecutionTimeoutException.class, () -> sut.runTests(new CodingQuestionDTO()));
    }

    @Test
    void runTestsThrowsExecutionTimeoutExceptionWithCompiledCode() {
        sut.setCompiler(new RuntimeCompiler());
        CodingQuestionDTO codingQuestionDTO = getTestData(4);
        Assertions.assertThrows(ExecutionTimeoutException.class, () -> sut.runTests(codingQuestionDTO));

    }

    @Test
    void canCompileCodeWithTestCode() throws CanNotCompileException {
        sut.setCompiler(new RuntimeCompiler());
        for (int i = 1; i <= 3; i++) {
            CodingQuestionDTO codingQuestionDTO = getTestData(i);
            var result = sut.canCompile(codingQuestionDTO);
            Assertions.assertTrue(result);
        }
    }

    @Test
    void canCompileThrowsCanNotCompileException() {
        Mockito.when(mockedCompiler.compile()).thenThrow(RuntimeException.class);
        Assertions.assertThrows(CanNotCompileException.class, () -> sut.canCompile(new CodingQuestionDTO()));
    }

    private CodingQuestionDTO getTestData(int id) {
        try {
            return new CodingQuestionDTO(10, getFile("src/test/resources/code" + id + ".java"), getFile("src/test/resources/testCode" + id + ".java"));
        } catch (IOException e) {
            throw new RuntimeException(String.format("NO FILE WITH ID %d", id));
        }
    }

    private String getFile(String path) throws IOException {
        String data = new String(Files.readAllBytes(Paths.get(path)));
        return data;
    }
}