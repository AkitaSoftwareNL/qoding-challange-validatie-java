package nl.quintor.qodingchallengejavavalidator.service;

import nl.quintor.qodingchallengejavavalidator.dto.CodingQuestionDTO;
import nl.quintor.qodingchallengejavavalidator.service.compiler.Compiler;
import nl.quintor.qodingchallengejavavalidator.service.compiler.exception.RuntimeCompilerException;
import nl.quintor.qodingchallengejavavalidator.service.exception.CanNotCompileException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
    void canRuntTests() {

    }

    @Test
    void runTestsThrows() {
        Mockito.when(mockedCompiler.compile()).thenThrow(RuntimeCompilerException.class);

        Assertions.assertThrows(CanNotCompileException.class, () -> sut.runTests(new CodingQuestionDTO()));
    }

    @Test
    void canCompileCode() {

    }

    @Test
    void canCompileThrows() {
        Mockito.when(mockedCompiler.compile()).thenThrow(RuntimeCompilerException.class);

        Assertions.assertThrows(CanNotCompileException.class, () -> sut.canCompile(new CodingQuestionDTO()));
    }

    private String getFile(String path) throws IOException {
        String data = new String(Files.readAllBytes(Paths.get(path)));
        return data;
    }
}