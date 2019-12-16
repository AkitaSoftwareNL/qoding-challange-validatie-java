package nl.quintor.qodingchallengejavavalidator.service;

import nl.quintor.qodingchallengejavavalidator.dto.CodingQuestionDTO;
import nl.quintor.qodingchallengejavavalidator.dto.TestResultDTO;
import nl.quintor.qodingchallengejavavalidator.service.compiler.Compiler;
import nl.quintor.qodingchallengejavavalidator.service.exception.CanNotCompileException;
import nl.quintor.qodingchallengejavavalidator.service.exception.ExecutionTimeoutException;

public interface CompilerService {

    TestResultDTO runTests(CodingQuestionDTO codingQuestionDTO) throws CanNotCompileException, ExecutionTimeoutException;

    boolean canCompile(CodingQuestionDTO codingQuestionDTO) throws CanNotCompileException;

    void setCompiler(Compiler compiler);
}
