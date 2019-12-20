package nl.quintor.qodingchallengejavavalidator.service;

import nl.quintor.qodingchallengejavavalidator.dto.CodingQuestionDTO;
import nl.quintor.qodingchallengejavavalidator.dto.TestResultDTO;
import nl.quintor.qodingchallengejavavalidator.service.compiler.Compiler;

public interface CompilerService {

    TestResultDTO runTests(CodingQuestionDTO codingQuestionDTO);

    boolean canCompile(CodingQuestionDTO codingQuestionDTO);

    void setCompiler(Compiler compiler);
}
